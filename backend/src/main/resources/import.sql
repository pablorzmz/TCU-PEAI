/* Script post deployment*/
/*Observaciones: No separar una instrucción en varias línea. No le gusta a MySQL*/

/*Profes*/
INSERT INTO usuario (nombre_usuario, apellidos, correo, fecha_nacimiento,foto,nombre,habilitado,salt,sexo,telefono) VALUES ('jorge54', 'Fonseca Castro', 'jorge54@yopmail.com', '1995-01-01', 'ruta/foto/server', 'Jorge', 1, '$2a$10$tM4OB700xydM2T5EilZLWeovyeK974xxF0c2BZHd53v856BaTWgu.', 'Otro', '88282838');
INSERT INTO usuario (nombre_usuario, apellidos, correo, fecha_nacimiento,foto,nombre,habilitado,salt,sexo,telefono) VALUES ('mario76', 'Vega Tenorio', 'marito54@yopmail.com', '1995-01-01', 'ruta/foto/server', 'Mario',1, '$2a$10$7QqULJ8RYmzGvmR/ucAc5OWL/Qkv/YU9/aNZtvHzPHwkQUc4pWZf2', 'Otro', '88282345');
INSERT INTO usuario (nombre_usuario, apellidos, correo, fecha_nacimiento,foto,nombre,habilitado,salt,sexo,telefono) VALUES ('gabrielaPo98', 'Poveda Castro', 'gabiPoveda@yopmail.com', '1995-01-01', 'ruta/foto/server', 'Gabriela',1, '$2a$10$WzZfBkvkFpLmM0dSJgcsCegHA0OhTpRojUCvmYrIEvemi1xBT7xji', 'Otro', '84356838');
INSERT INTO usuario (nombre_usuario, apellidos, correo, fecha_nacimiento,foto,nombre,habilitado,salt,sexo,telefono) VALUES ('marii', 'Perez Perez', 'mari54@yopmail.com', '1995-01-01', 'ruta/foto/server', 'Maria',1, '$2a$10$ge0wJhlmsOI.Vb8ZCMeAMenb6zPY6/uQUYp1xTcuBgNkxfaMbFwmu', 'Otro', '88296338');
INSERT INTO usuario (nombre_usuario, apellidos, correo, fecha_nacimiento,foto,nombre,habilitado,salt,sexo,telefono) VALUES ('bran24', 'Dota Noria', 'bran24@yopmail.com', '1995-01-01', 'ruta/foto/server', 'Brandon',1, '$2a$10$8RPnZmMcNjAYLExOjvanvudAmfHJ3wO2uoQsCSzQrjIAeZQtN.7RS', 'Otro', '76382838');

/*Estudiantes*/
INSERT INTO usuario (nombre_usuario, apellidos, correo, fecha_nacimiento,foto,nombre,habilitado,salt,sexo,telefono) VALUES ('steveen', 'Fort Castro', 'steveen@yopmail.com', '1998-01-01', 'ruta/foto/server', 'Steven',1, '$2a$10$sSyjvSJM7JIuQ8XyUj7xz.DIj6ba0jMhTWOF8PYH0KaBn.mhNB3A.', 'Otro', '84376838');
INSERT INTO usuario (nombre_usuario, apellidos, correo, fecha_nacimiento,foto,nombre,habilitado,salt,sexo,telefono) VALUES ('stephXO', 'Loria Perez', 'sthephanieLP@yopmail.com', '1998-01-01', 'ruta/foto/server', 'Sthephanie',1, '$2a$10$mbgCttJe87jG8XEteSeds.9hDFeKGdc/SCMcrPgFUpNoE1Z9nmU7.', 'Otro', '88288738');
INSERT INTO usuario (nombre_usuario, apellidos, correo, fecha_nacimiento,foto,nombre,habilitado,salt,sexo,telefono) VALUES ('devora45', 'Zeledón Álvarez', 'nanaAL@yopmail.com', '1998-01-01', 'ruta/foto/server', 'Devora',1, '$2a$10$GeCFnD90HZLsd1GXVHd.nuVwHMtGgE11WE0IIXAWtYqWMIPI.KFwi', 'Otro', '71232838');


/*Instituciones*/
INSERT INTO institucion (institucion_nombre, descripcion, foto, habilitada, ubicacion) VALUES ('Cedes Don Bosco', 'Colegio Técnico Profesional Semi-Privado','https://www.cedesdonbosco.ed.cr/es/images/logos/logocedes.png',1,'San José');
INSERT INTO institucion (institucion_nombre, descripcion, foto, habilitada, ubicacion) VALUES ('Universidad de Costa Rica', 'Universidad pública #1 del país','http://www.cia.ucr.ac.cr/wp-content/uploads/2016/02/logoUCR_0.png',1,'San Pedro');


/*Siglas tematicas*/
INSERT INTO sigla_tematica VALUES (1, 'FIS-001')
INSERT INTO sigla_tematica VALUES (2, 'MAT-001')
INSERT INTO sigla_tematica VALUES (3, 'ART-001')

/*Areas tematicas*/
INSERT INTO area_tematica VALUES (1, 'Área temática de Fisica', 'https://image.freepik.com/vector-gratis/fondo-acerca-ciencia_1284-699.jpg', 'Física', 'Cedes Don Bosco',  1);
INSERT INTO area_tematica VALUES (2, 'Área temática de Matemática', 'https://image.freepik.com/vector-gratis/fondo-acerca-ciencia_1284-699.jpg', 'Matemática', 'Cedes Don Bosco', 2);
INSERT INTO area_tematica VALUES (3, 'Área temática de Fisica', 'https://image.freepik.com/vector-gratis/fondo-acerca-ciencia_1284-699.jpg','Física', 'Universidad de Costa Rica', 2);
INSERT INTO area_tematica VALUES (4, 'Área temática de Arte', 'https://image.freepik.com/vector-gratis/fondo-acerca-ciencia_1284-699.jpg', 'Arte','Universidad de Costa Rica', 3);
INSERT INTO area_tematica VALUES (5, 'Área temática de Arte', 'https://image.freepik.com/vector-gratis/fondo-acerca-ciencia_1284-699.jpg', 'Arte','Cedes Don Bosco', 3);

/*Cursos*/
INSERT INTO curso VALUES ('Física General I', 'Curso donde se cubre los temas generales de Física', 'ruta/foto/server', 1);
INSERT INTO curso VALUES ('Física General II', 'Curso donde se cubre los temas generales, más avanzados, de Física', 'ruta/foto/server', 1);
INSERT INTO curso VALUES ('Algebra', 'Curso donde se cubre la algebra básica', 'ruta/foto/server', 2);
INSERT INTO curso VALUES ('Física I', 'Curso donde se cubre los temas generales de Física, se trabaja con la práctica', 'ruta/foto/server', 3);
INSERT INTO curso VALUES ('Cine', 'Curso donde se cubre ls historia del Cine', 'ruta/foto/server', 4);

/*Grupos*/
INSERT INTO grupo VALUES (1, '1 semestre', 'Física I', 'jorge54');
INSERT INTO grupo VALUES (2, '1 semestre', 'Física I', 'marii');
INSERT INTO grupo VALUES (1, '2 semestre',  'Cine', 'mario76');
INSERT INTO grupo VALUES (1, '1 semestre', 'Física General I', 'bran24');
INSERT INTO grupo VALUES (1, '1 semestre', 'Física General II', 'marii');
INSERT INTO grupo VALUES (2, '1 semestre', 'Física General II', 'marii');
INSERT INTO grupo VALUES (1, '1 semestre', 'Algebra', 'gabrielaPo98');

/*Perfiles*/
INSERT INTO perfil (perfil_id, nombre, descripcion) VALUES (1,'ROLE_Profesor', 'Encargado de impartir cursos');
INSERT INTO perfil (perfil_id, nombre, descripcion) VALUES (2,'ROLE_Estudiante', 'Puede recibir cursos');

/*Permisos*/
INSERT INTO permiso VALUES (1, 'Impartir un curso');
INSERT INTO permiso VALUES (2, 'Recibir un curso');


/*Perfil-Permiso*/
INSERT INTO institucion_perfil_permiso  (perfil_id,permiso_id,institucion_nombre) VALUES (1, 1, 'Universidad de Costa Rica');
INSERT INTO institucion_perfil_permiso  (perfil_id,permiso_id,institucion_nombre) VALUES (2, 2, 'Universidad de Costa Rica');
INSERT INTO institucion_perfil_permiso  (perfil_id,permiso_id,institucion_nombre) VALUES (1, 1, 'Cedes Don Bosco');
INSERT INTO institucion_perfil_permiso  (perfil_id,permiso_id,institucion_nombre) VALUES (2, 2, 'Cedes Don Bosco');


/*Perfil-Usuario*/
INSERT INTO institucion_perfil_usuario  (perfil_id,nombre_usuario,institucion_nombre) VALUES (2, 'steveen', 'Universidad de Costa Rica');
INSERT INTO institucion_perfil_usuario  (perfil_id,nombre_usuario,institucion_nombre) VALUES (2, 'steveen', 'Cedes Don Bosco');
INSERT INTO institucion_perfil_usuario  (perfil_id,nombre_usuario,institucion_nombre) VALUES (2, 'stephXO', 'Universidad de Costa Rica');
INSERT INTO institucion_perfil_usuario  (perfil_id,nombre_usuario,institucion_nombre) VALUES (2, 'devora45', 'Cedes Don Bosco');

INSERT INTO institucion_perfil_usuario  (perfil_id,nombre_usuario,institucion_nombre) VALUES (1, 'marii', 'Cedes Don Bosco');
INSERT INTO institucion_perfil_usuario  (perfil_id,nombre_usuario,institucion_nombre) VALUES (1, 'bran24', 'Cedes Don Bosco');
INSERT INTO institucion_perfil_usuario  (perfil_id,nombre_usuario,institucion_nombre) VALUES (1, 'gabrielaPo98', 'Cedes Don Bosco');
INSERT INTO institucion_perfil_usuario  (perfil_id,nombre_usuario,institucion_nombre) VALUES (1, 'jorge54', 'Universidad de Costa Rica');
INSERT INTO institucion_perfil_usuario  (perfil_id,nombre_usuario,institucion_nombre) VALUES (1, 'jorge54', 'Cedes Don Bosco');
INSERT INTO institucion_perfil_usuario  (perfil_id,nombre_usuario,institucion_nombre) VALUES (2, 'jorge54', 'Cedes Don Bosco');
INSERT INTO institucion_perfil_usuario  (perfil_id,nombre_usuario,institucion_nombre) VALUES (1, 'marii', 'Universidad de Costa Rica');
INSERT INTO institucion_perfil_usuario  (perfil_id,nombre_usuario,institucion_nombre) VALUES (1, 'mario76', 'Universidad de Costa Rica');


/*Usuario_Grupo_Inscrito*/
INSERT INTO usuario_grupo_inscrito (nombre_usuario,nota_final,numero,periodo_tiempo,curso_nombre) VALUES ('steveen', 0, 1, '1 semestre', 'Física I');
INSERT INTO usuario_grupo_inscrito (nombre_usuario,nota_final,numero,periodo_tiempo,curso_nombre) VALUES ('stephXO', 0, 1, '2 semestre', 'Cine');
INSERT INTO usuario_grupo_inscrito (nombre_usuario,nota_final,numero,periodo_tiempo,curso_nombre) VALUES ('stephXO', 0, 1, '1 semestre', 'Física I');
