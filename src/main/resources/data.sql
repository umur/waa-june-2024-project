insert into majors (id, name, description) value (1, 'Compro', 'IT and Computer Science');
insert into majors (id, name, description) value (2, 'MBA', 'Business Management');

INSERT into feedback_categories(name, description)
VALUES ("University Feedback", "Feedback on University in general");
INSERT into feedback_categories(name, description)
VALUES ("Faculity Feedback", "Feedback to Faculity in general");

INSERT into discussion_categories(name, description)
VALUES ("Life in USA", "Personal experiences sharing being in USA");
INSERT into discussion_categories(name, description)
VALUES ("Persoanl Health", "Importantce of personal health");

-- temp
insert into users(id, username, password, first_name, last_name, email, birth_date, gender_type, account_status,
                  role_type, user_type)
values (1, "student1", "{noop}123", "Student", "1", "student1@gmail.com", null, "MALE", "ACTIVE", "STUDENT", "STUDENT");


-- temp
insert into students(id, student_code, major_id, academic_years, picture)
values (1, "159880", 1, "2024", "1234");

insert into users(id, username, password, first_name, last_name, email, birth_date, gender_type, account_status,
                  role_type, user_type)
values (2, "admin", "{noop}admin", "John", "Doe", "john.doe@miu.edu", "2008-01-28", "MALE", "ACTIVE", "ADMIN", "ADMIN");

INSERT INTO `admins`(id)
VALUES (1);

-- temp
insert into achievements(student_id, achievements)
values (1, "Successfully completed 4 Subject Master Course on Campus");

-- temp
insert into interests(student_id, interest)
values (1, "Coding");
insert into interests(student_id, interest)
values (1, "Swimming");
insert into interests(student_id, interest)
values (1, "Learning New Technology");

-- temp
insert into extra_activities(student_id, extra_activities)
values (1, "Social Party");

-- temp
INSERT INTO events (name, description, event_date, event_time)
VALUES
('Harriet"s Birthday', 'Come have fun and celebrate.', '2024-08-08', '09:00:00'),
('Book Fair', 'Book fair with stalls from various publishers and authors.', '2024-11-12', '14:00:00'),
('Fitness Bootcamp', 'Outdoor fitness bootcamp for all levels.', '2025-03-10', '07:00:00'),
('Coding Workshop', 'Hands-on coding workshop for beginners.', '2025-04-22', '11:00:00');

-- temp
INSERT INTO courses (name, code, major_id) VALUES
('Introduction to Programming', 'CS101', 1),
('Algorithm', 'CS102', 1),
('Business Accounting', 'MBA101', 2),
('Taxation', 'MBA102', 2);