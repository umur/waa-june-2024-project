INSERT INTO `action` (id, code, description, name)
VALUES
(1, 'create_user', 'Create System User', 'Create System User'),
(2, 'view_user', 'Read System User', 'Read System User'),
(3, 'modify_user', 'Update System User', 'Update System User'),
(4, 'delete_user', 'Delete System User', 'Delete System User'),
(5, 'view_user_list', 'Read System User List', 'Read System User List'),
(6, 'create_action', 'Create new system actions', 'Create System Action'),
(7, 'view_action', 'View existing system actions', 'Read System Action'),
(8, 'modify_action', 'Modify system action details', 'Update System Action'),
(9, 'delete_action', 'Delete system actions', 'Delete System Action'),
(10, 'view_action_list', 'Read System action List', 'Read System Action List'),
(11, 'create_role', 'Create new system roles', 'Create System Role'),
(12, 'view_role', 'View existing system roles', 'Read System Role'),
(13, 'modify_role', 'Modify system role details', 'Update System Role'),
(14, 'delete_role', 'Delete system roles', 'Delete System Role'),
(15, 'view_role_list', 'Read System role List', 'Read System Action List');


INSERT INTO `action` (code, description, name)
VALUES
-- Category actions
('create_category', 'Create new categories', 'Create Category'),
('view_category', 'View existing categories', 'Read Category'),
('modify_category', 'Modify category details', 'Update Category'),
('delete_category', 'Delete categories', 'Delete Category'),
('view_category_list', 'Read category list', 'Read Category List'),

-- Event actions
('create_event', 'Create new events', 'Create Event'),
('view_event', 'View existing events', 'Read Event'),
('modify_event', 'Modify event details', 'Update Event'),
('delete_event', 'Delete events', 'Delete Event'),
('view_event_list', 'Read event list', 'Read Event List'),

-- Event RSVP actions
('create_event_rsvp', 'Create new event RSVPs', 'Create Event RSVP'),
('view_event_rsvp', 'View existing event RSVPs', 'Read Event RSVP'),
('modify_event_rsvp', 'Modify event RSVP details', 'Update Event RSVP'),
('delete_event_rsvp', 'Delete event RSVPs', 'Delete Event RSVP'),
('view_event_rsvp_list', 'Read event RSVP list', 'Read Event RSVP List'),

-- Event Attendance actions
('create_event_attendance', 'Create new event attendances', 'Create Event Attendance'),
('view_event_attendance', 'View existing event attendances', 'Read Event Attendance'),
('modify_event_attendance', 'Modify event attendance details', 'Update Event Attendance'),
('delete_event_attendance', 'Delete event attendances', 'Delete Event Attendance'),
('view_event_attendance_list', 'Read event attendance list', 'Read Event Attendance List'),

-- Moderation Report actions
('create_moderation_report', 'Create new moderation reports', 'Create Moderation Report'),
('view_moderation_report', 'View existing moderation reports', 'Read Moderation Report'),
('modify_moderation_report', 'Modify moderation report details', 'Update Moderation Report'),
('delete_moderation_report', 'Delete moderation reports', 'Delete Moderation Report'),
('view_moderation_report_list', 'Read moderation report list', 'Read Moderation Report List'),

-- Post actions
('create_post', 'Create new posts', 'Create Post'),
('view_post', 'View existing posts', 'Read Post'),
('modify_post', 'Modify post details', 'Update Post'),
('delete_post', 'Delete posts', 'Delete Post'),
('view_post_list', 'Read post list', 'Read Post List'),

-- Reply actions
('create_reply', 'Create new replies', 'Create Reply'),
('view_reply', 'View existing replies', 'Read Reply'),
('modify_reply', 'Modify reply details', 'Update Reply'),
('delete_reply', 'Delete replies', 'Delete Reply'),
('view_reply_list', 'Read reply list', 'Read Reply List'),

-- Profile actions
('create_profile', 'Create new profiles', 'Create Profile'),
('view_profile', 'View existing profiles', 'Read Profile'),
('modify_profile', 'Modify profile details', 'Update Profile'),
('delete_profile', 'Delete profiles', 'Delete Profile'),
('view_profile_list', 'Read profile list', 'Read Profile List'),

-- Resource actions
('create_resource', 'Create new resources', 'Create Resource'),
('view_resource', 'View existing resources', 'Read Resource'),
('modify_resource', 'Modify resource details', 'Update Resource'),
('delete_resource', 'Delete resources', 'Delete Resource'),
('view_resource_list', 'Read resource list', 'Read Resource List'),

-- Survey actions
('create_survey', 'Create new surveys', 'Create Survey'),
('view_survey', 'View existing surveys', 'Read Survey'),
('modify_survey', 'Modify survey details', 'Update Survey'),
('delete_survey', 'Delete surveys', 'Delete Survey'),
('view_survey_list', 'Read survey list', 'Read Survey List'),

-- Survey Questionnaire actions
('create_survey_questionnaire', 'Create new survey questionnaires', 'Create Survey Questionnaire'),
('view_survey_questionnaire', 'View existing survey questionnaires', 'Read Survey Questionnaire'),
('modify_survey_questionnaire', 'Modify survey questionnaire details', 'Update Survey Questionnaire'),
('delete_survey_questionnaire', 'Delete survey questionnaires', 'Delete Survey Questionnaire'),
('view_survey_questionnaire_list', 'Read survey questionnaire list', 'Read Survey Questionnaire List'),

-- Survey Response actions
('create_survey_response', 'Create new survey responses', 'Create Survey Response'),
('view_survey_response', 'View existing survey responses', 'Read Survey Response'),
('modify_survey_response', 'Modify survey response details', 'Update Survey Response'),
('delete_survey_response', 'Delete survey responses', 'Delete Survey Response'),
('view_survey_response_list', 'Read survey response list', 'Read Survey Response List'),

-- Student actions
('create_student', 'Create new students', 'Create Student'),
('view_student', 'View existing students', 'Read Student'),
('modify_student', 'Modify student details', 'Update Student'),
('delete_student', 'Delete students', 'Delete Student'),
('view_student_list', 'Read student list', 'Read Student List');

INSERT INTO `role` (actions, code, description, name)
VALUES
('["create_user", "view_user", "modify_user", "delete_user", "view_user_list", "create_action", "view_action", "modify_action", "delete_action", "view_action_list", "create_role", "view_role", "modify_role", "delete_role", "view_role_list", "create_category", "view_category", "modify_category", "delete_category", "view_category_list", "create_event", "view_event", "modify_event", "delete_event", "view_event_list", "create_event_rsvp", "view_event_rsvp", "modify_event_rsvp", "delete_event_rsvp", "view_event_rsvp_list", "create_event_attendance", "view_event_attendance", "modify_event_attendance", "delete_event_attendance", "view_event_attendance_list", "create_moderation_report", "view_moderation_report", "modify_moderation_report", "delete_moderation_report", "view_moderation_report_list", "create_post", "view_post", "modify_post", "delete_post", "view_post_list", "create_reply", "view_reply", "modify_reply", "delete_reply", "view_reply_list", "create_profile", "view_profile", "modify_profile", "delete_profile", "view_profile_list", "create_resource", "view_resource", "modify_resource", "delete_resource", "view_resource_list", "create_survey", "view_survey", "modify_survey", "delete_survey", "view_survey_list", "create_survey_questionnaire", "view_survey_questionnaire", "modify_survey_questionnaire", "delete_survey_questionnaire", "view_survey_questionnaire_list", "create_survey_response", "view_survey_response", "modify_survey_response", "delete_survey_response", "view_survey_response_list", "create_student", "view_student", "modify_student", "delete_student", "view_student_list"]', 'superuser', 'SuperUser', 'SuperUser'),
('["create_user", "view_user", "modify_user", "delete_user", "view_user_list", "create_event", "view_event", "modify_event", "delete_event", "view_event_list", "create_event_rsvp", "view_event_rsvp", "modify_event_rsvp", "delete_event_rsvp", "view_event_rsvp_list", "create_event_attendance", "view_event_attendance", "modify_event_attendance", "delete_event_attendance", "view_event_attendance_list", "create_moderation_report","create_post", "view_post", "modify_post", "delete_post", "view_post_list", "create_reply", "view_reply", "modify_reply", "delete_reply", "view_reply_list", "create_profile", "view_profile", "modify_profile", "create_resource", "view_resource", "modify_resource", "delete_resource", "view_resource_list", "create_survey", "view_survey", "modify_survey", "delete_survey", "view_survey_list", "create_survey_questionnaire", "view_survey_questionnaire", "modify_survey_questionnaire", "delete_survey_questionnaire", "view_survey_questionnaire_list", "create_survey_response", "view_survey_response", "modify_survey_response", "delete_survey_response", "view_survey_response_list"]', 'normal-user', 'Normal User', 'Normal User');


INSERT INTO `user` (id, created_at, created_by, last_modified_at, last_modified_by, email, enabled, password, username)
VALUES
(1, NULL, NULL, NULL, NULL, 'kushraj1204@gmail.com', 1, '$2a$10$zrX76Bn6VTIiJLbs2UNZzuo2CptsqFM6Lwm2vRvlncPBqB7ExsGZa', 'kush'),
(1, NULL, NULL, NULL, NULL, 'johndoe@example.com', 1, '$2a$10$zrX76Bn6VTIiJLbs2UNZzuo2CptsqFM6Lwm2vRvlncPBqB7ExsGZa', 'jdoe');


INSERT INTO `category` (id, title)
VALUES
(1, 'Career'),
(2, 'Campus Services'),
(3, 'General'),
(4, 'Recreation'),
(5, 'Directory'),
(6, 'Student Feeds'),
(7, 'Health'),
(8, 'Volunteer');


INSERT INTO `post` (category_id, content, user_id)
VALUES (1, '', 1);


INSERT INTO `reply` (content, post_id, user_id)
VALUES ('', 1, 1);
