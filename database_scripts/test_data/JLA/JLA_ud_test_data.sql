/*
 * This is a test data script
 *
 */

-- Statuses
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
VALUES (1, 1, 'missing', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
VALUES (2, 1, 'orphaned', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
VALUES (26, 1, 'decommissioned', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
VALUES (27, 1, 'inactive', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
VALUES (28, 1, 'leased in', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
VALUES (29, 1, 'leased out', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);

-- Categories
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
VALUES (3, 2, 'Laundry Equipment', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
VALUES (11, 2, 'Catering', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
VALUES (12, 2, 'Medical', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
VALUES (30, 2, 'Detergents', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
VALUES (31, 2, 'Compliance & Safety', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);


-- Country
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
VALUES (4, 3, 'United Kingdom', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
VALUES (5, 3, 'France', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
VALUES (34, 3, 'Germany', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
VALUES (35, 3, 'USA', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
VALUES (36, 3, 'Sweden', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
VALUES (37, 3, 'Belgium', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);

-- Company
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
VALUES (6, 4, 'glaxo', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
VALUES (7, 4, 'atos', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
VALUES (38, 4, 'JCB', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
VALUES (39, 4, 'smith * nephew', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
VALUES (40, 4, 'rsclare', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
VALUES (41, 4, 'worscester', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);

-- Department
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
VALUES (8, 7, 'Parts', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
VALUES (9, 7, 'Human Resources', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
VALUES (10, 7, 'ICT', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
VALUES (42, 7, 'Maintenance', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);

-- Location
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
VALUES (13, 8, 'Ground floor', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
VALUES (14, 8, 'Top floor', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
VALUES (43, 8, 'Building 1', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
VALUES (44, 8, 'Building 2', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
VALUES (45, 8, 'Bin 1', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
VALUES (46, 8, 'Bin 2', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);

-- Site
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
VALUES (15, 6, 'Coventry', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
VALUES (16, 6, 'Manchester', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
VALUES (47, 6, 'Chester', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
VALUES (48, 6, 'Lincoln', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
VALUES (49, 6, 'Falmouth', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);

-- Division
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
VALUES (17, 5, 'Arrivals', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
VALUES (18, 5, 'Dispatch', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
VALUES (50, 5, 'Paint shop', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
VALUES (51, 5, 'Fitting shop', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);

-- Asset number 1
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
VALUES (19, 9, 'UK', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
VALUES (20, 9, 'Germany', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
VALUES (21, 9, 'Italy', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
VALUES (52, 9, 'France', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
VALUES (53, 9, 'Belgium', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
VALUES (54, 9, 'Austria', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);

-- Asset number 2
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
VALUES (22, 10, 'Manchester', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
VALUES (23, 10, 'Leeds', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
VALUES (55, 10, 'London', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
VALUES (56, 10, 'Birmingham', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
VALUES (57, 10, 'Lincoln', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
VALUES (58, 10, 'Chester', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);

-- Asset number 3
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
VALUES (24, 11, 'Building 1', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
VALUES (25, 11, 'Building 2', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
VALUES (59, 11, 'Building 3', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
VALUES (60, 11, 'Building 4', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
VALUES (61, 11, 'Building 5', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
VALUES (62, 11, 'Building 6', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);

-- Asset Register > Tenure > Type
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
VALUES (63, 12, 'Hire Purchase', 1, true, 1,'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
VALUES (64, 12, 'Personal Contract Purchase', 1, true, 1,'ud_test_data', now(), 'ud_test_data', now(), 1);

-- Asset Register > Tenure > Charge period
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
VALUES (65, 13, 'Per Day', 1, true, 1,'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
VALUES (66, 13, 'Per Weekend', 1, true, 1,'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
VALUES (67, 13, 'Per Week', 1, true, 1,'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
VALUES (68, 13, 'Per Month', 1, true, 1,'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
VALUES (69, 13, 'Per Year', 1, true, 1,'ud_test_data', now(), 'ud_test_data', now(), 1);

-- Asset Register > finance > VAT Codes
INSERT INTO acumen.user_data (id, user_data_types_id, name, value, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
VALUES (70, 14, 'T0 - zero rated transactions', '0', 1, true, 1,'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, value, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
VALUES (71, 14, 'T1 - standard rate', '20',1, true, 1,'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, value, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
VALUES (72, 14, 'T2 - exempt transactions', '0', 1, true, 1,'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, value, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
VALUES (73, 14, 'T3 - used for Reduced Rate 5%', '5', 1, true, 1,'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, value, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
VALUES (74, 14, 'T4 - sales to customers in EC', '0',1, true, 1,'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, value, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
VALUES (75, 14, 'T5 - lower rate', '0', 1, true, 1,'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, value, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
VALUES (76, 14, 'T6 - Items excluded from calculation', '0' ,1, true, 1,'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, value, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
VALUES (77, 14, 'T7 - zero-rated purchases from suppliers in EEC', '0', 1, true, 1,'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, value, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
VALUES (78, 14, 'T8 - standard-rated purchases from suppliers in EEC', '17.5',1, true, 1,'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, value, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
VALUES (79, 14, 'T9 - transactions outside the scope of VAT', '0',1, true, 1,'ud_test_data', now(), 'ud_test_data', now(), 1);

-- Asset Register > finance > Purchase lead time
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
VALUES (83, 16, '1 Week', 1, true, 1,'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
VALUES (84, 16, '2 Weeks', 1, true, 1,'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
VALUES (85, 16, '3 Week', 1, true, 1,'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
VALUES (86, 16, '1 Month', 1, true, 1,'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
VALUES (87, 16, '2 Months', 1, true, 1,'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
VALUES (88, 16, '3 Months', 1, true, 1,'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
VALUES (89, 16, '4 Months', 1, true, 1,'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
VALUES (90, 16, 'Up to 11 Months', 1, true, 1,'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
VALUES (91, 16, '1 Year', 1, true, 1,'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
VALUES (92, 16, '2 Years', 1, true, 1,'ud_test_data', now(), 'ud_test_data', now(), 1);

-- Asset Register > Indemnities > Insurance Types
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
VALUES (80, 15, '3rd Party', 1, true, 1,'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
VALUES (81, 15, '3rd Party, Fire and Theft', 1, true, 1,'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
VALUES (82, 15, 'Fully Comprehensive', 1, true, 1,'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
VALUES (113, 15, 'Indemnity', 1, true, 1,'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
VALUES (114, 15, 'Blanket', 1, true, 1,'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
VALUES (115, 15, 'Public Liability', 1, true, 1,'ud_test_data', now(), 'ud_test_data', now(), 1);

-- Asset Register > Indemnities > Warranty Types
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
VALUES (93, 17, 'Warranty Type 1', 1, true, 1,'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
VALUES (94, 17, 'Warranty Type 2', 2, true, 1,'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
VALUES (95, 17, 'Warranty Type 3', 2, true, 1,'ud_test_data', now(), 'ud_test_data', now(), 1);

-- Asset Register > finance > Depreciation Codes
INSERT INTO acumen.user_data (id, user_data_types_id, name, value, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
VALUES (96, 18, '10%', '10', 1, true, 1,'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, value, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
VALUES (97, 18, '15%', '15', 1, true, 1,'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, value, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
VALUES (98, 18, '20%', '20', 1, true, 1,'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, value, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
VALUES (99, 18, '25%', '25', 1, true, 1,'ud_test_data', now(), 'ud_test_data', now(), 1);
INSERT INTO acumen.user_data (id, user_data_types_id, name, value, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
VALUES (100, 18, '30%', '30', 1, true, 1,'ud_test_data', now(), 'ud_test_data', now(), 1);


-- Asset Names
INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
VALUES (101, 19, 'Washer', 1, true, 1,'ud_test_data', now(), 'ud_test_data', now(), 1);

INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
VALUES (102, 19 , 'Combi Oven', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);

INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
VALUES (103, 19 , 'Commercial Fryer', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);

INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
VALUES (104, 19 , 'Glass Washer', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);

INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
VALUES (105, 19 , 'Fridge', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);

INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
VALUES (106, 19 , 'Freezer', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);

INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
VALUES (107, 19 , 'Medical Macerator', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);

INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
VALUES (108, 19 , 'Rotary Iron', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);

INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
VALUES (109, 19 , 'OTEX', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);

INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
VALUES (110, 19 , 'Stacked Washer & Dryer', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);

INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
VALUES (111, 19 , 'Utensil Washer', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);

INSERT INTO acumen.user_data (id, user_data_types_id, name, ud_order, active, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number) 
VALUES (112, 19 , 'Gas Cooktop', 1, true, 1, 'ud_test_data', now(), 'ud_test_data', now(), 1);