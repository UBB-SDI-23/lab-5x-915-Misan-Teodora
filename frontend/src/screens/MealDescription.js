import React, { useState, useEffect } from "react";
import axios from "../axios";
import { Link, useParams } from "react-router-dom";
import { Toast, ToastContainer } from "react-bootstrap";

const MealDescription = () => {
  const [mealsOBJ, setMealsOBJ] = useState({});
  const [showToast, setShowToast] = useState(false);

  const { id } = useParams();
  const idUser = 5;

  useEffect(() => {
    const fetchMeals = async () => {
      const { data } = await axios.get(`/api/meal/${id}`);
      console.log(data);
      setMealsOBJ(data);
    };

    fetchMeals();
  }, []);

  const handleAddToMealPlan = async () => {
    // Make the POST request to your server
    try {
      const response = await axios.post("/api/plan", {
        person_id: idUser,
        meal_id: id,
        name: "dinner",
      });
      // Handle the response or perform any additional actions
      console.log(response.data);
      console.log(response.status);
      if (response.status === 201) {
        setShowToast(true);
      }
    } catch (error) {
      // Handle the error
      console.error(error);
    }
  };

  const handleToastClose = () => {
    setShowToast(false);
  };

  return (
    <>
      <Link className="btn btn-secondary my-3" to="/">
        Go back
      </Link>

      <h3 className="p-3">
        {mealsOBJ.mealName}: {mealsOBJ.calories} calories
      </h3>
      <hr className="card-separator rounded" />
      <h4 className="p-2">{mealsOBJ.type} meal.</h4>
      <h5 className="p-2">Ingredients:</h5>
      <div className="p-2">{mealsOBJ.notes}</div>

      <button
        type="button"
        className="btn btn-primary my-4"
        onClick={handleAddToMealPlan}
      >
        ADD TO MEAL PLAN
      </button>
      <ToastContainer position="middle-center">
        {showToast && (
          <Toast onClose={handleToastClose}>
            <Toast.Header>
              <strong className="me-auto">Success!</strong>
              <small>Just now</small>
            </Toast.Header>
            <Toast.Body>
              The Meal was added successfully to your Meal Plan!
            </Toast.Body>
          </Toast>
        )}
      </ToastContainer>
    </>
  );
};

export default MealDescription;

// "mealID": 2,
//     "mealName": "Test",
//     "type": "dinner",
//     "calories": 1500,
//     "notes": "Test",
//     "person": {
//         "id": 2,
//         "name": "Misan Teodora",
//         "age": 21,
//         "kg": 55,
//         "height": 172
//     }
// }
