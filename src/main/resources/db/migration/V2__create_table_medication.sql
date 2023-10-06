CREATE TABLE IF NOT EXISTS medication
(
    id            SERIAL PRIMARY KEY,
    description   VARCHAR                                                   NOT NULL,
    dosage        VARCHAR                                                   NOT NULL,
    unit          VARCHAR CHECK (unit IN ('Grams', 'Milligrams', 'Tablet')) NOT NULL,
    time_to_take  TIME                                                      NOT NULL,
    creation_date DATE                                                      NOT NULL,
    modify_date   DATE                                                      NOT NULL,
    patient_id    INT REFERENCES patient(id)                                NOT NULL
);