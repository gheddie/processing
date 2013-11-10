CREATE SEQUENCE processing_id_seq;

CREATE TABLE process_instance (
	id INTEGER DEFAULT NEXTVAL('processing_id_seq'),
	name VARCHAR(32),
	state VARCHAR(32),
	creationDate timestamp,
	primary key (id)
	);
	
CREATE TABLE process_task (
	id INTEGER DEFAULT NEXTVAL('processing_id_seq'),
	name VARCHAR(32),
	processId INTEGER,
	primary key (id)
	);

ALTER TABLE process_task ADD FOREIGN KEY (processId) REFERENCES process_instance (id);