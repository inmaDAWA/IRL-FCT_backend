
/* tabla cursos */

INSERT INTO cursos (nivel_id, titulo, descripcion, create_at) VALUES(3, 'Programación Web con HTML5, JavaScript, CSS3 y ASP.NET', 'Preparación del examen 70-480 de Microsoft, necesario para obtener la certificación MCSA: Web Applications de Microsoft', '2018-01-01');
INSERT INTO cursos (nivel_id, titulo, descripcion, create_at) VALUES(2, 'Arduino', 'Este curso consta de los siguiente módulos: El proceso del prototipado, Introducción al software y hardware con Arduino, Fundamentos de programación con Arduino', '2018-01-02');
INSERT INTO cursos (nivel_id, titulo, descripcion, create_at) VALUES(2, 'Administración de bases de datos MySQL', 'Programa formativo para poder instalar, configurar y administrar MySQL Server de manera profesional.', '2018-01-03');
INSERT INTO cursos (nivel_id, titulo, descripcion, create_at) VALUES(1, 'Introducción a la programación en Java', 'Iniciación en el desarrollo de aplicaciones orientadas a objetos con el lenguaje de programación Java','2018-01-04');
INSERT INTO cursos (nivel_id, titulo, descripcion, create_at) VALUES(2, 'Google Cloud Certified Professional Cloud Developer', 'Aprender el valor de Google Cloud Platform y cómo incorporar soluciones basadas en cloud en estrategias de negocio.', '2018-02-01');
INSERT INTO cursos (nivel_id, titulo, descripcion, create_at) VALUES(1, 'ITIL Foundation', 'Enfoque práctico y flexible para moverse al nuevo mundo de la transformación digital y que adopta un modelo operativo de punta a punta para la entrega y operación de productos y servicios.', '2018-02-10');
INSERT INTO cursos (nivel_id, titulo, descripcion, create_at) VALUES(3, 'AWS Solutions Architect Professional', 'Este curso versa sobre cómo compilar soluciones complejas con servicios de datos corporativos, gobernanza y seguridad en AWS.', '2018-02-18');



/* tabla niveles */

INSERT INTO niveles (nombre) VALUES ('Basico');
INSERT INTO niveles (nombre) VALUES ('Medio');
INSERT INTO niveles (nombre) VALUES ('Alto');


/* Creamos algunos usuarios con sus roles */

INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('inma', '$2a$10$yijU2DOxR9juB3AedfIH8.m9CfUYrTM6JZSJGU.mVk57FfGk.nlAO', 1, 'IRL', 'Rey', 'irl@gmail.com');
INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('admin', '$2a$10$o52WVx77clN52tKXan5H4OemKUe5aJkZOBR2fPul8KO1t85Efu4eK', 1, 'Inma', 'Rey', 'inmar@gmail.com');

INSERT INTO roles (nombre) VALUES ('ROLE_USER');
INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (1, 1);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2, 2);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2, 1);