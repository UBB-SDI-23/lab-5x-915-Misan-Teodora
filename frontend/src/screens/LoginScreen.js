import React, { useState } from "react";
import { Link } from "react-router-dom";
import { Card, Container, Form, Button } from "react-bootstrap";

const LoginScreen = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleSubmit = (event) => {
    event.preventDefault();
    console.log("Email:", email);
    console.log("Password:", password);
    // Perform login logic here
  };

  return (
    <Container className="d-flex justify-content-center align-items-center vh-100">
      <Card className="w-50">
        <Card.Body>
          <h2>Login</h2>
          <Form onSubmit={handleSubmit}>
            <Form.Group controlId="formEmail">
              <Form.Label>Email address</Form.Label>
              <Form.Control
                type="email"
                placeholder="Enter email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
              />
            </Form.Group>

            <Form.Group controlId="formPassword">
              <Form.Label>Password</Form.Label>
              <Form.Control
                type="password"
                placeholder="Password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
              />
            </Form.Group>

            <Button variant="primary" type="submit" className="my-2">
              Sign In
            </Button>
          </Form>

          <div className="mt-3">
            Don't have an account? <Link to="/signup">Sign up</Link>
          </div>
        </Card.Body>
      </Card>
    </Container>
  );
};

export default LoginScreen;
