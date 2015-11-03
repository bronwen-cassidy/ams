--Supplier
--Kitchen Elite
INSERT INTO acumen.parties (
				id, uid, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (200, 'KE_135791113151719212325',1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.companies (
				parties_id, name, reg_no, vat_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date) 
		VALUES (200, 'Kitchen Elite', '1234', '098765', 1, 'parties_test_data', now(), 'parties_test_data', now());
INSERT INTO acumen.party_type(
	            id, parties_id, type_name, type_of_supplier, tenant_id, created_by, 
	            created_date, last_updated_by, last_updated_date, version_number)
	    VALUES (200, 200, 'SUPPLIER', null, 1, 'party_script', 
	            now(), 'party_script', now(), 0);
INSERT INTO acumen.emails (
				id, email_address, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (200, 'KitchenElite@kitchenelite.com', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_emails (
				id, parties_id, emails_id, emails_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (200, 200, 200, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);	
INSERT INTO acumen.tel_numbers (
				id, tel_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (200, '0161 1234567', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);		
INSERT INTO acumen.party_telephone_numbers (
				id, parties_id, tel_numbers_id, tel_numbers_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (200, 200, 200, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);	
INSERT INTO acumen.addresses (
				id, address_line_1, address_line_2, city, zip_post_code, country, type_of_place, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (200, 'Number 2', 'Irlam Road', 'Manchester', 'M43 1GK', 'England', 'Warehouse', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_addresses (
				id, addresses_id, parties_id, address_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (200, 200, 200, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);		
INSERT INTO acumen.bank_details (
				id, bank_name, bank_address, account_number, sort_code, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (200, 'RBS', 'Manchester', 97531357, 098234, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_bank_details (
				id, bank_details_id, parties_id, account_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (200, 200, 200, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_contact_details (
				id, parties_id, party_addresses_id, party_telephone_numbers_id, party_emails_id, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (200, 200, 200, 200, 200, 200, 'parties_test_data', now(), 'parties_test_data', now(), 1);


--electrolux
INSERT INTO acumen.parties (
				id, uid, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (201, 'ele_2468101214161830222',1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.companies (
				parties_id, name, reg_no, vat_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date) 
		VALUES (201, 'Electrolux', '1234', '024681', 1, 'parties_test_data', now(), 'parties_test_data', now());
INSERT INTO acumen.party_type(
	            id, parties_id, type_name, type_of_supplier, tenant_id, created_by, 
	            created_date, last_updated_by, last_updated_date, version_number)
	    VALUES (201, 201, 'SUPPLIER', null, 1, 'party_script', 
	            now(), 'party_script', now(), 0);
INSERT INTO acumen.emails (
				id, email_address, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (201, 'electrolux@electrolux.com', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_emails (
				id, parties_id, emails_id, emails_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (201, 201, 201, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);	
INSERT INTO acumen.tel_numbers (
				id, tel_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (201, '0161 3489185', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);		
INSERT INTO acumen.party_telephone_numbers (
				id, parties_id, tel_numbers_id, tel_numbers_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (201, 201, 201, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);	
INSERT INTO acumen.addresses (
				id, address_line_1, address_line_2, city, zip_post_code, country, type_of_place, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (201, 'Number 234', 'Trafford Park', 'Trafford', 'M63 5TY', 'England', 'Warehouse', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_addresses (
				id, addresses_id, parties_id, address_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (201, 201, 201, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);		
INSERT INTO acumen.bank_details (
				id, bank_name, bank_address, account_number, sort_code, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (201, 'Natwest', 'Altrincham', 2254619, 023465, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_bank_details (
				id, bank_details_id, parties_id, account_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (201, 201, 201, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_contact_details (
				id, parties_id, party_addresses_id, party_telephone_numbers_id, party_emails_id, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (201, 201, 201, 201, 201, 201, 'parties_test_data', now(), 'parties_test_data', now(), 1);
		
--4washerhelp
INSERT INTO acumen.parties (
				id, uid, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (202, '4WH_3456789101112131415',1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.companies (
				parties_id, name, reg_no, vat_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date) 
		VALUES (202, '4washerhelp', '1234', '056340', 1, 'parties_test_data', now(), 'parties_test_data', now());
INSERT INTO acumen.party_type(
	            id, parties_id, type_name, type_of_supplier, tenant_id, created_by, 
	            created_date, last_updated_by, last_updated_date, version_number)
	    VALUES (202, 202, 'SUPPLIER', null, 1, 'party_script', 
	            now(), 'party_script', now(), 0);
INSERT INTO acumen.emails (
				id, email_address, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (202, '4washerhelp@gmail.com', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_emails (
				id, parties_id, emails_id, emails_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (202, 202, 202, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);	
INSERT INTO acumen.tel_numbers (
				id, tel_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (202, '0161 4135763', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);		
INSERT INTO acumen.party_telephone_numbers (
				id, parties_id, tel_numbers_id, tel_numbers_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (202, 202, 202, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);	
INSERT INTO acumen.addresses (
				id, address_line_1, address_line_2, city, zip_post_code, country, type_of_place, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (202, 'Warehouse 5', 'Newquay Avenue', 'Cornwall', 'C42 5KS', 'England', 'Warehouse', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_addresses (
				id, addresses_id, parties_id, address_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (202, 202, 202, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);		
INSERT INTO acumen.bank_details (
				id, bank_name, bank_address, account_number, sort_code, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (202, 'HSBC', 'Newquay', 2959103, 011225, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_bank_details (
				id, bank_details_id, parties_id, account_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (202, 202, 202, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_contact_details (
				id, parties_id, party_addresses_id, party_telephone_numbers_id, party_emails_id, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (202, 202, 202, 202, 202, 202, 'parties_test_data', now(), 'parties_test_data', now(), 1);
		
--yourspares
INSERT INTO acumen.parties (
				id, uid, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (203, 'YS_1122334455667788990',1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.companies (
				parties_id, name, reg_no, vat_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date) 
		VALUES (203, 'Yourspares', '1234', '030394', 1, 'parties_test_data', now(), 'parties_test_data', now());
INSERT INTO acumen.party_type(
	            id, parties_id, type_name, type_of_supplier, tenant_id, created_by, 
	            created_date, last_updated_by, last_updated_date, version_number)
	    VALUES (203, 203, 'SUPPLIER', null, 1, 'party_script', 
	            now(), 'party_script', now(), 0);
INSERT INTO acumen.emails (
				id, email_address, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (203, 'yourspares@yourspares.co.uk', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_emails (
				id, parties_id, emails_id, emails_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (203, 203, 203, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);	
INSERT INTO acumen.tel_numbers (
				id, tel_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (203, '0161 3049585', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);		
INSERT INTO acumen.party_telephone_numbers (
				id, parties_id, tel_numbers_id, tel_numbers_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (203, 203, 203, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);	
INSERT INTO acumen.addresses (
				id, address_line_1, address_line_2, city, zip_post_code, country, type_of_place, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (203, 'Number 5', 'Cotton Road', 'Rhyl', 'T76 1FD', 'Wales', 'Warehouse', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_addresses (
				id, addresses_id, parties_id, address_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (203, 203, 203, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);		
INSERT INTO acumen.bank_details (
				id, bank_name, bank_address, account_number, sort_code, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (203, 'TSB', 'Rhyl', 9039485, 019304, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_bank_details (
				id, bank_details_id, parties_id, account_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (203, 203, 203, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_contact_details (
				id, parties_id, party_addresses_id, party_telephone_numbers_id, party_emails_id, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (203, 203, 203, 203, 203, 203, 'parties_test_data', now(), 'parties_test_data', now(), 1);
		
--sparesdirect
INSERT INTO acumen.parties (
				id, uid, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (204, 'SD_1930495869304920194',1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.companies (
				parties_id, name, reg_no, vat_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date) 
		VALUES (204, 'Sparesdirect', '1234', '010394', 1, 'parties_test_data', now(), 'parties_test_data', now());
INSERT INTO acumen.party_type(
	            id, parties_id, type_name, type_of_supplier, tenant_id, created_by, 
	            created_date, last_updated_by, last_updated_date, version_number)
	    VALUES (204, 204, 'SUPPLIER', null, 1, 'party_script', 
	            now(), 'party_script', now(), 0);
INSERT INTO acumen.emails (
				id, email_address, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (204, 'sparesdirect@sparesdirect.com', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_emails (
				id, parties_id, emails_id, emails_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (204, 204, 204, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);	
INSERT INTO acumen.tel_numbers (
				id, tel_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (204, '0161 2910394', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);		
INSERT INTO acumen.party_telephone_numbers (
				id, parties_id, tel_numbers_id, tel_numbers_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (204, 204, 204, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);	
INSERT INTO acumen.addresses (
				id, address_line_1, address_line_2, city, zip_post_code, country, type_of_place, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (204, 'Warehouse 1', 'Silk Lane', 'Bristol', 'BR32 5AS', 'England', 'Warehouse', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_addresses (
				id, addresses_id, parties_id, address_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (204, 204, 204, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);		
INSERT INTO acumen.bank_details (
				id, bank_name, bank_address, account_number, sort_code, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (204, 'Barclays', 'Bristol', 3829102, 039201, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_bank_details (
				id, bank_details_id, parties_id, account_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (204, 204, 204, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_contact_details (
				id, parties_id, party_addresses_id, party_telephone_numbers_id, party_emails_id, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (204, 204, 204, 204, 204, 204, 'parties_test_data', now(), 'parties_test_data', now(), 1);
		
--Machine Parts
INSERT INTO acumen.parties (
				id, uid, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (205, 'MP_1930495829304938271',1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.companies (
				parties_id, name, reg_no, vat_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date) 
		VALUES (205, 'Grand Supplier ltd', '1234', '010394', 1, 'parties_test_data', now(), 'parties_test_data', now());
INSERT INTO acumen.party_type(
	            id, parties_id, type_name, type_of_supplier, tenant_id, created_by, 
	            created_date, last_updated_by, last_updated_date, version_number)
	    VALUES (205, 205, 'SUPPLIER', null, 1, 'party_script', 
	            now(), 'party_script', now(), 0);
INSERT INTO acumen.emails (
				id, email_address, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (205, 'orderparts@gmail.com', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_emails (
				id, parties_id, emails_id, emails_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (205, 205, 205, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);	
INSERT INTO acumen.tel_numbers (
				id, tel_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (205, '0161 1293044', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);		
INSERT INTO acumen.party_telephone_numbers (
				id, parties_id, tel_numbers_id, tel_numbers_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (205, 205, 205, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);	
INSERT INTO acumen.addresses (
				id, address_line_1, address_line_2, city, zip_post_code, country, type_of_place, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (205, 'Number 8', 'Crofton Road', 'Manchester', 'WA15 6AB', 'England', 'Warehouse', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_addresses (
				id, addresses_id, parties_id, address_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (205, 205, 205, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);		
INSERT INTO acumen.bank_details (
				id, bank_name, bank_address, account_number, sort_code, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (205, 'Halifax', 'Sale', 3456938, 026374, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_bank_details (
				id, bank_details_id, parties_id, account_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (205, 205, 205, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_contact_details (
				id, parties_id, party_addresses_id, party_telephone_numbers_id, party_emails_id, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (205, 205, 205, 205, 205, 205, 'parties_test_data', now(), 'parties_test_data', now(), 1);

--eSpares
INSERT INTO acumen.parties (
				id, uid, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (206, 'ES_3948592849318493847',1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.companies (
				parties_id, name, reg_no, vat_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date) 
		VALUES (206, 'eSpare', '1234', '029384', 1, 'parties_test_data', now(), 'parties_test_data', now());
INSERT INTO acumen.party_type(
	            id, parties_id, type_name, type_of_supplier, tenant_id, created_by, 
	            created_date, last_updated_by, last_updated_date, version_number)
	    VALUES (206, 206, 'SUPPLIER', null, 1, 'party_script', 
	            now(), 'party_script', now(), 0);
INSERT INTO acumen.emails (
				id, email_address, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (206, 'esparesorder@espare.co.uk', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_emails (
				id, parties_id, emails_id, emails_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (206, 206, 206, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);	
INSERT INTO acumen.tel_numbers (
				id, tel_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (206, '0161 3948593', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);		
INSERT INTO acumen.party_telephone_numbers (
				id, parties_id, tel_numbers_id, tel_numbers_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (206, 206, 206, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);	
INSERT INTO acumen.addresses (
				id, address_line_1, address_line_2, city, zip_post_code, country, type_of_place, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (206, '34', 'Robson Way', 'Newcastle', 'NU17 5FC', 'England', 'Warehouse', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_addresses (
				id, addresses_id, parties_id, address_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (206, 206, 206, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);		
INSERT INTO acumen.bank_details (
				id, bank_name, bank_address, account_number, sort_code, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (206, 'Santander', 'Newcastle', 2938493, 039482, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_bank_details (
				id, bank_details_id, parties_id, account_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (206, 206, 206, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_contact_details (
				id, parties_id, party_addresses_id, party_telephone_numbers_id, party_emails_id, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (206, 206, 206, 206, 206, 206, 'parties_test_data', now(), 'parties_test_data', now(), 1);
		
--Partmaster
INSERT INTO acumen.parties (
				id, uid, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (207, 'PM_2930495829402938453',1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.companies (
				parties_id, name, reg_no, vat_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date) 
		VALUES (207, 'Partmaster', '1234', '010293', 1, 'parties_test_data', now(), 'parties_test_data', now());
INSERT INTO acumen.party_type(
	            id, parties_id, type_name, type_of_supplier, tenant_id, created_by, 
	            created_date, last_updated_by, last_updated_date, version_number)
	    VALUES (207, 207, 'SUPPLIER', null, 1, 'party_script', 
	            now(), 'party_script', now(), 0);
INSERT INTO acumen.emails (
				id, email_address, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (207, 'partmaster@partmaster.com', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_emails (
				id, parties_id, emails_id, emails_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (207, 207, 207, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);	
INSERT INTO acumen.tel_numbers (
				id, tel_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (207, '0161 9283732', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);		
INSERT INTO acumen.party_telephone_numbers (
				id, parties_id, tel_numbers_id, tel_numbers_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (207, 207, 207, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);	
INSERT INTO acumen.addresses (
				id, address_line_1, address_line_2, city, zip_post_code, country, type_of_place, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (207, '45', 'Brick Lane', 'Swindon', 'SW42 9HJ', 'England', 'Warehouse', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_addresses (
				id, addresses_id, parties_id, address_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (207, 207, 207, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);		
INSERT INTO acumen.bank_details (
				id, bank_name, bank_address, account_number, sort_code, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (207, 'Barclays', 'Swindon', 1938485, 092838, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_bank_details (
				id, bank_details_id, parties_id, account_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (207, 207, 207, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_contact_details (
				id, parties_id, party_addresses_id, party_telephone_numbers_id, party_emails_id, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (207, 207, 207, 207, 207, 207, 'parties_test_data', now(), 'parties_test_data', now(), 1);
		
-- Clients
-- wythenshawe hospital
INSERT INTO acumen.parties (
				id, uid, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (300, 'WH_9186916876332847871',1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.companies (
				parties_id, name, reg_no, vat_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date) 
		VALUES (300, 'Wythenshawe hospital', '1234', '192848', 1, 'parties_test_data', now(), 'parties_test_data', now());
INSERT INTO acumen.party_type(
            	id, parties_id, type_name, type_of_supplier, tenant_id, created_by, 
            	created_date, last_updated_by, last_updated_date, version_number)
    	VALUES (300, 300, 'CLIENT', null, 1, 'party_script', 
            	now(), 'party_script', now(), 0);
INSERT INTO acumen.emails (
				id, email_address, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (300, 'wythenshawehospital@wythenshawehospital.com', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_emails (
				id, parties_id, emails_id, emails_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (300, 300, 300, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.tel_numbers (
				id, tel_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (300, '01942 2938422', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_telephone_numbers (
				id, parties_id, tel_numbers_id, tel_numbers_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (300, 300, 300, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.addresses (
				id, address_line_1, address_line_2, city, zip_post_code, country, type_of_place, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (300, 'wythenshawe hospital', 'Roundthorn Road', 'Manchester', 'M23 3AW', 'England', 'Hospital', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_addresses (
				id, addresses_id, parties_id, address_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (300, 300, 300, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.bank_details (
				id, bank_name, bank_address, account_number, sort_code, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (300, 'Halifax', 'Warrington', 92831924, 193029, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_bank_details (
				id, bank_details_id, parties_id, account_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (300, 300, 300, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_contact_details (
				id, parties_id, party_addresses_id, party_telephone_numbers_id, party_emails_id, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (300, 300, 300, 300, 300, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);

--Wellington School
INSERT INTO acumen.parties (
				id, uid, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (301, 'WS_9293841032845813402',1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.companies (
				parties_id, name, reg_no, vat_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date) 
		VALUES (301, 'Wellington School', '1234', '2918402', 1, 'parties_test_data', now(), 'parties_test_data', now());
INSERT INTO acumen.party_type(
            	id, parties_id, type_name, type_of_supplier, tenant_id, created_by, 
            	created_date, last_updated_by, last_updated_date, version_number)
    	VALUES (301, 301, 'CLIENT', null, 1, 'party_script', 
            	now(), 'party_script', now(), 0);
INSERT INTO acumen.emails (
				id, email_address, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (301, 'wellingtonschool@wellingtonschool.com', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_emails (
				id, parties_id, emails_id, emails_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (301, 301, 301, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.tel_numbers (
				id, tel_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (301, '01942 2838193', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_telephone_numbers (
				id, parties_id, tel_numbers_id, tel_numbers_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (301, 301, 301, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.addresses (
				id, address_line_1, address_line_2, address_line_3, city, zip_post_code, country, type_of_place, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (301, 'Wellington School', 'Wellington Road', 'Altrincham', 'Manchester', 'WA14 6AH', 'England', 'school', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_addresses (
				id, addresses_id, parties_id, address_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (301, 301, 301, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.bank_details (
				id, bank_name, bank_address, account_number, sort_code, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (301, 'HSBC', 'Altrincham', 92839284, 283103, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_bank_details (
				id, bank_details_id, parties_id, account_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (301, 301, 301, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_contact_details (
				id, parties_id, party_addresses_id, party_telephone_numbers_id, party_emails_id, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (301, 301, 301, 301, 301, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
		
--The cedars care home
INSERT INTO acumen.parties (
				id, uid, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (302, 'CCH_6821479303193720183',1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.companies (
				parties_id, name, reg_no, vat_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date) 
		VALUES (302, 'The cedars care home', '1234', '4832938', 1, 'parties_test_data', now(), 'parties_test_data', now());
INSERT INTO acumen.party_type(
            	id, parties_id, type_name, type_of_supplier, tenant_id, created_by, 
            	created_date, last_updated_by, last_updated_date, version_number)
    	VALUES (302, 302, 'CLIENT', null, 1, 'party_script', 
            	now(), 'party_script', now(), 0);
INSERT INTO acumen.emails (
				id, email_address, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (302, 'Thecedarscarehome@Thecedarscarehome.com', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_emails (
				id, parties_id, emails_id, emails_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (302, 302, 302, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.tel_numbers (
				id, tel_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (302, '0194 2491035', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_telephone_numbers (
				id, parties_id, tel_numbers_id, tel_numbers_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (302, 302, 302, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.addresses (
				id, address_line_1, address_line_2, city, zip_post_code, country, type_of_place, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (302, 'Cedar Care Home', 'Langham Road', 'Altrincham', 'T414 6AO', 'England', 'retirment home', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_addresses (
				id, addresses_id, parties_id, address_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (302, 302, 302, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.bank_details (
				id, bank_name, bank_address, account_number, sort_code, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (302, 'HSBC', 'Altrincham', '9283928409', '283103', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_bank_details (
				id, bank_details_id, parties_id, account_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (302, 302, 302, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_contact_details (
				id, parties_id, party_addresses_id, party_telephone_numbers_id, party_emails_id, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (302, 302, 302, 302, 302, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
		
--Vet
INSERT INTO acumen.parties (
				id, uid, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (303, 'VET_2839482938329374173',1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.companies (
				parties_id, name, reg_no, vat_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date) 
		VALUES (303, 'Vet', '1234', '4938234', 1, 'parties_test_data', now(), 'parties_test_data', now());
INSERT INTO acumen.party_type(
            	id, parties_id, type_name, type_of_supplier, tenant_id, created_by, 
            	created_date, last_updated_by, last_updated_date, version_number)
    	VALUES (303, 303, 'CLIENT', null, 1, 'party_script', 
            	now(), 'party_script', now(), 0);
INSERT INTO acumen.emails (
				id, email_address, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (303, 'vet@vet.com', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_emails (
				id, parties_id, emails_id, emails_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (303, 303, 303, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.tel_numbers (
				id, tel_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (303, '01942 5820139', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_telephone_numbers (
				id, parties_id, tel_numbers_id, tel_numbers_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (303, 303, 303, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.addresses (
				id, address_line_1, address_line_2, address_line_3, city, zip_post_code, country, type_of_place, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (303, 'Vet', 'Moss Lane', 'Altrincham', 'Manchester','WA14 6AH', 'England', 'vet', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_addresses (
				id, addresses_id, parties_id, address_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (303, 303, 303, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.bank_details (
				id, bank_name, bank_address, account_number, sort_code, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (303, 'Lloyds', 'Altrincham', 29384752, 394024, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_bank_details (
				id, bank_details_id, parties_id, account_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (303, 303, 303, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_contact_details (
				id, parties_id, party_addresses_id, party_telephone_numbers_id, party_emails_id, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (303, 303, 303, 303, 303, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
		
--The Midland Hotel
INSERT INTO acumen.parties (
				id, uid, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (304, 'MID_8394712540392364721',1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.companies (
				parties_id, name, reg_no, vat_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date) 
		VALUES (304, 'The Midland Hotel', '1234', '8282012', 1, 'parties_test_data', now(), 'parties_test_data', now());
INSERT INTO acumen.party_type(
            	id, parties_id, type_name, type_of_supplier, tenant_id, created_by, 
            	created_date, last_updated_by, last_updated_date, version_number)
    	VALUES (304, 304, 'CLIENT', null, 1, 'party_script', 
            	now(), 'party_script', now(), 0);
INSERT INTO acumen.emails (
				id, email_address, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (304, 'midland@midland.com', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_emails (
				id, parties_id, emails_id, emails_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (304, 304, 304, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.tel_numbers (
				id, tel_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (304, '01942 2938404', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_telephone_numbers (
				id, parties_id, tel_numbers_id, tel_numbers_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (304, 304, 304, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.addresses (
				id, address_line_1, address_line_2, city, zip_post_code, country, type_of_place, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (304, 'The MidLand Hotel', 'Princess Street', 'Manchester', 'WA14 6AH', 'England', 'hotel', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_addresses (
				id, addresses_id, parties_id, address_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (304, 304, 304, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.bank_details (
				id, bank_name, bank_address, account_number, sort_code, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (304, 'TSB', 'Manchester', 38483932, 839384, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_bank_details (
				id, bank_details_id, parties_id, account_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (304, 304, 304, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_contact_details (
				id, parties_id, party_addresses_id, party_telephone_numbers_id, party_emails_id, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (304, 304, 304, 304, 304, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);

--Johnson's dry cleaning
INSERT INTO acumen.parties (
				id, uid, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (305, 'JDC_3940291853830491845',1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.companies (
				parties_id, name, reg_no, vat_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date) 
		VALUES (305, 'Johnsons dry cleaning', '1234', '2837404', 1, 'parties_test_data', now(), 'parties_test_data', now());
INSERT INTO acumen.party_type(
            	id, parties_id, type_name, type_of_supplier, tenant_id, created_by, 
            	created_date, last_updated_by, last_updated_date, version_number)
    	VALUES (305, 305, 'CLIENT', null, 1, 'party_script', 
            	now(), 'party_script', now(), 0);
INSERT INTO acumen.emails (
				id, email_address, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (305, 'johnsons@johnsons.com', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_emails (
				id, parties_id, emails_id, emails_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (305, 305, 305, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.tel_numbers (
				id, tel_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (305, '01942 5820139', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_telephone_numbers (
				id, parties_id, tel_numbers_id, tel_numbers_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (305, 305, 305, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.addresses (
				id, address_line_1, address_line_2, address_line_3, city, zip_post_code, country, type_of_place, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (305, 'Johnsons', 'Park Road', 'Timperley', 'Manchester','WA14 6BG', 'England', 'laundry', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_addresses (
				id, addresses_id, parties_id, address_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (305, 305, 305, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.bank_details (
				id, bank_name, bank_address, account_number, sort_code, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (305, 'NatWest', 'Timperley', '32456901029', '384593', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_bank_details (
				id, bank_details_id, parties_id, account_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (305, 305, 305, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_contact_details (
				id, parties_id, party_addresses_id, party_telephone_numbers_id, party_emails_id, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (305, 305, 305, 305, 305, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
		
--Wetherspoons
INSERT INTO acumen.parties (
				id, uid, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (306, 'WET_9182738493103948557',1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.companies (
				parties_id, name, reg_no, vat_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date) 
		VALUES (306, 'Wetherspoons', '1234', '2837491', 1, 'parties_test_data', now(), 'parties_test_data', now());
INSERT INTO acumen.party_type(
            	id, parties_id, type_name, type_of_supplier, tenant_id, created_by, 
            	created_date, last_updated_by, last_updated_date, version_number)
    	VALUES (306, 306, 'CLIENT', null, 1, 'party_script', 
            	now(), 'party_script', now(), 0);
INSERT INTO acumen.emails (
				id, email_address, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (306, 'wetherspoons@wetherspoons.com', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_emails (
				id, parties_id, emails_id, emails_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (306, 306, 306, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.tel_numbers (
				id, tel_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (306, '01942 2938475', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_telephone_numbers (
				id, parties_id, tel_numbers_id, tel_numbers_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (306, 306, 306, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.addresses (
				id, address_line_1, address_line_2, address_line_3, city, zip_post_code, country, type_of_place, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (306, 'Wetherspoons', 'George Street', 'Altrincham', 'Manchester','WA12 6SD', 'England', 'pub', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_addresses (
				id, addresses_id, parties_id, address_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (306, 306, 306, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.bank_details (
				id, bank_name, bank_address, account_number, sort_code, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (306, 'Santander', 'Altrincham', '82930484721', '8392323', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_bank_details (
				id, bank_details_id, parties_id, account_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (306, 306, 306, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_contact_details (
				id, parties_id, party_addresses_id, party_telephone_numbers_id, party_emails_id, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (306, 306, 306, 306, 306, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
		
-- Custodians
--Chris Munton
INSERT INTO acumen.parties (
				id, uid, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (400, 'CM_9283847591039384729', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.people (
				parties_id, forenames, surname, gender, tenant_id, created_by, created_date, last_updated_by, last_updated_date) 
		VALUES (400, 'Chris', 'Munton', 'M', 1, 'parties_test_data', now(), 'parties_test_data', now());
INSERT INTO acumen.party_type(
            	id, parties_id, type_name, type_of_supplier, tenant_id, created_by, 
            	created_date, last_updated_by, last_updated_date, version_number)
    	VALUES (400, 400, 'CUSTODIAN', null, 1, 'party_script', 
            	now(), 'party_script', now(), 0);
INSERT INTO acumen.emails (
				id, email_address, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (400, 'chrismunton@hotmail.com', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_emails (
				id, parties_id, emails_id, emails_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (400, 400, 400, 'personal', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);		
INSERT INTO acumen.tel_numbers (
				id, tel_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (400, '0161 3451903', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_telephone_numbers (
				id, parties_id, tel_numbers_id, tel_numbers_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (400, 400, 400, 'personal', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.addresses (
				id, address_line_1, address_line_2, city, zip_post_code, country, type_of_place, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (400, 'Number 34', 'Spade Street', 'Runcorn', 'R12 2MF', 'England', 'house', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_addresses (
				id, addresses_id, parties_id, address_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (400, 400, 400, 'home', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1); 
INSERT INTO acumen.bank_details (
				id, bank_name, bank_address, account_number, sort_code, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (400, 'Halifax', 'Runcorn', 29384029, 019283, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_bank_details (
				id, bank_details_id, parties_id, account_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (400, 400, 400, 'personal', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_contact_details (
				id, parties_id, party_addresses_id, party_telephone_numbers_id, party_emails_id, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (400, 400, 400, 400, 400, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
		
--Becky Meade
INSERT INTO acumen.parties (
				id, uid, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (401, 'BM_1928390384591293484', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.people (
				parties_id, forenames, surname, gender, tenant_id, created_by, created_date, last_updated_by, last_updated_date) 
		VALUES (401, 'Becky', 'Meade', 'F', 1, 'parties_test_data', now(), 'parties_test_data', now());
INSERT INTO acumen.party_type(
            	id, parties_id, type_name, type_of_supplier, tenant_id, created_by, 
            	created_date, last_updated_by, last_updated_date, version_number)
    	VALUES (401, 401, 'CUSTODIAN', null, 1, 'party_script', 
            	now(), 'party_script', now(), 0);
INSERT INTO acumen.emails (
				id, email_address, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (401, 'beckymeade@hotmail.com', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_emails (
				id, parties_id, emails_id, emails_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (401, 401, 401, 'personal', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);		
INSERT INTO acumen.tel_numbers (
				id, tel_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (401, '0161 9238495', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_telephone_numbers (
				id, parties_id, tel_numbers_id, tel_numbers_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (401, 401, 401, 'personal', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.addresses (
				id, address_line_1, address_line_2, city, zip_post_code, country, type_of_place, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (401, 'Number 1', 'Sylvan Street', 'Wythenshawe', 'M13 2MF', 'England', 'house', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_addresses (
				id, addresses_id, parties_id, address_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (401, 401, 401, 'home', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1); 
INSERT INTO acumen.bank_details (
				id, bank_name, bank_address, account_number, sort_code, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (401, 'HSBC', 'Altrincham', 98317384, 033847, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_bank_details (
				id, bank_details_id, parties_id, account_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (401, 401, 401, 'personal', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_contact_details (
				id, parties_id, party_addresses_id, party_telephone_numbers_id, party_emails_id, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (401, 401, 401, 401, 401, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);

--Ashley Hurst
INSERT INTO acumen.parties (
				id, uid, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (402, 'AH_9384758330284758392', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.people (
				parties_id, forenames, surname, gender, tenant_id, created_by, created_date, last_updated_by, last_updated_date) 
		VALUES (402, 'Ashley', 'Hurst', 'M', 1, 'parties_test_data', now(), 'parties_test_data', now());
INSERT INTO acumen.party_type(
            	id, parties_id, type_name, type_of_supplier, tenant_id, created_by, 
            	created_date, last_updated_by, last_updated_date, version_number)
    	VALUES (402, 402, 'CUSTODIAN', null, 1, 'party_script', 
            	now(), 'party_script', now(), 0);
INSERT INTO acumen.emails (
				id, email_address, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (402, 'Ashley.Hurst@hotmail.com', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_emails (
				id, parties_id, emails_id, emails_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (402, 402, 402, 'personal', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);		
INSERT INTO acumen.tel_numbers (
				id, tel_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (402, '0161 8394856', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_telephone_numbers (
				id, parties_id, tel_numbers_id, tel_numbers_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (402, 402, 402, 'personal', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.addresses (
				id, address_line_1, address_line_2, city, zip_post_code, country, type_of_place, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (402, 'Number 123', 'Ridgeway Roadt', 'Timperley', 'WA12 4OL', 'England', 'house', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_addresses (
				id, addresses_id, parties_id, address_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (402, 402, 402, 'home', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1); 
INSERT INTO acumen.bank_details (
				id, bank_name, bank_address, account_number, sort_code, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (402, 'Natwest', 'Timperley', 83948345, 083747, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_bank_details (
				id, bank_details_id, parties_id, account_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (402, 402, 402, 'personal', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_contact_details (
				id, parties_id, party_addresses_id, party_telephone_numbers_id, party_emails_id, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (402, 402, 402, 402, 402, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);

--Stephen Ellis
INSERT INTO acumen.parties (
				id, uid, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (403, 'SE_2839485938274563123', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.people (
				parties_id, forenames, surname, gender, tenant_id, created_by, created_date, last_updated_by, last_updated_date) 
		VALUES (403, 'Stephen', 'Ellis', 'M', 1, 'parties_test_data', now(), 'parties_test_data', now());
INSERT INTO acumen.party_type(
            	id, parties_id, type_name, type_of_supplier, tenant_id, created_by, 
            	created_date, last_updated_by, last_updated_date, version_number)
    	VALUES (403, 303, 'CUSTODIAN', null, 1, 'party_script', 
            	now(), 'party_script', now(), 0);
INSERT INTO acumen.emails (
				id, email_address, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (403, 'Ellis.Stephen@hotmail.com', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_emails (
				id, parties_id, emails_id, emails_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (403, 403, 403, 'personal', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);		
INSERT INTO acumen.tel_numbers (
				id, tel_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (403, '0161 2839434', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_telephone_numbers (
				id, parties_id, tel_numbers_id, tel_numbers_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (403, 403, 403, 'personal', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.addresses (
				id, address_line_1, address_line_2, city, zip_post_code, country, type_of_place, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (403, 'Number 82', 'Tabley Street', 'Sunderland', 'S19 1GF', 'England', 'house', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_addresses (
				id, addresses_id, parties_id, address_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (403, 403, 403, 'home', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1); 
INSERT INTO acumen.bank_details (
				id, bank_name, bank_address, account_number, sort_code, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (403, 'Santander', 'Sunderland', 283949023, 038371, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_bank_details (
				id, bank_details_id, parties_id, account_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (403, 403, 403, 'personal', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_contact_details (
				id, parties_id, party_addresses_id, party_telephone_numbers_id, party_emails_id, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (403, 403, 403, 403, 403, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
		
--Mike Proctor
INSERT INTO acumen.parties (
				id, uid, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (404, 'MP_9384920128357284520', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.people (
				parties_id, forenames, surname, gender, tenant_id, created_by, created_date, last_updated_by, last_updated_date) 
		VALUES (404, 'Mike', 'Proctor', 'M', 1, 'parties_test_data', now(), 'parties_test_data', now());
INSERT INTO acumen.party_type(
            	id, parties_id, type_name, type_of_supplier, tenant_id, created_by, 
            	created_date, last_updated_by, last_updated_date, version_number)
    	VALUES (404, 404, 'CUSTODIAN', null, 1, 'party_script', 
            	now(), 'party_script', now(), 0);
INSERT INTO acumen.emails (
				id, email_address, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (404, 'mikeproctor@hotmail.com', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_emails (
				id, parties_id, emails_id, emails_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (404, 404, 404, 'personal', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);		
INSERT INTO acumen.tel_numbers (
				id, tel_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (404, '0161 2934904', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_telephone_numbers (
				id, parties_id, tel_numbers_id, tel_numbers_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (404, 404, 404, 'personal', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.addresses (
				id, address_line_1, address_line_2, city, zip_post_code, country, type_of_place, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (404, 'Number 23', 'Long Avenue', 'Bangor', 'BA34 5SC', 'Wales', 'house', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_addresses (
				id, addresses_id, parties_id, address_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (404, 404, 404, 'home', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1); 
INSERT INTO acumen.bank_details (
				id, bank_name, bank_address, account_number, sort_code, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (404, 'TSB', 'Kendal', 82374848, 019283, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_bank_details (
				id, bank_details_id, parties_id, account_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (404, 404, 404, 'personal', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_contact_details (
				id, parties_id, party_addresses_id, party_telephone_numbers_id, party_emails_id, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (404, 404, 404, 404, 404, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
		
--Ed Walsh
INSERT INTO acumen.parties (
				id, uid, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (405, 'EW_3940593951893049517', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.people (
				parties_id, forenames, surname, gender, tenant_id, created_by, created_date, last_updated_by, last_updated_date) 
		VALUES (405, 'Ed', 'Walsh', 'M', 1, 'parties_test_data', now(), 'parties_test_data', now());
INSERT INTO acumen.party_type(
            	id, parties_id, type_name, type_of_supplier, tenant_id, created_by, 
            	created_date, last_updated_by, last_updated_date, version_number)
    	VALUES (405, 405, 'CUSTODIAN', null, 1, 'party_script', 
            	now(), 'party_script', now(), 0);
INSERT INTO acumen.emails (
				id, email_address, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (405, 'edwalsh@hotmail.com', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_emails (
				id, parties_id, emails_id, emails_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (405, 405, 405, 'personal', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);		
INSERT INTO acumen.tel_numbers (
				id, tel_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (405, '0161 2849502', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_telephone_numbers (
				id, parties_id, tel_numbers_id, tel_numbers_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (405, 405, 405, 'personal', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.addresses (
				id, address_line_1, address_line_2, city, zip_post_code, country, type_of_place, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (405, 'Number 15', 'Cobble Close', 'Norfolk', 'NF34 9JF', 'England', 'house', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_addresses (
				id, addresses_id, parties_id, address_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (405, 405, 405, 'home', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1); 
INSERT INTO acumen.bank_details (
				id, bank_name, bank_address, account_number, sort_code, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (405, 'HSBC', 'Norfolk', 3948201, 019283, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_bank_details (
				id, bank_details_id, parties_id, account_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (405, 405, 405, 'personal', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_contact_details (
				id, parties_id, party_addresses_id, party_telephone_numbers_id, party_emails_id, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (405, 405, 405, 405, 405, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
		
--Sarah Kirton
INSERT INTO acumen.parties (
				id, uid, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (406, 'SK_1029384501294576829', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.people (
				parties_id, forenames, surname, gender, tenant_id, created_by, created_date, last_updated_by, last_updated_date) 
		VALUES (406, 'Sarah', 'Kirton', 'F', 1, 'parties_test_data', now(), 'parties_test_data', now());
INSERT INTO acumen.party_type(
            	id, parties_id, type_name, type_of_supplier, tenant_id, created_by, 
            	created_date, last_updated_by, last_updated_date, version_number)
    	VALUES (406, 406, 'CUSTODIAN', null, 1, 'party_script', 
            	now(), 'party_script', now(), 0);
INSERT INTO acumen.emails (
				id, email_address, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (406, 'sarah.kirton@hotmail.com', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_emails (
				id, parties_id, emails_id, emails_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (406, 406, 406, 'personal', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);		
INSERT INTO acumen.tel_numbers (
				id, tel_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (406, '0161 3948502', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_telephone_numbers (
				id, parties_id, tel_numbers_id, tel_numbers_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (406, 406, 406, 'personal', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.addresses (
				id, address_line_1, address_line_2, city, zip_post_code, country, type_of_place, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (406, 'Number 465', 'Uphill Road', 'Sheffield', 'SH76 4AV', 'England', 'house', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_addresses (
				id, addresses_id, parties_id, address_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (406, 406, 406, 'home', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1); 
INSERT INTO acumen.bank_details (
				id, bank_name, bank_address, account_number, sort_code, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (406, 'Yorkshire Bank', 'Sheffield', 2394923, 023456, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_bank_details (
				id, bank_details_id, parties_id, account_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (406, 406, 406, 'personal', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_contact_details (
				id, parties_id, party_addresses_id, party_telephone_numbers_id, party_emails_id, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (406, 406, 406, 406, 406, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
		
