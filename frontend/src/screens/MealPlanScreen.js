import React, { useState, useEffect, useContext } from "react";
import axios from "../axios";
import { ListGroup, Button, ButtonGroup, FormControl } from "react-bootstrap";
import Meal from "../components/Meal";
import { Link } from "react-router-dom";
import { AuthContext } from "../AuthContext";

const MealPlanScreen = () => {
  const [mealPlan, setMealPlan] = useState([]);
  const [page, setPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const size = 3;
  const userInfo = useContext(AuthContext).userInfo;

  useEffect(() => {
    console.log(userInfo);
    const fetchMeals = async () => {
      const { data } = await axios.get(
        `/api/plan/person/${userInfo.userId}?page=${page}&size=${size}`
      );

      setMealPlan(data.meals.content);
      setTotalPages(data.meals.totalPages);
    };

    fetchMeals();
  }, [page, size]);

  const handleNextPage = () => {
    if (page < totalPages - 1) {
      setPage((prevPage) => prevPage + 1);
    }
  };

  const handlePreviousPage = () => {
    if (page > 0) {
      setPage((prevPage) => prevPage - 1);
    }
  };

  const handlePageChange = (event) => {
    const newPage = Number(event.target.value);
    if (newPage >= 0 && newPage < totalPages) {
      setPage(newPage);
    }
  };

  return (
    <>
      <Link className="btn btn-secondary my-3" to="/">
        Go back
      </Link>
      <h1>My Meal Plan</h1>
      <div className="d-flex justify-content-center">
        <ListGroup className="w-100">
          {mealPlan?.map((meal) => (
            <ListGroup.Item key={meal.id}>
              <Meal meal={meal} />
              <Button variant="dark">Delete Meal</Button>
            </ListGroup.Item>
          ))}
        </ListGroup>
      </div>
      <div className="d-flex justify-content-center">
        <ButtonGroup>
          <Button onClick={handlePreviousPage} disabled={page === 0}>
            Previous
          </Button>
          <FormControl
            type="number"
            value={page}
            onChange={handlePageChange}
            style={{ width: "100px" }}
          />
          <Button onClick={handleNextPage} disabled={page === totalPages - 1}>
            Next
          </Button>
        </ButtonGroup>
      </div>
    </>
  );
};

export default MealPlanScreen;
