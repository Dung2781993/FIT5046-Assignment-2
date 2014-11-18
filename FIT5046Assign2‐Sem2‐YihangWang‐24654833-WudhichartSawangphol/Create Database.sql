CREATE TABLE GEOINFO(
    GEO_ID VARCHAR(255),
    GEO_ADDRESS VARCHAR(255),
    LATITUDE VARCHAR(255),
    LONGTITUDE VARCHAR(255),
    CONSTRAINT PK_GEOINFO PRIMARY KEY (GEO_ID)
);
INSERT INTO GEOINFO VALUES ('1', 'Monash Medical Centre Clayton', '-37.9749933', '145.3655916');
INSERT INTO GEOINFO VALUES ('2', 'Dandenong Hospital', '-37.9740125', '145.2234761');
INSERT INTO GEOINFO VALUES ('3', 'Caulfield Hospital', '-37.9623582', '145.2077146');
INSERT INTO GEOINFO VALUES ('4', 'Blackburn Road Medical Centre', '-37.8140118', '145.1503595');
INSERT INTO GEOINFO VALUES ('5', 'Knox Private Hospital', '-37.8491851', '145.1426869');
INSERT INTO GEOINFO VALUES ('6', 'Waverley Private Hospital', '-37.8589439', '145.1473217');
INSERT INTO GEOINFO VALUES ('7', 'Wells Road Medical Centre', '-38.0044955', '145.1588231');
INSERT INTO GEOINFO VALUES ('8', 'Lynbrook Village Medical Centre', '-38.0653384', '145.2207928');
INSERT INTO GEOINFO VALUES ('9', 'Sandringham Hospital', '-37.9612832', '145.1335794');
INSERT INTO GEOINFO VALUES ('10', 'Box Hill Hospital', '-37.8588931', '145.1617318');
CREATE TABLE PATIENTS(
    PATIENT_ID VARCHAR(255),
    PATIENT_FIRST_NAME VARCHAR(255),
    PATIENT_LAST_NAME VARCHAR(255),
    CONSTRAINT PK_PATIENTS PRIMARY KEY (PATIENT_ID)
);
INSERT INTO PATIENTS VALUES ('1', 'Steven', 'Gerrard');
INSERT INTO PATIENTS VALUES ('2', 'Rickie', 'Lambert');
INSERT INTO PATIENTS VALUES ('3', 'Jordan', 'Henderson');
INSERT INTO PATIENTS VALUES ('4', 'Daniel', 'Agger');
INSERT INTO PATIENTS VALUES ('5', 'Adam', 'Lallana');
INSERT INTO PATIENTS VALUES ('6', 'Lucas', 'Leiva');
INSERT INTO PATIENTS VALUES ('7', 'Simon', 'Mignolet');
INSERT INTO PATIENTS VALUES ('8', 'Joe', 'Allen');
INSERT INTO PATIENTS VALUES ('9', 'Martin', 'Skrtel');
INSERT INTO PATIENTS VALUES ('10', 'Jon', 'Flanagan');
INSERT INTO PATIENTS VALUES ('11', 'Brad', 'Jones');
INSERT INTO PATIENTS VALUES ('12', 'Glen', 'Johnson');
INSERT INTO PATIENTS VALUES ('13', 'Jose', 'Enrique');
INSERT INTO PATIENTS VALUES ('14', 'Dejan', 'Lovren');
INSERT INTO PATIENTS VALUES ('15', 'Philippe', 'Coutinho');
INSERT INTO PATIENTS VALUES ('16', 'Daniel', 'Sturridge');
INSERT INTO PATIENTS VALUES ('17', 'Javi', 'Manquillo');
INSERT INTO PATIENTS VALUES ('18', 'Fabio', 'Borini');
INSERT INTO PATIENTS VALUES ('19', 'Raheem', 'Sterling');
INSERT INTO PATIENTS VALUES ('20', 'Jerome', 'Sinclair');
CREATE TABLE USERS(
    USER_ID VARCHAR(255),
    USER_NAME VARCHAR(255),
    USER_PASSWORD VARCHAR(255),
    USER_FIRST_NAME VARCHAR(255),
    USER_LAST_NAME VARCHAR(255),
    USER_POSITION VARCHAR(255),
    USER_ORGANIZATION VARCHAR(255),
    CONSTRAINT PK_USERS PRIMARY KEY (USER_ID)
);
INSERT INTO USERS VALUES ('1', 'CB', '123456', 'Cate', 'Blanchett', 'Physician', 'Box Hill Hospital');
INSERT INTO USERS VALUES ('2', 'NW', '123456', 'Naomi', 'Watts', 'Nurse', 'Sandringham Hospital');
INSERT INTO USERS VALUES ('3', 'RB', '123456', 'Rose', 'Byrne', 'Nurse', 'Lynbrook Village Medical Centre');
INSERT INTO USERS VALUES ('4', 'NK', '123456', 'Nicole', 'Kidman', 'Nurse', 'Wells Road Medical Centre');
INSERT INTO USERS VALUES ('5', 'HW', '123456', 'Hugo', 'Weaving', 'Physician', 'Waverley Private Hospital');
INSERT INTO USERS VALUES ('6', 'HJ', '123456', 'Hugh', 'Jackman', 'Physician', 'Knox Private Hospital');
INSERT INTO USERS VALUES ('7', 'DW', '123456', 'David', 'Wenham', 'Physician', 'Blackburn Road Medical Centre');
INSERT INTO USERS VALUES ('8', 'RC', '123456', 'Russell', 'Crowe', 'Paramedics', 'Caulfield Hospital');
INSERT INTO USERS VALUES ('9', 'GR', '123456', 'Geoffrey', 'Rush', 'Paramedics', 'Dandenong Hospital');
INSERT INTO USERS VALUES ('10', 'CH', '123456', 'Chris', 'Hemsworth', 'Paramedics', 'Monash Medical Centre Clayton');
CREATE TABLE SIEVE(
    SIEVE_ID VARCHAR(255),
    WALK_STATUS VARCHAR(255),
    BREATH_STATUS VARCHAR(255),
    A_O_BREATHING VARCHAR(255),
    RESPIRATORY_RATE VARCHAR(255),
    PULSE_RATE VARCHAR(255),
    SYMPTOMS VARCHAR(255),
    INJURIES VARCHAR(255),
    PRIORITY VARCHAR(255),
    SIEVE_DATE VARCHAR(255),
    SIEVE_TIME VARCHAR(255),
    GEO_ID VARCHAR(255),
    USER_ID VARCHAR(255),
    PATIENT_ID VARCHAR(255),
    CONSTRAINT PK_SIEVE PRIMARY KEY (SIEVE_ID),
    CONSTRAINT FK_SIEVE_USERS FOREIGN KEY (USER_ID) REFERENCES USERS (USER_ID) ON DELETE CASCADE,
    CONSTRAINT FK_SIEVE_PATIENTS FOREIGN KEY (PATIENT_ID) REFERENCES PATIENTS (PATIENT_ID) ON DELETE CASCADE,
    CONSTRAINT FK_SIEVE_GEOINFO FOREIGN KEY (GEO_ID) REFERENCES GEOINFO (GEO_ID) ON DELETE CASCADE
);
INSERT INTO SIEVE VALUES ('1', 'NO', 'YES', '', '10-29', '>120', 'Diarrhoea', 'Bites', '1', '2014-01-01', '01:01:01', '10', '1', '1');
INSERT INTO SIEVE VALUES ('2', 'YES', '', '', '', '', 'Ferver', 'Burns', '3', '2014-02-02', '02:02:02', '9', '2', '2');
INSERT INTO SIEVE VALUES ('3', 'NO', 'NO', 'YES', '', '', 'Headache', 'Cardiovascular Problems', '1', '2014-03-03', '03:03:03', '8', '3', '3');
INSERT INTO SIEVE VALUES ('4', 'NO', 'NO', 'NO', '', '', 'Joint/MuscleAche', 'Fracture/Sprain/Dislocation', '4', '2014-04-04', '04:04:04', '7', '4', '4');
INSERT INTO SIEVE VALUES ('5', 'NO', 'YES', '', '<10 Or >29', '', 'Rash', 'Heart Related Condition', '1', '2014-05-05', '05:05:05', '6', '5', '5');
INSERT INTO SIEVE VALUES ('6', 'NO', 'YES', '', '10-29', '<120 Or =120', 'Seizure', 'Hypothemia', '2', '2014-06-06', '06:06:06', '5', '6', '6');
INSERT INTO SIEVE VALUES ('7', 'NO', 'YES', '', '10-29', '<120 Or =120', 'Unconsciousness', 'Laceration', '2', '2014-07-07', '07:07:07', '4', '7', '7');
INSERT INTO SIEVE VALUES ('8', 'NO', 'YES', '', '<10 Or >29', '', 'Vomiting', 'Wounds', '1', '2014-08-08', '08:08:08', '3', '8', '8');
INSERT INTO SIEVE VALUES ('9', 'NO', 'NO', 'NO', '', '', 'Not Classified', 'Not Classified', '4', '2014-09-09', '09:09:09', '2', '9', '9');
INSERT INTO SIEVE VALUES ('10', 'NO', 'NO', 'YES', '', '', 'Not Classified', 'Not Classified', '1', '2013-10-10', '10:10:10', '1', '10', '10');
CREATE TABLE SORT(
    SORT_ID VARCHAR(255),
    EYE_OPEN VARCHAR(255),
    VERBAL_RESPONSE VARCHAR(255),
    MOTOR_RESPONSE VARCHAR(255),
    GLASGOW_COMA_SCORE VARCHAR(255),
    GCS_VALUE VARCHAR(255),
    RESPIRATORY_RATE VARCHAR(255),
    SYSTILIC_BLOOD_PRESURE VARCHAR(255),
    SCORE VARCHAR(255),
    PRIORITY VARCHAR(255),
    SYMPTOMS VARCHAR(255),
    INJURIES VARCHAR(255),
    SORT_DATE VARCHAR(255),
    SORT_TIME VARCHAR(255),
    GEO_ID VARCHAR(255),
    USER_ID VARCHAR(255),
    PATIENT_ID VARCHAR(255),
    CONSTRAINT PK_SORT PRIMARY KEY (SORT_ID),
    CONSTRAINT FK_SORT_USERS FOREIGN KEY (USER_ID) REFERENCES USERS (USER_ID) ON DELETE CASCADE,
    CONSTRAINT FK_SORT_PATIENTS FOREIGN KEY (PATIENT_ID) REFERENCES PATIENTS (PATIENT_ID) ON DELETE CASCADE,
    CONSTRAINT FK_SORT_GEOINFO FOREIGN KEY (GEO_ID) REFERENCES GEOINFO (GEO_ID) ON DELETE CASCADE
);
INSERT INTO SORT VALUES ('1', '4', '5', '6', '15', '4', '4', '4', '12', '3', 'Diarrhoea', 'Bites', '2014-01-01', '01:01:01', '1', '10', '11');
INSERT INTO SORT VALUES ('2', '3', '4', '5', '12', '3', '3', '3', '9', '1', 'Ferver', 'Burns', '2014-02-02', '02:02:02', '2', '9', '12');
INSERT INTO SORT VALUES ('3', '2', '3', '4', '9', '3', '2', '2', '7', '1', 'Headache', 'Cardiovascular Problems', '2014-03-03', '03:03:03', '3', '8', '13');
INSERT INTO SORT VALUES ('4', '1', '2', '3', '6', '2', '1', '1', '4', '1', 'Joint/MuscleAche', 'Fracture/Sprain/Dislocation', '2014-04-04', '04:04:04', '4', '7', '14');
INSERT INTO SORT VALUES ('5', '4', '1', '2', '7', '2', '1', '4', '7', '1', 'Rash', 'Heart Related Condition', '2014-05-05', '05:05:05', '5', '6', '15');
INSERT INTO SORT VALUES ('6', '3', '5', '1', '9', '3', '2', '3', '8', '1', 'Seizure', 'Hypothemia', '2014-06-06', '06:06:06', '6', '5', '16');
INSERT INTO SORT VALUES ('7', '2', '4', '6', '12', '3', '3', '2', '8', '1', 'Unconsciousness', 'Laceration', '2014-07-07', '07:07:07', '7', '4', '17');
INSERT INTO SORT VALUES ('8', '1', '3', '5', '9', '3', '4', '1', '8', '1', 'Vomiting', 'Wounds', '2014-08-08', '08:08:08', '8', '3', '18');
INSERT INTO SORT VALUES ('9', '4', '2', '4', '10', '3', '1', '4', '8', '1', 'Not Classified', 'Not Classified', '2014-09-09', '09:09:09', '9', '2', '19');
INSERT INTO SORT VALUES ('10', '3', '1', '3', '7', '2', '2', '3', '12', '3', 'Not Classified', 'Not Classified', '2013-10-10', '10:10:10', '10', '1', '20');