CREATE TABLE IF NOT EXISTS `meal_plan` (
    `id` INTEGER NOT NULL AUTO_INCREMENT,
    `person_id` INTEGER NOT NULL,
    `meal_id` INTEGER NOT NULL,
    `time_of_creation` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `name` VARCHAR(256) NOT NULL,
    PRIMARY KEY(id),
    CONSTRAINT fk_person_id
    FOREIGN KEY (person_id)
    REFERENCES person(id)
    ON DELETE CASCADE,
    CONSTRAINT fk_meal_id
    FOREIGN KEY (meal_id)
    REFERENCES meal(id)
    ON DELETE CASCADE
);