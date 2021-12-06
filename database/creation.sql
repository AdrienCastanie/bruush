CREATE DATABASE IF NOT EXISTS BRUUSH;

DROP TABLE IF EXISTS COMMANDE;
DROP TABLE IF EXISTS ACHAT;
DROP TABLE IF EXISTS ARTICLE;
DROP TABLE IF EXISTS CLIENT;

CREATE TABLE CLIENT (
 id_client integer NOT NULL AUTO_INCREMENT ,
 nom       varchar(50) NOT NULL ,
 prenom    varchar(50) NOT NULL ,
 mail      varchar(50) NOT NULL UNIQUE,
 mdp       varchar(50) NOT NULL ,
 addr      varchar(100) NULL DEFAULT NULL,
 bloque    integer(1) DEFAULT 0 ,
 PRIMARY KEY (id_client)
);

CREATE TABLE COMMANDE (
 id_commande integer NOT NULL AUTO_INCREMENT ,
 id_client   integer NOT NULL ,
 total       integer NOT NULL ,
 date        date NOT NULL ,
 PRIMARY KEY (id_commande),
 FOREIGN KEY (id_client) REFERENCES CLIENT(id_client)
);

CREATE TABLE ACHAT (
 id_achat    integer NOT NULL AUTO_INCREMENT ,
 id_commande integer NOT NULL ,
 id_article  integer NOT NULL ,
 qte         integer NOT NULL ,
 PRIMARY KEY (id_achat),
 FOREIGN KEY (id_commande) REFERENCES COMMANDE(id_commande)
);

CREATE TABLE ARTICLE (
 id_article  integer NOT NULL ,
 nom         varchar(100) NOT NULL ,
 prix        integer NOT NULL ,
 stock       integer NOT NULL ,
 description text NOT NULL ,
 img         text NOT NULL ,
 PRIMARY KEY (id_article)
);

INSERT INTO `CLIENT` (`id_client`,`nom`,`prenom`,`mail`,`mdp`,`addr`,`bloque`)
VALUES
    (1,"Holmes","Stafford","ut.tincidunt@hotmail.ca","LVO27WNJ8QU","834-3959 Egestas Av.",0),
    (2,"Susan","Salazar","commodo.hendrerit.donec@google.org","MKH44IHP4AY","Ap #623-5415 Rutrum St.",0),
    (3,"Zachery","Pate","ac.tellus@google.edu","XXE17GBJ8GB","Ap #424-2821 Consequat, St.",0),
    (4,"Rudyard","Duke","nisl.sem@yahoo.com","APG18WDC7RL","P.O. Box 580, 8382 Morbi St.",0),
    (5,"Charde","Roberts","ligula.tortor@protonmail.com","IQP58IYI8LC","478-4005 Quam Rd.",0),
    (6,"Damian","Price","sed@hotmail.com","WEK03LKE2EC","Ap #846-2168 Adipiscing, Av.",0),
    (7,"Cassady","Ortega","vel.nisl@icloud.couk","JOE29ICC2FI","308-4179 Vehicula. Road",0),
    (8,"Ruth","Mccarty","tempus@aol.couk","UYY58QJY2TP","P.O. Box 825, 3279 Nunc Road",0),
    (9,"Iris","Holman","dignissim.pharetra@google.ca","CRR32SGN4XX","353-8523 A, Rd.",0),
    (10,"Cassandra","Pennington","ipsum.dolor.sit@protonmail.ca","XXA01CME6IV","492-2225 Libero Avenue",0);
INSERT INTO `CLIENT` (`id_client`,`nom`,`prenom`,`mail`,`mdp`,`addr`,`bloque`)
VALUES
    (11,"Quyn","Mcneil","lacinia@icloud.edu","WAD75APY6IS","Ap #404-8822 Rutrum Rd.",0),
    (12,"Kirk","Carroll","magna.lorem@icloud.couk","QBO68RFA0NA","6619 Metus Road",0),
    (13,"Macy","Carter","blandit.at.nisi@google.org","FVD24SVW9RX","P.O. Box 645, 4527 Eu Ave",0),
    (14,"Abdul","Bullock","facilisi@aol.net","KIV67YFM1CF","Ap #857-7066 Lectus. Rd.",0),
    (15,"Dante","Norton","eleifend.nunc@yahoo.net","OSE22TSW3PS","8908 Eu, Avenue",0),
    (16,"Liberty","Dalton","suspendisse.non@outlook.ca","VMV14EOQ8LX","Ap #814-5963 Massa. Ave",0),
    (17,"Anthony","Hood","est.ac@google.couk","SQU08NKK9YG","311-8518 Ut Rd.",0),
    (18,"Brennan","Odom","non@protonmail.couk","YPN14DTG1XW","Ap #604-5389 Nunc St.",0),
    (19,"Georgia","O'Neill","eros.nec.tellus@hotmail.org","DEN17OPX6GK","Ap #474-5862 Augue Av.",0),
    (20,"Cairo","Duran","euismod.enim@icloud.org","VUQ97CEY2SB","Ap #868-7344 Eros Road",0);
INSERT INTO `CLIENT` (`id_client`,`nom`,`prenom`,`mail`,`mdp`,`addr`,`bloque`)
VALUES
    (21,"Philip","Peterson","molestie@outlook.net","XMH27IJC3BM","Ap #755-7499 Lorem. St.",0),
    (22,"Holmes","Church","pulvinar.arcu.et@icloud.ca","NBF82HQT6SM","P.O. Box 684, 614 Mauris St.",0),
    (23,"Quinlan","Mcgowan","nisi.cum.sociis@protonmail.com","FJJ65MFP3TF","P.O. Box 997, 3974 In Avenue",0),
    (24,"Lillith","Farrell","vitae@aol.org","JZL18PMO5YW","P.O. Box 285, 3249 Tristique Avenue",0),
    (25,"Phoebe","Acevedo","neque@aol.net","DHP43JIT4WK","9323 Ornare, Road",0),
    (26,"Pandora","Simon","ullamcorper.eu@hotmail.com","MQG12IXR1TP","P.O. Box 248, 2961 Sed St.",0),
    (27,"Blake","Bennett","odio.sagittis@aol.couk","GDU64SSO6PN","1140 Turpis Rd.",0),
    (28,"Emily","Justice","ante.blandit@google.com","FQI51JFY6HK","Ap #184-942 Fames Ave",0),
    (29,"Kitra","Curtis","dapibus.rutrum@aol.ca","CSJ75USQ4QJ","931-3800 Fringilla, Rd.",0),
    (30,"Shaine","Golden","libero.morbi.accumsan@protonmail.org","FRJ22FAB8KG","Ap #404-6027 Volutpat Avenue",0);
INSERT INTO `CLIENT` (`id_client`,`nom`,`prenom`,`mail`,`mdp`,`addr`,`bloque`)
VALUES
    (31,"Katell","Rivers","malesuada.vel@hotmail.edu","MHT73MWR9NQ","179-4926 Mauris Rd.",0),
    (32,"Barry","Knapp","sem.vitae.aliquam@protonmail.couk","FOV19JCL8JK","Ap #932-2440 Donec Road",0),
    (33,"Jonas","Benson","in.tempus@yahoo.com","VLB10PVM4BY","Ap #517-3517 Egestas Rd.",0),
    (34,"Hayley","Nixon","cras.lorem@aol.com","MNO57BSH3NN","433-9029 Amet Avenue",0),
    (35,"Pearl","Daniels","congue@outlook.com","BMR50JTH8GY","122-3996 Ut St.",0),
    (36,"Rudyard","Bennett","donec.elementum.lorem@icloud.org","XYP89JRJ2KO","Ap #582-9030 Orci. Ave",0),
    (37,"Lionel","Huff","luctus@aol.org","HQY94WGL9RQ","897-7686 Mauris Ave",0),
    (38,"Cade","Hickman","non.justo.proin@icloud.net","RAO61HHX7XY","Ap #869-264 Amet Av.",0),
    (39,"Tanisha","Dale","facilisis.lorem@protonmail.edu","XYW23VTD7SX","Ap #800-3148 Porttitor Av.",0),
    (40,"Sylvia","Wyatt","elit.elit.fermentum@hotmail.couk","NJV54ARL7QM","388-7383 Posuere Rd.",0);
INSERT INTO `CLIENT` (`id_client`,`nom`,`prenom`,`mail`,`mdp`,`addr`,`bloque`)
VALUES
    (41,"Sade","Foster","sapien.aenean.massa@google.ca","QTD14UFN5RJ","663-8810 Morbi Rd.",0),
    (42,"Leah","Dennis","feugiat.tellus@yahoo.com","BUC40RRL6FE","Ap #240-3083 Sed, Av.",0),
    (43,"Judah","Grimes","a.sollicitudin@hotmail.edu","YFJ41LUY1PO","Ap #408-7135 Ut, St.",0),
    (44,"Colin","Garner","nulla.in@protonmail.org","DHQ11TLY2CB","167-1939 Vehicula Road",0),
    (45,"Carissa","Stafford","ornare.lectus.justo@outlook.ca","VDX31AVO6TT","Ap #510-4942 Et, Rd.",0),
    (46,"Graiden","Pearson","luctus.curabitur.egestas@hotmail.org","PLZ55STR7OF","8183 Luctus Avenue",0),
    (47,"Uriel","Melton","auctor@yahoo.couk","ZUE32TKF0KV","172-6105 Tincidunt, Av.",0),
    (48,"Alexandra","Spears","nascetur.ridiculus@icloud.net","NKI80TBL5XL","Ap #156-9128 Sapien St.",0),
    (49,"Marny","Rodgers","velit.in@aol.couk","UOV78NEC4RK","Ap #899-804 Cras St.",0),
    (50,"Orli","Manning","elit@protonmail.edu","MZS68IJH8VO","647-4257 Faucibus Avenue",0);
INSERT INTO `CLIENT` (`id_client`,`nom`,`prenom`,`mail`,`mdp`,`addr`,`bloque`)
VALUES
    (51,"Carl","Mendoza","risus.donec@yahoo.ca","JCX58OTO8RX","Ap #699-1396 Aliquam Ave",0),
    (52,"Rogan","Long","tempus.scelerisque@google.edu","PUY22CHV2EM","P.O. Box 229, 557 Mollis. Av.",0),
    (53,"Aristotle","Gordon","ut.eros@protonmail.net","IUU75WNB3WL","P.O. Box 261, 781 Eget Street",0),
    (54,"Hedwig","Yates","aliquam.iaculis@hotmail.net","ZNL08LKB2PE","P.O. Box 954, 234 Ut Street",0),
    (55,"Mason","Wilkins","dolor@aol.com","JLH70HCD4CT","837-9219 Erat Street",0),
    (56,"Judah","Huber","ligula.aenean@protonmail.net","NKP63TCC8ZC","4175 Sit Street",0),
    (57,"Rachel","Cain","maecenas.malesuada@google.couk","RSU97ARG6BV","Ap #169-2420 Nec, Ave",0),
    (58,"Adam","Joyner","blandit.mattis.cras@icloud.couk","EEV46DXV2TC","Ap #253-9222 Est Ave",0),
    (59,"Kenyon","Fischer","eget.tincidunt.dui@icloud.couk","YMK14MMS8HQ","2998 Dolor Street",0),
    (60,"Frances","Osborn","proin.sed.turpis@aol.edu","MTG65PUD3XJ","P.O. Box 536, 4820 Tincidunt Street",0);
INSERT INTO `CLIENT` (`id_client`,`nom`,`prenom`,`mail`,`mdp`,`addr`,`bloque`)
VALUES
    (61,"Freya","Armstrong","vivamus.nibh.dolor@hotmail.com","ZDM67FFQ8IR","986-3990 Lorem, Road",0),
    (62,"Alma","Blevins","et.magnis.dis@protonmail.edu","OSC36ESC6MJ","5727 Nam Road",0),
    (63,"Reed","Huffman","vulputate.risus.a@google.net","MLT64OCQ1UJ","2760 Nunc Rd.",0),
    (64,"Leroy","Williamson","cursus.et@yahoo.edu","WWM08ELQ1GJ","917-3126 Malesuada Road",0),
    (65,"Malcolm","Horn","rutrum.non@google.edu","CYL39MGK4ZI","Ap #267-9426 Arcu Rd.",0),
    (66,"Melvin","Valentine","libero.integer@google.ca","QCL12IPQ1GI","P.O. Box 950, 895 Quisque Road",0),
    (67,"Maxine","Gonzales","aliquet@hotmail.org","RVN22WQS9LL","Ap #905-7406 Risus. Ave",0),
    (68,"Kelsey","Gross","tellus.imperdiet@protonmail.com","KSO26AZD5ZZ","Ap #207-4566 Praesent St.",0),
    (69,"Lance","Mccall","cursus.in@icloud.com","ZKS66KPV0ED","570-9353 Adipiscing Road",0),
    (70,"Tiger","Adams","non.arcu.vivamus@hotmail.couk","DXE11MVI6FO","Ap #730-315 Mauris Ave",0);
INSERT INTO `CLIENT` (`id_client`,`nom`,`prenom`,`mail`,`mdp`,`addr`,`bloque`)
VALUES
    (71,"Gay","Pugh","vulputate.eu@aol.couk","SLP10OHA3VN","P.O. Box 385, 1926 Sagittis St.",0),
    (72,"Hyatt","Farrell","cras@yahoo.couk","JJO17NBM8RO","Ap #210-3802 Blandit. St.",0),
    (73,"Celeste","Shepherd","ligula.nullam.enim@icloud.ca","JGM42WKE4SS","Ap #554-5261 Varius. Street",0),
    (74,"Vera","Nicholson","urna.nunc.quis@yahoo.org","SZV31XNE1CD","P.O. Box 905, 526 Rhoncus Rd.",0),
    (75,"Alden","Franks","mauris.id@google.org","DKS49QIY5PX","Ap #122-1668 Adipiscing, Road",0),
    (76,"Jerry","Estes","praesent.interdum@aol.couk","QSC17IRK8QF","Ap #832-670 Tincidunt St.",0),
    (77,"Justin","Rodriguez","facilisis.suspendisse.commodo@icloud.couk","MRT33WLB6SM","228-472 Interdum. Street",0),
    (78,"Suki","Guerrero","malesuada@aol.ca","YQU45IGP8WJ","191-2627 Eu, Rd.",0),
    (79,"Brooke","Briggs","sapien.aenean.massa@aol.ca","RSI74MFC3RL","Ap #975-6042 Tellus. Street",0),
    (80,"Cadman","Compton","sem@outlook.net","XMJ58DGR8BL","445-9369 In, Av.",0);
INSERT INTO `CLIENT` (`id_client`,`nom`,`prenom`,`mail`,`mdp`,`addr`,`bloque`)
VALUES
    (81,"Chelsea","Burt","cum.sociis@icloud.net","SUO94QQJ6JT","2632 Nec, Avenue",0),
    (82,"Athena","Chandler","quis.pede@aol.com","LMJ75CHQ5QU","516-3168 Molestie Rd.",0),
    (83,"Noel","Page","est.mollis@aol.couk","SIR62LTV8VR","Ap #156-8761 Amet Rd.",0),
    (84,"Hedley","Allen","commodo.auctor.velit@outlook.ca","KSB50TVH4TG","P.O. Box 786, 5794 Massa. St.",0),
    (85,"Hamilton","Durham","nulla.eu@outlook.ca","REN53DQC0RG","P.O. Box 492, 749 Lorem. Road",0),
    (86,"Gavin","Collins","dignissim.pharetra.nam@protonmail.couk","NFT38IXN5YN","416-7599 Eros St.",0),
    (87,"Driscoll","Harmon","cras.eu@google.net","RVL98JEV6GQ","P.O. Box 231, 8209 Fusce Rd.",0),
    (88,"Katelyn","Walsh","vivamus.non@aol.couk","TGE71PCZ3SG","P.O. Box 378, 314 Netus Av.",0),
    (89,"Erich","Waters","dapibus@icloud.edu","BYV78JPN0RD","Ap #652-8263 Rhoncus. Ave",0),
    (90,"Sean","Anthony","posuere.enim@icloud.couk","YPU75THY2NT","225-7024 Donec Rd.",0);
INSERT INTO `CLIENT` (`id_client`,`nom`,`prenom`,`mail`,`mdp`,`addr`,`bloque`)
VALUES
    (91,"Ethan","Montoya","duis.ac@outlook.com","RWA68GYW2MM","302-1870 Luctus Av.",0),
    (92,"Cameron","Carey","ut.cursus@hotmail.couk","CCZ96TNU3LI","535-2904 Aliquam Road",0),
    (93,"Jonas","Marsh","ipsum.leo@yahoo.ca","CEM19EDX7JU","P.O. Box 132, 3871 Non St.",0),
    (94,"Dale","Rodriquez","et.ultrices@hotmail.edu","NDM73CWT2LQ","Ap #862-9256 Aliquet St.",0),
    (95,"Kiayada","Mckinney","nec@google.com","XMZ03SGU3SH","P.O. Box 817, 4901 Iaculis Avenue",0),
    (96,"Bert","Salas","donec.elementum@aol.net","YDS72YCR8ZK","P.O. Box 122, 6110 Ornare Av.",0),
    (97,"Felicia","Hull","quis@aol.ca","FFI18JAJ0OG","Ap #252-2698 Sagittis Av.",0),
    (98,"Ava","Martinez","turpis.egestas@outlook.com","KEU77LXU6PW","4723 Euismod Rd.",0),
    (99,"Kyle","Roach","bibendum.donec@icloud.com","DMH60RNN4SJ","774-3017 Et St.",0),
    (100,"Cecilia","Mack","massa.non.ante@yahoo.edu","GEF81SSM2GA","634-7055 Suspendisse Road",0);
