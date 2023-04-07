INSERT INTO `pizzas` (`description`, `name`, `price`) VALUES ( 'Pomodoro, mozzarella', 'Pizza Margherita', 5.00);
INSERT INTO `pizzas` (`description`, `name`, `price`) VALUES ( 'Pomodoro, mozzarella, capperi, acciughe', 'Pizza Romana', 6.00);
INSERT INTO `pizzas` (`description`, `name`, `price`) VALUES ( 'Pomodoro, frutti di mare', 'Pizza Frutti di mare', 7.50);
INSERT INTO `pizzas` (`description`, `name`, `price`) VALUES ( 'Pomodoro, mozzarella, patatine fritte', 'Pizza Patatine', 5.50);

INSERT INTO sales (title, start_date, expire_date, pizza_id) VALUES('offerta 1', '2023-01-01', '2023-02-01', 1);
INSERT INTO sales (title, start_date, expire_date, pizza_id) VALUES('offerta 2','2023-03-01', '2023-04-01', 1);
INSERT INTO sales (title, start_date, expire_date, pizza_id) VALUES('offerta 3','2023-01-01', '2023-02-01', 2);
INSERT INTO sales (title, start_date, expire_date, pizza_id) VALUES('offerta 4','2023-01-01', '2023-02-01', 3);

INSERT INTO categories (description, name) VALUES('Pomodoro', 'Pomodoro');
INSERT INTO categories (description, name) VALUES('Mozzarella', 'Mozzarella');
INSERT INTO categories (description, name) VALUES('Sale', 'Sale');
INSERT INTO categories (description, name) VALUES('Acciughe', 'Acciughe');
INSERT INTO categories (description, name) VALUES('Melanzane', 'Melanzane');
INSERT INTO categories (description, name) VALUES('Zucchine', 'Zucchine');
INSERT INTO categories (description, name) VALUES('Tonno', 'Tonno');
INSERT INTO categories (description, name) VALUES('Capperi', 'Capperi');

INSERT INTO users (email, first_name, last_name, password) VALUES('john@email.it', 'John', 'Doe', '{noop}john');
INSERT INTO users (email, first_name, last_name, password) VALUES('jane@email.it', 'Jane', 'Smith','{noop}jane');

INSERT INTO roles (id, name) VALUES(1, 'ADMIN');
INSERT INTO roles (id, name) VALUES(2, 'USER');

INSERT into users_roles(user_id, roles_id) VALUES(1, 1);
INSERT into users_roles(user_id, roles_id) VALUES(2, 2);