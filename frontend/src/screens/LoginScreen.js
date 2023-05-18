import React, { useContext, useState } from "react";
import { Link } from "react-router-dom";
import { Card, Container, Form, Button } from "react-bootstrap";
import axios from "../axios";
import { useNavigate } from "react-router-dom";
import { AuthContext } from "../AuthContext";

const LoginScreen = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();
  const { login } = useContext(AuthContext);

  const handleSubmit = async (event) => {
    event.preventDefault();
    console.log("Username:", username);
    console.log("Password:", password);

    try {
      var response = await axios.post("/api/auth/login", {
        username: username,
        password: password,
      });

      if (response.status === 200) {
        console.log("User logged in:", response.data);

        localStorage.setItem("token", response.data.access_token);
        login(response.data.access_token);
        navigate("/");
      }
    } catch (error) {
      console.error("Error logging in:", error);
    }
  };

  return (
    <Container className="d-flex justify-content-center align-items-center vh-100">
      <Card className="w-50">
        <Card.Body>
          <h2>Login</h2>
          <Form onSubmit={handleSubmit}>
            <Form.Group controlId="formUsername">
              <Form.Label>Username</Form.Label>
              <Form.Control
                type="text"
                placeholder="Enter username"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
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
            Don't have an account? <Link to="/register">Sign up</Link>
          </div>
        </Card.Body>
      </Card>
    </Container>
  );
};

export default LoginScreen;
