
insert into status(status,description) values ('AVAILABLE','Ticket available for purchase');
insert into status(status,description) values ('RESERVED','Ticket on process of being purchased');
insert into status(status,description) values ('SOLD','Ticket already sold');

insert into participant(name,description) values('Metallica', 'American heavy metal band');
insert into participant(name,description) values('Therion', 'Sweedish symphonic metal band');
insert into participant(name,description) values('Harry Styles', 'Pop artist');
insert into participant(name,description) values('Billie Eilish', 'Pop artist');
insert into participant(name,description) values('The Weeknd', 'Singer');
insert into participant(name,description) values('Justin Bieber', 'Pop artist');
insert into participant(name,description) values('Mago de Oz', 'Spanish folk metal band');
insert into participant(name,description) values('Enanitos Verdes', 'Argentinian rock band');
insert into participant(name,description) values('5 Seconds Of Summer', 'Australian pop rock band');


insert into venue(name, website, phone_number, address, city, country) 
values('Foro Sol','https://forosol.mx/','(55) 5237 9999 ext. 71008','Viaducto Río de la Piedad S/N, Granjas Mexico','CDMX','Mexico');
insert into venue(name, website, phone_number, address, city, country) 
values('Foro DiDi','http://www.forodidi.com/','52 (81) 2133 5630','Pabellón Melchor Ocampo #130, Col Centro','Monterrey, NL','Mexico');
insert into venue(name, website, phone_number, address, city, country) 
values('Auditorio Telmex','https://www.auditorio-telmex.com/','(33) 3818 3800','Obreros de Cananea #747, Col. Villas de los Belenes','Zapopan, JAL','Mexico');
insert into venue(name, website, phone_number, address, city, country) 
values('Auditorio Nacional','http://www.auditorio.com.mx/','(55) 9138-1350','Paseo de la Reforma #50 Col. Polanco, V Sección','CDMX','Mexico');
insert into venue(name, website, phone_number, address, city, country) 
values('Estadio Azteca','https://www.estadioazteca.com.mx/','(55) 5487 3100','Calzada de Tlalpan #3665 Col. Santa Úrsula Coapa','CDMX','Mexico');
insert into venue(name, website, phone_number, address, city, country) 
values('Auditorio Pabellón M','http://www.auditoriopabellonm.com/','52 (81) 2133 5630','Avenida Constitución S/N, Col. Centro','Monterrey, NL','Mexico');
insert into venue(name, website, phone_number, address, city, country) 
values('Arena Monterrey','http://www.arenamonterrey.com/','(81) 15 15 41 00','Av. Francisco I. Madero #2500, Centro','Monterrey, NL','Mexico');
insert into venue(name, website, phone_number, address, city, country) 
values('Forum de Mundo Imperial','https://www.forummundoimperial.com/','744.466.33.22','Boulevard de Las Naciones S/N, esq. Boulevard Barra Vieja, Col. Plan de los Amates','Acapulco, GRO','Mexico');
insert into venue(name, website, phone_number, address, city, country) 
values('El Domo','http://eldomosanluis.com/','(444) 799 3013','Av. Prol. Francisco Martínez de la Vega #255','San Luis Potosí','Mexico');

insert into event(name,description,date,idVenue) values('Metallica World Tour','First day','2021-12-16',1);
insert into event(name,description,date,idVenue) values('Metallica World Tour','Second day','2021-12-17',1);
insert into event(name,description,date,idVenue) values('Therion Lemuria','2021 World Tour','2021-11-16',1);

insert into section(name,totalCapacity,cost,idEvent) values('GENERAL A', 100,50,1);
insert into section(name,totalCapacity,cost,idEvent) values('GENERAL B', 100,50,1);
insert into section(name,totalCapacity,cost,idEvent) values('SILVER A',  100,60,1);
insert into section(name,totalCapacity,cost,idEvent) values('SILVER B',  100,70,1);
insert into section(name,totalCapacity,cost,idEvent) values('PLATINUM A',100,100,1);
insert into section(name,totalCapacity,cost,idEvent) values('PLATINUM B',100,110,1);

insert into section(name,totalCapacity,cost,idEvent) values('GENERAL A', 100,50,2);
insert into section(name,totalCapacity,cost,idEvent) values('GENERAL B', 100,50,2);
insert into section(name,totalCapacity,cost,idEvent) values('SILVER A',  100,60,2);
insert into section(name,totalCapacity,cost,idEvent) values('SILVER B',  100,70,2);
insert into section(name,totalCapacity,cost,idEvent) values('PLATINUM A',100,100,2);
insert into section(name,totalCapacity,cost,idEvent) values('PLATINUM B',100,110,2);

insert into section(name,totalCapacity,cost,idEvent) values('GENERAL A',100,50,3);
insert into section(name,totalCapacity,cost,idEvent) values('GENERAL B',100,50,3);
insert into section(name,totalCapacity,cost,idEvent) values('SILVER',   200,60,3);
insert into section(name,totalCapacity,cost,idEvent) values('PLATINUM', 200,110,3);

insert into event_has_participant(idEvent, idParticipant) values(1,1);
insert into event_has_participant(idEvent, idParticipant) values(2,1);
insert into event_has_participant(idEvent, idParticipant) values(3,2);

-- 1st event tickets
insert into ticket(seatNumber,idStatus,idSection) values('1GA', 1,1);
insert into ticket(seatNumber,idStatus,idSection) values('2GA' ,1,1);
insert into ticket(seatNumber,idStatus,idSection) values('3GA' ,1,1);
insert into ticket(seatNumber,idStatus,idSection) values('4GA' ,1,1);
insert into ticket(seatNumber,idStatus,idSection) values('5GA' ,1,1);
insert into ticket(seatNumber,idStatus,idSection) values('6GA' ,1,1);
insert into ticket(seatNumber,idStatus,idSection) values('7GA' ,1,1);
insert into ticket(seatNumber,idStatus,idSection) values('8GA' ,1,1);
insert into ticket(seatNumber,idStatus,idSection) values('9GA' ,1,1);
insert into ticket(seatNumber,idStatus,idSection) values('10GA',1,1);
insert into ticket(seatNumber,idStatus,idSection) values('1GB' ,1,2);
insert into ticket(seatNumber,idStatus,idSection) values('2GB', 1,2);
insert into ticket(seatNumber,idStatus,idSection) values('3GB', 1,2);
insert into ticket(seatNumber,idStatus,idSection) values('4GB', 1,2);
insert into ticket(seatNumber,idStatus,idSection) values('5GB', 1,2);
insert into ticket(seatNumber,idStatus,idSection) values('6GB', 1,2);
insert into ticket(seatNumber,idStatus,idSection) values('7GB', 1,2);
insert into ticket(seatNumber,idStatus,idSection) values('8GB', 1,2);
insert into ticket(seatNumber,idStatus,idSection) values('9GB', 1,2);
insert into ticket(seatNumber,idStatus,idSection) values('10GB',1,2);
insert into ticket(seatNumber,idStatus,idSection) values('1SA', 1,3);
insert into ticket(seatNumber,idStatus,idSection) values('2SA', 1,3);
insert into ticket(seatNumber,idStatus,idSection) values('3SA', 1,3);
insert into ticket(seatNumber,idStatus,idSection) values('4SA', 1,3);
insert into ticket(seatNumber,idStatus,idSection) values('5SA', 1,3);
insert into ticket(seatNumber,idStatus,idSection) values('6SA', 1,3);
insert into ticket(seatNumber,idStatus,idSection) values('7SA', 1,3);
insert into ticket(seatNumber,idStatus,idSection) values('8SA', 1,3);
insert into ticket(seatNumber,idStatus,idSection) values('9SA', 1,3);
insert into ticket(seatNumber,idStatus,idSection) values('10SA',1,3);
insert into ticket(seatNumber,idStatus,idSection) values('1SB', 1,4);
insert into ticket(seatNumber,idStatus,idSection) values('2SB', 1,4);
insert into ticket(seatNumber,idStatus,idSection) values('3SB', 1,4);
insert into ticket(seatNumber,idStatus,idSection) values('4SB', 1,4);
insert into ticket(seatNumber,idStatus,idSection) values('5SB', 1,4);
insert into ticket(seatNumber,idStatus,idSection) values('6SB', 1,4);
insert into ticket(seatNumber,idStatus,idSection) values('7SB', 1,4);
insert into ticket(seatNumber,idStatus,idSection) values('8SB', 1,4);
insert into ticket(seatNumber,idStatus,idSection) values('9SB', 1,4);
insert into ticket(seatNumber,idStatus,idSection) values('10SB',1,4);
insert into ticket(seatNumber,idStatus,idSection) values('1PA', 1,5);
insert into ticket(seatNumber,idStatus,idSection) values('2PA', 1,5);
insert into ticket(seatNumber,idStatus,idSection) values('3PA', 1,5);
insert into ticket(seatNumber,idStatus,idSection) values('4PA', 1,5);
insert into ticket(seatNumber,idStatus,idSection) values('5PA', 1,5);
insert into ticket(seatNumber,idStatus,idSection) values('6PA', 1,5);
insert into ticket(seatNumber,idStatus,idSection) values('7PA', 1,5);
insert into ticket(seatNumber,idStatus,idSection) values('8PA', 1,5);
insert into ticket(seatNumber,idStatus,idSection) values('9PA', 1,5);
insert into ticket(seatNumber,idStatus,idSection) values('10PA',1,5);
insert into ticket(seatNumber,idStatus,idSection) values('1PB', 1,6);
insert into ticket(seatNumber,idStatus,idSection) values('2PB', 1,6);
insert into ticket(seatNumber,idStatus,idSection) values('3PB', 1,6);
insert into ticket(seatNumber,idStatus,idSection) values('4PB', 1,6);
insert into ticket(seatNumber,idStatus,idSection) values('5PB', 1,6);
insert into ticket(seatNumber,idStatus,idSection) values('6PB', 1,6);
insert into ticket(seatNumber,idStatus,idSection) values('7PB', 1,6);
insert into ticket(seatNumber,idStatus,idSection) values('8PB', 1,6);
insert into ticket(seatNumber,idStatus,idSection) values('9PB', 1,6);
insert into ticket(seatNumber,idStatus,idSection) values('10PB',1,6);

-- 2nd event tickets
insert into ticket(seatNumber,idStatus,idSection) values('1GA', 1,7);
insert into ticket(seatNumber,idStatus,idSection) values('2GA' ,1,7);
insert into ticket(seatNumber,idStatus,idSection) values('3GA' ,1,7);
insert into ticket(seatNumber,idStatus,idSection) values('4GA' ,1,7);
insert into ticket(seatNumber,idStatus,idSection) values('5GA' ,1,7);
insert into ticket(seatNumber,idStatus,idSection) values('6GA' ,1,7);
insert into ticket(seatNumber,idStatus,idSection) values('7GA' ,1,7);
insert into ticket(seatNumber,idStatus,idSection) values('8GA' ,1,7);
insert into ticket(seatNumber,idStatus,idSection) values('9GA' ,1,7);
insert into ticket(seatNumber,idStatus,idSection) values('10GA',1,7);
insert into ticket(seatNumber,idStatus,idSection) values('1GB' ,1,8);
insert into ticket(seatNumber,idStatus,idSection) values('2GB', 1,8);
insert into ticket(seatNumber,idStatus,idSection) values('3GB', 1,8);
insert into ticket(seatNumber,idStatus,idSection) values('4GB', 1,8);
insert into ticket(seatNumber,idStatus,idSection) values('5GB', 1,8);
insert into ticket(seatNumber,idStatus,idSection) values('6GB', 1,8);
insert into ticket(seatNumber,idStatus,idSection) values('7GB', 1,8);
insert into ticket(seatNumber,idStatus,idSection) values('8GB', 1,8);
insert into ticket(seatNumber,idStatus,idSection) values('9GB', 1,8);
insert into ticket(seatNumber,idStatus,idSection) values('10GB',1,8);
insert into ticket(seatNumber,idStatus,idSection) values('1SA', 1,9);
insert into ticket(seatNumber,idStatus,idSection) values('2SA', 1,9);
insert into ticket(seatNumber,idStatus,idSection) values('3SA', 1,9);
insert into ticket(seatNumber,idStatus,idSection) values('4SA', 1,9);
insert into ticket(seatNumber,idStatus,idSection) values('5SA', 1,9);
insert into ticket(seatNumber,idStatus,idSection) values('6SA', 1,9);
insert into ticket(seatNumber,idStatus,idSection) values('7SA', 1,9);
insert into ticket(seatNumber,idStatus,idSection) values('8SA', 1,9);
insert into ticket(seatNumber,idStatus,idSection) values('9SA', 1,9);
insert into ticket(seatNumber,idStatus,idSection) values('10SA',1,9);
insert into ticket(seatNumber,idStatus,idSection) values('1SB', 1,10);
insert into ticket(seatNumber,idStatus,idSection) values('2SB', 1,10);
insert into ticket(seatNumber,idStatus,idSection) values('3SB', 1,10);
insert into ticket(seatNumber,idStatus,idSection) values('4SB', 1,10);
insert into ticket(seatNumber,idStatus,idSection) values('5SB', 1,10);
insert into ticket(seatNumber,idStatus,idSection) values('6SB', 1,10);
insert into ticket(seatNumber,idStatus,idSection) values('7SB', 1,10);
insert into ticket(seatNumber,idStatus,idSection) values('8SB', 1,10);
insert into ticket(seatNumber,idStatus,idSection) values('9SB', 1,10);
insert into ticket(seatNumber,idStatus,idSection) values('10SB',1,10);
insert into ticket(seatNumber,idStatus,idSection) values('1PA', 1,11);
insert into ticket(seatNumber,idStatus,idSection) values('2PA', 1,11);
insert into ticket(seatNumber,idStatus,idSection) values('3PA', 1,11);
insert into ticket(seatNumber,idStatus,idSection) values('4PA', 1,11);
insert into ticket(seatNumber,idStatus,idSection) values('5PA', 1,11);
insert into ticket(seatNumber,idStatus,idSection) values('6PA', 1,11);
insert into ticket(seatNumber,idStatus,idSection) values('7PA', 1,11);
insert into ticket(seatNumber,idStatus,idSection) values('8PA', 1,11);
insert into ticket(seatNumber,idStatus,idSection) values('9PA', 1,11);
insert into ticket(seatNumber,idStatus,idSection) values('10PA',1,11);
insert into ticket(seatNumber,idStatus,idSection) values('1PB', 1,12);
insert into ticket(seatNumber,idStatus,idSection) values('2PB', 1,12);
insert into ticket(seatNumber,idStatus,idSection) values('3PB', 1,12);
insert into ticket(seatNumber,idStatus,idSection) values('4PB', 1,12);
insert into ticket(seatNumber,idStatus,idSection) values('5PB', 1,12);
insert into ticket(seatNumber,idStatus,idSection) values('6PB', 1,12);
insert into ticket(seatNumber,idStatus,idSection) values('7PB', 1,12);
insert into ticket(seatNumber,idStatus,idSection) values('8PB', 1,12);
insert into ticket(seatNumber,idStatus,idSection) values('9PB', 1,12);
insert into ticket(seatNumber,idStatus,idSection) values('10PB',1,12);

-- 3rd event tickets
insert into ticket(seatNumber,idStatus,idSection) values('1GA', 1,13);
insert into ticket(seatNumber,idStatus,idSection) values('2GA' ,1,13);
insert into ticket(seatNumber,idStatus,idSection) values('3GA' ,1,13);
insert into ticket(seatNumber,idStatus,idSection) values('4GA' ,1,13);
insert into ticket(seatNumber,idStatus,idSection) values('5GA' ,1,13);
insert into ticket(seatNumber,idStatus,idSection) values('6GA' ,1,13);
insert into ticket(seatNumber,idStatus,idSection) values('7GA' ,1,13);
insert into ticket(seatNumber,idStatus,idSection) values('8GA' ,1,13);
insert into ticket(seatNumber,idStatus,idSection) values('9GA' ,1,13);
insert into ticket(seatNumber,idStatus,idSection) values('10GA',1,13);
insert into ticket(seatNumber,idStatus,idSection) values('1GB' ,1,14);
insert into ticket(seatNumber,idStatus,idSection) values('2GB', 1,14);
insert into ticket(seatNumber,idStatus,idSection) values('3GB', 1,14);
insert into ticket(seatNumber,idStatus,idSection) values('4GB', 1,14);
insert into ticket(seatNumber,idStatus,idSection) values('5GB', 1,14);
insert into ticket(seatNumber,idStatus,idSection) values('6GB', 1,14);
insert into ticket(seatNumber,idStatus,idSection) values('7GB', 1,14);
insert into ticket(seatNumber,idStatus,idSection) values('8GB', 1,14);
insert into ticket(seatNumber,idStatus,idSection) values('9GB', 1,14);
insert into ticket(seatNumber,idStatus,idSection) values('10GB',1,14);
insert into ticket(seatNumber,idStatus,idSection) values('1S',  1,15);
insert into ticket(seatNumber,idStatus,idSection) values('2S',  1,15);
insert into ticket(seatNumber,idStatus,idSection) values('3S',  1,15);
insert into ticket(seatNumber,idStatus,idSection) values('4S',  1,15);
insert into ticket(seatNumber,idStatus,idSection) values('5S',  1,15);
insert into ticket(seatNumber,idStatus,idSection) values('6S',  1,15);
insert into ticket(seatNumber,idStatus,idSection) values('7S',  1,15);
insert into ticket(seatNumber,idStatus,idSection) values('8S',  1,15);
insert into ticket(seatNumber,idStatus,idSection) values('9S',  1,15);
insert into ticket(seatNumber,idStatus,idSection) values('10S', 1,15);
insert into ticket(seatNumber,idStatus,idSection) values('1P', 1,16);
insert into ticket(seatNumber,idStatus,idSection) values('2P', 1,16);
insert into ticket(seatNumber,idStatus,idSection) values('3P', 1,16);
insert into ticket(seatNumber,idStatus,idSection) values('4P', 1,16);
insert into ticket(seatNumber,idStatus,idSection) values('5P', 1,16);
insert into ticket(seatNumber,idStatus,idSection) values('6P', 1,16);
insert into ticket(seatNumber,idStatus,idSection) values('7P', 1,16);
insert into ticket(seatNumber,idStatus,idSection) values('8P', 1,16);
insert into ticket(seatNumber,idStatus,idSection) values('9P', 1,16);
insert into ticket(seatNumber,idStatus,idSection) values('10P',1,16);
