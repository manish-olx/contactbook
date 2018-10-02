SET autocommit=0;
START TRANSACTION;

CREATE TABLE `user_contact` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(155) DEFAULT NULL,
  `password` varchar(155) DEFAULT NULL,
  `email` varchar(155) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `created_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

COMMIT;