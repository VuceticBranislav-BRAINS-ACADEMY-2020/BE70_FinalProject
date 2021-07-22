# Initialization of created database with values

# Roles
INSERT INTO roles (`id`, `rolename`, `status`, `version`) VALUES ('1', 'ROLE_ADMIN', 'ACTIVE', '0');
INSERT INTO roles (`id`, `rolename`, `status`, `version`) VALUES ('2', 'ROLE_CLASSMASTER', 'ACTIVE', '0');
INSERT INTO roles (`id`, `rolename`, `status`, `version`) VALUES ('3', 'ROLE_TEACHER', 'ACTIVE', '0');
INSERT INTO roles (`id`, `rolename`, `status`, `version`) VALUES ('4', 'ROLE_STUDENT', 'ACTIVE', '0');
INSERT INTO roles (`id`, `rolename`, `status`, `version`) VALUES ('5', 'ROLE_PARENT', 'ACTIVE', '0');

# Addresses
INSERT INTO addresses (`id`, `apartment`, `city`, `number`, `status`, `street`, `version`) VALUES ('10', 'unknown', 'unknown', 'unknown', 'ACTIVE', 'unknown', '0');
INSERT INTO addresses (`id`, `apartment`, `city`, `number`, `status`, `street`, `version`) VALUES ('11', '50', 'Novi Sad', '100', 'ACTIVE', 'Balkanska', '0');
INSERT INTO addresses (`id`, `apartment`, `city`, `number`, `status`, `street`, `version`) VALUES ('12', '12a', 'Beograd', '25A', 'ACTIVE', 'Kralja Petra', '0');
INSERT INTO addresses (`id`, `apartment`, `city`, `number`, `status`, `street`, `version`) VALUES ('13', 'II', 'Beograd', '26', 'ACTIVE', 'Kosmajska', '0');
INSERT INTO addresses (`id`, `apartment`, `city`, `number`, `status`, `street`, `version`) VALUES ('14', 'A', 'Kikinda', '27', 'ACTIVE', 'Novosadska', '0');
INSERT INTO addresses (`id`, `apartment`, `city`, `number`, `status`, `street`, `version`) VALUES ('15', '32', 'Zrenjanin', '2', 'ACTIVE', 'Bulevarska', '0');

# Persons
INSERT INTO persons (`id`, `birthdate`, `firstname`, `jmbg`, `lastname`, `mphone`, `status`, `version`, `idaddress`) VALUES ('20', '1900-01-01', 'admin', '0000000000000', 'admin', '+000000000', 'ACTIVE', '0', '10');
INSERT INTO persons (`id`, `birthdate`, `firstname`, `jmbg`, `lastname`, `mphone`, `status`, `version`, `idaddress`) VALUES ('21', '1980-05-06', 'Petar', '0605980123456', 'Markovic', '+063632458', 'ACTIVE', '0', '11');
INSERT INTO persons (`id`, `birthdate`, `firstname`, `jmbg`, `lastname`, `mphone`, `status`, `version`, `idaddress`) VALUES ('22', '1990-12-12', 'Jovan', '1212990878556', 'Petrovic', '+063789789', 'ACTIVE', '0', '11');
INSERT INTO persons (`id`, `birthdate`, `firstname`, `jmbg`, `lastname`, `mphone`, `status`, `version`, `idaddress`) VALUES ('23', '2000-07-10', 'Ivan',  '1007000323658', 'Prpara', '+064456456', 'ACTIVE', '0', '12');
INSERT INTO persons (`id`, `birthdate`, `firstname`, `jmbg`, `lastname`, `mphone`, `status`, `version`, `idaddress`) VALUES ('24', '1999-11-11', 'Janko', '1111999124578', 'Dindo', '+063635789', 'ACTIVE', '0', '13');
INSERT INTO persons (`id`, `birthdate`, `firstname`, `jmbg`, `lastname`, `mphone`, `status`, `version`, `idaddress`) VALUES ('25', '2005-09-01', 'Marko', '0109005987413', 'Kostic', '+063333333', 'ACTIVE', '0', '14');
INSERT INTO persons (`id`, `birthdate`, `firstname`, `jmbg`, `lastname`, `mphone`, `status`, `version`, `idaddress`) VALUES ('26', '2005-09-01', 'Kiki', '0109005987888', 'Jokic', '+063233233', 'ACTIVE', '0', '14');

# Users
INSERT INTO users (`id`, `password`, `status`, `username`, `version`, `idperson`, `idrole`) VALUES ('30', '$2a$10$jgw8uiTl3yQgXlw6yOJEg.vhFFvOcki2wgTTNC9/NZ4SfSj8CGZGW', 'ACTIVE', 'admin', '0', '20', '1');
INSERT INTO users (`id`, `password`, `status`, `username`, `version`, `idperson`, `idrole`) VALUES ('31', '$2a$10$jgw8uiTl3yQgXlw6yOJEg.vhFFvOcki2wgTTNC9/NZ4SfSj8CGZGW', 'ACTIVE', 'pera', '0', '21', '2');
INSERT INTO users (`id`, `password`, `status`, `username`, `version`, `idperson`, `idrole`) VALUES ('32', '$2a$10$jgw8uiTl3yQgXlw6yOJEg.vhFFvOcki2wgTTNC9/NZ4SfSj8CGZGW', 'ACTIVE', 'yole', '0', '22', '3');
INSERT INTO users (`id`, `password`, `status`, `username`, `version`, `idperson`, `idrole`) VALUES ('33', '$2a$10$jgw8uiTl3yQgXlw6yOJEg.vhFFvOcki2wgTTNC9/NZ4SfSj8CGZGW', 'ACTIVE', 'miki', '0', '23', '4');
INSERT INTO users (`id`, `password`, `status`, `username`, `version`, `idperson`, `idrole`) VALUES ('34', '$2a$10$jgw8uiTl3yQgXlw6yOJEg.vhFFvOcki2wgTTNC9/NZ4SfSj8CGZGW', 'ACTIVE', 'sole', '0', '24', '5');
INSERT INTO users (`id`, `password`, `status`, `username`, `version`, `idperson`, `idrole`) VALUES ('35', '$2a$10$jgw8uiTl3yQgXlw6yOJEg.vhFFvOcki2wgTTNC9/NZ4SfSj8CGZGW', 'ACTIVE', 'xxxx', '0', '20', '3');
INSERT INTO users (`id`, `password`, `status`, `username`, `version`, `idperson`, `idrole`) VALUES ('36', '$2a$10$jgw8uiTl3yQgXlw6yOJEg.vhFFvOcki2wgTTNC9/NZ4SfSj8CGZGW', 'ACTIVE', 'nnn', '0', '25', '4');
INSERT INTO users (`id`, `password`, `status`, `username`, `version`, `idperson`, `idrole`) VALUES ('37', '$2a$10$jgw8uiTl3yQgXlw6yOJEg.vhFFvOcki2wgTTNC9/NZ4SfSj8CGZGW', 'ACTIVE', 'mamaitata', '0', '26', '5');

# Specializations
INSERT INTO admins (`email`, `id`) VALUES ('testemail@gmail.com', '30');
INSERT INTO teachers (`id`) VALUES ('31');
INSERT INTO teachers (`id`) VALUES ('32');
INSERT INTO teachers (`id`) VALUES ('35');
INSERT INTO students (`id`) VALUES ('33');
INSERT INTO students (`id`) VALUES ('36');
INSERT INTO parents (`email`, `id`) VALUES ('email@gmail.com', '34');
INSERT INTO parents (`email`, `id`) VALUES ('xxx@gmail.com', '37');

# Parent-Strudent
INSERT INTO student_parents (`id`, `idparent`, `idstudent`) VALUES ('40', '34', '36');

# Classes
INSERT INTO classes (`id`, `name`, `status`, `version`, `year`) VALUES ('50', 'I', 'ACTIVE', '0', '2021');
INSERT INTO classes (`id`, `name`, `status`, `version`, `year`) VALUES ('51', 'II', 'ACTIVE', '0', '2021');
INSERT INTO classes (`id`, `name`, `status`, `version`, `year`) VALUES ('52', 'III', 'ACTIVE', '0', '2021');
INSERT INTO classes (`id`, `name`, `status`, `version`, `year`) VALUES ('53', 'IV', 'ACTIVE', '0', '2021');
INSERT INTO classes (`id`, `name`, `status`, `version`, `year`) VALUES ('54', 'V', 'ACTIVE', '0', '2021');
INSERT INTO classes (`id`, `name`, `status`, `version`, `year`) VALUES ('55', 'VI', 'ACTIVE', '0', '2021');
INSERT INTO classes (`id`, `name`, `status`, `version`, `year`) VALUES ('56', 'VII', 'ACTIVE', '0', '2021');
INSERT INTO classes (`id`, `name`, `status`, `version`, `year`) VALUES ('57', 'VIII', 'ACTIVE', '0', '2021');

# Subjects
INSERT INTO subjects (`id`, `name`, `status`, `version`) VALUES ('80', 'Matematika', 'ACTIVE', '0');
INSERT INTO subjects (`id`, `name`, `status`, `version`) VALUES ('81', 'Srpski', 'ACTIVE', '0');
INSERT INTO subjects (`id`, `name`, `status`, `version`) VALUES ('82', 'Muzicko', 'ACTIVE', '0');
INSERT INTO subjects (`id`, `name`, `status`, `version`) VALUES ('83', 'Likovno', 'ACTIVE', '0');
INSERT INTO subjects (`id`, `name`, `status`, `version`) VALUES ('84', 'Istorija', 'ACTIVE', '0');
INSERT INTO subjects (`id`, `name`, `status`, `version`) VALUES ('85', 'Geografija', 'ACTIVE', '0');
INSERT INTO subjects (`id`, `name`, `status`, `version`) VALUES ('86', 'Hemija', 'ACTIVE', '0');
INSERT INTO subjects (`id`, `name`, `status`, `version`) VALUES ('87', 'Informatika', 'ACTIVE', '0');
INSERT INTO subjects (`id`, `name`, `status`, `version`) VALUES ('88', 'Fizicko', 'ACTIVE', '0');
INSERT INTO subjects (`id`, `name`, `status`, `version`) VALUES ('89', 'Tehnicko', 'ACTIVE', '0');


# Hibernate sequence fixup
UPDATE hibernate_sequence SET next_val = 200;