CREATE TABLE department (
                            id   bigserial PRIMARY KEY,
                            name varchar(50) NOT NULL,
                            code varchar(20) NOT NULL
);

-- Tabla ciudad
CREATE TABLE city (
                      id            bigserial PRIMARY KEY,
                      name          varchar(50) NOT NULL,
                      code          varchar(20) NOT NULL,
                      id_department bigint NOT NULL,
                      CONSTRAINT fk_department FOREIGN KEY (id_department) REFERENCES department(id) ON DELETE NO ACTION
);

-- Tabla material
CREATE TABLE material (
                          id            bigserial PRIMARY KEY,
                          name          varchar(50) NOT NULL,
                          description   varchar(100) NOT NULL,
                          type          varchar(50) NOT NULL,
                          price         double precision NOT NULL,
                          date_purchase date NOT NULL,
                          date_sale     date NOT NULL,
                          status        int NOT NULL,
                          id_city       bigint NOT NULL,
                          CONSTRAINT fk_city FOREIGN KEY (id_city) REFERENCES city(id) ON DELETE NO ACTION
);

INSERT INTO department (name, code)
VALUES ('boyaca', '1213ab');

INSERT INTO city (name, code, id_department)
VALUES ('tunja', '12a', 1),
       ('duitama', '13a', 1);

INSERT INTO material (name, description, type, price, date_purchase, date_sale, status, id_city)
VALUES ('hierro', 'material para construccion', 'MINERAL', 111.1, '2024-08-01', '2024-08-02', 'ACTIVO', 1),
       ('cobre', 'material para redes', 'MINERAL', 1231.5, '2024-12-30', '2024-12-31', 'ACTIVO', 2);