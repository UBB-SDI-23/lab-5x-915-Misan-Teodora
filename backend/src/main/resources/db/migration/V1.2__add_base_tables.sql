CREATE TABLE IF NOT EXISTS `personal_data` (
    `id` INTEGER NOT NULL AUTO_INCREMENT,
    `person_id` INTEGER NOT NULL,
    `email` VARCHAR(50) NOT NULL,
    `country` VARCHAR(20) NOT NULL,
    `city` VARCHAR(20) NOT NULL,
    PRIMARY KEY(id)
);