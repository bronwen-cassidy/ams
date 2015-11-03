-- Suppliers
-- Ace Suppliers
INSERT INTO acumen.parties (
				id, uid, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (1, 'das_pt_9186916876332847871',1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.companies (
				parties_id, name, reg_no, vat_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date) 
		VALUES (1, 'Ace Suppliers Ltd', '1234', '929293', 1, 'parties_test_data', now(), 'parties_test_data', now());
INSERT INTO acumen.party_type(
	            id, parties_id, type_name, type_of_supplier, tenant_id, created_by, 
	            created_date, last_updated_by, last_updated_date, version_number)
	    VALUES (1, 1, 'SUPPLIER', null, 1, 'party_script', 
	            now(), 'party_script', now(), 0);
INSERT INTO acumen.emails (
				id, email_address, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (1, 'Ace_Suppliers@hotmail.com', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_emails (
				id, parties_id, emails_id, emails_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (1, 1, 1, 171, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.tel_numbers (
				id, tel_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (1, '0161 1234567', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_telephone_numbers (
				id, parties_id, tel_numbers_id, tel_numbers_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (1, 1, 1, 174, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.addresses (
				id, address_line_1, address_line_2, address_line_3, zip_post_code, country, type_of_place, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (1, 'Number 1', 'Manchester Road', 'Manchester', 'M45 1NM', 'England', 'Warehouse', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_addresses (
				id, addresses_id, parties_id, address_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (1, 1, 1, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.bank_details (
				id, bank_name, bank_address, account_number, sort_code, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (1, 'Halifax', 'Manchester', 12345678, 010201, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_bank_details (
				id, bank_details_id, parties_id, account_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (1, 1, 1, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_contact_details (
				id, parties_id, party_addresses_id, party_telephone_numbers_id, party_emails_id, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (1, 1, 1, 1, 1, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);

-- Clients
-- Lime House Residential Home
INSERT INTO acumen.parties (
				id, uid, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (2, 'das_pt_9186916876332847871',1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.companies (
				parties_id, name, reg_no, vat_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date) 
		VALUES (2, 'Lime House', '1234', '929293', 1, 'parties_test_data', now(), 'parties_test_data', now());
INSERT INTO acumen.party_type(
            	id, parties_id, type_name, type_of_supplier, tenant_id, created_by, 
            	created_date, last_updated_by, last_updated_date, version_number)
    	VALUES (2, 2, 'CLIENT', null, 1, 'party_script', 
            	now(), 'party_script', now(), 0);
INSERT INTO acumen.emails (
				id, email_address, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (2, 'Lime_house@hotmail.com', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_emails (
				id, parties_id, emails_id, emails_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (2, 2, 2, 171, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.tel_numbers (
				id, tel_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (2, '01942 123456', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_telephone_numbers (
				id, parties_id, tel_numbers_id, tel_numbers_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (2, 2, 2, 174, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.addresses (
				id, address_line_1, address_line_2, address_line_3, zip_post_code, country, type_of_place, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (2, 'Lime House', 'Newton Road', 'Lowton', 'WA3 3AW', 'England', 'Residential Home', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_addresses (
				id, addresses_id, parties_id, address_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (2, 2, 2, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.bank_details (
				id, bank_name, bank_address, account_number, sort_code, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (2, 'Halifax', 'Warrington', 12345678, 101123, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_bank_details (
				id, bank_details_id, parties_id, account_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (2, 2, 2, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_contact_details (
				id, parties_id, party_addresses_id, party_telephone_numbers_id, party_emails_id, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (2, 2, 2, 2, 2, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);

-- Custodians
INSERT INTO acumen.parties (
				id, uid, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (3, 'das_pt_7416396282453037280', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.people (
				parties_id, forenames, surname, gender, tenant_id, created_by, created_date, last_updated_by, last_updated_date) 
		VALUES (3, 'James', 'Brown', 'M', 1, 'parties_test_data', now(), 'parties_test_data', now());
INSERT INTO acumen.party_type(
            	id, parties_id, type_name, type_of_supplier, tenant_id, created_by, 
            	created_date, last_updated_by, last_updated_date, version_number)
    	VALUES (3, 3, 'CUSTODIAN', null, 1, 'party_script', 
            	now(), 'party_script', now(), 0);
INSERT INTO acumen.emails (
				id, email_address, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (3, 'James_Brown@hotmail.com', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_emails (
				id, parties_id, emails_id, emails_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (3, 3, 3, 170, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);		
INSERT INTO acumen.tel_numbers (
				id, tel_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (3, '0161 2345678', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_telephone_numbers (
				id, parties_id, tel_numbers_id, tel_numbers_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (3, 3, 3, 173, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.addresses (
				id, address_line_1, address_line_2, address_line_3, zip_post_code, country, type_of_place, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (3, 'Number 5', 'Chester Street', 'Chester', 'C25 1EF', 'England', 'house', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_addresses (
				id, addresses_id, parties_id, address_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (3, 3, 3, 'home', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1); 
INSERT INTO acumen.bank_details (
				id, bank_name, bank_address, account_number, sort_code, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (3, 'Santander', 'Chester', 23456789, 020301, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_bank_details (
				id, bank_details_id, parties_id, account_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (3, 3, 3, 'personal', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_contact_details (
				id, parties_id, party_addresses_id, party_telephone_numbers_id, party_emails_id, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (3, 3, 3, 3, 3, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);

INSERT INTO acumen.parties (
				id, uid, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (4, 'das_pt_4300577365449773093', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.people (
				parties_id, forenames, surname, gender, tenant_id, created_by, created_date, last_updated_by, last_updated_date) 
		VALUES (4, 'William', 'Wilson', 'M', 1, 'parties_test_data', now(), 'parties_test_data', now());
INSERT INTO acumen.party_type(
		    	id, parties_id, type_name, type_of_supplier, tenant_id, created_by, 
		    	created_date, last_updated_by, last_updated_date, version_number)
		VALUES (4, 4, 'CUSTODIAN', null, 1, 'party_script', 
		    	now(), 'party_script', now(), 0);
INSERT INTO acumen.emails (
				id, email_address, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (4, 'William_Wilson@hotmail.com', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_emails (
				id, parties_id, emails_id, emails_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (4, 4, 4, 171, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.tel_numbers (
				id, tel_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (4, '0161 4567890', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_telephone_numbers (
				id, parties_id, tel_numbers_id, tel_numbers_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (4, 4, 4, 174, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.addresses (
				id, address_line_1, address_line_2, address_line_3, zip_post_code, country, type_of_place, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (4, 'Number 205', 'Coventry Avenue', 'Coventry', 'CL9 8FQ', 'England', 'Warehouse', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_addresses (
				id, addresses_id, parties_id, address_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (4, 4, 4, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.bank_details (
				id, bank_name, bank_address, account_number, sort_code, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (4, 'Natwest', 'Coventry', 45678901, 040501, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_bank_details (
				id, bank_details_id, parties_id, account_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (4, 4, 4, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_contact_details (
				id, parties_id, party_addresses_id, party_telephone_numbers_id, party_emails_id, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (4, 4, 4, 4, 4, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);

-- Internal Resources
INSERT INTO acumen.parties (
				id, uid, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (5, 'das_pt_9737360180714830293',1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.internal_resources (
				parties_id, report_to, forenames, surname, gender, tenant_id, created_by, created_date, last_updated_by, last_updated_date)
		VALUES (5, 'Mark', 'Homer', 'Simpson', 'M', 1, 'parties_test_data', now(), 'parties_test_data', now());
		
INSERT INTO acumen.parties (
				id, uid, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (6, 'das_pt_0317198975189456315',1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.internal_resources (
				parties_id, report_to, forenames, surname, gender, tenant_id, created_by, created_date, last_updated_by, last_updated_date)
		VALUES (6, 'James', 'Marge', 'Simpson', 'F', 1, 'parties_test_data', now(), 'parties_test_data', now());
		
INSERT INTO acumen.parties (
				id, uid, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (7, 'das_pt_0388162028662664287',1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.internal_resources (
				parties_id, report_to, forenames, surname, gender, tenant_id, created_by, created_date, last_updated_by, last_updated_date) 
		VALUES (7, 'Sue', 'Bart', 'Simpson', 'M', 1, 'parties_test_data', now(), 'parties_test_data', now());
		
INSERT INTO acumen.parties (
				id, uid, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (8, 'das_pt_1801706682599681531',1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.internal_resources (
				parties_id, report_to, forenames, surname, gender, tenant_id, created_by, created_date, last_updated_by, last_updated_date)
		VALUES (8, 'Alex', 'David', 'Cameron', 'M', 1, 'parties_test_data', now(), 'parties_test_data', now());
		
INSERT INTO acumen.parties (
				id, uid, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (9, 'das_pt_4523949585625999490',1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.internal_resources (
				parties_id, report_to, forenames, surname, gender, tenant_id, created_by, created_date, last_updated_by, last_updated_date)
		VALUES (9, 'Mark', 'Maisy', 'Jones', 'F', 1, 'parties_test_data', now(), 'parties_test_data', now());
