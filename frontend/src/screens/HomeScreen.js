import React, { useState, useEffect } from "react";
import Meal from "../components/Meal";
import axios from "../axios";
import { ListGroup } from "react-bootstrap";
import { Link } from "react-router-dom";

const HomeScreen = () => {
  const [meals, setMeals] = useState([]);

  useEffect(() => {
    const fetchMeals = async () => {
      const { data } = await axios.get("/api/meal");

      setMeals(data);
    };

    fetchMeals();
  }, []);

  return (
    <>
      <h1>Meals</h1>
      <div className="d-flex justify-content-center">
        <ListGroup className="w-100">
          {meals.map((meal) => (
            <ListGroup.Item key={meal.id}>
              <Link to={`/meal/${meal.id}`} className="card-link">
                <Meal meal={meal} />
              </Link>
            </ListGroup.Item>
          ))}
        </ListGroup>
      </div>
    </>
  );
};

export default HomeScreen;
