CREATE TABLE `departamento` (
`nombre` VARCHAR( 20 ) NOT NULL ,
`numDepto` INT( 2 ) NOT NULL ,
PRIMARY KEY ( `numDepto` )
) ;

INSERT INTO `departamento` (`nombre` ,`numDepto`)
VALUES ('Informatica', '01');

INSERT INTO `departamento` (`nombre` ,`numDepto`)
VALUES ('Administracion', '02');


CREATE TABLE `persona` (
`nombrePersona` VARCHAR( 50 ) NOT NULL ,
`identificador` INT( 4 ) NOT NULL ,
`numDepartamento` INT( 2 ) NOT NULL ,
PRIMARY KEY ( `nombrePersona` ),
FOREIGN KEY (numDepartamento) REFERENCES departamento(numDepto)
) ;

INSERT INTO `persona` (`nombrePersona` ,`identificador` ,`numDepartamento`)
VALUES ('Juan Perez Rodriguez', '1', '02');

INSERT INTO `persona` (`nombrePersona` ,`identificador` ,`numDepartamento`)
VALUES ('Jose Maria Jimenez Perez', '2', '01');

INSERT INTO `persona` (`nombrePersona` ,`identificador` ,`numDepartamento`)
VALUES ('Pedro Marin Garcia', '3', '01');

INSERT INTO `persona` (`nombrePersona` ,`identificador` ,`numDepartamento`)
VALUES ('Julia Aguilar Lopez', '4', '01');

INSERT INTO `persona` (`nombrePersona` ,`identificador` ,`numDepartamento`)
VALUES ('Juana Castro Moreno', '5', '02');

INSERT INTO `persona` (`nombrePersona` ,`identificador` ,`numDepartamento`)
VALUES ('Jose Hidalgo Torres', '6', '01');

INSERT INTO `persona` (`nombrePersona` ,`identificador` ,`numDepartamento`)
VALUES ('Jorge Aguado Molina', '7', '02');

INSERT INTO `persona` (`nombrePersona` ,`identificador` ,`numDepartamento`)
VALUES ('Elena Martinez Martinez', '8', '01');

INSERT INTO `persona` (`nombrePersona` ,`identificador` ,`numDepartamento`)
VALUES ('Cristina Marquez Lucena', '9', '01');

INSERT INTO `persona` (`nombrePersona` ,`identificador` ,`numDepartamento`)
VALUES ('Dolores Morillo Suarez', '10', '01');

INSERT INTO `persona` (`nombrePersona` ,`identificador` ,`numDepartamento`)
VALUES ('Estefania Gomez Enriquez', '11', '01');

INSERT INTO `persona` (`nombrePersona` ,`identificador` ,`numDepartamento`)
VALUES ('Antonio Galvez Navarro', '12', '02');

INSERT INTO `persona` (`nombrePersona` ,`identificador` ,`numDepartamento`)
VALUES ('Rafael Torres Agudo', '13', '02');

INSERT INTO `persona` (`nombrePersona` ,`identificador` ,`numDepartamento`)
VALUES ('Miguel Reina Martin', '14', '02');

INSERT INTO `persona` (`nombrePersona` ,`identificador` ,`numDepartamento`)
VALUES ('Marta Samiento Martin', '15', '02');

INSERT INTO `persona` (`nombrePersona` ,`identificador` ,`numDepartamento`)
VALUES ('Alejandro Alcaide Ruano', '16', '02');