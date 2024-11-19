-- Таблица владельца автомобиля
CREATE TABLE owner
(
    owner_id    SERIAL PRIMARY KEY NOT NULL,
    last_name   VARCHAR(50)        NOT NULL,
    first_name  VARCHAR(50)        NOT NULL,
    middle_name VARCHAR(50),
    phone       VARCHAR(25)        NOT NULL
);

-- Таблица автомобиля
CREATE TABLE car
(
    car_id       SERIAL PRIMARY KEY NOT NULL,
    vin_number   VARCHAR(17) UNIQUE NOT NULL,
    brand        VARCHAR(50)        NOT NULL,
    release_date DATE               NOT NULL,
    owner_id     INT                NOT NULL,
    FOREIGN KEY (owner_id) REFERENCES owner (owner_id)
);

-- Таблица запросов
CREATE TABLE request
(
    request_id      SERIAL PRIMARY KEY NOT NULL,
    request_date    DATE               NOT NULL,
    description     TEXT               NOT NULL,
    repair          TEXT               NOT NULL,
    resolution_date DATE               NOT NULL,
    car_id          INT                NOT NULL,
    owner_id        INT                NOT NULL,
    FOREIGN KEY (car_id) REFERENCES car (car_id),
    FOREIGN KEY (owner_id) REFERENCES owner (owner_id)
);

-- Таблица запчастей на заказ
CREATE TABLE spare_parts
(
    part_id     SERIAL PRIMARY KEY NOT NULL,
    part_name   VARCHAR(50)        NOT NULL,
    part_number VARCHAR(25)        NOT NULL
);

-- Таблица для запросов и запчастей
CREATE TABLE spare_request
(
    request_id INT NOT NULL,
    part_id    INT NOT NULL,
    FOREIGN KEY (request_id) REFERENCES request (request_id),
    FOREIGN KEY (part_id) REFERENCES spare_parts (part_id)
);

-- Таблица сотрудника
CREATE TABLE employee
(
    employee_id SERIAL PRIMARY KEY NOT NULL,
    last_name   VARCHAR(50)        NOT NULL,
    first_name  VARCHAR(50)        NOT NULL,
    middle_name VARCHAR(50),
    address     TEXT               NOT NULL,
    phone       VARCHAR(25)        NOT NULL
);

-- Таблица для запроса и сотрудника
CREATE TABLE request_employee
(
    request_id  INT NOT NULL,
    employee_id INT NOT NULL,
    FOREIGN KEY (request_id) REFERENCES request (request_id),
    FOREIGN KEY (employee_id) REFERENCES employee (employee_id)
);

-- Таблица должностей
CREATE TABLE position
(
    position_id   SERIAL PRIMARY KEY NOT NULL,
    position_name VARCHAR(50)        NOT NULL
);

-- Таблица информации сотрудника
CREATE TABLE employee_info
(
    employee_info_id SERIAL PRIMARY KEY NOT NULL,
    salary           INT                NOT NULL,
    experience       INT                NOT NULL,
    work_schedule    VARCHAR(50),
    employee_id      INT                NOT NULL,
    position_id      INT                NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES employee (employee_id),
    FOREIGN KEY (position_id) REFERENCES position (position_id)
);
