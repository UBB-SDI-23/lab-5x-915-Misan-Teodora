import React, { useState, useEffect, useContext } from "react";
import Meal from "../components/Meal";
import axios from "../axios";
import { ListGroup } from "react-bootstrap";
import { Link } from "react-router-dom";
import { Button, ButtonGroup, FormControl } from "react-bootstrap";
import { AuthContext } from "../AuthContext";
import { useNavigate } from "react-router-dom";

const HomeScreen = () => {
  const [meals, setMeals] = useState([]);
  const [page, setPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const navigate = useNavigate();
  const userInfo = useContext(AuthContext).userInfo;
  const size = 5;

  useEffect(() => {
    const fetchMeals = async () => {
      console.log(userInfo);
      const { data } = await axios.get(`/api/meal?page=${page}&size=${size}`);

      setMeals(data.content);
      setTotalPages(data.totalPages);
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

  const editmeal = () => {};

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
              {userInfo !== null && userInfo.role === "ROLE_MODERATOR" ? (
                <Button onClick={() => navigate(`/updatemeal/${meal.id}`)}>
                  Edit
                </Button>
              ) : null}
            </ListGroup.Item>
          ))}
        </ListGroup>
      </div>
      <div className="d-flex justify-content-center my-3">
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

export default HomeScreen;
