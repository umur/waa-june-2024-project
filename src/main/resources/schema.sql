-- Full-Text Index for the Post table
CREATE FULLTEXT INDEX idx_content ON Post(content);

-- Full-Text Index for the User table
CREATE FULLTEXT INDEX idx_username_email ON User(username, email);

-- Full-Text Index for the Student table
CREATE FULLTEXT INDEX idx_first_name_last_name ON Student(first_name, last_name);
