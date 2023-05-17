import React from "react";
import { Card } from "react-bootstrap";
import { Link } from "react-router-dom";

const Person = ({ person }) => {
  return (
    <Card className="my-3 p-3 rounded">
      <Card.Body>
        <Link to={`/person/${person.id}`}>
          <Card.Title as="h4">
            <strong>{person.name}</strong>
          </Card.Title>
        </Link>
        <Card.Text as="h6">age: {person.age}</Card.Text>
        <Card.Text as="h6">kg: {person.kg}</Card.Text>
        <Card.Text as="h6">height: {person.height}</Card.Text>
      </Card.Body>
    </Card>
  );
};

export default Person;
