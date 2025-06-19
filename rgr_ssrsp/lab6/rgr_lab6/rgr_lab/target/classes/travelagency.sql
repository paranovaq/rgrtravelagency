DROP DATABASE IF EXISTS travelagency;
CREATE DATABASE travelagency DEFAULT CHARACTER SET utf8;
USE travelagency;

DROP TABLE IF EXISTS tour_guide_tour;
DROP TABLE IF EXISTS tours;
DROP TABLE IF EXISTS tour_guides;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id BIGINT NOT NULL AUTO_INCREMENT,
    username VARCHAR(64) NOT NULL,
    password VARCHAR(64) NOT NULL,
    authority VARCHAR(64) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (username)
) ENGINE=InnoDB;

CREATE TABLE tours (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    type VARCHAR(50) NOT NULL,
    tour_code VARCHAR(20) NOT NULL,
    destination VARCHAR(200) NOT NULL,
    start_date DATE NULL,
    end_date DATE NULL,
    status VARCHAR(50) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (tour_code)
) ENGINE=InnoDB;

CREATE TABLE tour_guides (
    id BIGINT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    specialization VARCHAR(100) NOT NULL,
    languages VARCHAR(255) NULL,
    hire_date DATE NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE tour_guide_tour (
    tour_guide_id BIGINT NOT NULL,
    tour_id BIGINT NOT NULL,
    PRIMARY KEY (tour_guide_id, tour_id),
    FOREIGN KEY (tour_guide_id) REFERENCES tour_guides(id) ON DELETE CASCADE,
    FOREIGN KEY (tour_id) REFERENCES tours(id) ON DELETE CASCADE
) ENGINE=InnoDB;

INSERT INTO users (username, password, authority) VALUES
('admin', '$2a$10$B1eABE/Tohph7ZCx115MhetSqstxRNkaTmWV1c4i6IPoHw8nfbQ3m', 'ROLE_ADMIN'),
('manager', '$2a$10$ZMD54/wxXmWWZs2htVueFO/eQNTa8sw/FbgSSZ9pvuOufrVrHUDNa', 'ROLE_MANAGER'),
('user', '$2a$10$oYTWizWmfwE65Ct5.4rX6ejNvtjdEXpIvVAtkSeitTXhKdKNLfgi.', 'ROLE_USER');

INSERT INTO tours (name, type, tour_code, destination, start_date, end_date, status) VALUES
('Европейское приключение', 'Групповой', 'EUR-001', 'Париж, Рим, Берлин', '2023-07-15', '2023-07-30', 'Доступен'),
('Карибский круиз', 'Круиз', 'CRU-001', 'Багамы, Ямайка', '2023-08-20', '2023-08-29', 'Бронирование'),
('Японский культурный тур', 'Индивидуальный', 'JPN-001', 'Токио, Киото', '2022-09-01', '2022-09-15', 'Завершен');

INSERT INTO tour_guides (first_name, last_name, specialization, languages, hire_date) VALUES
('Джон', 'Смит', 'Исторические туры', 'Английский, Французский', '2020-03-15'),
('Мария', 'Гарсия', 'Приключенческие туры', 'Испанский, Английский', '2021-07-10'),
('Хироши', 'Танака', 'Культурные туры', 'Японский, Английский', '2021-09-14');

INSERT INTO tour_guide_tour (tour_guide_id, tour_id) VALUES
(1, 1),
(1, 2),
(2, 1),
(2, 3),
(3, 2),
(3, 3);