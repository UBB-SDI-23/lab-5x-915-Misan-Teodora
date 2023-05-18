-- Create Persons
DELIMITER $$
CREATE PROCEDURE reset_tables()
BEGIN
  SET FOREIGN_KEY_CHECKS = 0;

  TRUNCATE TABLE meal_plan;
  TRUNCATE TABLE personal_data;
  TRUNCATE TABLE meal;
  TRUNCATE TABLE person;
  
  SET FOREIGN_KEY_CHECKS = 1;
END$$
DELIMITER ;

-- Call the procedure to reset all tables
CALL reset_tables();

DELIMITER $$
CREATE PROCEDURE generate_persons(IN person_count INT, IN start_value INT)
BEGIN
  DECLARE i INT DEFAULT start_value;
  WHILE i < start_value + person_count DO
    INSERT INTO person (name, username, password, age, kg, height) 
    VALUES (CONCAT('Name-', i), CONCAT('Username-', i), '$2a$10$f2oac28mnKPrmm4Z0nhPkeTLg2jjC2hH356rlrl5nd9tlPJSqkryy', FLOOR(20 + RAND() * 50), FLOOR(50 + RAND() * 50), FLOOR(150 + RAND() * 50));
    SET i = i + 1;
  END WHILE;
END$$
DELIMITER ;

-- Create Meals
DELIMITER $$
CREATE PROCEDURE generate_meals(IN meal_count INT, IN start_value INT)
BEGIN
  DECLARE i INT DEFAULT start_value;
  WHILE i < start_value + meal_count DO
    INSERT INTO meal (person_id, name, type, calories, date, notes) 
    VALUES (i+1, CONCAT('MealName-', i), 'Dinner', FLOOR(500 + RAND() * 500), DATE_FORMAT(DATE_ADD(CURDATE(), INTERVAL -i DAY), '%d-%m-%Y'), CONCAT('Notes-', i));
    SET i = i + 1;
  END WHILE;
END$$
DELIMITER ;

-- Create Meal Plans
DELIMITER $$
CREATE PROCEDURE generate_meal_plans(IN meal_plan_count INT, IN start_value INT)
BEGIN
  DECLARE i INT DEFAULT start_value;
  WHILE i < start_value + meal_plan_count DO
    INSERT INTO meal_plan (person_id, meal_id, name) 
    VALUES (i+1, i+1, CONCAT('MealPlan-', i));
    SET i = i + 1;
  END WHILE;
END$$
DELIMITER ;

-- Create Personal Data
DELIMITER $$
CREATE PROCEDURE generate_personal_data(IN personal_data_count INT, IN start_value INT)
BEGIN
  DECLARE i INT DEFAULT start_value;
  DECLARE countries JSON DEFAULT '["USA", "Romania", "Belgium", "France", "UK"]';
  DECLARE cities JSON DEFAULT '["New York", "Los Angeles", "Chicago", "Bucharest", "Cluj-Napoca", "Iasi", "Brussels", "Antwerp", "Ghent", "Paris", "Marseille", "Lyon", "London", "Birmingham", "Leeds"]';
  DECLARE country_index INT;
  DECLARE city_index INT;
  WHILE i < start_value + personal_data_count DO
    SET country_index = FLOOR(0 + RAND() * 5);
    SET city_index = country_index * 3 + FLOOR(0 + RAND() * 3);
    INSERT INTO personal_data (person_id, email, country, city) 
    VALUES (i+1, CONCAT('email-', i, '@example.com'), JSON_EXTRACT(countries, CONCAT('$[', country_index, ']')), JSON_EXTRACT(cities, CONCAT('$[', city_index, ']')));
    SET i = i + 1;
  END WHILE;
END$$
DELIMITER ;

CALL generate_persons(10000, 0);
CALL generate_meals(10000, 0);
CALL generate_meal_plans(10000, 0);
CALL generate_personal_data(10000, 0);