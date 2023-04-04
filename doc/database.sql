DB-Structure
============
DB-NAME:	parts_list
DBMS:		MySql

article
-------
id					INT
name				VARCHAR(40)
description			TEXT
trader				VARCHAR(40)
price 				DECIMAL(6,2)
dt_created			DATETIME
dt_updated			DATETIME


CREATE TABLE `article` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `description` text DEFAULT NULL,
  `trader` varchar(40) NOT NULL,
  `price` decimal(6,2) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- DROP TABLE article;
===================================================================================

use parts_list;

-- einen User für Springboot anlegen
create user 'springuser'@'%' identified by '#EBIT2022'; -- Creates the user

-- dem Springboot User alle Rechte auf die gerade angelegte DB geben
grant all on parts_list.* to 'springuser'@'%';

===================================================================================

insert into article (name, description, trader, price, created_at) 
values ('Holzschraube 4 x 45 Torx', 'Eine total coole Holzschraube', 'OBI', 0.32, NOW());

insert into article (name, description, trader, price, created_at) 
values ('Leiste Buche 1000x42x37', 'Holzleiste Buche 1000/42/37 mm (L/B/H)', 'Hornbach', 2.32, NOW());

===================================================================================

select * from article;