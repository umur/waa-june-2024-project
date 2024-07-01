insert into majors (id,name,description) value (1, 'Compro', 'IT and Computer Science');
insert into majors (id,name,description) value (2, 'MBA', 'Business Management');

INSERT into feedback_categories(name, description) VALUES ("University Feedback", "Feedback on University in general");
INSERT into feedback_categories(name, description) VALUES ("Faculity Feedback", "Feedback to Faculity in general");

INSERT into discussion_categories(name, description) VALUES("Life in USA", "Personal experiences sharing being in USA");
INSERT into discussion_categories(name, description) VALUES("Persoanl Health", "Importantce of personal health");

-- temp
insert into users(id,username,password,first_name,last_name,email,birth_date,gender_type,account_status,role_type)
values (1,"student1","123","Student","1","student1@gmail.com",null,"Male","Active","STUDENT");

-- temp
insert into students(id,student_code,major_id,academic_years,picture) values (1,"159880", 1 , "2024", "1234");

--temp
insert into achievements(student_id,achievements) values (1, "Successfully completed 4 Subject Master Course on Campus");

--temp
insert into interests(student_id,interest) values (1, "Coding");
insert into interests(student_id,interest) values (1, "Swimming");
insert into interests(student_id,interest) values (1, "Learning New Technology");

--temp
insert into extra_activities(student_id,extra_activities) values (1, "Social Party");