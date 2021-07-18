# Initialization of created database with values

# Roles
INSERT INTO role (`id`, `role`, `status`, `version`) VALUES ('1', 'ROLE_ADMIN', 'ACTIVE', '0');
INSERT INTO role (`id`, `role`, `status`, `version`) VALUES ('2', 'ROLE_CLASSMASTER', 'ACTIVE', '0');
INSERT INTO role (`id`, `role`, `status`, `version`) VALUES ('3', 'ROLE_TEACHER', 'ACTIVE', '0');
INSERT INTO role (`id`, `role`, `status`, `version`) VALUES ('4', 'ROLE_STUDENT', 'ACTIVE', '0');
INSERT INTO role (`id`, `role`, `status`, `version`) VALUES ('5', 'ROLE_PARENT', 'ACTIVE', '0');

# Addresses
INSERT INTO address (`id`, `apartment`, `city`, `number`, `status`, `street`, `version`) VALUES ('10', 'unknown', 'unknown', 'unknown', 'ACTIVE', 'unknown', '0');
INSERT INTO address (`id`, `apartment`, `city`, `number`, `status`, `street`, `version`) VALUES ('11', '50', 'Novi Sad', '100', 'ACTIVE', 'Balkanska', '0');
INSERT INTO address (`id`, `apartment`, `city`, `number`, `status`, `street`, `version`) VALUES ('12', '12a', 'Beograd', '25A', 'ACTIVE', 'Kralja Petra', '0');
INSERT INTO address (`id`, `apartment`, `city`, `number`, `status`, `street`, `version`) VALUES ('13', 'II', 'Beograd', '26', 'ACTIVE', 'Kosmajska', '0');
INSERT INTO address (`id`, `apartment`, `city`, `number`, `status`, `street`, `version`) VALUES ('14', 'A', 'Kikinda', '27', 'ACTIVE', 'Novosadska', '0');
INSERT INTO address (`id`, `apartment`, `city`, `number`, `status`, `street`, `version`) VALUES ('15', '32', 'Zrenjanin', '2', 'ACTIVE', 'Bulevarska', '0');

# Persons
INSERT INTO personality (`id`, `birthdate`, `firstname`, `jmbg`, `lastname`, `mphone`, `status`, `version`, `address`) VALUES ('20', '1900-01-01', 'admin', '0000000000000', 'admin', '+000000000', 'ACTIVE', '0', '10');
INSERT INTO personality (`id`, `birthdate`, `firstname`, `jmbg`, `lastname`, `mphone`, `status`, `version`, `address`) VALUES ('21', '1980-05-06', 'Petar', '0605980123456', 'Markovic', '+063632458', 'ACTIVE', '0', '11');
INSERT INTO personality (`id`, `birthdate`, `firstname`, `jmbg`, `lastname`, `mphone`, `status`, `version`, `address`) VALUES ('22', '1990-12-12', 'Jovan', '1212990878556', 'Petrovic', '+063789789', 'ACTIVE', '0', '11');
INSERT INTO personality (`id`, `birthdate`, `firstname`, `jmbg`, `lastname`, `mphone`, `status`, `version`, `address`) VALUES ('23', '2000-07-10', 'Ivan',  '1007000323658', 'Prpara', '+064456456', 'ACTIVE', '0', '12');
INSERT INTO personality (`id`, `birthdate`, `firstname`, `jmbg`, `lastname`, `mphone`, `status`, `version`, `address`) VALUES ('24', '1999-11-11', 'Janko', '1111999124578', 'Dindo', '+063635789', 'ACTIVE', '0', '13');
INSERT INTO personality (`id`, `birthdate`, `firstname`, `jmbg`, `lastname`, `mphone`, `status`, `version`, `address`) VALUES ('25', '2005-09-01', 'Marko', '0109005987413', 'Kostic', '+063333333', 'ACTIVE', '0', '14');
INSERT INTO personality (`id`, `birthdate`, `firstname`, `jmbg`, `lastname`, `mphone`, `status`, `version`, `address`) VALUES ('26', '2005-09-01', 'Kiki', '0109005987888', 'Jokic', '+063233233', 'ACTIVE', '0', '14');

# Users
INSERT INTO user (`id`, `password`, `status`, `username`, `version`, `personality`, `role`) VALUES ('30', '$2a$10$jgw8uiTl3yQgXlw6yOJEg.vhFFvOcki2wgTTNC9/NZ4SfSj8CGZGW', 'ACTIVE', 'admin', '0', '20', '1');
INSERT INTO user (`id`, `password`, `status`, `username`, `version`, `personality`, `role`) VALUES ('31', '$2a$10$jgw8uiTl3yQgXlw6yOJEg.vhFFvOcki2wgTTNC9/NZ4SfSj8CGZGW', 'ACTIVE', 'pera', '0', '21', '2');
INSERT INTO user (`id`, `password`, `status`, `username`, `version`, `personality`, `role`) VALUES ('32', '$2a$10$jgw8uiTl3yQgXlw6yOJEg.vhFFvOcki2wgTTNC9/NZ4SfSj8CGZGW', 'ACTIVE', 'yole', '0', '22', '3');
INSERT INTO user (`id`, `password`, `status`, `username`, `version`, `personality`, `role`) VALUES ('33', '$2a$10$jgw8uiTl3yQgXlw6yOJEg.vhFFvOcki2wgTTNC9/NZ4SfSj8CGZGW', 'ACTIVE', 'miki', '0', '23', '4');
INSERT INTO user (`id`, `password`, `status`, `username`, `version`, `personality`, `role`) VALUES ('34', '$2a$10$jgw8uiTl3yQgXlw6yOJEg.vhFFvOcki2wgTTNC9/NZ4SfSj8CGZGW', 'ACTIVE', 'sole', '0', '24', '5');
INSERT INTO user (`id`, `password`, `status`, `username`, `version`, `personality`, `role`) VALUES ('35', '$2a$10$jgw8uiTl3yQgXlw6yOJEg.vhFFvOcki2wgTTNC9/NZ4SfSj8CGZGW', 'ACTIVE', 'xxxx', '0', '20', '3');
INSERT INTO user (`id`, `password`, `status`, `username`, `version`, `personality`, `role`) VALUES ('36', '$2a$10$jgw8uiTl3yQgXlw6yOJEg.vhFFvOcki2wgTTNC9/NZ4SfSj8CGZGW', 'ACTIVE', 'nnn', '0', '25', '4');
INSERT INTO user (`id`, `password`, `status`, `username`, `version`, `personality`, `role`) VALUES ('37', '$2a$10$jgw8uiTl3yQgXlw6yOJEg.vhFFvOcki2wgTTNC9/NZ4SfSj8CGZGW', 'ACTIVE', 'mamaitata', '0', '26', '5');

# Specializations
INSERT INTO `admin` (`id`) VALUES ('30');
INSERT INTO `teacher` (`id`) VALUES ('31');
INSERT INTO `teacher` (`id`) VALUES ('32');
INSERT INTO `teacher` (`id`) VALUES ('35');
INSERT INTO `student` (`id`) VALUES ('33');
INSERT INTO `student` (`id`) VALUES ('36');
# Parent
INSERT INTO `parent` (`email`, `id`) VALUES ('email@gmail.com', '34');
INSERT INTO `parent` (`email`, `id`) VALUES ('xxx@gmail.com', '37');

# Parent-Strudent
INSERT INTO student_parent (`id`, `parent`, `student`) VALUES ('40', '34', '36');

# Hibernate sequence fixup
UPDATE hibernate_sequence SET next_val = 50;