DROP TABLE IF EXISTS contacts;
DROP SEQUENCE IF EXISTS id_seq;
CREATE SEQUENCE id_seq;
CREATE TABLE contacts (
	"id" bigint NOT NULL default nextval('id_seq'),
	"name" varchar NOT NULL,
	CONSTRAINT contacts_pk PRIMARY KEY ("id")
);
ALTER SEQUENCE id_seq owned by contacts.id;