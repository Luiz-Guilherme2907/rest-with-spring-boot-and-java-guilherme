CREATE TABLE IF NOT EXISTS `person` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(80) NOT NULL,
  `adress` varchar(255) NOT NULL,
  `cpf` varchar(11) NOT NULL,
  PRIMARY KEY (`id`)
);