# Query
### CREATE
CREATE TABLE jUnitTbl (
    id serial PRIMARY KEY,
    content text
);
### INSERT
INSERT INTO jUnitTbl (id, content) VALUES (1, 'jUnitTbl 01');
INSERT INTO jUnitTbl (id, content) VALUES (2, 'jUnitTbl 02');
INSERT INTO jUnitTbl (id, content) VALUES (3, 'jUnitTbl 03');
INSERT INTO jUnitTbl (id, content) VALUES (4, 'jUnitTbl 04');
INSERT INTO jUnitTbl (id, content) VALUES (5, 'jUnitTbl 05');
### GRANT
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE jUnitTbl TO testuser;
### COMMIT
commit;