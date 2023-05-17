import React, { useState, useEffect } from "react";
import axios from "axios";
import { ListGroup, Button } from "react-bootstrap";
import Meal from "../components/Meal";
import { Link } from "react-router-dom";

const MealPlanScreen = () => {
  const [mealPlan, setMealPlan] = useState([]);
  const idUser = 5;

  useEffect(() => {
    const fetchMeals = async () => {
      const { data } = await axios.get(`/api/plan/person/${idUser}`);

      setMealPlan(data);
    };

    fetchMeals();
  }, []);

  return (
    <>
      <Link className="btn btn-secondary my-3" to="/">
        Go back
      </Link>
      <h1>My Meal Plan</h1>
      <div className="d-flex justify-content-center">
        <ListGroup className="w-100">
          {mealPlan.meals?.map((meal) => (
            <ListGroup.Item key={meal.id}>
              <Meal meal={meal} />
              <Button variant="dark">Delete Meal</Button>
            </ListGroup.Item>
          ))}
        </ListGroup>
      </div>
    </>
  );
};

export default MealPlanScreen;
