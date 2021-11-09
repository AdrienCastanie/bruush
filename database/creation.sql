CREATE DATABASE IF NOT EXISTS BRUUSH;


DROP TABLE IF EXISTS COMMANDE;
DROP TABLE IF EXISTS ACHAT;
DROP TABLE IF EXISTS ARTICLE;
DROP TABLE IF EXISTS CLIENT;




-- ************************************** CLIENT

CREATE TABLE CLIENT
(
 id_client integer NOT NULL ,
 nom       varchar(50) NOT NULL ,
 prenom    varchar(50) NOT NULL ,
 mail      varchar(50) NOT NULL ,
 addr      varchar(100) NOT NULL ,
 bloque    integer NOT NULL ,

PRIMARY KEY (id_client)
);



-- ************************************** COMMANDE

CREATE TABLE COMMANDE
(
 id_commande integer NOT NULL AUTO_INCREMENT ,
 id_client   integer NOT NULL ,
 total       integer NOT NULL ,
 date        date NOT NULL ,

PRIMARY KEY (id_commande),
FOREIGN KEY (id_client) REFERENCES CLIENT(id_client)
);


-- ************************************** ACHAT

CREATE TABLE ACHAT
(
 id_achat    integer NOT NULL AUTO_INCREMENT ,
 id_commande integer NOT NULL ,
 id_article  integer NOT NULL ,
 qte         integer NOT NULL ,

PRIMARY KEY (id_achat),
FOREIGN KEY (id_commande) REFERENCES COMMANDE(id_commande)
);


-- ************************************** ARTICLE

CREATE TABLE ARTICLE
(
 id_article  integer NOT NULL ,
 nom         varchar(100) NOT NULL ,
 prix        integer NOT NULL ,
 stock       integer NOT NULL ,
 description text NOT NULL ,
 img         text NOT NULL ,

PRIMARY KEY (id_article)
);