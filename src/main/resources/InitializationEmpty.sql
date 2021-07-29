INSERT INTO `roles` VALUES (1,'ROLE_ADMIN','ACTIVE',0),(2,'ROLE_CLASSMASTER','ACTIVE',0),(3,'ROLE_TEACHER','ACTIVE',0),(4,'ROLE_STUDENT','ACTIVE',0),(5,'ROLE_PARENT','ACTIVE',0);
INSERT INTO `addresses` VALUES (200,'unknown','Sremska Mitrovica','51','ACTIVE','Cara Dusana ',0);
INSERT INTO `persons` VALUES (300,'1971-10-13','Andjelko','2405014769437','Smoljan','064/886-969','ACTIVE',0,200);
INSERT INTO `users` VALUES (400,'$2a$10$jgw8uiTl3yQgXlw6yOJEg.vhFFvOcki2wgTTNC9/NZ4SfSj8CGZGW','ACTIVE','admin',0,300,1);
INSERT INTO `admins` VALUES ('testemail@gmail.com',400);
# Hibernate sequence fixup
UPDATE hibernate_sequence SET next_val = 2000;