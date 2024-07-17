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
values (1, "student1", "{noop}123", "Allen", "Walker", "allen.walker@gmail.com", "2008-01-28", "MALE", "ACTIVE",
        "STUDENT",
        "STUDENT");


-- temp
insert into students(id, student_code, major_id, academic_years, picture)
values (1, "159880", 1, "2024", "/uploads/defaults/profile_20240705.png");

insert into users(id, username, password, first_name, last_name, email, birth_date, gender_type, account_status,
                  role_type, user_type)
values (2, "admin", "{noop}admin", "John", "Doe", "john.doe@miu.edu", "2008-01-28", "MALE", "ACTIVE", "ADMIN", "ADMIN");

INSERT INTO admins(id)
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

-- temp
insert into academic_resource_types(id, name)
values (1, "Study Guide");

-- Adding students
INSERT INTO users (id, username, password, first_name, last_name, email, birth_date, gender_type, account_status, role_type, user_type)
VALUES
    (3, 'jdoe', '{noop}123', 'John', 'Doe', 'jdoe@gmail.com', '2001-05-21', 'MALE', 'ACTIVE', 'STUDENT', 'STUDENT'),
    (4, 'asmith', '{noop}123', 'Alice', 'Smith', 'asmith@gmail.com', '2002-06-11', 'FEMALE', 'ACTIVE', 'STUDENT', 'STUDENT'),
    (5, 'bwhite', '{noop}123', 'Bob', 'White', 'bwhite@gmail.com', '2003-07-15', 'MALE', 'ACTIVE', 'STUDENT', 'STUDENT'),
    (6, 'cjohnson', '{noop}123', 'Catherine', 'Johnson', 'cjohnson@gmail.com', '2000-01-12', 'FEMALE', 'ACTIVE', 'STUDENT', 'STUDENT'),
    (7, 'dlee', '{noop}123', 'David', 'Lee', 'dlee@gmail.com', '2001-02-22', 'MALE', 'ACTIVE', 'STUDENT', 'STUDENT'),
    (8, 'eclark', '{noop}123', 'Emily', 'Clark', 'eclark@gmail.com', '1999-03-18', 'FEMALE', 'ACTIVE', 'STUDENT', 'STUDENT'),
    (9, 'fmartin', '{noop}123', 'Frank', 'Martin', 'fmartin@gmail.com', '1998-04-05', 'MALE', 'ACTIVE', 'STUDENT', 'STUDENT'),
    (10, 'gking', '{noop}123', 'Grace', 'King', 'gking@gmail.com', '2002-05-27', 'FEMALE', 'ACTIVE', 'STUDENT', 'STUDENT'),
    (11, 'hrodriguez', '{noop}123', 'Henry', 'Rodriguez', 'hrodriguez@gmail.com', '2001-06-08', 'MALE', 'ACTIVE', 'STUDENT', 'STUDENT'),
    (12, 'irivers', '{noop}123', 'Isabella', 'Rivers', 'irivers@gmail.com', '2000-07-19', 'FEMALE', 'ACTIVE', 'STUDENT', 'STUDENT'),
    (13, 'jkhan', '{noop}123', 'Jack', 'Khan', 'jkhan@gmail.com', '1999-08-30', 'MALE', 'ACTIVE', 'STUDENT', 'STUDENT'),
    (14, 'kjames', '{noop}123', 'Karen', 'James', 'kjames@gmail.com', '2001-09-10', 'FEMALE', 'ACTIVE', 'STUDENT', 'STUDENT'),
    (15, 'lmiller', '{noop}123', 'Lucas', 'Miller', 'lmiller@gmail.com', '2002-10-21', 'MALE', 'ACTIVE', 'STUDENT', 'STUDENT'),
    (16, 'nwilliams', '{noop}123', 'Nancy', 'Williams', 'nwilliams@gmail.com', '2000-11-03', 'FEMALE', 'ACTIVE', 'STUDENT', 'STUDENT'),
    (17, 'osmith', '{noop}123', 'Oliver', 'Smith', 'osmith@gmail.com', '2003-12-14', 'MALE', 'ACTIVE', 'STUDENT', 'STUDENT'),
    (18, 'pparker', '{noop}123', 'Paula', 'Parker', 'pparker@gmail.com', '1998-01-25', 'FEMALE', 'ACTIVE', 'STUDENT', 'STUDENT'),
    (19, 'qcollins', '{noop}123', 'Quincy', 'Collins', 'qcollins@gmail.com', '2001-02-15', 'MALE', 'ACTIVE', 'STUDENT', 'STUDENT'),
    (20, 'rrichardson', '{noop}123', 'Rachel', 'Richardson', 'rrichardson@gmail.com', '1999-03-26', 'FEMALE', 'ACTIVE', 'STUDENT', 'STUDENT'),
    (21, 'sjackson', '{noop}123', 'Samuel', 'Jackson', 'sjackson@gmail.com', '2002-04-16', 'MALE', 'ACTIVE', 'STUDENT', 'STUDENT'),
    (22, 'thall', '{noop}123', 'Tina', 'Hall', 'thall@gmail.com', '2000-05-06', 'FEMALE', 'ACTIVE', 'STUDENT', 'STUDENT');

-- Adding corresponding students data
INSERT INTO students (id, student_code, major_id, academic_years, picture)
VALUES
    (3, '159881', 1, '2024', '/uploads/defaults/profile_20240705.png'),
    (4, '159882', 1, '2024', '/uploads/defaults/profile_20240705.png'),
    (5, '159883', 2, '2024', '/uploads/defaults/profile_20240705.png'),
    (6, '159884', 1, '2024', '/uploads/defaults/profile_20240705.png'),
    (7, '159885', 1, '2024', '/uploads/defaults/profile_20240705.png'),
    (8, '159886', 2, '2024', '/uploads/defaults/profile_20240705.png'),
    (9, '159887', 1, '2024', '/uploads/defaults/profile_20240705.png'),
    (10, '159888', 1, '2024', '/uploads/defaults/profile_20240705.png'),
    (11, '159889', 2, '2024', '/uploads/defaults/profile_20240705.png'),
    (12, '159890', 1, '2024', '/uploads/defaults/profile_20240705.png'),
    (13, '159891', 1, '2024', '/uploads/defaults/profile_20240705.png'),
    (14, '159892', 2, '2024', '/uploads/defaults/profile_20240705.png'),
    (15, '159893', 1, '2024', '/uploads/defaults/profile_20240705.png'),
    (16, '159894', 1, '2024', '/uploads/defaults/profile_20240705.png'),
    (17, '159895', 2, '2024', '/uploads/defaults/profile_20240705.png'),
    (18, '159896', 1, '2024', '/uploads/defaults/profile_20240705.png'),
    (19, '159897', 1, '2024', '/uploads/defaults/profile_20240705.png'),
    (20, '159898', 2, '2024', '/uploads/defaults/profile_20240705.png'),
    (21, '159899', 1, '2024', '/uploads/defaults/profile_20240705.png'),
    (22, '159900', 1, '2024', '/uploads/defaults/profile_20240705.png');

-- Adding interests
INSERT INTO interests (student_id, interest)
VALUES
    (3, 'Photography'), (3, 'Music'), (3, 'Gaming'),
    (4, 'Sports'), (4, 'Reading'), (4, 'Traveling'),
    (5, 'Drawing'), (5, 'Cooking'), (5, 'Yoga'),
    (6, 'Coding'), (6, 'Running'), (6, 'Blogging'),
    (7, 'Swimming'), (7, 'Cycling'), (7, 'Dancing'),
    (8, 'Painting'), (8, 'Hiking'), (8, 'Singing'),
    (9, 'Writing'), (9, 'Gardening'), (9, 'Fishing'),
    (10, 'Photography'), (10, 'Gaming'), (10, 'Traveling'),
    (11, 'Music'), (11, 'Drawing'), (11, 'Cooking'),
    (12, 'Reading'), (12, 'Swimming'), (12, 'Yoga'),
    (13, 'Sports'), (13, 'Blogging'), (13, 'Running'),
    (14, 'Hiking'), (14, 'Cycling'), (14, 'Dancing'),
    (15, 'Painting'), (15, 'Singing'), (15, 'Fishing'),
    (16, 'Gardening'), (16, 'Writing'), (16, 'Blogging'),
    (17, 'Photography'), (17, 'Music'), (17, 'Gaming'),
    (18, 'Sports'), (18, 'Reading'), (18, 'Traveling'),
    (19, 'Drawing'), (19, 'Cooking'), (19, 'Yoga'),
    (20, 'Coding'), (20, 'Running'), (20, 'Swimming'),
    (21, 'Cycling'), (21, 'Dancing'), (21, 'Singing'),
    (22, 'Painting'), (22, 'Hiking'), (22, 'Gardening');

INSERT INTO extra_activities (student_id, extra_activities)
VALUES
    (3, 'Volunteering'), (3, 'Community Service'), (3, 'Chess Club'),
    (4, 'Debate Team'), (4, 'Math Club'), (4, 'Photography Club'),
    (5, 'Dance Club'), (5, 'Science Club'), (5, 'Drama Club'),
    (6, 'Photography Club'), (6, 'Art Club'), (6, 'Music Band'),
    (7, 'Math Club'), (7, 'Robotics Club'), (7, 'Community Service'),
    (8, 'Science Club'), (8, 'Debate Team'), (8, 'Volunteering'),
    (9, 'Drama Club'), (9, 'Art Club'), (9, 'Music Band'),
    (10, 'Robotics Club'), (10, 'Chess Club'), (10, 'Math Club'),
    (11, 'Volunteering'), (11, 'Community Service'), (11, 'Science Club'),
    (12, 'Debate Team'), (12, 'Photography Club'), (12, 'Art Club'),
    (13, 'Math Club'), (13, 'Robotics Club'), (13, 'Music Band'),
    (14, 'Science Club'), (14, 'Drama Club'), (14, 'Community Service'),
    (15, 'Volunteering'), (15, 'Photography Club'), (15, 'Debate Team'),
    (16, 'Art Club'), (16, 'Math Club'), (16, 'Robotics Club'),
    (17, 'Science Club'), (17, 'Chess Club'), (17, 'Music Band'),
    (18, 'Volunteering'), (18, 'Community Service'), (18, 'Art Club'),
    (19, 'Debate Team'), (19, 'Robotics Club'), (19, 'Photography Club'),
    (20, 'Math Club'), (20, 'Science Club'), (20, 'Drama Club'),
    (21, 'Music Band'), (21, 'Volunteering'), (21, 'Community Service'),
    (22, 'Debate Team'), (22, 'Chess Club'), (22, 'Art Club');


INSERT INTO discussions (category_id,id,student_id,body,title) VALUES (1,1,1,'Learning new technologies by doing TM is really effective.','MIU Life');
INSERT INTO discussions (category_id,id,student_id,body,title) VALUES (2,2,1,'MIU provided all the various foods that are good for the healthy.','Health and dinning ');

INSERT INTO discussion_comments (discussion_id,id,parent_comment_id,student_id,comments) VALUES (2,1,NULL,3,'good to hear that!');
INSERT INTO discussion_comments (discussion_id,id,parent_comment_id,student_id,comments) VALUES (2,2,NULL,1,'Agree!');
INSERT INTO discussion_comments (discussion_id,id,parent_comment_id,student_id,comments) VALUES (1,3,NULL,3,'Yes, it is');
INSERT INTO discussion_comments (discussion_id,id,parent_comment_id,student_id,comments) VALUES (1,4,NULL,1,'Agree!');
INSERT INTO discussion_comments (id, parent_comment_id,student_id, comments)
VALUES ('5', '3',4, 'Explain a little bit more please.');
INSERT INTO discussion_comments (id, parent_comment_id, student_id, comments)
VALUES ('6', '3', '5', 'What is the benefit of doing like that?');


