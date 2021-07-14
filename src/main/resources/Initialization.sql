# Initialization of created database with values

# Adding roles to database
# Enumeration names match ERole
INSERT INTO role (`id`, `role_name`, `status`, `version`) VALUES ('3', 'ROLE_ADMIN', 'ACTIVE', '0');
INSERT INTO role (`id`, `role_name`, `status`, `version`) VALUES ('4', 'ROLE_CLASSMASTER', 'ACTIVE', '0');
INSERT INTO role (`id`, `role_name`, `status`, `version`) VALUES ('5', 'ROLE_TEACHER', 'ACTIVE', '0');
INSERT INTO role (`id`, `role_name`, `status`, `version`) VALUES ('6', 'ROLE_STUDENT', 'ACTIVE', '0');
INSERT INTO role (`id`, `role_name`, `status`, `version`) VALUES ('7', 'ROLE_PARENT', 'ACTIVE', '0');

# Adding admin
INSERT INTO identity (`id`, `firstname`, `jmbg`, `lastname`, `status`, `version`) VALUES ('1', 'admin', '0000000000000', 'admin', 'ACTIVE', '0');
INSERT INTO user (`id`, `password`, `status`, `username`, `version`, `identity`, `role`) VALUES ('2', '$2a$10$jgw8uiTl3yQgXlw6yOJEg.vhFFvOcki2wgTTNC9/NZ4SfSj8CGZGW', 'ACTIVE', 'admin', '0', '1', '3');
INSERT INTO `admin` (`id`) VALUES ('2');

# Hibernate sequence fixup
UPDATE hibernate_sequence SET next_val = 8;