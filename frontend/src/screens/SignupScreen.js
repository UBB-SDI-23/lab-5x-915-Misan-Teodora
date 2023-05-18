import React, { useState } from "react";
import { Link } from "react-router-dom";
import { Container, Card, Form, Button, Alert } from "react-bootstrap";

const SignupScreen = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [error, setError] = useState("");

  // Handle form submission
  const handleSubmit = (event) => {
    event.preventDefault();

    if (password !== confirmPassword) {
      setError("Passwords do not match");
      return;
    }

    // Perform sign-up logic here with 'email' and 'password' values
    console.log("Email:", email);
    console.log("Password:", password);
  };

  return (
    <Container className="d-flex justify-content-center align-items-center vh-100">
      <Card className="w-50">
        <Card.Body>
          <h2>Sign Up</h2>

          {error && <Alert variant="danger">{error}</Alert>}

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

            <Form.Group controlId="formConfirmPassword">
              <Form.Label>Confirm Password</Form.Label>
              <Form.Control
                type="password"
                placeholder="Confirm Password"
                value={confirmPassword}
                onChange={(e) => setConfirmPassword(e.target.value)}
              />
            </Form.Group>

            <Button className="my-2" variant="primary" type="submit">
              Sign Up
            </Button>
          </Form>

          <div className="mt-3">
            Already have an account? <Link to="/login">Sign in</Link>
          </div>
        </Card.Body>
      </Card>
    </Container>
  );
};

export default SignupScreen;
