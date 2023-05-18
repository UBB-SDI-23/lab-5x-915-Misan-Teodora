import { Container } from "react-bootstrap";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Header from "./components/Header";
import Footer from "./components/Footer";
import HomeScreen from "./screens/HomeScreen";
import MealDescription from "./screens/MealDescription";
import PersonalMealsScreen from "./screens/PersonalMealsScreen";
import MealPlanScreen from "./screens/MealPlanScreen";
import AddMealScreen from "./screens/AddMealScreen";
import UpdateMealScreen from "./screens/UpdateMealScreen";
import LoginScreen from "./screens/LoginScreen";
import SignupScreen from "./screens/SignupScreen";
import { useState, useEffect } from "react";
import { AuthProvider } from "./AuthProvider";

const App = () => {
  return (
    <AuthProvider>
      <Router>
        <Header />
        <main className="py-3">
          <Container>
            <Routes>
              <Route path="/" element={<HomeScreen />} exact />
              <Route path="/meal/:id/" element={<MealDescription />} />
              <Route path="/mealplan/" element={<MealPlanScreen />} />
              <Route path="/meals/" element={<PersonalMealsScreen />} />
              <Route path="/addmeal/" element={<AddMealScreen />} />
              <Route path="/updatemeal/:id" element={<UpdateMealScreen />} />
              <Route path="/login" element={<LoginScreen />} />
              <Route path="/register" element={<SignupScreen />} />
            </Routes>
          </Container>
        </main>
        <Footer />
      </Router>
    </AuthProvider>
  );
};

export default App;
