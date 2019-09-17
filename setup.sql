CREATE DATABASE treningsdagbok;
USE treningsdagbok;
CREATE TABLE bruker(
    brukerID INTEGER(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    navn VARCHAR(255) UNIQUE
);

CREATE TABLE Treningsokt(
    oktID INTEGER NOT NULL PRIMARY KEY,
    tidspunkt DATE,
    varighet INT, 
    notat varchar(10000),
	prestasjon INTEGER(2),
    form INTEGER(2)
);

CREATE TABLE brukerokt(
    brukerID INTEGER NOT NULL,
    oktID INTEGER NOT NULL,
    CONSTRAINT brukerokt_FK1 FOREIGN KEY (brukerID) REFERENCES bruker(brukerID) ON UPDATE CASCADE ON DELETE NO ACTION,
    CONSTRAINT brukerokt_FK2 FOREIGN KEY (oktID) REFERENCES Treningsokt(oktID) ON UPDATE CASCADE ON DELETE NO ACTION
);

CREATE TABLE ovelse(
    ovelseNavn varchar(255) NOT NULL PRIMARY KEY,
    sett INTEGER(6)
);

CREATE TABLE ovelseIokt(
    oktID INTEGER NOT NULL,
    ovelseNavn varchar(255) NOT NULL,
    CONSTRAINT ovelseIokt_FK1 FOREIGN KEY (ovelseNavn) REFERENCES ovelse(ovelseNavn) ON UPDATE CASCADE ON DELETE NO ACTION,
    CONSTRAINT ovelseIokt_FK2 FOREIGN KEY (oktID) REFERENCES treningsokt(oktID) ON UPDATE CASCADE ON DELETE NO ACTION
);


CREATE TABLE ovelsesgruppe(
    gruppeNavn VARCHAR(255) NOT NULL PRIMARY KEY,
    beskrivelse varchar(10000)
);


CREATE TABLE ovelseigruppe(
    ovelseNavn VARCHAR(255) NOT NULL,
    gruppeNavn VARCHAR(255) NOT NULL,
    CONSTRAINT ovelseigruppe_FK1 FOREIGN KEY (ovelseNavn) REFERENCES ovelse(ovelseNavn) ON UPDATE CASCADE ON DELETE NO ACTION,
    CONSTRAINT ovelseigruppe_FK2 FOREIGN KEY (gruppeNavn) REFERENCES ovelsesgruppe(gruppeNavn) ON UPDATE CASCADE ON DELETE NO ACTION

);


CREATE TABLE apparatovelse(
    ovelseNavn VARCHAR(255) NOT NULL,
    kilo INTEGER(6),
    CONSTRAINT apparatovelse_FK1 FOREIGN KEY (ovelseNavn) REFERENCES ovelse(ovelseNavn) ON UPDATE CASCADE ON DELETE NO ACTION
);

CREATE TABLE frivekt(
    ovelseNavn VARCHAR(255) NOT NULL, 
    beskrivelse VARCHAR(255),
    CONSTRAINT frivekt_FK1 FOREIGN KEY (ovelseNavn) REFERENCES ovelse(ovelseNavn) ON UPDATE CASCADE ON DELETE NO ACTION

);

CREATE TABLE apparat(
    apparatNavn VARCHAR(255) NOT NULL PRIMARY KEY,
    beskrivelse VARCHAR(255)
);

CREATE TABLE apparatrelasjon(
    ovelseNavn VARCHAR(255) NOT NULL,
    apparatNavn VARCHAR(255) NOT NULL,
    CONSTRAINT apparatrelasjon_FK1 FOREIGN KEY (ovelseNavn) REFERENCES ovelse(ovelseNavn) ON UPDATE CASCADE ON DELETE NO ACTION,
    CONSTRAINT apparatrelasjon_FK2 FOREIGN KEY (apparatNavn) REFERENCES apparat(apparatNavn) ON UPDATE CASCADE ON DELETE NO ACTION
);


insert ignore into treningsokt values (1, "2010-09-17", 1, 'Bra treningsokt. I dag har Joakim bursdag. Jeg liker Joakim. Han er snill', 10, 10);
insert ignore into treningsokt values (2, "2011-09-17", 2, 'Aarlig oekt paa Joakims bursdag. Han er kjempesnill', 9, 10);
insert ignore into treningsokt values (3, "2013-09-17", 2, 'Fint vaer. Joakim har bursdag. Tenker paa han', 9, 8);
insert ignore into treningsokt values (4, "2014-09-17", 1, 'Haaper Joakim liker gaven jeg har kjoept til han. Den er fin. Det er Joakim og.', 9, 1);
insert ignore into treningsokt values (5, "2014-09-20", 1, 'Koseøkt', 9, 3);
insert ignore into treningsokt values (6, "2014-09-23", 1, 'Tror jeg kanskje skal slutte å trene', 1, 1);

insert ignore into apparat values ('Benk', 'Her kan du benkpresse');
insert ignore into apparat values ('Benpress', 'Her kan du benpresse');
insert ignore into apparat values ('Bicep machine', 'Her skal det curles');
insert ignore into apparat values ('Lat pulldown', 'Get big or die trying');

insert ignore into ovelse values ('Benkpress', 3);
insert ignore into ovelse values ('Pushups', 10);
insert ignore into ovelse values ('Megarygg', 3);
insert ignore into ovelse values ('Skraabenk', 3);
insert ignore into ovelse values ('Bicep curls', 3);

insert ignore into apparatrelasjon values ('Benkpress', 'Benk');
insert ignore into apparatrelasjon values ('Skraabenk', 'Benk');
insert ignore into apparatrelasjon values ('Megarygg', 'Lat pulldown');
insert ignore into apparatrelasjon values ('Bicep curls', 'Bicep machine');

insert ignore into bruker values (null, 'Milos');
insert ignore into bruker values (null, 'Adrian');
insert ignore into bruker values (null, 'Halvor');
insert ignore into bruker values (null, 'Joakim');

insert ignore into ovelsesgruppe values('Biceps', 'Hyggelig sted for store og smaa biceps');
insert ignore into ovelsesgruppe values('Rygg', 'En klok mann har sagt: smaa rygger har bare mend med smaa baller');
insert ignore into ovelsesgruppe values('Bryst', 'Bryst er kanskje den viktigste brystmuskelen');
insert ignore into ovelsesgruppe values('Ben', 'Ben er viktig. Men ikke på vinteren.');

insert ignore into brukerokt values (1, 1); 
insert ignore into brukerokt values (2, 2);
insert ignore into brukerokt values (3, 3);
insert ignore into brukerokt values (4, 4);
insert ignore into brukerokt values (4, 5);
insert ignore into brukerokt values (4, 6);


insert ignore into ovelseigruppe values('Benkpress', 'Bryst');
insert ignore into ovelseigruppe values('Skraabenk', 'Bryst');
insert ignore into ovelseigruppe values('Pushups', 'Bryst');
insert ignore into ovelseigruppe values('Bicep curls', 'Bryst');

insert ignore into ovelseiokt values(1, 'Benkpress');
insert ignore into ovelseiokt values(1, 'Skraabenk');
insert ignore into ovelseiokt values(2, 'Bicep curls');
insert ignore into ovelseiokt values(3, 'Megarygg');
insert ignore into ovelseiokt values(4, 'Benkpress');
insert ignore into ovelseiokt values(5, 'Benkpress');
insert ignore into ovelseiokt values(4, 'Bicep curls');


