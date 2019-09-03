package com.agencyservice.agencyservice.controller;

import com.agencyservice.agencyservice.enums.ROLE;
import com.agencyservice.agencyservice.payload.LoginRequest;
import com.agencyservice.agencyservice.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.server.authentication.logout.RedirectServerLogoutSuccessHandler;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;
  /*  @PostMapping("/login")
    public Mono<ResponseEntity<Map<String, Set<ROLE>>>> performLogin(@RequestBody LoginRequest loginRequest){
        return Mono.just(loginService.performLogin(loginRequest)).map(roles ->Collections.singletonMap("roles", roles))
                .map(col -> {
                    ResponseEntity<Map<String, Set<ROLE>>> responseEntity = new ResponseEntity<>(col, HttpStatus.FOUND);
                    responseEntity.
                    return responseEntity;
                });
    }*/

  @GetMapping("/login")
  public String login(Model model, String error, String logout) {
      if (error != null)
          model.addAttribute("error", "Your username and password is invalid.");

      if (logout != null)
          model.addAttribute("message", "You have been logged out successfully.");

      return "login";
  }
    @GetMapping("/hello")
    public Mono<String> hello(){
        return Mono.just("Hello There!!!!");
    }

    public ServerLogoutSuccessHandler logoutSuccessHandler(String uri) {
        RedirectServerLogoutSuccessHandler successHandler = new RedirectServerLogoutSuccessHandler();
        successHandler.setLogoutSuccessUrl(URI.create(uri));
        return successHandler;
    }
}
