/* Script post deployment*/
/*Observaciones: No separar una instrucción en varias línea. No le gusta a MySQL*/

/*Profes*/
INSERT INTO usuario VALUES ('jorge54', 'Fonseca Castro', 'jorge54@yopmail.com', '1995-01-01', 'ruta/foto/server', 'Jorge', 'E1F53135E559C253', 'Otro', '88282838');
INSERT INTO usuario VALUES ('mario76', 'Vega Tenorio', 'marito54@yopmail.com', '1995-01-01', 'ruta/foto/server', 'Mario', 'E1F53135E559C253', 'Otro', '88282345');
INSERT INTO usuario VALUES ('gabrielaPo98', 'Poveda Castro', 'gabiPoveda@yopmail.com', '1995-01-01', 'ruta/foto/server', 'Gabriela', 'E1F53135E559C253', 'Otro', '84356838');
INSERT INTO usuario VALUES ('marii', 'Perez Perez', 'mari54@yopmail.com', '1995-01-01', 'ruta/foto/server', 'Maria', 'E1F53135E559C253', 'Otro', '88296338');
INSERT INTO usuario VALUES ('bran24', 'Dota Noria', 'bran24@yopmail.com', '1995-01-01', 'ruta/foto/server', 'Brandon', 'E1F53135E559C253', 'Otro', '76382838');

/*Estudiantes*/
INSERT INTO usuario VALUES ('steveen', 'Fort Castro', 'steveen@yopmail.com', '1998-01-01', 'ruta/foto/server', 'Steven', 'E1F53135E559C253', 'Otro', '84376838');
INSERT INTO usuario VALUES ('stephXO', 'Loria Perez', 'sthephanieLP@yopmail.com', '1998-01-01', 'ruta/foto/server', 'Sthephanie', 'E1F53135E559C253', 'Otro', '88288738');
INSERT INTO usuario VALUES ('devora45', 'Zeledón Álvarez', 'nanaAL@yopmail.com', '1998-01-01', 'ruta/foto/server', 'Devora', 'E1F53135E559C253', 'Otro', '71232838');


/*Instituciones*/
INSERT INTO institucion (institucion_nombre, descripcion, foto, habilitada, ubicacion) VALUES ('Cedes Don Bosco', 'Colegio Técnico Profesional Semi-Privado','ruta/foto/server',true,'San José');
INSERT INTO institucion (institucion_nombre, descripcion, foto, habilitada, ubicacion) VALUES ('Universidad de Costa Rica', 'Universidad pública #1 del país','ruta/foto/server',true,'San Pedro');


/*Siglas tematicas*/
INSERT INTO sigla_tematica VALUES (1, 'FIS-001')
INSERT INTO sigla_tematica VALUES (2, 'MAT-001')
INSERT INTO sigla_tematica VALUES (3, 'ART-001')

/*Areas tematicas*/
INSERT INTO area_tematica VALUES (1, 'Área temática de Fisica', 'Física', 'Cedes Don Bosco',  1);
INSERT INTO area_tematica VALUES (2, 'Área temática de Matemática', 'Matemática', 'Cedes Don Bosco', 2);
INSERT INTO area_tematica VALUES (3, 'Área temática de Fisica', 'Física', 'Universidad de Costa Rica', 2);
INSERT INTO area_tematica VALUES (4, 'Área temática de Arte', 'Arte','Universidad de Costa Rica', 3);

/*Cursos*/
INSERT INTO curso VALUES ('Física General I', 'Curso donde se cubre los temas generales de Física', 'ruta/foto/server', 1);
INSERT INTO curso VALUES ('Física General II', 'Curso donde se cubre los temas generales, más avanzados, de Física', 'ruta/foto/server', 1);
INSERT INTO curso VALUES ('Algebra', 'Curso donde se cubre la algebra básica', 'ruta/foto/server', 2);
INSERT INTO curso VALUES ('Física I', 'Curso donde se cubre los temas generales de Física, se trabaja con la práctica', 'ruta/foto/server', 3);
INSERT INTO curso VALUES ('Cine', 'Curso donde se cubre ls historia del Cine', 'ruta/foto/server', 4);

/*Grupos*/
INSERT INTO grupo VALUES (1, '1 semestre', 'Física I', 'jorge54');
INSERT INTO grupo VALUES (2, '1 semestre', 'Física I', 'marii');
INSERT INTO grupo VALUES (1, '2 semestres',  'Cine', 'mario76');
INSERT INTO grupo VALUES (1, '1 semestre', 'Física General I', 'bran24');
INSERT INTO grupo VALUES (1, '1 semestre', 'Física General II', 'marii');
INSERT INTO grupo VALUES (2, '1 semestre', 'Física General II', 'marii');
INSERT INTO grupo VALUES (1, '1 semestre', 'Algebra', 'gabrielaPo98');

/*Perfiles*/
INSERT INTO perfil VALUES (1, 'Encargado de impartir cursos', 'Profesor', 'Universidad de Costa Rica');
INSERT INTO perfil VALUES (2, 'Encargado de impartir cursos', 'Profesor', 'Cedes Don Bosco');
INSERT INTO perfil VALUES (3, 'Puede recibir cursos', 'Estudiante', 'Universidad de Costa Rica');
INSERT INTO perfil VALUES (4, 'Puede recibir cursos', 'Estudiante', 'Cedes Don Bosco');

/*Permisos*/
INSERT INTO permiso VALUES (1, 'Impartir un curso');
INSERT INTO permiso VALUES (2, 'Recibir un curso');


/*Perfil-Permiso*/
INSERT INTO institucion_perfil_permiso VALUES (1, 1, 'Universidad de Costa Rica');
INSERT INTO institucion_perfil_permiso VALUES (2, 2, 'Universidad de Costa Rica');
INSERT INTO institucion_perfil_permiso VALUES (1, 1, 'Cedes Don Bosco');
INSERT INTO institucion_perfil_permiso VALUES (2, 2, 'Cedes Don Bosco');


/*Perfil-Usuario*/
INSERT INTO institucion_perfil_usuario VALUES (3, 'steveen', 'Universidad de Costa Rica');
INSERT INTO institucion_perfil_usuario VALUES (4, 'steveen', 'Cedes Don Bosco');
INSERT INTO institucion_perfil_usuario VALUES (3, 'stephXO', 'Universidad de Costa Rica');
INSERT INTO institucion_perfil_usuario VALUES (4, 'devora45', 'Cedes Don Bosco');

INSERT INTO institucion_perfil_usuario VALUES (2, 'marii', 'Cedes Don Bosco');
INSERT INTO institucion_perfil_usuario VALUES (2, 'bran24', 'Cedes Don Bosco');
INSERT INTO institucion_perfil_usuario VALUES (2, 'gabrielaPo98', 'Cedes Don Bosco');
INSERT INTO institucion_perfil_usuario VALUES (1, 'jorge54', 'Universidad de Costa Rica');
INSERT INTO institucion_perfil_usuario VALUES (1, 'marii', 'Universidad de Costa Rica');
INSERT INTO institucion_perfil_usuario VALUES (1, 'mario76', 'Universidad de Costa Rica');



/*Usuario_Grupo_Inscrito*/
INSERT INTO usuario_grupo_inscrito VALUES ('steveen', 0, 1, '1 semestre', 'Física I');
INSERT INTO usuario_grupo_inscrito VALUES ('stephXO', 0, 1, '2 semestres', 'Cine');
INSERT INTO usuario_grupo_inscrito VALUES ('devora45', 0, 1, '1 semestre', 'Algebra');
INSERT INTO usuario_grupo_inscrito VALUES ('steveen', 0, 1, '1 semestre', 'Algebra');