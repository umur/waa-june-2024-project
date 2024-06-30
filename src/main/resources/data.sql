CREATE DATABASE IF NOT EXISTS `project` /*!40100 DEFAULT CHARACTER SET utf8 */;
INSERT INTO user (id,email, first_name,last_name,password,username,account_non_expired,account_non_locked,credentials_non_expired,enabled)
VALUES (1,"XYZ@gmail.com", "Mark","Jaen","aa12","mark",0,0,0,0);

INSERT INTO category (id,name, description)
VALUES (1,"Sell and Buy", "Sell and Buy");