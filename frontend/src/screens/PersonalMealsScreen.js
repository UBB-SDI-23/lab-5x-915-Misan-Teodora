import React, { useState, useEffect, useContext } from "react";
import { ListGroup } from "react-bootstrap";
import { Link } from "react-router-dom";
import Meal from "../components/Meal";
import axios from "../axios";
import {
  Row,
  Col,
  Button,
  ToastContainer,
  Toast,
  ButtonGroup,
  FormControl,
} from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import { AuthContext } from "../AuthContext";

const PersonalMealsScreen = () => {
  const [mealsOBJ, setMealsOBJ] = useState([]);
  const [nameUser, setNameUser] = useState("");
  const [showToast, setShowToast] = useState(false);
  const [messageToast, setMessageToast] = useState("");
  const [page, setPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const size = 3;
  const navigate = useNavigate();
  const userInfo = useContext(AuthContext).userInfo;

  useEffect(() => {
    console.log(userInfo);

    const fetchMeals = async () => {
      const { data } = await axios.get(
        `/api/person/${userInfo.userId}?page=${page}&size=${size}`
      );

      setMealsOBJ(data.meals.content);
      setNameUser(data.name);
      setTotalPages(data.meals.totalPages);
    };

    fetchMeals();
  }, [page, size]);

  // if (!mealsOBJ.meals.content) {
  //   return <p>Loading</p>;
  // }

  const handleToastClose = () => {
    setShowToast(false);
    window.location.reload();
  };

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

  const handleDeleteMeal = async (mealId) => {
    try {
      const result = await axios.delete(`/api/meal/${mealId}`);

      console.log(result.status);
      if (result.status === 204) {
        setMessageToast("The Meal was deleted successfully!");
        setShowToast(true);
      }
    } catch (error) {
      setMessageToast("The Meal could not be deleted!");
      setShowToast(true);
      console.error(error);
    }
  };

  return (
    <>
      <Link className="btn btn-secondary my-3" to="/">
        Go back
      </Link>
      <Row>
        {mealsOBJ?.length !== 0 ? (
          <Col md={9}>
            <h1>{nameUser}'s meals</h1>
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
        {mealsOBJ?.map((meal, index) => (
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
      <ToastContainer position="middle-center">
        {showToast && (
          <Toast onClose={handleToastClose}>
            <Toast.Header>
              <strong className="me-auto">Message</strong>
              <small>Just now</small>
            </Toast.Header>
            <Toast.Body>{messageToast}</Toast.Body>
          </Toast>
        )}
      </ToastContainer>
    </>
  );
};

export default PersonalMealsScreen;
