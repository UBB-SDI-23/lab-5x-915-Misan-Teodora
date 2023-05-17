import React, { useState, useEffect } from "react";
import Person from "../components/Person";
import axios from "axios";
import ListGroup from "react-bootstrap/ListGroup";

const PersonScreen = () => {
  const [persons, setPersons] = useState([]);

  useEffect(() => {
    const fetchPersons = async () => {
      const { data } = await axios.get("/api/person");

      setPersons(data);
    };

    fetchPersons();
  }, []);

  return (
    <>
      <h1>Persons</h1>
      <div className="d-flex justify-content-center">
        <ListGroup className="center">
          {persons.map((pers) => (
            <ListGroup.Item key={pers.id} className="card-custom-width">
              <Person person={pers} />
            </ListGroup.Item>
          ))}
        </ListGroup>
      </div>
    </>
  );
};

export default PersonScreen;
