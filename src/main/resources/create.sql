-- Add create scripts for DB here.
CREATE TABLE `securities`.`symbol` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `symbol` VARCHAR(45) NULL,
  `name` VARCHAR(255) NULL,
  `type` INT(3) NULL,
  `exchange` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `securities`.`eod` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `symbol_id` INT NOT NULL,
  `date` DATE NOT NULL,
  `open` DECIMAL(11) NULL,
  `high` DECIMAL(11) NULL,
  `low` DECIMAL(11) NULL,
  `close` DECIMAL(11) NULL,
  `volume` INT NULL,
  `open_interest` INT NULL,
  `last_update` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `symbol_id_idx` (`symbol_id` ASC),
  CONSTRAINT `id`
    FOREIGN KEY (`symbol_id`)
    REFERENCES `securities`.`symbol` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
