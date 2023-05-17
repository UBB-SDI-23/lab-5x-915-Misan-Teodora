import React, { useState, useEffect } from "react";
import { ListGroup } from "react-bootstrap";
import { Link } from "react-router-dom";
import Meal from "../components/Meal";
import axios from "../axios";
import { Row, Col, Button, ToastContainer, Toast } from "react-bootstrap";
import { useNavigate } from "react-router-dom";

const PersonalMealsScreen = () => {
  const [mealsOBJ, setMealsOBJ] = useState({});
  const [showToast, setShowToast] = useState(false);
  const navigate = useNavigate();
  const idUser = 5;

  useEffect(() => {
    const fetchMeals = async () => {
      const { data } = await axios.get(`/api/person/${idUser}`);

      setMealsOBJ(data);
    };

    fetchMeals();
  }, []);

  const handleToastClose = () => {
    setShowToast(false);
    window.location.reload();
  };

  const handleDeleteMeal = async (mealId) => {
    try {
      const result = await axios.delete(`/api/meal/${mealId}`);

      console.log(result.status);
      if (result.status === 204) {
        setShowToast(true);
      }
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <>
      <Link className="btn btn-secondary my-3" to="/">
        Go back
      </Link>
      <Row>
        {mealsOBJ.meals?.length !== 0 ? (
          <Col md={9}>
            <h1>{mealsOBJ.name}'s meals</h1>
          </Col>
        ) : (
          <Col md={9}>
            <h1>There is no meal added by {mealsOBJ.name}</h1>
          </Col>
        )}
        <Col md={3}>
          <Link className="btn btn-primary my-3" to="/addmeal">
            ADD NEW MEAL
          </Link>
        </Col>
      </Row>
      <ListGroup className="center">
        {mealsOBJ.meals?.map((meal, index) => (
          <ListGroup.Item key={meal.id}>
            <Meal meal={meal} />
            <Button variant="dark" onClick={() => handleDeleteMeal(meal.id)}>
              Delete Meal
            </Button>
            <Button
              variant="dark"
              className="mx-3"
              onClick={() => navigate(`/updatemeal/${meal.id}`)}
            >
              Update Meal
            </Button>
          </ListGroup.Item>
        ))}
      </ListGroup>
      <ToastContainer position="middle-center">
        {showToast && (
          <Toast onClose={handleToastClose}>
            <Toast.Header>
              <strong className="me-auto">Success!</strong>
              <small>Just now</small>
            </Toast.Header>
            <Toast.Body>The Meal was deleted successfully!</Toast.Body>
          </Toast>
        )}
      </ToastContainer>
    </>
  );
};

export default PersonalMealsScreen;
