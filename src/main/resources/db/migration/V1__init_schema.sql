CREATE TABLE users (
    id INT auto_increment NOT NULL,
    username varchar(100) UNIQUE NOT NULL,
    password varchar(100) NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE test.tasks (
    id INT auto_increment NOT NULL,
    userId INT NOT NULL,
    title varchar(100) NOT NULL,
    description varchar(100) NULL,
    dueDate date NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (userId) REFERENCES users(id)
);

INSERT INTO users VALUES (1, "admin", "{bcrypt}$2a$12$rR6fImOTl1EjDKEn6kjEzucQkfG3K2oR3zT2nJKuHT6bmKIH7nSK6")