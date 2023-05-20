drop database if exists bd_smanga;
create database bd_smanga;
use bd_smanga;

-- tables
create table tb_rol(
cod_rol int primary key auto_increment,
nom_rol varchar(45)
);

create table tb_usuario(
cod_usu int primary key auto_increment,
nom_usu varchar(50),
ape_usu varchar(50),
dni_usu int,
ema_usu varchar(50),
tel_usu int,
dir_usu varchar(50),
user_usu varchar(70),
clave varchar(70),
cod_rol int
);

create table tb_administrador(
cod_adm int primary key auto_increment,
nom_adm  varchar(50),
ape_adm  varchar(50),
dni_adm int,
ema_adm varchar(50),
tel_adm int,
fec_nac date,
image varchar(255),
user_adm varchar(70),
clave varchar(70),
cod_rol int
);

create table tb_editorial(
cod_edi int primary key auto_increment,
nom_edi varchar(210),
pai_edi varchar(50),
dir_edi varchar(100),
ema_edi varchar(100),
tel_edi int
);

create table tb_autor(
cod_aut int primary key auto_increment,
nom_aut varchar(50),
pai_aut varchar(50)
);

create table tb_libro(
cod_lib int primary key auto_increment,
tit_lib varchar(70),
des_lib varchar(255),
anio_lib int,
gen_lib varchar(15),
stock_lib int,
vol_lib int,
precio_alquiler_dia decimal(4,2),
img_lib longblob,
nom_archivo varchar(75),
cod_aut int,
cod_edi int
);

create table tb_alquiler(
cod_alq int primary key auto_increment,
fec_alq date,
fec_dev date,
monto_alq decimal(5,2),
estado varchar(15),
cod_usu int,
cod_adm int
);

create table tb_detalle_alquiler(
cod_det_alq int primary key auto_increment,
precio decimal(5,2),
cantidad int,
cod_lib int,
cod_alq int
);

-- union y restricciones claves secundarias
alter table tb_usuario
add constraint pk_cod_rol_1 foreign key(cod_rol) references tb_rol(cod_rol);

alter table tb_administrador
add constraint pk_cod_rol foreign key(cod_rol) references tb_rol(cod_rol);

alter table tb_alquiler
add constraint pk_cod_usu foreign key(cod_usu) references tb_usuario(cod_usu);

alter table tb_alquiler
add constraint pk_cod_adm foreign key(cod_adm) references tb_administrador(cod_adm);

alter table tb_libro
add constraint pk_cod_aut foreign key(cod_aut) references tb_autor(cod_aut);

alter table tb_libro
add constraint pk_cod_edi foreign key(cod_edi) references tb_editorial(cod_edi);

alter table tb_detalle_alquiler
add constraint pk_cod_lib foreign key(cod_lib) references tb_libro(cod_lib);

alter table tb_detalle_alquiler
add constraint pk_cod_alq foreign key(cod_alq) references tb_alquiler(cod_alq);

-- inserts
insert into tb_rol values
(1, 'ADMIN'),
(2, 'USER');

insert into tb_usuario values
(1, 'Jhon Alex', 'Perez Romero', 78787876, 'jhon@gmail.com', 912912394, 'Villa Rosa Urb 11', 'jhon', '123', 2),
(2, 'Martina Guzmán', 'Sol Hermosa', 71787872, 'naomi@gmail.com', 912923195, 'Geronimo Parque Solar', 'naomi', 'abc', 2),
(3, 'Luis Fernando', 'Mendez', 78787875, 'luis@gmail.com', 912934821, 'Calle Francisco de Zela', 'luis', 'def', 2),
(4, 'Ana Maria', 'Paredes', 68787874, 'ana@gmail.com', 912934812, 'Av. Los Heroes 123', 'ana', '456', 2),
(5, 'Diego Alberto', 'Sanchez Rodriguez', 98787873, 'diego@gmail.com', 912934833, 'Jr. Los Pinos 456', 'diego', '789', 2),
(6, 'Valentina Maria', 'Castillo Diaz', 48787872, 'vale@gmail.com', 912934844, 'Av. La Marina 234', 'vale', '101112', 2),
(7, 'Gonzalo Andres', 'Alvarez Ramirez', 28787871, 'gonza@gmail.com', 912934855, 'Jr. Las Camelias 567', 'gonza', '131415', 2),
(8, 'Lucia Fernanda', 'Morales Torres', 78787870, 'lucia@gmail.com', 912934866, 'Av. Paseo de la Republica 789', 'lucia', '161718', 2),
(9, 'Pedro Juan', 'Rodriguez Garcia', 78787879, 'pedro@gmail.com', 912934877, 'Av. Primavera 234', 'pedro', '192021', 2),
(10, 'Carolina Sofia', 'Perez Gutierrez', 78787868, 'caro@gmail.com', 912934888, 'Calle 2 de Mayo 567', 'caro', '222324', 2),
(11, 'Juan Pablo', 'Garcia Perez', 73487867, 'juan@gmail.com', 912934899, 'Jr. Los Olivos 890', 'juan', '252627', 2),
(12, 'Lorena Paola', 'Mendez Luna', 70787866, 'lorena@gmail.com', 912934900, 'Calle Las Palmeras 123', 'lorena', '282930', 2);

insert into tb_administrador values
(1, 'Ronald Damian', 'Esteban Montes', 71787540, 'ronald@gmail.com', 912912871, '2000-01-10', '', 'ronald', '123', 1),
(2, 'Gabriel Sánchez', 'Avilar Morales', 71787345, 'ignacio@gmail.com', 922912336, '2000-01-10', '', 'ignacio', 'abc', 1),
(3, 'Jhon Alex', 'Villar Morata', 71787345, 'jhon@gmail.com', 934512336, '2001-01-10', '', 'jhon', 'abc', 1),
(4, 'Luisa Fernanda', 'Gómez González', 71787987, 'luisa@gmail.com', 914512347, '2001-02-15', '', 'luisa', '123', 1),
(5, 'Paola Andrea', 'Rodríguez Pérez', 71787989, 'paola@gmail.com', 912946238, '2002-05-20', '', 'paola', 'abc', 2),
(6, 'Mario Antonio', 'Martínez García', 71787654, 'mario@gmail.com', 912912945, '1999-11-27', '', 'mario', '123', 2),
(7, 'Sofía Alejandra', 'Ramírez Ríos', 71787999, 'sofia@gmail.com', 912912347, '2003-08-10', '', 'sofia', 'abc', 1),
(8, 'Camila Andrea', 'Castro Torres', 71787992, 'camila@gmail.com', 912912945, '1998-07-18', '', 'camila', '123', 2);

insert into tb_editorial values
(1, 'Editorial Ambar', 'Perú', 'Av. Benavides 449', 'ambar@gmail.com', 912915712),
(2, 'María Trinidad', 'Perú', 'Chorrillos, Peru. 234', 'trinidad@gmail.com', 912912340),
(3, 'Editorial Planeta', 'Perú', 'Av. Pardo y Aliaga 660, San Isidro', 'info@planetadelibros.com.pe', 912912340),
(4, 'Editorial Santillana', 'Perú', 'Calle Julio C. Tello 745, Surquillo', 'atencionalcliente.pe@santillana.com', 912912340),
(5, 'Fondo Editorial', 'Perú', 'Av. Germán Amézaga Nº 375, Lima', 'fondeditorial@unmsm.edu.pe', 987678900),
(6, 'Editorial Bruño', 'Perú', 'Av. República de Panamá 2572, San Isidro, Lima', 'editorial.bruno@bruno.com.pe', 955912340),
(7, 'Editorial San Marcos', 'Perú', 'Ciudad Universitaria, Av. Germán Amézaga Nº 375', 'editorialsanmarcos@unmsm.edu.pe', 987654345),
(8, 'Editorial El Comercio', 'Perú', 'Av. Alfonso Ugarte 873, Lima', 'editorial@comercio.com.pe', 952912340),
(9, 'Editorial PUCP', 'Perú', 'Av. Universitaria 1801, San Miguel', 'editorial@pucp.edu.pe', 942912340),
(10, 'Ediciones Altazor', 'Perú', 'Jr. Echenique 615, Cercado de Lima', 'info@edicionesaltazor.com', 972912340);

insert into tb_autor values
(1, 'Akira Toriyama', 'Japón'),
(2, 'Hiromu Arakawa ', 'Japón'),
(3, 'Eiichiro Oda', 'Japón'),
(4, 'Hajime Isayama', 'Japón'),
(5, 'Naoko Takeuchi', 'Japón'),
(6, 'Osamu Tezuka', 'Japón'),
(7, 'Rumiko Takahashi', 'Japón'),
(8, 'Katsuhiro Otomo', 'Japón'),
(9, 'Kazuo Koike', 'Japón'),
(10, 'Mitsuru Adachi', 'Japón');

insert into tb_libro values
(1, 'Fullmetal Alchemist', 'Dos hermanos que viven en el país de Amestris, un estado militarizado por completo (el jefe del estado es el jefe del ejército), en un mundo donde la alquimia es algo cotidiano, dándole un trasfondo fantástico a la obra', 
2004, 'Aventura', 10, 3, 1.00, '', '', '1', '1'),
(2, 'Hunter × Hunter', 'Un joven en su búsqueda por encontrar a su padre, quien es un legendario cazador de élite. A lo largo de la historia, Gon hace amigos y enemigos mientras aprende sobre los peligros y la complejidad del mundo de los cazadores', 
2003, 'Aventura', 15, 5, 1.00, '', '', '2', '2'),
(3, 'One Piece', 'Monkey D. Luffy y su tripulación pirata se embarcan en una búsqueda épica para encontrar el tesoro legendario "One Piece" y convertirse en el Rey de los Piratas', 
1997, 'Aventura', 98, 2, 1.00, '', '', '1', '1'),
(4, 'Naruto', 'Naruto Uzumaki, un joven ninja con el sueño de convertirse en Hokage, el líder de su aldea. A lo largo de su camino, Naruto enfrenta muchos desafíos y enemigos, mientras aprende a controlar su poder', 
1999, 'Aventura', 72, 1, 1.00, '', '', '2', '2'),
(5, 'Attack on Titan', 'La humanidad vive en una ciudad rodeada de muros gigantes para protegerse de los Titanes, criaturas gigantes y caníbales que devoran a los humanos',
2009, 'Acción', 34, 1, 1.00, '', '', '3', '3'),
(6, 'Death Note', 'Light Yagami encuentra un cuaderno sobrenatural que le permite matar a cualquier persona cuyo nombre escriba en él. Light se convierte en un justiciero que elimina criminales', 
2003, 'Misterio', 12, 1, 1.00, '', '', '4', '4'),
(7, 'Dragon Ball', 'Goku, un joven guerrero con cola de mono, se embarca en una búsqueda para encontrar las siete Esferas del Dragón, que conceden cualquier deseo', 
1984, 'Acción', 42, 3, 1.00, '', '', '5', '5'),
(8, 'Bleach', 'Ichigo Kurosaki, un adolescente con la habilidad de ver fantasmas, obtiene los poderes de un Shinigami, un ser que guía a las almas al más allá', 
2001, 'Acción', 74, 3, 1.00, '', '', '6', '6'),
(9, 'Kokkoku', 'Juri Yukawa intenta salvar a su hermano y sobrino de una secta religiosa que los ha secuestrado. Sin embargo, se encuentra con una extraña habilidad que le permite detener el tiempo', 
2014, 'Misterio', 12, 4, 1.00, '', '', '7', '7'),
(10, 'Isekai Shokudō', 'En una ciudad moderna, un restaurante llamado "Yōshoku no Nekoya" ("El restaurante de comida occidental") tiene una puerta en su cocina que se conecta a otros mundos', 
2016, 'Fantasía', 6, 6, 1.00, '', '', '8', '8'),
(11, 'Akatsuki no Yona', 'Yona es una princesa que vive felizmente en su reino hasta que su padre es asesinado. Huyendo de sus enemigos, Yona comienza una nueva vida acompañada de Hak, su guardaespaldas', 
2009, 'Romance', 34, 5, 1.00, '', '', '9', '9'),
(12, 'Kino no Tabi', 'Kino es una viajera que viaja por el mundo en su motocicleta. En cada país que visita, se queda solamente tres días para experimentar la vida y cultura local', 
2000, 'Aventura', 20, 2, 1.00, '', '', '10', '10'),
(13, 'Drifters', 'Shimazu Toyohisa, un samurái del siglo XVI, es transportado a un mundo paralelo lleno de guerras y conflictos. Allí se une a otros grandes guerreros para luchar por el bien', 
2009, 'Acción', 7, 7, 1.00, '', '', '10', '10');

insert into tb_alquiler values
(1, '2023-03-12', '2023-03-16', 10.00, 'Devuelto', 1, 1),
(2, '2023-03-11', '2023-03-15', 3.00, 'Vencido', 2, 1),
(3, '2023-03-16', '2023-03-18', 4.00, 'Prestado', 2, 1);

-- query
select * from tb_rol;
select * from tb_usuario;
select * from tb_administrador;
select * from tb_editorial;
select * from tb_autor;
select * from tb_libro;
select * from tb_alquiler;
select * from tb_detalle_alquiler;
