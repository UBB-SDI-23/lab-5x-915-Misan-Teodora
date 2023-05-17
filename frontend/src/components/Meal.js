import React from "react";
import { Card } from "react-bootstrap";
import { Link } from "react-router-dom";

const Meal = ({ meal }) => {
  return (
    <Card className="my-3 rounded border-0">
      <Card.Body>
        <Card.Title as="h4">
          <strong>{meal.name}</strong>
        </Card.Title>
        <Card.Text as="h6">type: {meal.type}</Card.Text>
        <Card.Text as="h6">calories: {meal.calories}</Card.Text>
        <Card.Text as="h6">date: {meal.date}</Card.Text>
        <Card.Text as="h6">notes: {meal.notes}</Card.Text>
      </Card.Body>
    </Card>
  );
};

export default Meal;
