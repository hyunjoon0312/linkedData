CREATE TABLE Rusers(
	Rid VARCHAR(12) NOT NULL,
	Rpasswd VARCHAR(255) NOT NULL,
	Rname VARCHAR(20) NOT NULL,
	Remail VARCHAR(30) NOT NULL,
	PRIMARY KEY(Rid)
	);
	
	INSERT INTO Rusers VALUES ('test', password('test'), 'joon', 'joon@gmail.com');