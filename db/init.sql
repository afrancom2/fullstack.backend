CREATE TABLE department (
                            id   bigserial PRIMARY KEY,
                            name varchar(50) NOT NULL,
                            code varchar(20) NOT NULL
);

CREATE TABLE app_user (
    id bigserial PRIMARY KEY,
    username varchar(100) NOT NULL,
    password varchar(200) NOT NULL,
    role varchar(50) NOT NULL
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
                          status        varchar(50) NOT NULL,
                          id_city       bigint NOT NULL,
                          CONSTRAINT fk_city FOREIGN KEY (id_city) REFERENCES city(id) ON DELETE NO ACTION
);

INSERT INTO app_user (id, username, password, role)
VALUES (1, '123456', '$2a$12$tDg6bg1MP2k6KyWxqRmWT.4Q3ApzZImUvmmfrtY34O2hBdt2MrSzq', 'ADMIN');


INSERT INTO department (name, code)
VALUES
    ('Cundinamarca', 'CUN001'),
    ('Antioquia', 'ANT002'),
    ('Valle del Cauca', 'VAL003'),
    ('Santander', 'SAN004');

INSERT INTO city (name, code, id_department)
VALUES
    ('Bogotá', 'BOG001', 1),
    ('Medellín', 'MED001', 2),
    ('Cali', 'CAL001', 3),
    ('Bucaramanga', 'BUC001', 4);

INSERT INTO material (name, description, type, price, date_purchase, date_sale, status, id_city)
VALUES
    ('Acero', 'material estructural', 'CONSTRUCCION', 2500.0, '2024-07-15', '2024-07-20', 'DISPONIBLE', 3),
    ('Aluminio', 'ligero y resistente', 'MECANICO', 1850.5, '2024-09-01', '2024-09-10', 'ASIGNADO', 2),
    ('Plomo', 'resistente a la corrosión', 'MINERAL', 980.75, '2024-10-05', '2024-10-12', 'ACTIVO', 4),
    ('Cable eléctrico', 'para instalaciones', 'ELECTRICO', 340.0, '2024-11-01', '2024-11-02', 'DISPONIBLE', 1),
    ('PVC', 'material plástico para tuberías', 'PLASTICO', 400.0, '2024-12-01', '2024-12-03', 'ASIGNADO', 4);
