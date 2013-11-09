CREATE SEQUENCE processing_id_seq;

CREATE TABLE process_instance (
	id INTEGER DEFAULT NEXTVAL('processing_id_seq'),
	name VARCHAR(32),
	state VARCHAR(32),
	primary key (id)
	);