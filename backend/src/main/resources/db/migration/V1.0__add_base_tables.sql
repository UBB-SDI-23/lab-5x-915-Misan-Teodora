CREATE TABLE IF NOT EXISTS `person` (
    `id` INTEGER NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(256) NOT NULL,
    `age` INTEGER NOT NULL,
    `kg` INTEGER NOT NULL,
    `height` INTEGER NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS `meal` (
    `id` INTEGER NOT NULL AUTO_INCREMENT,
    `person_id` INTEGER NOT NULL,
    `name` VARCHAR(256) NOT NULL,
    `type` VARCHAR(20) NOT NULL,
    `calories` INTEGER NOT NULL,
    `date` VARCHAR(10) NOT NULL,
    `notes` VARCHAR(256) NOT NULL,
    PRIMARY KEY(id),
    CONSTRAINT fk_meal_person_id
    FOREIGN KEY (person_id)
    REFERENCES person(id)
    ON DELETE CASCADE
);
