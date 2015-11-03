**************************************************************************************************************
DAS_Acumen database scripts
**************************************************************************************************************
Prereq to run script:
 -Must have postgresql v9.3 installed
 -Have main/first user called "postgres" and password is "password"
 -Must have psql.exe in PATH variable
	-psql should be where you installed postgres, in the bin folder
	-To put it in PATH go to control panel -> search for "environment" -> go to edit evironment variables 
	(NEED AN ADMIN TO DO THIS)
	 -> copy the bin folder path -> edit the PATH variable -> paste the bin path with ";" at the start
 -Configure pgpass.conf correctly in "C:\Users\USERNAME\AppData\Roaming\postgresql" like below:

  localhost:5432:DAS_Acumen:das:das
  localhost:5432:DAS_Acumen_Test:das:das
  localhost:5432:*:postgres:password
	
After you have done this just go to the database scripts folder and run the:

	CreateMainDb.bat

This will create everything for you by running the psql command line program and then feed in the files 
CreateAll1.txt and CreateAll2.txt.To delete everything you need to make sure you are not connected to the 
database (close pgadmin especially) then run the bat:

	DropMainDb.bat
	
This will drop the whole database and the das user.

permanent and test data**************************************************************************************
The permanent_data and test_data folders hold data for the dbs to use. The permanent data holds user data type and 
user data cat rows. The test_data folder holds party, user data and asset rows. Permanent data is inserted into both
main and test db whereas the test data is only inserted into the main db because of demo purposes.

Test Database ***********************************************************************************************
To make the testdb just run:

CreateTestDb.bat

To drop the testdb just run:

DropTestDb.bat

These run very similar to the main db bat files. The test db will be run with all unit and integration tests, 
the scripts set-up a database called "Das_Acumen_Test"

**************************************************************************************************************
DAS_Acumen database design files
**************************************************************************************************************
In the database_design folder holds the dbdesignerfork files:

Database_design_current.xml - This is the current implementation of the database.
Database_design_master.xml - This is an outdated full implementation of the database that is used to help
current implementation.

To view these files download DBDesignerFork from sourceforge and open them with the tool.

To replace the current scripts export the files using the tool. Then find and replace serial with BIGSERIAL
I believe this is to handle the 'long' type for the ids. Also add 'acumen.' as a prefix to all the tables
(usually after REFERENCES). 
**************************************************************************************************************
