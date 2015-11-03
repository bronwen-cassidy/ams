-- Database: "DAS_Acumen"

CREATE DATABASE "DAS_Acumen_Test"
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TEMPLATE = template0
       TABLESPACE = pg_default
       LC_COLLATE = 'English_United States.1252'
       LC_CTYPE = 'English_United States.1252'
       CONNECTION LIMIT = -1;
       
--Linux equivalent for lc collate and lc type for linux postgres: en_US.UTF-8
--Windows lc collate and lc type values for windows postgres: English_United States.1252

CREATE ROLE das LOGIN
  PASSWORD 'das'
  NOSUPERUSER INHERIT NOCREATEDB NOCREATEROLE NOREPLICATION;
  
  --Remove this for Linux postgres: NOREPLICATION
  --Add NOREPLIcation for windows



