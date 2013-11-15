CREATE SEQUENCE processing_id_seq;

--drop table process_instance;
CREATE TABLE process_instance (
	id INTEGER DEFAULT NEXTVAL('processing_id_seq'),
	name VARCHAR(32),
	state VARCHAR(32),
	creationDate timestamp,
	primary key (id)
	);

--drop table process_item;
CREATE TABLE process_item (
	id INTEGER DEFAULT NEXTVAL('processing_id_seq'),
	name VARCHAR(32),
	processId INTEGER,
	state VARCHAR(32),
	expiryDate timestamp,
	itemType VARCHAR(32),
	primary key (id)
	);

ALTER TABLE process_item ADD FOREIGN KEY (processId) REFERENCES process_instance (id);