import React, { useState } from "react";
import axios from "../axios";
import { ToastContainer, Toast } from "react-bootstrap";
import { useNavigate } from "react-router-dom";

const AddMealScreen = () => {
  const [showToast, setShowToast] = useState(false);
  const navigate = useNavigate();

  const handleSubmit = async (event) => {
    event.preventDefault();
    const form = event.target;
    const nameEx = form.elements.name.value;
    const type = form.elements.type.value;
    const calories = form.elements.calories.value;
    const recipe = form.elements.recipe.value;
    console.log(nameEx, type, calories, recipe);
    const idUser = 30;

    try {
      const response = await axios.post("/api/meal", {
        name: nameEx,
        personId: idUser,
        type: type,
        calories: calories,
        date: "23-03-2023",
        notes: recipe,
      });
      console.log(response.status);
      if (response.status === 201) {
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

  return (
    <div>
      <form onSubmit={handleSubmit}>
        <fieldset>
          <legend>Add Meal</legend>
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
            />
          </div>
          <button type="submit" className="btn btn-primary my-3">
            Add
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
            <Toast.Body>The Meal was created successfully!</Toast.Body>
          </Toast>
        )}
      </ToastContainer>
    </div>
  );
};

export default AddMealScreen;

//      "name": "Salata Caesar",
//     "personId": 3,
//     "type": "breakfast",
//     "calories": 1360,
//     "date": "23-03-2023",
//     "notes": "2 bucati pui, 2-3 fileuri de ansoa in ulei, 4-5 fire de ceapa, sare, piper"
