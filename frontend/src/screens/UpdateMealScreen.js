import React, { useEffect, useState, useContext } from "react";
import axios from "../axios";
import { useParams } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import { Toast, ToastContainer } from "react-bootstrap";
import { AuthContext } from "../AuthContext";

const UpdateMealScreen = () => {
  const { id } = useParams();
  const [meal, setMeal] = useState({});
  const [showToast, setShowToast] = useState(false);
  const navigate = useNavigate();
  const userInfo = useContext(AuthContext).userInfo;

  const handleSubmit = async (event) => {
    event.preventDefault();
    const form = event.target;
    const nameEx = form.elements.name.value;
    const type = form.elements.type.value;
    const calories = form.elements.calories.value;
    const recipe = form.elements.recipe.value;
    console.log(nameEx, type, calories, recipe);

    try {
      const response = await axios.put("/api/meal", {
        id: id,
        personId: userInfo.userId,
        name: nameEx,
        type: type,
        calories: calories,
        date: "23-03-2023",
        notes: recipe,
      });
      console.log(response.status);
      if (response.status === 200) {
        setShowToast(true);
      }

      console.log(response.data);
    } catch (error) {
      console.error(error);
    }
  };

  const handleToastClose = () => {
    setShowToast(false);
    navigate(-1);
  };

  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setMeal((prevMeal) => ({
      ...prevMeal,
      [name]: value,
    }));
  };

  useEffect(() => {
    const fetchMeal = async () => {
      const { data } = await axios.get(`/api/meal/${id}`);

      setMeal(data);
    };

    fetchMeal();
  }, []);

  return (
    <div>
      <form onSubmit={handleSubmit}>
        <fieldset>
          <legend>Update Meal</legend>
          <div class="form-group">
            <label for="name" class="form-label mt-4">
              Name
            </label>
            <input
              type=""
              class="form-control"
              id="exampleInputEmail1"
              aria-describedby="nameHelp"
              placeholder="Enter name of meal"
              name="name"
              value={meal.mealName}
              onChange={handleInputChange}
            />
          </div>
          <div class="form-group">
            <label for="type" class="form-label mt-4">
              Type
            </label>
            <input
              type=""
              class="form-control"
              id="exampleInputEmail1"
              aria-describedby="typeHelp"
              placeholder="Breakfast, lunch, dinner"
              name="type"
              value={meal.type}
              onChange={handleInputChange}
            />
          </div>
          <div class="form-group">
            <label for="type" class="form-label mt-4">
              Calories
            </label>
            <input
              type=""
              class="form-control"
              id="exampleInputEmail1"
              aria-describedby="typeHelp"
              placeholder="Number of calories"
              name="calories"
              value={meal.calories}
              onChange={handleInputChange}
            />
          </div>
          <div class="form-group">
            <label for="type" class="form-label mt-4">
              Recipe
            </label>
            <input
              type=""
              class="form-control"
              id="exampleInputEmail1"
              aria-describedby="recipeHelp"
              placeholder="Recipe incredients/Notes"
              name="recipe"
              value={meal.notes}
              onChange={handleInputChange}
            />
          </div>
          <button type="submit" className="btn btn-primary my-3">
            Edit
          </button>
        </fieldset>
      </form>
      <ToastContainer position="middle-center">
        {showToast && (
          <Toast onClose={handleToastClose}>
            <Toast.Header>
              <strong className="me-auto">Success!</strong>
              <small>Just now</small>
            </Toast.Header>
            <Toast.Body>The Meal was edited successfully!</Toast.Body>
          </Toast>
        )}
      </ToastContainer>
    </div>
  );
};

export default UpdateMealScreen;
