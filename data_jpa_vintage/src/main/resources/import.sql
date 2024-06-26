INSERT INTO cliente ( name, surname, email, create_at, photo)VALUES ( 'John', 'Doe', 'johndoe@mail.com', '2020-01-01', '');
INSERT INTO cliente ( name, surname, email, create_at, photo)VALUES ( 'Jane', 'Doe', 'janedoe@mail.com', '2020-01-02', '');
INSERT INTO cliente ( name, surname, email, create_at, photo)VALUES ( 'Alice', 'Smith', 'alicesmith@mail.com', '2020-01-03', ''); 
INSERT INTO cliente ( name, surname, email, create_at, photo)VALUES ( 'Bob', 'Johnson', 'bobjohnson@mail.com', '2020-01-04', ''); 
INSERT INTO cliente ( name, surname, email, create_at, photo)VALUES ( 'Eva', 'Davis', 'evadavis@mail.com', '2020-01-05', ''); 
INSERT INTO cliente ( name, surname, email, create_at, photo)VALUES ( 'Michael', 'Wilson', 'michaelwilson@mail.com', '2020-01-06', '');
INSERT INTO cliente ( name, surname, email, create_at, photo)VALUES ( 'David', 'Miller', 'davidmiller@mail.com', '2020-01-07', '');
INSERT INTO cliente ( name, surname, email, create_at, photo)VALUES ( 'Sarah', 'Brown', 'sarahbrown@mail.com', '2020-01-08', '');
INSERT INTO cliente ( name, surname, email, create_at, photo)VALUES ( 'Daniel', 'Taylor', 'danieltaylor@mail.com', '2020-01-09', '');
INSERT INTO cliente ( name, surname, email, create_at, photo)VALUES ( 'Laura', 'Anderson', 'lauraanderson@mail.com', '2020-01-10', '');
INSERT INTO cliente ( name, surname, email, create_at, photo)VALUES ( 'James', 'Thomas', 'jamesthomas@mail.com', '2020-01-11', '');
INSERT INTO cliente ( name, surname, email, create_at, photo)VALUES ( 'Emma', 'Jackson', 'emmajackson@mail.com', '2020-01-12', '');
INSERT INTO cliente ( name, surname, email, create_at, photo)VALUES ( 'Anthony', 'White', 'anthonywhite@mail.com', '2020-01-13', '');
INSERT INTO cliente ( name, surname, email, create_at, photo)VALUES ( 'Sophia', 'Harris', 'sophiaharris@mail.com', '2020-01-14', '');
INSERT INTO cliente ( name, surname, email, create_at, photo)VALUES ( 'Andrew', 'Martin', 'andrewmartin@mail.com', '2020-01-15', '');
INSERT INTO cliente ( name, surname, email, create_at, photo)VALUES ( 'Olivia', 'Thompson', 'oliviathompson@mail.com', '2020-01-16', '');
INSERT INTO cliente ( name, surname, email, create_at, photo)VALUES ( 'Jacob', 'Garcia', 'jacobgarcia@mail.com', '2020-01-17', '');
INSERT INTO cliente ( name, surname, email, create_at, photo)VALUES ( 'Emily', 'Martinez', 'emilymartinez@mail.com', '2020-01-18', '');
INSERT INTO cliente ( name, surname, email, create_at, photo)VALUES ( 'Joshua', 'Robinson', 'joshuarobinson@mail.com', '2020-01-19', '');
INSERT INTO cliente ( name, surname, email, create_at, photo)VALUES ( 'Ava', 'Clark', 'avaclark@mail.com', '2020-01-20', '');
INSERT INTO cliente ( name, surname, email, create_at, photo)VALUES ( 'Christopher', 'Rodriguez', 'christopherrodriguez@mail.com', '2020-01-21', '');
INSERT INTO cliente ( name, surname, email, create_at, photo)VALUES ( 'Madison', 'Lewis', 'madisonlewis@mail.com', '2020-01-22', '');
INSERT INTO cliente ( name, surname, email, create_at, photo)VALUES ( 'Ryan', 'Lee', 'ryanlee@mail.com', '2020-01-23', '');
INSERT INTO cliente ( name, surname, email, create_at, photo)VALUES ( 'Isabella', 'Walker', 'isabellawalker@mail.com', '2020-01-24', '');
INSERT INTO cliente ( name, surname, email, create_at, photo)VALUES ( 'Matthew', 'Hall', 'matthewhall@mail.com', '2020-01-25', '');
INSERT INTO cliente ( name, surname, email, create_at, photo)VALUES ( 'Mia', 'Allen', 'miaallen@mail.com', '2020-01-26', '');

INSERT INTO producto ( nombre, precio, create_at)VALUES ( 'Panasonic Pantalla LCD', 1000, NOW());
INSERT INTO producto ( nombre, precio, create_at)VALUES ( 'Sony Camara Digital', 2000, NOW());
INSERT INTO producto ( nombre, precio, create_at)VALUES ( 'Apple iPod', 3000, NOW());
INSERT INTO producto ( nombre, precio, create_at)VALUES ( 'Sony Notebook', 4000, NOW());
INSERT INTO producto ( nombre, precio, create_at)VALUES ( 'Hewlett Packard Multifuncional', 5000, NOW());
INSERT INTO producto ( nombre, precio, create_at)VALUES ( 'Mueble de Oficina', 6000, NOW());
INSERT INTO producto ( nombre, precio, create_at)VALUES ( 'Mueble de Comedor', 7000, NOW());



INSERT INTO factura (descripcion, observacion, cliente_id, create_at)VALUES ('Factura de prueba 1', 'Primera factura', 1, NOW());
INSERT INTO item_factura (producto_id, cantidad, factura_id)VALUES (1, 2, 1);
INSERT INTO item_factura (producto_id, cantidad, factura_id)VALUES (2, 3, 1);
INSERT INTO item_factura (producto_id, cantidad, factura_id)VALUES (3, 1, 1);
INSERT INTO item_factura (producto_id, cantidad, factura_id)VALUES (5, 7, 1);

INSERT INTO factura (descripcion, observacion, cliente_id, create_at)VALUES ('Factura de prueba 2', 'Sin observaciones',1, NOW());
INSERT INTO item_factura (producto_id, cantidad, factura_id)VALUES (3, 4, 2);
INSERT INTO item_factura (producto_id, cantidad, factura_id)VALUES (4, 5, 2);

