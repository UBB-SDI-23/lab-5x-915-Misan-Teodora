ALTER TABLE `person`
ADD COLUMN `username` VARCHAR(256) NOT NULL,
ADD COLUMN `password` VARCHAR(256) NOT NULL,
ADD CONSTRAINT `unique_username` UNIQUE (`username`);