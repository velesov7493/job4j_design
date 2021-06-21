DROP TABLE IF EXISTS tz_persons;
DROP TABLE IF EXISTS tz_addresses;

CREATE TABLE tz_addresses (
    id BIGSERIAL PRIMARY KEY,
    aCountry VARCHAR(120) NOT NULL,
    aState VARCHAR(120) NOT NULL,
    aCity VARCHAR(120) NOT NULL,
    aStreet VARCHAR(120) NOT NULL,
    aBuilding VARCHAR(12) NOT NULL,
    aApartment VARCHAR(10),
    aPostalCode INTEGER NOT NULL
);

CREATE TABLE tz_persons (
    id BIGSERIAL PRIMARY KEY,
    id_address BIGINT UNIQUE REFERENCES tz_addresses (id) ON DELETE SET NULL,
    pName VARCHAR(120) NOT NULL,
    pPhone VARCHAR(20)
);

