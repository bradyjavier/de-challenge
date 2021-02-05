DROP TABLE Console  IF exists;

CREATE TABLE Console (
ConsoleID INT IDENTITY NOT NULL PRIMARY KEY,
NameConsole VARCHAR(20),
Company_ID INT

) 

DROP TABLE Company  IF exists;

CREATE TABLE Company (
Company_ID INT IDENTITY NOT NULL PRIMARY KEY,
NameCompany VARCHAR(30)

) 

DROP TABLE Game IF exists;

CREATE TABLE Game (
NameGameID INT IDENTITY NOT NULL PRIMARY KEY,
NameGame VARCHAR(200)

) 

DROP TABLE Result IF exists;

CREATE TABLE Result ( 
ResultID INT IDENTITY NOT NULL PRIMARY KEY,
Metascore	INT, 
NameGame_ID INT,			
Console_ID  INT,
Userscore 	DECIMAL(10,1),
Fecha		DATE
)






