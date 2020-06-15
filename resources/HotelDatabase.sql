CREATE DATABASE hotel;
USE hotel;

CREATE TABLE users(id BIGINT AUTO_INCREMENT, first_name VARCHAR(50) NOT NULL, last_name VARCHAR(50) NOT NULL, birthday DATE NOT NULL, email VARCHAR(50) NOT NULL UNIQUE, password VARCHAR(50) NOT NULL UNIQUE, role VARCHAR(10) NOT NULL, PRIMARY KEY(id));
CREATE TABLE additional_conveniences(id BIGINT AUTO_INCREMENT, title VARCHAR(50) NOT NULL UNIQUE, cost INT NOT NULL, PRIMARY KEY(id));
CREATE TABLE room_types(id BIGINT AUTO_INCREMENT, room_title VARCHAR(50) NOT NULL UNIQUE, cost_for_one_night INT NOT NULL, PRIMARY KEY(id));
CREATE TABLE rooms(id BIGINT AUTO_INCREMENT, number_of_places INT NOT NULL, room_type_id BIGINT NOT NULL, PRIMARY KEY(id), FOREIGN KEY (room_type_id) REFERENCES room_types (id));
CREATE TABLE reservations(id BIGINT AUTO_INCREMENT, room_id BIGINT NOT NULL, day_of_arrival DATE NOT NULL, day_of_departure DATE NOT NULL, user_id BIGINT NOT NULL, PRIMARY KEY(id), FOREIGN KEY (user_id) REFERENCES users(id), FOREIGN KEY (room_id) REFERENCES rooms(id));
CREATE TABLE reservations_conveniences(convenience_id BIGINT NOT NULL, reservation_id BIGINT NOT NULL, PRIMARY KEY (reservation_id, convenience_id), FOREIGN KEY (convenience_id) REFERENCES additional_conveniences (id), FOREIGN KEY (reservation_id) REFERENCES reservations (id));

INSERT INTO users (first_name, last_name, birthday, email, password, role) VALUES ('John', 'Doy', '1990-01-01', 'johndoy@gmail.com', 'password', 'ADMIN');

INSERT INTO room_types (room_title, cost_for_one_night) VALUES ('lux', 100);
INSERT INTO room_types (room_title, cost_for_one_night) VALUES ('standard', 50);
INSERT INTO room_types (room_title, cost_for_one_night) VALUES ('economy', 25);

