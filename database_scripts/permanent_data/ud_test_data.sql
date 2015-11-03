/*
 * This is a data script for the User data cat and types tables. 
 *
 * This is not a test data script
 */

INSERT INTO acumen.user_data_cat (id, user_data_cat_parent_id, name, description, language_code, desc_code, created_by, created_date, last_updated_by, last_updated_date) VALUES (1, NULL, 'Asset Register', 'todo', 'systemAdmin.user.userData.assetRegister', 'todo', 'sje', '2014-05-06 12:50:30.293', 'sje', '2014-05-06 12:50:30.293');
INSERT INTO acumen.user_data_cat (id, user_data_cat_parent_id, name, description, language_code, desc_code, created_by, created_date, last_updated_by, last_updated_date) VALUES (2, NULL, 'Finance', 'todo', 'systemAdmin.user.userData.finance', 'todo', 'sje', '2014-05-06 12:50:30.293', 'sje', '2014-05-06 12:50:30.293');
INSERT INTO acumen.user_data_cat (id, user_data_cat_parent_id, name, description, language_code, desc_code, created_by, created_date, last_updated_by, last_updated_date) VALUES (3, NULL, 'Maintenance', 'todo', 'systemAdmin.user.userData.maintenance', 'todo', 'sje', '2014-05-06 12:50:30.293', 'sje', '2014-05-06 12:50:30.293');
INSERT INTO acumen.user_data_cat (id, user_data_cat_parent_id, name, description, language_code, desc_code, created_by, created_date, last_updated_by, last_updated_date) VALUES (4, NULL, 'Parts', 'todo', 'systemAdmin.user.userData.parts', 'todo', 'sje', '2014-05-06 12:50:30.293', 'sje', '2014-05-06 12:50:30.293');
INSERT INTO acumen.user_data_cat (id, user_data_cat_parent_id, name, description, language_code, desc_code, created_by, created_date, last_updated_by, last_updated_date) VALUES (5, 1, 'Asset', 'todo', 'systemAdmin.user.userData.asset', 'todo', 'sje', '2014-05-07 12:33:22.968', 'sje', '2014-05-07 12:33:22.968');
INSERT INTO acumen.user_data_cat (id, user_data_cat_parent_id, name, description, language_code, desc_code, created_by, created_date, last_updated_by, last_updated_date) VALUES (6, 1, 'Something else', 'todo', 'systemAdmin.user.userData.somethingElse', 'todo', 'sje', '2014-05-07 12:33:22.968', 'sje', '2014-05-07 12:33:22.968');
INSERT INTO acumen.user_data_cat (id, user_data_cat_parent_id, name, description, language_code, desc_code, created_by, created_date, last_updated_by, last_updated_date) VALUES (7, 5, 'General', 'todo', 'systemAdmin.user.userData.asset.general', 'todo', 'sje', '2014-05-07 12:33:22.968', 'sje', '2014-05-07 12:33:22.968');
INSERT INTO acumen.user_data_cat (id, user_data_cat_parent_id, name, description, language_code, desc_code, created_by, created_date, last_updated_by, last_updated_date) VALUES (8, 5, 'Finance', 'todo', 'systemAdmin.user.userData.asset.finance', 'todo', 'sje', '2014-05-07 12:33:22.968', 'sje', '2014-05-07 12:33:22.968');
INSERT INTO acumen.user_data_cat (id, user_data_cat_parent_id, name, description, language_code, desc_code, created_by, created_date, last_updated_by, last_updated_date) VALUES (9, 5, 'Tenure', 'todo', 'systemAdmin.user.userData.asset.tenure', 'todo', 'sje', '2014-05-07 12:33:22.968', 'sje', '2014-05-07 12:33:22.968');
INSERT INTO acumen.user_data_cat (id, user_data_cat_parent_id, name, description, language_code, desc_code, created_by, created_date, last_updated_by, last_updated_date) VALUES (10, 5, 'Maintenance', 'todo', 'systemAdmin.user.userData.asset.maintenance', 'todo', 'sje', '2014-05-07 12:33:22.968', 'sje', '2014-05-07 12:33:22.968');
INSERT INTO acumen.user_data_cat (id, user_data_cat_parent_id, name, description, language_code, desc_code, created_by, created_date, last_updated_by, last_updated_date) VALUES (11, 5, 'Indemnities', 'todo', 'systemAdmin.user.userData.asset.indemnities', 'todo', 'sje', '2014-05-07 12:33:22.968', 'sje', '2014-05-07 12:33:22.968');
INSERT INTO acumen.user_data_cat (id, user_data_cat_parent_id, name, description, language_code, desc_code, created_by, created_date, last_updated_by, last_updated_date) VALUES (12, 6, 'Stuff', 'todo', 'systemAdmin.user.userData.stuff', 'todo', 'sje', '2014-05-07 12:33:22.968', 'sje', '2014-05-07 12:33:22.968');
INSERT INTO acumen.user_data_cat (id, user_data_cat_parent_id, name, description, language_code, desc_code, created_by, created_date, last_updated_by, last_updated_date) VALUES (13, 2, 'Supplier', 'todo', 'systemAdmin.user.userData.finance.supplier', 'todo', 'bw', '2014-03-09 12:33:22.968', 'bw', '2014-05-07 12:33:22.968');
INSERT INTO acumen.user_data_cat (id, user_data_cat_parent_id, name, description, language_code, desc_code, created_by, created_date, last_updated_by, last_updated_date) VALUES (14, NULL, 'Sales', 'todo', 'systemAdmin.user.userData.finance.supplier', 'todo', 'bw', '2014-08-10 12:33:22.968', 'bw', '2014-08-10 12:33:22.968');


INSERT INTO acumen.user_data_types (id, parent_id, user_data_cat_id, name, value_required, description, created_by, created_date, last_updated_by, last_updated_date, lang_code, desc_code) VALUES (1, NULL, 7, 'Statuses', false,'The status of the asset', 'sje', '2014-05-07 12:38:16.568', 'sje', '2014-05-07 12:38:16.568', 'assetRegister.asset.generalTab.status', 'todo');
INSERT INTO acumen.user_data_types (id, parent_id, user_data_cat_id, name, value_required, description, created_by, created_date, last_updated_by, last_updated_date, lang_code, desc_code) VALUES (2, NULL, 7, 'Categories', false, 'Asset category', 'sje', '2014-05-07 12:38:16.568', 'sje', '2014-05-07 12:38:16.568', 'assetRegister.asset.generalTab.category', 'todo');

INSERT INTO acumen.user_data_types (id, parent_id, user_data_cat_id, name, value_required, description, created_by, created_date, last_updated_by, last_updated_date, lang_code, desc_code) VALUES (3, NULL, 7, 'Country', false, 'todo', 'sje', '2014-05-07 12:38:16.568', 'sje', '2014-05-07 12:38:16.568', 'assetRegister.asset.generalTab.country', 'todo');
INSERT INTO acumen.user_data_types (id, parent_id, user_data_cat_id, name, value_required, description, created_by, created_date, last_updated_by, last_updated_date, lang_code, desc_code) VALUES (4, 3, 7, 'Company', false, 'todo', 'sje', '2014-05-07 12:38:16.568', 'sje', '2014-05-07 12:38:16.568', 'assetRegister.asset.generalTab.company', 'todo');
INSERT INTO acumen.user_data_types (id, parent_id, user_data_cat_id, name, value_required, description, created_by, created_date, last_updated_by, last_updated_date, lang_code, desc_code) VALUES (5, 4, 7, 'Division', false, 'todo', 'sje', '2014-05-07 12:38:16.568', 'sje', '2014-05-07 12:38:16.568', 'assetRegister.asset.generalTab.division', 'todo');
INSERT INTO acumen.user_data_types (id, parent_id, user_data_cat_id, name, value_required, description, created_by, created_date, last_updated_by, last_updated_date, lang_code, desc_code) VALUES (6, NULL, 7, 'Site', false, 'todo', 'sje', '2014-05-07 12:38:16.568', 'sje', '2014-05-07 12:38:16.568', 'assetRegister.asset.generalTab.site', 'todo');
INSERT INTO acumen.user_data_types (id, parent_id, user_data_cat_id, name, value_required, description, created_by, created_date, last_updated_by, last_updated_date, lang_code, desc_code) VALUES (7, NUlL, 7, 'Department', false, 'todo', 'sje', '2014-05-07 12:38:16.568', 'sje', '2014-05-07 12:38:16.568', 'assetRegister.asset.generalTab.department', 'todo');
INSERT INTO acumen.user_data_types (id, parent_id, user_data_cat_id, name, value_required, description, created_by, created_date, last_updated_by, last_updated_date, lang_code, desc_code) VALUES (8, NULL, 7, 'Location', false, 'todo', 'sje', '2014-05-07 12:38:16.568', 'sje', '2014-05-07 12:38:16.568', 'assetRegister.asset.generalTab.location', 'todo');

INSERT INTO acumen.user_data_types (id, parent_id, user_data_cat_id, name, value_required, description, created_by, created_date, last_updated_by, last_updated_date, lang_code, desc_code) VALUES (9, NULL, 7, 'Asset Number Part 1', false, 'todo', 'sje', '2014-05-07 12:38:16.568', 'sje', '2014-05-07 12:38:16.568', 'assetRegister.asset.generalTab.assetNumberPart1', 'todo');
INSERT INTO acumen.user_data_types (id, parent_id, user_data_cat_id, name, value_required, description, created_by, created_date, last_updated_by, last_updated_date, lang_code, desc_code) VALUES (10, NULL, 7, 'Asset Number Part 2', false, 'todo', 'sje', '2014-05-07 12:38:16.568', 'sje', '2014-05-07 12:38:16.568', 'assetRegister.asset.generalTab.assetNumberPart2', 'todo');
INSERT INTO acumen.user_data_types (id, parent_id, user_data_cat_id, name, value_required, description, created_by, created_date, last_updated_by, last_updated_date, lang_code, desc_code) VALUES (11, NULL, 7, 'Asset Number Part 3', false, 'todo', 'sje', '2014-05-07 12:38:16.568', 'sje', '2014-05-07 12:38:16.568', 'assetRegister.asset.generalTab.assetNumberPart3', 'todo');

-- Asset Register > tenure > VAT Codes
INSERT INTO acumen.user_data_types (id, parent_id, user_data_cat_id, name, value_required, description, created_by, created_date, last_updated_by, last_updated_date, lang_code, desc_code) VALUES (12, NULL, 9, 'Lease Type', false, 'todo', 'gwb', '2014-07-07 10:57:21', 'gwb', '2014-07-07 10:57:21', 'assetRegister.asset.tenureTab.leaseType', 'todo');
INSERT INTO acumen.user_data_types (id, parent_id, user_data_cat_id, name, value_required, description, created_by, created_date, last_updated_by, last_updated_date, lang_code, desc_code) VALUES (13, NULL, 9, 'Charge Period', false, 'todo', 'bw', '2014-07-08 09:54:21', 'bw', '2014-07-08 09:54:21', 'assetRegister.asset.tenureTab.chargePeriod', 'todo');

-- Asset Register > tenure > Optional Extras
INSERT INTO acumen.user_data_types (id, parent_id, user_data_cat_id, name, value_required, description, created_by, created_date, last_updated_by, last_updated_date, lang_code, desc_code) VALUES (31, NULL, 9, 'Optional Extra', true, 'todo', 'bw', '2014-09-09 13:00:21', 'bw', '2014-09-09 13:00:21', 'assetRegister.asset.tenureTab.optionalExtras', 'todo');

-- Asset Register > finance > VAT Codes
INSERT INTO acumen.user_data_types (id, parent_id, user_data_cat_id, name, value_required, description, created_by, created_date, last_updated_by, last_updated_date, lang_code, desc_code) VALUES (14, NULL, 8, 'VAT Code', true, 'todo', 'bw', '2014-07-08 11:35:21', 'bw', '2014-07-08 11:34:21', 'assetRegister.asset.financeTab.vatCode', 'todo');
INSERT INTO acumen.user_data_types (id, parent_id, user_data_cat_id, name, value_required, description, created_by, created_date, last_updated_by, last_updated_date, lang_code, desc_code) VALUES (16, NULL, 8, 'Purchase Lead times', false,'todo', 'bw', '2014-07-09 11:11:21', 'bw', '2014-07-09 11:11:21', 'assetRegister.asset.financeTab.purchaseLeadTime', 'todo');
INSERT INTO acumen.user_data_types (id, parent_id, user_data_cat_id, name, value_required, description, created_by, created_date, last_updated_by, last_updated_date, lang_code, desc_code) VALUES (18, NULL, 8, 'Depreciation Codes', false,'todo', 'bw', '2014-07-09 11:11:21', 'bw', '2014-07-09 11:11:21', 'assetRegister.asset.financeTab.depreciationCode', 'todo');

-- Asset Register > Indemnities > Insurance Types
INSERT INTO acumen.user_data_types (id, parent_id, user_data_cat_id, name, value_required, description, created_by, created_date, last_updated_by, last_updated_date, lang_code, desc_code) VALUES (15, NULL, 11, 'Insurance Type', false,'todo', 'bw', '2014-07-09 09:50:21', 'bw', '2014-07-09 09:50:21', 'assetRegister.asset.indemnitiesTab.insuranceType', 'todo');
-- Asset Register > Indemnities > Warranty Types
INSERT INTO acumen.user_data_types (id, parent_id, user_data_cat_id, name, value_required, description, created_by, created_date, last_updated_by, last_updated_date, lang_code, desc_code) VALUES (17, NULL, 11, 'Warranty Type', false,'todo', 'bw', '2014-07-09 11:28:21', 'bw', '2014-07-09 11:28:21', 'assetRegister.asset.indemnitiesTab.warrantyType', 'todo');

-- Asset Names
INSERT INTO acumen.user_data_types (id, parent_id, user_data_cat_id, name, value_required, description, created_by, created_date, last_updated_by, last_updated_date, lang_code, desc_code) VALUES (19, NULL, 1, 'Asset Names', false, 'Asset Names from landing register.', 'mw', '2014-07-18 15:28:21', 'bw', '2014-07-18 15:28:21', 'assetRegister.asset.search.asset.name', 'todo');

-- Finanial > create supplier > status
INSERT INTO acumen.user_data_types (id, parent_id, user_data_cat_id, name, value_required, description, created_by, created_date, last_updated_by, last_updated_date, lang_code, desc_code) VALUES (20, NULL, 13, 'Statuses', false,'The status of the supplier', 'bw', '2014-01-09 12:38:16.568', 'bw', '2014-01-09 12:38:16.568', 'finance.supplier.supplierDetailsTab.status', 'todo');

-- Finanial > create supplier > categories
INSERT INTO acumen.user_data_types (id, parent_id, user_data_cat_id, name, value_required, description, created_by, created_date, last_updated_by, last_updated_date, lang_code, desc_code) VALUES (21, NULL, 13, 'Categories', false,'The status of the supplier', 'bw', '2014-01-09 12:38:16.568', 'bw', '2014-01-09 12:38:16.568', 'finance.client.clientDetailsTab.categories', 'todo');

-- Finanial > create supplier > ratings
INSERT INTO acumen.user_data_types (id, parent_id, user_data_cat_id, name, value_required, description, created_by, created_date, last_updated_by, last_updated_date, lang_code, desc_code) VALUES (22, NULL, 13, 'Rating', false,'The rating of the websites', 'bw', '2014-01-09 12:38:16.568', 'bw', '2014-01-09 12:38:16.568', 'finance.client.clientDetailsTab.rating', 'todo');

-- Finanial > create supplier > currency accepted
INSERT INTO acumen.user_data_types (id, parent_id, user_data_cat_id, name, value_required, description, created_by, created_date, last_updated_by, last_updated_date, lang_code, desc_code) VALUES (23, NULL, 13, 'Currency Accepted', false,'The currencies accepted', 'bw', '2014-02-09 12:38:16.568', 'bw', '2014-02-09 12:38:16.568', 'finance.client.financialDetailsTab.currencyAccepted', 'todo');

-- Finanial > create supplier > terms agreed
INSERT INTO acumen.user_data_types (id, parent_id, user_data_cat_id, name, value_required, description, created_by, created_date, last_updated_by, last_updated_date, lang_code, desc_code) VALUES (24, NULL, 13, 'Terms Agreed', false,'The terms agreed', 'bw', '2014-02-09 12:38:16.568', 'bw', '2014-02-09 12:38:16.568', 'finance.client.financialDetailsTab.termsAgreed', 'todo');

-- Finanial > create supplier > spend limit
INSERT INTO acumen.user_data_types (id, parent_id, user_data_cat_id, name, value_required, description, created_by, created_date, last_updated_by, last_updated_date, lang_code, desc_code) VALUES (25, NULL, 13, 'Spend Limit', false,'The spend limit', 'bw', '2014-02-09 12:38:16.568', 'bw', '2014-02-09 12:38:16.568', 'finance.client.financialDetailsTab.spendLimit', 'todo');

-- Finanial > create supplier > accreditations
INSERT INTO acumen.user_data_types (id, parent_id, user_data_cat_id, name, value_required, description, created_by, created_date, last_updated_by, last_updated_date, lang_code, desc_code) VALUES (28, NULL, 13, 'Accreditations', false,'Accreditations of the supplier', 'bw', '2014-02-09 12:38:16.568', 'bw', '2014-02-09 12:38:16.568', 'finance.client.otherDetailsTab.accreditations', 'todo');

-- Finanial > create supplier > country
INSERT INTO acumen.user_data_types (id, parent_id, user_data_cat_id, name, value_required, description, created_by, created_date, last_updated_by, last_updated_date, lang_code, desc_code) VALUES (29, NULL, 13, 'Country', false,'The country of the supplier', 'bw', '2014-02-09 12:38:16.568', 'bw', '2014-02-09 12:38:16.568', 'finance.supplier.financialDetailsTab.country', 'todo');

-- Asset Register > General > Other System Names
--INSERT INTO acumen.user_data_types (id, parent_id, user_data_cat_id, name, value_required, description, created_by, created_date, last_updated_by, last_updated_date, lang_code, desc_code) VALUES (30, NULL, 12, 'Other System Name', false,'todo', 'bw', '2014-07-09 11:28:21', 'bw', '2014-07-09 11:28:21', 'assetRegister.asset.generalTab.otherSystemName', 'todo');

-- Sales > create proposal > invoicing schedule
INSERT INTO acumen.user_data_types (id, parent_id, user_data_cat_id, name, value_required, description, created_by, created_date, last_updated_by, last_updated_date, lang_code, desc_code) VALUES (32, NULL, 14, 'Invoicing Schedule', false,'Schedule for an invoice', 'bw', '2014-05-09 12:38:16.568', 'bw', '2014-05-09 12:38:16.568', 'sales.createproposal.invoicingSchedule', 'todo');



-- Asset Register > Asset > Maintenance > Industry
INSERT INTO acumen.user_data_types (id, parent_id, user_data_cat_id, name, value_required, description, created_by, created_date, last_updated_by, last_updated_date, lang_code, desc_code) VALUES (33, NULL, 10, 'Industry', false,'todo', 'bw', '2014-07-09 11:28:21', 'bw', '2014-07-09 11:28:21', 'assetRegister.asset.maintenance.industry', 'todo');

-- Asset Register > Asset > Maintenance > Core Skills
INSERT INTO acumen.user_data_types (id, parent_id, user_data_cat_id, name, value_required, description, created_by, created_date, last_updated_by, last_updated_date, lang_code, desc_code) VALUES (34, NULL, 10, 'Core Skills', false,'todo', 'bw', '2014-07-09 11:28:21', 'bw', '2014-07-09 11:28:21', 'assetRegister.asset.maintenance.coreSkills', 'todo');

-- Asset Register > Asset > Maintenance > Qualifications
INSERT INTO acumen.user_data_types (id, parent_id, user_data_cat_id, name, value_required, description, created_by, created_date, last_updated_by, last_updated_date, lang_code, desc_code) VALUES (35, NULL, 10, 'Qualifications', false,'todo', 'bw', '2014-07-09 11:28:21', 'bw', '2014-07-09 11:28:21', 'assetRegister.asset.maintenance.qualifications', 'todo');

-- Asset Register > Asset > Maintenance > Discispline
INSERT INTO acumen.user_data_types (id, parent_id, user_data_cat_id, name, value_required, description, created_by, created_date, last_updated_by, last_updated_date, lang_code, desc_code) VALUES (36, NULL, 10, 'Discipline', false,'todo', 'bw', '2014-07-09 11:28:21', 'bw', '2014-07-09 11:28:21', 'assetRegister.asset.maintenance.discipline', 'todo');

-- Financial > create supplier > addressType
INSERT INTO acumen.user_data_types (id, parent_id, user_data_cat_id, name, value_required, description, created_by, created_date, last_updated_by, last_updated_date, lang_code, desc_code) VALUES (37, NULL, 13, 'Address type', false, 'The type of an Address', 'bw', '2014-05-09 12:38:16.568', 'bw', '2014-05-09 12:38:16.568', 'finance.supplier.supplierDetailsTab.addressType', 'todo');

-- Financial > create supplier > emailType
INSERT INTO acumen.user_data_types (id, parent_id, user_data_cat_id, name, value_required, description, created_by, created_date, last_updated_by, last_updated_date, lang_code, desc_code) VALUES (38, NULL, 13, 'Email Type', false, 'The type of an email address', 'bw', '2014-05-09 12:38:16.568', 'bw', '2014-05-09 12:38:16.568', 'finance.supplier.supplierDetailsTab.emailType', 'todo');

-- Financial > create supplier > telephoneNumberType
INSERT INTO acumen.user_data_types (id, parent_id, user_data_cat_id, name, value_required, description, created_by, created_date, last_updated_by, last_updated_date, lang_code, desc_code) VALUES (39, NULL, 13, 'Telephone number type', false, 'The type of a telephone number', 'bw', '2014-05-09 12:38:16.568', 'bw', '2014-05-09 12:38:16.568', 'finance.supplier.supplierDetailsTab.telephoneType', 'todo');

