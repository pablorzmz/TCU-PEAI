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
INSERT INTO curso (descripcion, foto, nombre, area_tematica_id) VALUES ('Curso donde se cubre los temas generales de Física', 'https://previews.123rf.com/images/krekdm/krekdm1608/krekdm160800078/61579579-volver-a-la-escuela-sin-fisuras-patr%C3%B3n-se-puede-utilizar-para-fondos-de-escritorio-fondo-de-p%C3%A1gina-web-pa.jpg', 'Física General I',  1);
INSERT INTO curso (descripcion, foto, nombre, area_tematica_id) VALUES ('Curso donde se cubre los temas generales, más avanzados, de Física', 'https://previews.123rf.com/images/krekdm/krekdm1608/krekdm160800078/61579579-volver-a-la-escuela-sin-fisuras-patr%C3%B3n-se-puede-utilizar-para-fondos-de-escritorio-fondo-de-p%C3%A1gina-web-pa.jpg','Física General II', 1);
INSERT INTO curso (descripcion, foto, nombre, area_tematica_id) VALUES ('Curso donde se cubre la algebra básica', 'https://previews.123rf.com/images/krekdm/krekdm1608/krekdm160800078/61579579-volver-a-la-escuela-sin-fisuras-patr%C3%B3n-se-puede-utilizar-para-fondos-de-escritorio-fondo-de-p%C3%A1gina-web-pa.jpg', 'Algebra', 2);
INSERT INTO curso (descripcion, foto, nombre, area_tematica_id) VALUES ('Curso donde se cubre los temas generales de Física, se trabaja con la práctica', 'https://previews.123rf.com/images/krekdm/krekdm1608/krekdm160800078/61579579-volver-a-la-escuela-sin-fisuras-patr%C3%B3n-se-puede-utilizar-para-fondos-de-escritorio-fondo-de-p%C3%A1gina-web-pa.jpg','Física I',  3);
INSERT INTO curso (descripcion, foto, nombre, area_tematica_id) VALUES ('Curso donde se cubre ls historia del Cine', 'https://previews.123rf.com/images/krekdm/krekdm1608/krekdm160800078/61579579-volver-a-la-escuela-sin-fisuras-patr%C3%B3n-se-puede-utilizar-para-fondos-de-escritorio-fondo-de-p%C3%A1gina-web-pa.jpg', 'Cine',  4);

/*Grupos*/
INSERT INTO grupo VALUES (1, '1 semestre', 4, 'jorge54');
INSERT INTO grupo VALUES (2, '1 semestre', 4, 'marii');
INSERT INTO grupo VALUES (1, '2 semestre',  5, 'mario76');
INSERT INTO grupo VALUES (1, '1 semestre', 1, 'bran24');
INSERT INTO grupo VALUES (1, '1 semestre', 2, 'marii');
INSERT INTO grupo VALUES (2, '1 semestre', 2, 'marii');
INSERT INTO grupo VALUES (1, '1 semestre', 3, 'gabrielaPo98');

/*Perfiles*/
INSERT INTO perfil (perfil_id, nombre, descripcion) VALUES (1,'ROLE_Profesor', 'Encargado de impartir cursos');
INSERT INTO perfil (perfil_id, nombre, descripcion) VALUES (2,'ROLE_Estudiante', 'Puede recibir cursos');

/*Permisos*/
INSERT INTO permiso VALUES (1, 'Impartir un curso');
INSERT INTO permiso VALUES (2, 'Recibir un curso');
INSERT INTO permiso VALUES (3, 'Visualizar areas temáticas');
INSERT INTO permiso VALUES (4, 'Visualizar instituciones');
INSERT INTO permiso VALUES (5, 'Visualizar cursos');
INSERT INTO permiso VALUES (6, 'Agregar grupos');
INSERT INTO permiso VALUES (7, 'Crear Curso');


/*Perfil-Permiso*/
INSERT INTO institucion_perfil_permiso  (perfil_id,permiso_id,institucion_nombre) VALUES (1, 1, 'Universidad de Costa Rica');
INSERT INTO institucion_perfil_permiso  (perfil_id,permiso_id,institucion_nombre) VALUES (2, 2, 'Universidad de Costa Rica');
INSERT INTO institucion_perfil_permiso  (perfil_id,permiso_id,institucion_nombre) VALUES (1, 1, 'Cedes Don Bosco');
INSERT INTO institucion_perfil_permiso  (perfil_id,permiso_id,institucion_nombre) VALUES (2, 2, 'Cedes Don Bosco');
INSERT INTO institucion_perfil_permiso  (perfil_id,permiso_id,institucion_nombre) VALUES (1, 6, 'Universidad de Costa Rica');
INSERT INTO institucion_perfil_permiso  (perfil_id,permiso_id,institucion_nombre) VALUES (1, 6, 'Cedes Don Bosco');
INSERT INTO institucion_perfil_permiso  (perfil_id,permiso_id,institucion_nombre) VALUES (1, 7, 'Universidad de Costa Rica');
INSERT INTO institucion_perfil_permiso  (perfil_id,permiso_id,institucion_nombre) VALUES (1, 7, 'Cedes Don Bosco');

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
INSERT INTO usuario_grupo_inscrito (nombre_usuario,nota_final,numero,periodo_tiempo,curso_id) VALUES ('steveen', 0, 1, '1 semestre', 4);
INSERT INTO usuario_grupo_inscrito (nombre_usuario,nota_final,numero,periodo_tiempo,curso_id) VALUES ('stephXO', 0, 1, '2 semestre', 5);
INSERT INTO usuario_grupo_inscrito (nombre_usuario,nota_final,numero,periodo_tiempo,curso_id) VALUES ('stephXO', 0, 1, '1 semestre', 4);


/********************** Sub secciones de materiales ******************************************/
/* Grupo 1, Curso 4 */
INSERT INTO subseccion_material(curso_id, periodo_tiempo, numero, nombre, habilitada) VALUES (4, '1 semestre',1,'Todo el material',1);
INSERT INTO subseccion_material(curso_id, periodo_tiempo, numero, nombre, habilitada) VALUES (4, '1 semestre',1,'Lecturas complementarias',1);
INSERT INTO subseccion_material(curso_id, periodo_tiempo, numero, nombre, habilitada) VALUES (4, '1 semestre',1,'Prácticas para quices',1);

/*Grupo 2, Curso 4*/
INSERT INTO subseccion_material(curso_id, periodo_tiempo, numero, nombre, habilitada) VALUES ( 4, '1 semestre',2,'Todo el material',1);
INSERT INTO subseccion_material(curso_id, periodo_tiempo, numero, nombre, habilitada) VALUES ( 4, '1 semestre',2,'Practicas quices',1);
INSERT INTO subseccion_material(curso_id, periodo_tiempo, numero, nombre, habilitada) VALUES ( 4, '1 semestre',2,'Repaso algebra',1);

/*Grupo 1, Curso 5*/
INSERT INTO subseccion_material(curso_id, periodo_tiempo, numero, nombre, habilitada) VALUES ( 5, '2 semestre',1,'Todo el material',1);
INSERT INTO subseccion_material(curso_id, periodo_tiempo, numero, nombre, habilitada) VALUES ( 5, '2 semestre',1,'Practicas quices',1);
INSERT INTO subseccion_material(curso_id, periodo_tiempo, numero, nombre, habilitada) VALUES ( 5, '2 semestre',1,'Repaso metodologías investigacion',1);

/*Grupo 1, Curso 1*/
INSERT INTO subseccion_material(curso_id, periodo_tiempo, numero, nombre, habilitada) VALUES ( 1, '1 semestre',1,'Todo el material',1);
INSERT INTO subseccion_material(curso_id, periodo_tiempo, numero, nombre, habilitada) VALUES ( 1, '1 semestre',1,'Practicas',1);
INSERT INTO subseccion_material(curso_id, periodo_tiempo, numero, nombre, habilitada) VALUES ( 1, '1 semestre',1,'Documentación para proyecto',1);

/*Grupo 1, Curso 2*/
INSERT INTO subseccion_material(curso_id, periodo_tiempo, numero, nombre, habilitada) VALUES ( 2, '1 semestre',1,'Todo el material',1);
INSERT INTO subseccion_material(curso_id, periodo_tiempo, numero, nombre, habilitada) VALUES ( 2, '1 semestre',1,'Apoyo APA',1);

/*Grupo 2, Curso 2*/
INSERT INTO subseccion_material(curso_id, periodo_tiempo, numero, nombre, habilitada) VALUES ( 2, '1 semestre',2,'Todo el material',1);

/*Grupo 2, Curso 3*/
INSERT INTO subseccion_material(curso_id, periodo_tiempo, numero, nombre, habilitada) VALUES ( 3, '1 semestre',1,'Todo el material',1);