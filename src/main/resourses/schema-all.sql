DROP TABLE Company  IF exists;

CREATE TABLE Company (
Company_ID INT IDENTITY NOT NULL PRIMARY KEY,
NameCompany VARCHAR(30)
) 

DROP TABLE Console  IF exists;

CREATE TABLE Console (
ConsoleID INT IDENTITY NOT NULL PRIMARY KEY,
NameConsole VARCHAR(20),
CompanyID INT,
FOREIGN KEY (CompanyID) REFERENCES Company(Company_ID)
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
Fecha		DATE,
FOREIGN KEY (NameGame_ID) REFERENCES GAME(NameGameID),
FOREIGN KEY (Console_ID) REFERENCES Console(ConsoleID)
);

CREATE VIEW V_RESULT_BEST AS 

SELECT  Row_number() over() RN,
		Metascore, 
		REPLACE(NameGame,',','.') NameGame, 
		NameConsole, 
		Console_ID,
		Userscore, 
		Fecha,
		NameCompany
	    FROM  
	    	(SELECT  Metascore, 
	    			 NameGame_ID, 
	    			 Console_ID, 
	    			 Userscore, 
	    			 Fecha
	    			 FROM Result ORDER BY Metascore DESC ) A 
	    INNER JOIN Console 
	    ON A.Console_ID = Console.ConsoleID
	    INNER JOIN Game 
	    ON A.NameGame_ID = Game.NameGameID
	    INNER JOIN Company
	    ON Console.CompanyID = Company.Company_ID 


CREATE VIEW V_RESULT_WORST AS 

SELECT  Row_number() over() RN,
		Metascore, 
		REPLACE(NameGame,',','.') NameGame, 
		NameConsole, 
		Console_ID,
		Userscore, 
		Fecha,
		NameCompany
	    FROM  
	    	(SELECT  Metascore, 
	    			 NameGame_ID, 
	    			 Console_ID, 
	    			 Userscore, 
	    			 Fecha
	    			 FROM Result ORDER BY Metascore ASC ) A 
	    INNER JOIN Console 
	    ON A.Console_ID = Console.ConsoleID
	    INNER JOIN Game 
	    ON A.NameGame_ID = Game.NameGameID
	    INNER JOIN Company
	    ON Console.CompanyID = Company.Company_ID 



