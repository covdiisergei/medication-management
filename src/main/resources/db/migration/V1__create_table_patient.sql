CREATE TABLE IF NOT EXISTS patient (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR NOT NULL,
    last_name VARCHAR NOT NULL,
    gender VARCHAR CHECK (gender IN('Male', 'Female', 'Divers')) NOT NULL,
    date_of_birth DATE NOT NULL,
    creation_date DATE NOT NULL ,
    modify_date DATE NOT NULL
);