INSERT INTO `action`
(id, created_at, created_by, last_modified_at, last_modified_by, code, description, name)
VALUES(1, NULL, NULL, NULL, NULL, 'create_user', 'Create System User', 'Create System User');
INSERT INTO `action`
(id, created_at, created_by, last_modified_at, last_modified_by, code, description, name)
VALUES(2, NULL, NULL, NULL, NULL, 'view_user', 'Read System User', 'Read System User');
INSERT INTO `action`
(id, created_at, created_by, last_modified_at, last_modified_by, code, description, name)
VALUES(3, NULL, NULL, NULL, NULL, 'modify_user', 'Update System User', 'Update System User');
INSERT INTO `action`
(id, created_at, created_by, last_modified_at, last_modified_by, code, description, name)
VALUES(4, NULL, NULL, NULL, NULL, 'delete_user', 'Delete System User', 'Delete System User');
INSERT INTO `action`
(id, created_at, created_by, last_modified_at, last_modified_by, code, description, name)
VALUES(5, NULL, NULL, NULL, NULL, 'view_user_list', 'Read System User List', 'Read System User List');
INSERT INTO `action`
(id, created_at, created_by, last_modified_at, last_modified_by, code, description, name)
VALUES(6, NULL, NULL, NULL, NULL, 'create_action', 'Create new system actions', 'Create System Action');
INSERT INTO `action`
(id, created_at, created_by, last_modified_at, last_modified_by, code, description, name)
VALUES(7, NULL, NULL, NULL, NULL, 'view_action', 'View existing system actions', 'Read System Action');
INSERT INTO `action`
(id, created_at, created_by, last_modified_at, last_modified_by, code, description, name)
VALUES(8, NULL, NULL, NULL, NULL, 'modify_action', 'Modify system action details', 'Update System Action');
INSERT INTO `action`
(id, created_at, created_by, last_modified_at, last_modified_by, code, description, name)
VALUES(9, NULL, NULL, NULL, NULL, 'delete_action', 'Delete system actions', 'Delete System Action');
INSERT INTO `action`
(id, created_at, created_by, last_modified_at, last_modified_by, code, description, name)
VALUES(10, NULL, NULL, NULL, NULL, 'view_action_list', 'Read System action List', 'Read System Action List');
INSERT INTO `action`
(id, created_at, created_by, last_modified_at, last_modified_by, code, description, name)
VALUES(11, NULL, NULL, NULL, NULL, 'create_role', 'Create new system roles', 'Create System Role');
INSERT INTO `action`
(id, created_at, created_by, last_modified_at, last_modified_by, code, description, name)
VALUES(12, NULL, NULL, NULL, NULL, 'view_role', 'View existing system roles', 'Read System Role');
INSERT INTO `action`
(id, created_at, created_by, last_modified_at, last_modified_by, code, description, name)
VALUES(13, NULL, NULL, NULL, NULL, 'modify_role', 'Modify system role details', 'Update System Role');
INSERT INTO `action`
(id, created_at, created_by, last_modified_at, last_modified_by, code, description, name)
VALUES(14, NULL, NULL, NULL, NULL, 'delete_role', 'Delete system roles', 'Delete System Role');
INSERT INTO `action`
(id, created_at, created_by, last_modified_at, last_modified_by, code, description, name)
VALUES(15, NULL, NULL, NULL, NULL, 'view_role_list', 'Read System role List', 'Read System Action List');

INSERT INTO `role`
(id, created_at, created_by, last_modified_at, last_modified_by, actions, code, description, name)
VALUES(1, NULL, NULL, NULL, NULL, '["view_user_list", "view_action", "modify_user", "create_role", "delete_role", "modify_action", "view_role", "modify_role", "view_role_list", "create_action", "delete_action", "view_user", "create_user", "delete_user", "view_action_list"]', 'superuser', 'SuperUser', 'SuperUser');

INSERT INTO `user`
(id, created_at, created_by, last_modified_at, last_modified_by, email, enabled, password, username)
VALUES(1, NULL, NULL, NULL, NULL, 'kushraj1204@gmail.com', 1, '$2a$10$zrX76Bn6VTIiJLbs2UNZzuo2CptsqFM6Lwm2vRvlncPBqB7ExsGZa', 'kush');