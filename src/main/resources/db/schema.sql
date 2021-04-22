-- CREATE USER 'dbadmin'@'localhost' IDENTIFIED BY 'ticketmart';
-- CREATE SCHEMA ticketmartdb;
-- GRANT ALL PRIVILEGES ON ticketmartdb.* TO 'dbadmin'@'localhost';
-- FLUSH PRIVILEGES;
-- USE ticketmartdb;

-- Person/group performing in the event
CREATE TABLE participant (
  idParticipant  INT          NOT NULL AUTO_INCREMENT,
  name           VARCHAR(45)  NOT NULL,
  description    VARCHAR(255) NULL,
  CONSTRAINT PK_idParticipant   PRIMARY KEY (idParticipant)
);

-- The venue for an event to be held in
CREATE TABLE venue (
  idVenue        INT          NOT NULL AUTO_INCREMENT,
  name           VARCHAR(45)  NOT NULL,
  address        VARCHAR(45)  NOT NULL,
  city           VARCHAR(45)  NOT NULL,
  country        VARCHAR(45)  NOT NULL,
  CONSTRAINT PK_idVenue         PRIMARY KEY (idVenue)
);

-- The details of the event that requires ticketing system
CREATE TABLE event (
  idEvent        INT          NOT NULL AUTO_INCREMENT,
  name           VARCHAR(45)  NOT NULL,
  description    VARCHAR(255) NULL,
  date           DATETIME     NOT NULL,
  idVenue        INT          NOT NULL,
  CONSTRAINT PK_idEvent         PRIMARY KEY (idEvent),
  CONSTRAINT FK_idVenue         FOREIGN KEY (idVenue)  REFERENCES venue(idVenue)
);

CREATE TABLE event_has_participant (
  idEvent        INT          NOT NULL,
  idParticipant  INT          NOT NULL,
  CONSTRAINT PK_idEvent_idParticipant   PRIMARY KEY (idEvent, idParticipant),
  CONSTRAINT FK_Event_Participant       FOREIGN KEY (idEvent)       REFERENCES event(idEvent),
  CONSTRAINT FK_Participant_Event       FOREIGN KEY (idParticipant) REFERENCES participant(idParticipant)
);

-- A list of possible statuses for tickets:
-- eg. AVAILABLE, RESERVED, SOLD
CREATE TABLE status (
  idStatus      INT           NOT NULL AUTO_INCREMENT,
  status        VARCHAR(45)   NOT NULL,
  description   VARCHAR(255)  NULL,
  CONSTRAINT PK_idStatus        PRIMARY KEY (idStatus)
);

-- A list of ticket sections in a venue for an event:
-- eg. GENERAL A, SILVER B, PLATINUM C
CREATE TABLE section (
  idSection     INT           NOT NULL AUTO_INCREMENT,
  name          VARCHAR(20)   NOT NULL,
  totalCapacity INT           NOT NULL,
  cost          DECIMAL(4,2)  NOT NULL,
  idEvent       INT           NOT NULL,
  CONSTRAINT PK_idSection       PRIMARY KEY (idSection),
  CONSTRAINT FK_Event           FOREIGN KEY (idEvent)  REFERENCES event(idEvent)
);

-- A list of tickets for each section
CREATE TABLE ticket (
  idTicket      BIGINT(10)    NOT NULL AUTO_INCREMENT,
  seatNumber    VARCHAR(10)   NOT NULL,
  idStatus      INT           NOT NULL,
  idSection     INT           NOT NULL, 
  CONSTRAINT PK_idTicket        PRIMARY KEY (idTicket),
  CONSTRAINT FK_Status          FOREIGN KEY (idStatus)  REFERENCES status(idStatus),
  CONSTRAINT FK_Section         FOREIGN KEY (idSection) REFERENCES section(idSection)
);



