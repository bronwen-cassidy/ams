-- Suppliers
-- BP Explorations
INSERT INTO acumen.parties (
				id, uid, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (501, 'das_pt_9186916376332847871',1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.companies (
				parties_id, name, reg_no, vat_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date) 
		VALUES (501, 'BP Explorations', '1234', '929293', 1, 'parties_test_data', now(), 'parties_test_data', now());
INSERT INTO acumen.party_type(
	            id, parties_id, type_name, type_of_supplier, tenant_id, created_by, 
	            created_date, last_updated_by, last_updated_date, version_number)
	    VALUES (501, 501, 'CLIENT', null, 1, 'party_script', 
	            now(), 'party_script', now(), 0);
INSERT INTO acumen.emails (
				id, email_address, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (501, 'enquiries_operations@bp.com', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_emails (
				id, parties_id, emails_id, emails_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (501, 501, 501, 171, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.tel_numbers (
				id, tel_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (501, '01224 832 000', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.tel_numbers (
				id, tel_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (510, '01224 322 342', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_telephone_numbers (
				id, parties_id, tel_numbers_id, tel_numbers_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (501, 501, 501, 174, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_telephone_numbers (
				id, parties_id, tel_numbers_id, tel_numbers_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (510, 501, 510, 174, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.addresses (
				id, address_line_1, address_line_2, address_line_3, city, zip_post_code, country, type_of_place, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (501, '1-4  Wellheads Avenue', 'Dyce','Aberdeenshire', 'Aberdeen', 'AB21 7PB', 'United Kingdom', 'Office', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.addresses (
				id, address_line_1, address_line_2, address_line_3, city, zip_post_code, country, type_of_place, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (510, 'Dimlington Terminal', 'Easington', '', 'Hull', 'HU12 0SU ', 'United Kingdom', 'Office', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.addresses (
				id, address_line_1, address_line_2, address_line_3, city, zip_post_code, country, type_of_place, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (511, 'Sullom Voe Terminal', 'Mossbank', '', 'Shetland', 'ZE2 9TU', 'United Kingdom', 'Office', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_addresses (
				id, addresses_id, parties_id, address_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (501, 501, 501, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_addresses (
				id, addresses_id, parties_id, address_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (510, 510, 501, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_addresses (
				id, addresses_id, parties_id, address_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (511, 511, 501, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.bank_details (
				id, bank_name, bank_address, account_number, sort_code, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (501, 'Halifax', 'London', 12345678, 010201, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_bank_details (
				id, bank_details_id, parties_id, account_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (501, 501, 501, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_contact_details (
				id, parties_id, party_addresses_id, party_telephone_numbers_id, party_emails_id, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (501, 501, 501, 501, 501, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);

-- Clients
-- Weatherford UK Ltd
INSERT INTO acumen.parties (
				id, uid, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (502, 'das_pt_9186916346332847871',1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.companies (
				parties_id, name, reg_no, vat_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date) 
		VALUES (502, 'Weatherford UK Ltd', '1234', '929293', 1, 'parties_test_data', now(), 'parties_test_data', now());
INSERT INTO acumen.party_type(
            	id, parties_id, type_name, type_of_supplier, tenant_id, created_by, 
            	created_date, last_updated_by, last_updated_date, version_number)
    	VALUES (502, 502, 'CLIENT', null, 1, 'party_script', 
            	now(), 'party_script', now(), 0);
INSERT INTO acumen.emails (
				id, email_address, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (502, 'enquiries@weathford.com', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_emails (
				id, parties_id, emails_id, emails_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (502, 502, 502, 171, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.tel_numbers (
				id, tel_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (502, '01224 762800', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_telephone_numbers (
				id, parties_id, tel_numbers_id, tel_numbers_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (502, 502, 502, 174, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.addresses (
				id, address_line_1, address_line_2, address_line_3, city, zip_post_code, country, type_of_place, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (502, 'Weathford House, Lawson Drive', 'Dyce', 'Aberdeenshire', 'Aberdeen', 'AB21 0DR', 'United Kingdom', 'Office', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_addresses (
				id, addresses_id, parties_id, address_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (502, 502, 502, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.bank_details (
				id, bank_name, bank_address, account_number, sort_code, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (502, 'TSB', 'Warrington', 12345678, 101123, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_bank_details (
				id, bank_details_id, parties_id, account_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (502, 502, 502, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_contact_details (
				id, parties_id, party_addresses_id, party_telephone_numbers_id, party_emails_id, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (502, 502, 502, 502, 502, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);

-- Clients
-- Diamond Off Shore
INSERT INTO acumen.parties (
				id, uid, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (503, 'das_pt_9186916876332847871',1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.companies (
				parties_id, name, reg_no, vat_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date) 
		VALUES (503, 'Diamond Off Shore', '1234', '929293', 1, 'parties_test_data', now(), 'parties_test_data', now());
INSERT INTO acumen.party_type(
            	id, parties_id, type_name, type_of_supplier, tenant_id, created_by, 
            	created_date, last_updated_by, last_updated_date, version_number)
    	VALUES (503, 503, 'CLIENT', null, 1, 'party_script', 
            	now(), 'party_script', now(), 0);
INSERT INTO acumen.emails (
				id, email_address, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (503, 'enquiries@diamondoffshore.com', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_emails (
				id, parties_id, emails_id, emails_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (503, 503, 503, 171, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.tel_numbers (
				id, tel_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (503, '01224 727500', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_telephone_numbers (
				id, parties_id, tel_numbers_id, tel_numbers_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (503, 503, 503, 174, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.addresses (
				id, address_line_1, address_line_2, address_line_3, city, zip_post_code, country, type_of_place, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (503, 'Howe Moss Drive', 'Dyce', 'Aberdeenshire', 'Aberdeen', 'AB21 0GL', 'United Kingdom', 'Office', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_addresses (
				id, addresses_id, parties_id, address_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (503, 503, 503, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.bank_details (
				id, bank_name, bank_address, account_number, sort_code, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (503, 'Lloyds Bank', 'London', 12345678, 101123, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_bank_details (
				id, bank_details_id, parties_id, account_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (503, 503, 503, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_contact_details (
				id, parties_id, party_addresses_id, party_telephone_numbers_id, party_emails_id, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (503, 503, 503, 503, 503, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);

-- Clients
-- Woodside Petroleum Ltd
INSERT INTO acumen.parties (
				id, uid, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (504, 'das_pt_9186916855332847871',1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.companies (
				parties_id, name, reg_no, vat_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date) 
		VALUES (504, 'Woodside Petroleum Ltd', '1234', '929293', 1, 'parties_test_data', now(), 'parties_test_data', now());
INSERT INTO acumen.party_type(
            	id, parties_id, type_name, type_of_supplier, tenant_id, created_by, 
            	created_date, last_updated_by, last_updated_date, version_number)
    	VALUES (504, 504, 'CLIENT', null, 1, 'party_script', 
            	now(), 'party_script', now(), 0);
INSERT INTO acumen.emails (
				id, email_address, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (504, 'enquiries@woodsidepetroleum.com', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_emails (
				id, parties_id, emails_id, emails_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (504, 504, 504, 171, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.tel_numbers (
				id, tel_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (504, '0061 8 9348 4000', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_telephone_numbers (
				id, parties_id, tel_numbers_id, tel_numbers_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (504, 504, 504, 174, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.addresses (
				id, address_line_1, address_line_2, address_line_3, city, zip_post_code, country, type_of_place, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (504, 'Woodside P​laza', '240 St Georges Terrace', '','Perth', 'WA 6000', 'Australia', 'Office', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_addresses (
				id, addresses_id, parties_id, address_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (504, 504, 504, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.bank_details (
				id, bank_name, bank_address, account_number, sort_code, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (504, 'HSBC Bank', 'Australia', 12345678, 101123, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_bank_details (
				id, bank_details_id, parties_id, account_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (504, 504, 504, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_contact_details (
				id, parties_id, party_addresses_id, party_telephone_numbers_id, party_emails_id, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (504, 504, 504, 504, 504, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);	
		
		
-- Clients
-- Siapem UK Ltd
INSERT INTO acumen.parties (
				id, uid, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (505, 'das_pt_9186916877432847871',1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.companies (
				parties_id, name, reg_no, vat_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date) 
		VALUES (505, 'Siapem UK Ltd', '1234', '929293', 1, 'parties_test_data', now(), 'parties_test_data', now());
INSERT INTO acumen.party_type(
            	id, parties_id, type_name, type_of_supplier, tenant_id, created_by, 
            	created_date, last_updated_by, last_updated_date, version_number)
    	VALUES (505, 505, 'CLIENT', null, 1, 'party_script', 
            	now(), 'party_script', now(), 0);
INSERT INTO acumen.emails (
				id, email_address, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (505, 'enquiries@siapem.com', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_emails (
				id, parties_id, emails_id, emails_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (505, 505, 505, 171, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.tel_numbers (
				id, tel_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (505, '01224 843434', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_telephone_numbers (
				id, parties_id, tel_numbers_id, tel_numbers_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (505, 505, 505, 174, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.addresses (
				id, address_line_1, address_line_2, address_line_3, city, zip_post_code, country, type_of_place, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (505, 'Tern Place', 'Denmore Place', 'Bridge of Don', 'Aberdeen', 'AB23 8JX', 'United Kingdom', 'Office', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_addresses (
				id, addresses_id, parties_id, address_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (505, 505, 505, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.bank_details (
				id, bank_name, bank_address, account_number, sort_code, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (505, 'Halifax', 'London', 12345678, 101123, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_bank_details (
				id, bank_details_id, parties_id, account_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (505, 505, 505, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_contact_details (
				id, parties_id, party_addresses_id, party_telephone_numbers_id, party_emails_id, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (505, 505, 505, 505, 505, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);			
		
-- Clients
-- Jumbo Off Shore
INSERT INTO acumen.parties (
				id, uid, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (506, 'das_pt_9186916875632847871',1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.companies (
				parties_id, name, reg_no, vat_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date) 
		VALUES (506, 'Jumbo Off Shore', '1234', '929293', 1, 'parties_test_data', now(), 'parties_test_data', now());
INSERT INTO acumen.party_type(
            	id, parties_id, type_name, type_of_supplier, tenant_id, created_by, 
            	created_date, last_updated_by, last_updated_date, version_number)
    	VALUES (506, 506, 'CLIENT', null, 1, 'party_script', 
            	now(), 'party_script', now(), 0);
INSERT INTO acumen.emails (
				id, email_address, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (506, 'info@jumbomaritime.nl', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_emails (
				id, parties_id, emails_id, emails_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (506, 506, 506, 171, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.tel_numbers (
				id, tel_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (506, '+31 (0)10 7900 300', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_telephone_numbers (
				id, parties_id, tel_numbers_id, tel_numbers_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (506, 506, 506, 174, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.addresses (
				id, address_line_1, address_line_2, address_line_3, city, zip_post_code, country, type_of_place, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (506, 'Havenstraat 23', '3115 HC Schiedam', '3101 EB Schiedam', 'Rotterdam','P.O. Box 3070', 'Netherlands', 'Office', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_addresses (
				id, addresses_id, parties_id, address_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (506, 506, 506, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.bank_details (
				id, bank_name, bank_address, account_number, sort_code, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (506, 'HSBC', 'Amesterdam', 12345678, 101123, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_bank_details (
				id, bank_details_id, parties_id, account_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (506, 506, 506, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_contact_details (
				id, parties_id, party_addresses_id, party_telephone_numbers_id, party_emails_id, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (506, 506, 506, 506, 506, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);					
		

-- Clients
-- BP
INSERT INTO acumen.parties (
				id, uid, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (507, 'das_pt_9186914576332847871',1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.companies (
				parties_id, name, reg_no, vat_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date) 
		VALUES (507, 'BP', '1234', '929293', 1, 'parties_test_data', now(), 'parties_test_data', now());
INSERT INTO acumen.party_type(
            	id, parties_id, type_name, type_of_supplier, tenant_id, created_by, 
            	created_date, last_updated_by, last_updated_date, version_number)
    	VALUES (507, 507, 'CLIENT', null, 1, 'party_script', 
            	now(), 'party_script', now(), 0);
INSERT INTO acumen.emails (
				id, email_address, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (507, 'enquiries@bp.com', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_emails (
				id, parties_id, emails_id, emails_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (507, 507, 507, 171, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.tel_numbers (
				id, tel_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (507, '+44 (0)20 7496 4000', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_telephone_numbers (
				id, parties_id, tel_numbers_id, tel_numbers_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (507, 507, 507, 174, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.addresses (
				id, address_line_1, address_line_2, address_line_3, city, zip_post_code, country, type_of_place, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (507, 'International Headquarters', '1 St Jamess Square', '','London', 'SW1Y 4PD', 'United Kingdom', 'Office', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_addresses (
				id, addresses_id, parties_id, address_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (507, 507, 507, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.bank_details (
				id, bank_name, bank_address, account_number, sort_code, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (507, 'Lloyds Bank', 'London', 12345678, 101123, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_bank_details (
				id, bank_details_id, parties_id, account_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (507, 507, 507, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_contact_details (
				id, parties_id, party_addresses_id, party_telephone_numbers_id, party_emails_id, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (507, 507, 507, 507, 507, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);			
		
		
-- Clients
-- BHP Billiton Limited
INSERT INTO acumen.parties (
				id, uid, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (508, 'das_pt_9186900876332847871',1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.companies (
				parties_id, name, reg_no, vat_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date) 
		VALUES (508, 'BHP Billiton Limited', '1234', '929293', 1, 'parties_test_data', now(), 'parties_test_data', now());
INSERT INTO acumen.party_type(
            	id, parties_id, type_name, type_of_supplier, tenant_id, created_by, 
            	created_date, last_updated_by, last_updated_date, version_number)
    	VALUES (508, 508, 'CLIENT', null, 1, 'party_script', 
            	now(), 'party_script', now(), 0);
INSERT INTO acumen.emails (
				id, email_address, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (508, 'enquiries@bhpbilliton.com', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_emails (
				id, parties_id, emails_id, emails_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (508, 508, 508, 171, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.tel_numbers (
				id, tel_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (508, '(61 3) 9609 3333', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_telephone_numbers (
				id, parties_id, tel_numbers_id, tel_numbers_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (508, 508, 508, 174, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.addresses (
				id, address_line_1, address_line_2, address_line_3, city, zip_post_code, country, type_of_place, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (508, 'BHP Billiton Centre', '171 Collins Street', '', 'Melbourne Victoria', '3000', 'Australia', 'Office', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_addresses (
				id, addresses_id, parties_id, address_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (508, 508, 508, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.bank_details (
				id, bank_name, bank_address, account_number, sort_code, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (508, 'HSBC Bank', 'Australia', 12345678, 101123, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_bank_details (
				id, bank_details_id, parties_id, account_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (508, 508, 508, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_contact_details (
				id, parties_id, party_addresses_id, party_telephone_numbers_id, party_emails_id, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (508, 508, 508, 508, 508, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);

-- Clients
-- Maersk Oil UK
INSERT INTO acumen.parties (
				id, uid, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (509, 'das_pt_9186916879932847871',1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.companies (
				parties_id, name, reg_no, vat_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date) 
		VALUES (509, 'Maersk Oil UK', '1234', '929293', 1, 'parties_test_data', now(), 'parties_test_data', now());
INSERT INTO acumen.party_type(
            	id, parties_id, type_name, type_of_supplier, tenant_id, created_by, 
            	created_date, last_updated_by, last_updated_date, version_number)
    	VALUES (509, 509, 'CLIENT', null, 1, 'party_script', 
            	now(), 'party_script', now(), 0);
INSERT INTO acumen.emails (
				id, email_address, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (509, 'enquiries@maerskoil.com', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_emails (
				id, parties_id, emails_id, emails_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (509, 509, 509, 171, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.tel_numbers (
				id, tel_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (509, '+44 1224 242000', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_telephone_numbers (
				id, parties_id, tel_numbers_id, tel_numbers_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (509, 509, 509, 174, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.addresses (
				id, address_line_1, address_line_2, address_line_3, city, zip_post_code, country, type_of_place, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (509, 'Altens Ind Est', 'Crawpeel Rd', '', 'Aberdeen', 'AB12 3LG', 'United Kingdom', 'Office', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_addresses (
				id, addresses_id, parties_id, address_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (509, 509, 509, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.bank_details (
				id, bank_name, bank_address, account_number, sort_code, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (509, 'Halifax', 'London', 12345678, 101123, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_bank_details (
				id, bank_details_id, parties_id, account_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (509, 509, 509, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_contact_details (
				id, parties_id, party_addresses_id, party_telephone_numbers_id, party_emails_id, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
		VALUES (509, 509, 509, 509, 509, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);

--Chris Munton Employee of BP Exploration
INSERT INTO acumen.parties (
				id, uid, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (530, 'CM_9283847591039384729', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.people (
				parties_id, forenames, surname, gender, tenant_id, created_by, created_date, last_updated_by, last_updated_date) 
		VALUES (530, 'Chris', 'Munton', 'M', 1, 'parties_test_data', now(), 'parties_test_data', now());
INSERT INTO acumen.party_type(
            	id, parties_id, type_name, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
    	VALUES (530, 530, null, 1, 'party_script', now(), 'party_script', now(), 0);
INSERT INTO acumen.emails (
				id, email_address, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (530, 'chrismunton@bp.com', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.emails (
				id, email_address, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (531, 'chrismunton@bpexplorations.com', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_emails (
				id, parties_id, emails_id, emails_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (530, 530, 530, 171, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_emails (
				id, parties_id, emails_id, emails_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (531, 530, 531, 171, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);		
INSERT INTO acumen.tel_numbers (
				id, tel_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (530, '0161 6661903', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.tel_numbers (
				id, tel_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (531, '0191 7542234', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.tel_numbers (
				id, tel_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (532, '0451 5555654', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_telephone_numbers (
				id, parties_id, tel_numbers_id, tel_numbers_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (530, 530, 530, 174, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_telephone_numbers (
				id, parties_id, tel_numbers_id, tel_numbers_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (531, 530, 531, 174, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_telephone_numbers (
				id, parties_id, tel_numbers_id, tel_numbers_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (532, 530, 532, 173, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.addresses (
				id, address_line_1, address_line_2, city, zip_post_code, country, type_of_place, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (530, 'Number 34', 'Spade Street', 'Runcorn', 'R12 2MF', 'England', 'house', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_addresses (
				id, addresses_id, parties_id, address_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (530, 501, 530, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_addresses (
				id, addresses_id, parties_id, address_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (531, 530, 530, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1); 
INSERT INTO acumen.bank_details (
				id, bank_name, bank_address, account_number, sort_code, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (530, 'Halifax', 'Runcorn', 29384029, 019283, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_bank_details (
				id, bank_details_id, parties_id, account_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (530, 530, 530, 'personal', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_contact_details (
				id, parties_id, party_addresses_id, party_telephone_numbers_id, party_emails_id, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (530, 530, 530, 530, 530, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_links (
				id, parties_id_1, parties_id_2, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (1, 530, 501, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
		
--Nick Palmer Employee of BP Exploration
INSERT INTO acumen.parties (
				id, uid, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (531, 'CM_9283847591459384729', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.people (
				parties_id, forenames, surname, gender, tenant_id, created_by, created_date, last_updated_by, last_updated_date) 
		VALUES (531, 'Nick', 'Palmer', 'M', 1, 'parties_test_data', now(), 'parties_test_data', now());
INSERT INTO acumen.party_type(
            	id, parties_id, type_name, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
    	VALUES (531, 531, null, 1, 'party_script', now(), 'party_script', now(), 0);
INSERT INTO acumen.emails (
				id, email_address, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (532, 'nickpalmer@bp.com', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.emails (
				id, email_address, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (533, 'nickpalmer@bpexplorations.com', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_emails (
				id, parties_id, emails_id, emails_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (532, 531, 532, 171, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_emails (
				id, parties_id, emails_id, emails_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (533, 531, 533, 171, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);		
INSERT INTO acumen.tel_numbers (
				id, tel_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (533, '0151 3451903', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.tel_numbers (
				id, tel_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (534, '0161 5552234', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.tel_numbers (
				id, tel_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (535, '0221 9999654', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_telephone_numbers (
				id, parties_id, tel_numbers_id, tel_numbers_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (533, 531, 533, 173, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_telephone_numbers (
				id, parties_id, tel_numbers_id, tel_numbers_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (534, 531, 534, 173, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_telephone_numbers (
				id, parties_id, tel_numbers_id, tel_numbers_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (535, 531, 535, 173, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.addresses (
				id, address_line_1, address_line_2, city, zip_post_code, country, type_of_place, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (531, '23 Turner Street', 'worsly', 'Runcorn', 'A12 2MF', 'United Kingdom', 'office', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_addresses (
				id, addresses_id, parties_id, address_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (532, 501, 530, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_addresses (
				id, addresses_id, parties_id, address_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (533, 531, 531, 'home', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1); 
INSERT INTO acumen.party_contact_details (
				id, parties_id, party_addresses_id, party_telephone_numbers_id, party_emails_id, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (531, 531, 532, 533, 532, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_links (
				id, parties_id_1, parties_id_2, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (2, 531, 501, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
		
--Edward Bowler Employee of BP Exploration
INSERT INTO acumen.parties (
				id, uid, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (532, 'CM_9283847231459384729', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.people (
				parties_id, forenames, surname, gender, tenant_id, created_by, created_date, last_updated_by, last_updated_date) 
		VALUES (532, 'Edward', 'Bowler', 'M', 1, 'parties_test_data', now(), 'parties_test_data', now());
INSERT INTO acumen.party_type(
            	id, parties_id, type_name, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
    	VALUES (532, 532, null, 1, 'party_script', now(), 'party_script', now(), 0);
INSERT INTO acumen.emails (
				id, email_address, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (534, 'edwardbowler@bp.com', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.emails (
				id, email_address, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (535, 'edwardbowler@bpexplorations.com', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_emails (
				id, parties_id, emails_id, emails_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (534, 532, 534, 171, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_emails (
				id, parties_id, emails_id, emails_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (535, 531, 533, 171, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);		
INSERT INTO acumen.tel_numbers (
				id, tel_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (536, '0161 3222303', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.tel_numbers (
				id, tel_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (537, '0161 4244534', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.tel_numbers (
				id, tel_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (538, '0161 9993834', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_telephone_numbers (
				id, parties_id, tel_numbers_id, tel_numbers_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (536, 532, 536, 173, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_telephone_numbers (
				id, parties_id, tel_numbers_id, tel_numbers_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (537, 532, 537, 173, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_telephone_numbers (
				id, parties_id, tel_numbers_id, tel_numbers_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (538, 532, 538, 173, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.addresses (
				id, address_line_1, address_line_2, city, zip_post_code, country, type_of_place, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (532, '23 Turner Street', 'worsly', 'Runcorn', 'A12 2MF', 'United Kingdom', 'office', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_addresses (
				id, addresses_id, parties_id, address_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (534, 501, 530, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_addresses (
				id, addresses_id, parties_id, address_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (535, 531, 531, 'home', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1); 
INSERT INTO acumen.party_contact_details (
				id, parties_id, party_addresses_id, party_telephone_numbers_id, party_emails_id, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (533, 532, 534, 536, 534, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_links (
				id, parties_id_1, parties_id_2, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (3, 532, 501, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
		

--Ashley Hurst Employee of Weatherford UK Ltd
INSERT INTO acumen.parties (
				id, uid, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (540, 'CM_9283847591456384729', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.people (
				parties_id, forenames, surname, gender, tenant_id, created_by, created_date, last_updated_by, last_updated_date) 
		VALUES (540, 'Ashley', 'Hurst', 'M', 1, 'parties_test_data', now(), 'parties_test_data', now());
INSERT INTO acumen.party_type(
            	id, parties_id, type_name, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
    	VALUES (540, 540, null, 1, 'party_script', now(), 'party_script', now(), 0);
INSERT INTO acumen.emails (
				id, email_address, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (540, 'ashleyhurst@weatherford.com', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.emails (
				id, email_address, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (541, 'ashleyhurst@gmail.com', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_emails (
				id, parties_id, emails_id, emails_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (540, 540, 540, 171, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_emails (
				id, parties_id, emails_id, emails_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (541, 540, 541, 171, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);		
INSERT INTO acumen.tel_numbers (
				id, tel_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (540, '0231 3553445', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.tel_numbers (
				id, tel_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (541, '0231 9872234', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.tel_numbers (
				id, tel_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (542, '0231 7756654', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_telephone_numbers (
				id, parties_id, tel_numbers_id, tel_numbers_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (540, 540, 540, 174, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_telephone_numbers (
				id, parties_id, tel_numbers_id, tel_numbers_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (541, 540, 541, 174, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_telephone_numbers (
				id, parties_id, tel_numbers_id, tel_numbers_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (542, 540, 542, 173, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.addresses (
				id, address_line_1, address_line_2, city, zip_post_code, country, type_of_place, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (540, 'Blackness Road', 'Altens Industrial Estate', 'Aberdeen', 'AB12 3LH', 'United Kingdom', 'office', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_addresses (
				id, addresses_id, parties_id, address_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (540, 502, 540, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_addresses (
				id, addresses_id, parties_id, address_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (541, 540, 540, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1); 
INSERT INTO acumen.bank_details (
				id, bank_name, bank_address, account_number, sort_code, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (540, 'Halifax', 'Runcorn', 29384029, 019283, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_bank_details (
				id, bank_details_id, parties_id, account_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (540, 540, 540, 'personal', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_contact_details (
				id, parties_id, party_addresses_id, party_telephone_numbers_id, party_emails_id, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (540, 540, 540, 540, 540, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_links (
				id, parties_id_1, parties_id_2, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (4, 540, 502, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);

		
--Micheal Turner Employee of Weatherford UK Ltd
INSERT INTO acumen.parties (
				id, uid, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (541, 'CM_9283847591433284729', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.people (
				parties_id, forenames, surname, gender, tenant_id, created_by, created_date, last_updated_by, last_updated_date) 
		VALUES (541, 'Micheal', 'Turner', 'M', 1, 'parties_test_data', now(), 'parties_test_data', now());
INSERT INTO acumen.party_type(
            	id, parties_id, type_name, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
    	VALUES (541, 541, null, 1, 'party_script', now(), 'party_script', now(), 0);
INSERT INTO acumen.emails (
				id, email_address, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (542, 'michealturner@weatherford.com', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.emails (
				id, email_address, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (543, 'michealturner@wtl.com', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_emails (
				id, parties_id, emails_id, emails_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (542, 541, 542, 171, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_emails (
				id, parties_id, emails_id, emails_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (543, 541, 543, 171, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);		
INSERT INTO acumen.tel_numbers (
				id, tel_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (543, '0231 3553656', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.tel_numbers (
				id, tel_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (544, '0231 9872234', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.tel_numbers (
				id, tel_no, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (545, '0231 7756654', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_telephone_numbers (
				id, parties_id, tel_numbers_id, tel_numbers_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (543, 541, 543, 173, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_telephone_numbers (
				id, parties_id, tel_numbers_id, tel_numbers_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (544, 541, 544, 173, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_telephone_numbers (
				id, parties_id, tel_numbers_id, tel_numbers_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (545, 541, 545, 173, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.addresses (
				id, address_line_1, address_line_2, city, zip_post_code, country, type_of_place, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (541, 'St. Marks', 'Raymond Baxter Estate', 'Aberdeen', 'AB12 2JK', 'United Kingdom', 'office', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_addresses (
				id, addresses_id, parties_id, address_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (542, 502, 541, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_addresses (
				id, addresses_id, parties_id, address_type, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (543, 541, 541, 'work', 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_contact_details (
				id, parties_id, party_addresses_id, party_telephone_numbers_id, party_emails_id, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (541, 541, 542, 543, 542, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
INSERT INTO acumen.party_links (
				id, parties_id_1, parties_id_2, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
		VALUES (5, 541, 502, 1, 'parties_test_data', now(), 'parties_test_data', now(), 1);
