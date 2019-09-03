import React from 'react';
import {
    Collapse,
    Navbar,
    NavbarToggler,
    NavbarBrand,
    Nav,
    NavItem,
    NavLink } from 'reactstrap';
import {Link} from 'react-router-dom';

export const AppHeader = (props) =>{

    // const [isOpen,toggle] = useState(false);
    return(
        <div>
        <Navbar light expand="md" className = "navbar-custom shadow">
          <NavbarBrand href="/"><img src = "/stoica-ionela-530967-unsplash.jpg" width = "90" height = "50" alt ="logo"/></NavbarBrand>
          <NavbarToggler />
          <Collapse navbar>
            <Nav className={`ml-5 mr-auto ${props.Authenticated?'d-block':'d-none'}`} navbar>
              <NavItem>
                <Link to={`dashboard`}>Dashboard</Link>
              </NavItem>
              <NavItem>
                <NavLink href="https://github.com/reactstrap/reactstrap">GitHub</NavLink>
              </NavItem>
             </Nav>
          </Collapse>
        </Navbar>
      </div>
    );
}