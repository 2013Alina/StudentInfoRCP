CREATE TABLE IF NOT EXISTS Groups(
    id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name VARCHAR(200) UNIQUE
);
CREATE TABLE IF NOT EXISTS Students(
    id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name VARCHAR(200) UNIQUE,
    group_id VARCHAR(200),
    address VARCHAR(200),
    city VARCHAR(200),
    result int,
    image BLOB,
    FOREIGN KEY(group_id) REFERENCES Groups(id)
);


