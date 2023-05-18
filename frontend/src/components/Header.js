import React, { useContext } from "react";
import { Navbar, Nav, Container } from "react-bootstrap";
import { LinkContainer } from "react-router-bootstrap";
import { AuthContext } from "../AuthContext";

const Header = () => {
  const { isAuthenticated, logout } = useContext(AuthContext);

  return (
    <header>
      <Navbar bg="primary" variant="dark" expand="lg" collapseOnSelect>
        <Container>
          <LinkContainer to="/">
            <Navbar.Brand>Nutrition</Navbar.Brand>
          </LinkContainer>
          <Navbar.Toggle aria-controls="basic-navbar-nav" />
          <Navbar.Collapse id="basic-navbar-nav">
            <Nav className="ms-auto">
              {isAuthenticated ? (
                <LinkContainer to="/mealplan">
                  <Nav.Link>MealPlan</Nav.Link>
                </LinkContainer>
              ) : null}
              {isAuthenticated ? (
                <LinkContainer to="/meals">
                  <Nav.Link>MyMeals</Nav.Link>
                </LinkContainer>
              ) : null}
              {isAuthenticated ? (
                <LinkContainer to="/" onClick={logout}>
                  <Nav.Link>Logout</Nav.Link>
                </LinkContainer>
              ) : (
                <LinkContainer to="/login">
                  <Nav.Link>Sign In</Nav.Link>
                </LinkContainer>
              )}
            </Nav>
          </Navbar.Collapse>
        </Container>
      </Navbar>
    </header>
  );
};

export default Header;
