SELECT setval('acumen.assets_id_seq', max(id)) from acumen.assets;
SELECT setval('acumen.lists_id_seq', max(id)) from acumen.lists;
SELECT setval('acumen.list_contents_id_seq', max(id)) from acumen.list_contents;

SELECT setval('acumen.parties_id_seq', max(id)) from acumen.parties;

SELECT setval('acumen.emails_id_seq', max(id)) from acumen.emails;
SELECT setval('acumen.party_emails_id_seq', max(id)) from acumen.party_emails;

SELECT setval('acumen.tel_numbers_id_seq', max(id)) from acumen.tel_numbers;
SELECT setval('acumen.party_telephone_numbers_id_seq', max(id)) from acumen.party_telephone_numbers;

SELECT setval('acumen.addresses_id_seq', max(id)) from acumen.addresses;
SELECT setval('acumen.party_addresses_id_seq', max(id)) from acumen.party_addresses;

SELECT setval('acumen.bank_details_id_seq', max(id)) from acumen.bank_details;
SELECT setval('acumen.party_bank_details_id_seq', max(id)) from acumen.party_bank_details;

SELECT setval('acumen.party_contact_details_id_seq', max(id)) from acumen.party_contact_details;

SELECT setval('acumen.party_type_id_seq', max(id)) from acumen.party_type;

SELECT setval('acumen.user_data_id_seq', max(id)) from acumen.user_data;

SELECT setval('acumen.users_id_seq', max(id)) from acumen.users;

SELECT setval('acumen.tenants_id_seq', max(id)) from acumen.tenants;