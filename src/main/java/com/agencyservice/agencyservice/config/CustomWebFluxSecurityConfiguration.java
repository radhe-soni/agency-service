package com.agencyservice.agencyservice.config;

import com.agencyservice.agencyservice.reactive.security.auth.basic.BasicAuthenticationSuccessHandler;
import com.agencyservice.agencyservice.reactive.security.auth.bearer.BearerTokenReactiveAuthenticationManager;
import com.agencyservice.agencyservice.security.auth.jwt.AuthorizationHeaderPayload;
import com.agencyservice.agencyservice.security.auth.jwt.JWTCustomVerifier;
import com.agencyservice.agencyservice.security.auth.jwt.UsernamePasswordAuthenticationBearer;
import com.agencyservice.agencyservice.service.AdminService;
import com.agencyservice.agencyservice.service.CustomUserDetailsService;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.security.web.server.csrf.ServerCsrfTokenRepository;
import org.springframework.security.web.server.csrf.WebSessionServerCsrfTokenRepository;
import org.springframework.security.web.server.util.matcher.NegatedServerWebExchangeMatcher;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatcher;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

@Configuration
@EnableWebFluxSecurity
public class CustomWebFluxSecurityConfiguration {
    private static final String[] publicUrls = new String[]{
            "/public/**",
            "/login",
            "/logout",
            "/sample/**",
            "/register/**",
            "/hello"
    };
    private static final String BEARER = "Bearer ";
    private static final Predicate<String> matchBearerLength = authValue -> authValue.length() > BEARER.length();
    private static final Function<String, Mono<String>> isolateBearerValue = authValue -> Mono.justOrEmpty(authValue.substring(BEARER.length()));
    private static String DEFAULT_SECRET = "The_secret_length_must_be_at_least_256_bits";
    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private BasicAuthenticationSuccessHandler successHandler;

    @Bean
    @DependsOn({"adminService"})
    public MapReactiveUserDetailsService userDetailsRepository() {
        adminService.createAdminUsers();
        List<UserDetails> users = userDetailsService.loadAllUsers();
        return new MapReactiveUserDetailsService(users);
    }

    @Bean
    public ServerWebExchangeMatcher serverWebExchangeMatcher() {
        ServerWebExchangeMatcher serverWebExchangeMatcher = ServerWebExchangeMatchers.pathMatchers(publicUrls);
        NegatedServerWebExchangeMatcher negatedMatcher = new NegatedServerWebExchangeMatcher(serverWebExchangeMatcher);
        return negatedMatcher;
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http, ServerWebExchangeMatcher serverWebExchangeMatcher,
                                                            JWTCustomVerifier jwtCustomVerifier, UserDetailsRepositoryReactiveAuthenticationManager authManager) {
        http.csrf().csrfTokenRepository(csrfTokenRepository()).requireCsrfProtectionMatcher(serverWebExchangeMatcher).and()
                .authorizeExchange().pathMatchers(publicUrls).permitAll()
                /*.anyExchange()
                .authenticated()
                .and()
                .addFilterAt(basicAuthenticationFilter(authManager), SecurityWebFiltersOrder.HTTP_BASIC)
                */
                .and().authorizeExchange().anyExchange()
                //.pathMatchers("/authenticated/**")
                .authenticated()
                .and()
                .addFilterAt(bearerAuthenticationFilter(jwtCustomVerifier), SecurityWebFiltersOrder.AUTHENTICATION);
        return http.build();
    }

    @Bean
    public ServerCsrfTokenRepository csrfTokenRepository() {
        WebSessionServerCsrfTokenRepository repository = new WebSessionServerCsrfTokenRepository();
        repository.setHeaderName("X-CSRF-TK");
        return repository;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return encoder;
    }

    @Bean
    public MACVerifier macVerifier() throws JOSEException {
        return new MACVerifier(DEFAULT_SECRET);

    }

    @Bean
    public JWSSigner jwsSigner() throws KeyLengthException {
        return new MACSigner(DEFAULT_SECRET);
    }

    private AuthenticationWebFilter bearerAuthenticationFilter(JWTCustomVerifier jwtCustomVerifier) {
        AuthenticationWebFilter bearerAuthenticationFilter;
        ReactiveAuthenticationManager authManager;

        authManager = new BearerTokenReactiveAuthenticationManager();
        bearerAuthenticationFilter = new AuthenticationWebFilter(authManager);

        bearerAuthenticationFilter.setServerAuthenticationConverter(serverAuthenticationConverter(jwtCustomVerifier));
        bearerAuthenticationFilter.setRequiresAuthenticationMatcher(ServerWebExchangeMatchers.pathMatchers("/autheticated/**"));

        return bearerAuthenticationFilter;
    }

    private AuthenticationWebFilter basicAuthenticationFilter(UserDetailsRepositoryReactiveAuthenticationManager authManager) {
        AuthenticationWebFilter basicAuthenticationFilter = new AuthenticationWebFilter(authManager);
        basicAuthenticationFilter.setAuthenticationSuccessHandler(successHandler);

        return basicAuthenticationFilter;

    }

    @Bean
    public UserDetailsRepositoryReactiveAuthenticationManager authenticationManager(ReactiveUserDetailsService userDetailsRepository)
    {
        return new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsRepository);
    }
    private ServerAuthenticationConverter serverAuthenticationConverter(JWTCustomVerifier jwtCustomVerifier) {

        return (serverWebExchange) -> Mono.justOrEmpty(serverWebExchange)
                .flatMap(AuthorizationHeaderPayload::extract)
                .filter(matchBearerLength)
                .flatMap(isolateBearerValue)
                .flatMap(jwtCustomVerifier::check)
                .flatMap(UsernamePasswordAuthenticationBearer::create).log();

    }
}