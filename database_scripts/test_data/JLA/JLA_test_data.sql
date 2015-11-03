--Washer 1  
INSERT INTO acumen.assets(
            id, asset_status_id, supplier_id, manufacturer_id, custodian_id, 
            asset_number_part_1_id, asset_number_part_2_id, asset_number_part_3_id, 
            asset_number_part_4, site_id, location_id, division_id, department_id, depreciation_code, 
            country_id, company_id, category_id, uid, name, description,  
            serial_number, supplier_pn, manufacturer_pn, is_a_facility, is_equipment, 
            is_part, critical_asset, part_of_group, parts, risk_assessment, 
            bcp, tracking_device, cost, purchase_price, predicted_end_of_life_value, vat_code, date_of_purchase, installation_date, 
            commissioning_date, decommissioning_date, end_of_life_date, life_expectancy, 
            purchase_lead_time, budget_limit, tenant_id, created_by, created_date, 
            last_updated_by, last_updated_date, version_number
            )
    VALUES (100, 29, 200, NULL, 400, 
            21, 57, 59, 
            1, 16, 43, 18, 8, 96, 
            4, 7, 3, 'JLA_01234', 'Washer', 'Light Commerical',
            '19283', '20','' , false, false, 
            true, true, NULL, NULL, NULL, 
            false, true, '480.00', '400.00', '314.93', 71, '1/8/2014', '2/8/2014', 
            '2/8/2014', '2/8/2016', '2/8/2024', NULL, 
            91, '500', 1, 'JLA@JLA.co.uk', now(), 
            'JLA@JLA.co.uk', now(), 1
            );
        
INSERT INTO acumen.warranties(id, warranties_type_id, warranties_supplier_id, assets_id,commencement_date, expiry_date, cost, policy_number, om, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
            VALUES (100, 94, 200,100,'2014-05-07 00:00:00.000', '2015-05-09 00:00:00.000','300','KL-HGL1029373',true, 1, 'pJones', now(),'pJones',now(),1);
 
INSERT INTO acumen.asset_insurance_types(id, assets_id, insurance_type_id, mandatory, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
			VALUES(100, 100, 80, true, 1, 'pJones', now(), 'pJones', now(), 1 );
				
INSERT INTO acumen.insurance_policies(id, insurance_supplier_id, commencement_date, expiry_date, cost, policy_number, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
			VALUES(100, 200, '2014-05-07 00:00:00.000', '2015-05-07 00:00:00.000', '100', 'A3XY-45D1-1342-DLE5', 1, 'pJones', now(), 'pJones', now(), 1);

INSERT INTO acumen.insurance_type_policy_links(id, insurance_policy_id, asset_insurance_type_id, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
			VALUES(100, 100, 100, 1, 'pJones', now(),  'pJones', now(), 1);
									
INSERT INTO acumen.lease_in(id, assets_id, party_id, lease_type, lease_charge, vat_code, charge_period, lease_commences, lease_expires, lease_period, lease_cost, lease_value, lease_out_margin, location_postcode, maintenance_included, warranty_included, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
			VALUES(150, 100, 200, 63, '400.00', 71, 2, '2016-05-07 00:00:00.000', '2014-05-07 00:00:00.000', '6' , '10000.00', '480.00', '111.11', 'sal', true, true, 1, 'pJones', now(),  'pJones', now(), 1);
								
INSERT INTO acumen.lease_in(id, assets_id, party_id, lease_type, lease_charge, vat_code, charge_period, lease_commences, lease_expires, lease_period, lease_cost, lease_value, lease_out_margin, location_postcode, maintenance_included, warranty_included, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
			VALUES(151, 100, 301, 63, '840.00', 71, 2, '2016-05-07 00:00:00.000', '2014-05-07 00:00:00.000', '6' , '10000.00', '840.00', '360.00', 'sal', true, true, 1, 'pJones', now(),  'pJones', now(), 1);									

			SELECT setval('acumen.assets_id_seq', max(id)) from acumen.assets;            
					
--glass washer	 				
INSERT INTO acumen.assets(
            id, asset_status_id, supplier_id, manufacturer_id, custodian_id, 
            asset_number_part_1_id, asset_number_part_2_id, asset_number_part_3_id, 
            asset_number_part_4, site_id, location_id, division_id, department_id, depreciation_code, 
            country_id, company_id, category_id, uid, name, description,
            serial_number, supplier_pn, manufacturer_pn, is_a_facility, is_equipment, 
            is_part, critical_asset, part_of_group, parts, risk_assessment, 
            bcp, tracking_device, cost, purchase_price, predicted_end_of_life_value, vat_code, date_of_purchase, installation_date, 
            commissioning_date, decommissioning_date, end_of_life_date, life_expectancy, 
            purchase_lead_time, budget_limit, tenant_id, created_by, created_date, 
            last_updated_by, last_updated_date, version_number
            )
    VALUES (102, 29, 202, NULL, 402, 
            20, 57, 59, 
            1, 16, 43, 18, 8, 96, 
            4, 7, 3, 'JLA_0002', 'Glass Washer', 'Commerical',
            '19865', '17','' , false, false, 
            true, false, NULL, NULL, NULL, 
            false, true, '600.00', '500.00', '437.40', 71, '6/9/2014', '2/8/2014', 
            '5/9/2014', '2/8/2015', '5/9/2021', NULL, 
            91, '700', 1, 'JLA@JLA.co.uk', now(), 
            'JLA@JLA.co.uk', now(), 1
            );

INSERT INTO acumen.asset_insurance_types(id, assets_id, insurance_type_id, mandatory, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
			VALUES(102, 102, 81, true, 1, 'pJones', now(), 'pJones', now(), 1 );
				
INSERT INTO acumen.insurance_policies(id, insurance_supplier_id, commencement_date, expiry_date, cost, policy_number, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
			VALUES(102, 202, '2014-05-07 00:00:00.000', '2015-05-07 00:00:00.000', '150', '-89D1-1082-HRJ7', 1, 'pJones', now(), 'pJones', now(), 1);

INSERT INTO acumen.insurance_type_policy_links(id, insurance_policy_id, asset_insurance_type_id, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
			VALUES(102, 102, 102, 1, 'pJones', now(),  'pJones', now(), 1);
									
INSERT INTO acumen.lease_in(id, assets_id, party_id, lease_type, lease_charge, vat_code, charge_period, lease_commences, lease_expires, lease_period, lease_cost, lease_value, lease_out_margin, location_postcode, maintenance_included, warranty_included, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
			VALUES(152, 102, 202, 63, '500.00', 71, 2, '2016-05-07 00:00:00.000', '2014-05-07 00:00:00.000', '5' , '10000.00', '600.00', '111.11', 'sal', true, true, 1, 'pJones', now(),  'pJones', now(), 1);
								
INSERT INTO acumen.lease_in(id, assets_id, party_id, lease_type, lease_charge, vat_code, charge_period, lease_commences, lease_expires, lease_period, lease_cost, lease_value, lease_out_margin, location_postcode, maintenance_included, warranty_included, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
			VALUES(153, 102, 301, 63, '800.00', 71, 2, '2016-05-07 00:00:00.000', '2014-05-07 00:00:00.000', '5' , '10000.00', '960.00', '360.00', 'sal', true, false, 1, 'pJones', now(),  'pJones', now(), 1);									

			SELECT setval('acumen.assets_id_seq', max(id)) from acumen.assets;                  

-- Washer 2              
INSERT INTO acumen.assets(
            id, asset_status_id, supplier_id, manufacturer_id, custodian_id, 
            asset_number_part_1_id, asset_number_part_2_id, asset_number_part_3_id, 
            asset_number_part_4, site_id, location_id, division_id, department_id, depreciation_code, 
            country_id, company_id, category_id, uid, name, description,
            serial_number, supplier_pn, manufacturer_pn, is_a_facility, is_equipment, 
            is_part, critical_asset, part_of_group, parts, risk_assessment, 
            bcp, tracking_device, cost, purchase_price, predicted_end_of_life_value, vat_code, date_of_purchase, installation_date, 
            commissioning_date, decommissioning_date, end_of_life_date, life_expectancy, 
            purchase_lead_time, budget_limit, tenant_id, created_by, created_date, 
            last_updated_by, last_updated_date, version_number
            )
    VALUES (101, 28, 201, NULL, 401, 
            52, 58, 24, 
            2, 16, 43, 18, 8, 96, 
            4, 7, 3, 'JLA_0001', 'Washer', 'Light Commerical',
            '18645', '18','' , false, false, 
            true, false, NULL, NULL, NULL, 
            false, true, '480.00', '400.00', '388.80', 71, '1/8/2014', '2/8/2014', 
            '5/9/2014', '2/8/2015', '5/9/2016', NULL, 
            91, '500', 1, 'JLA@JLA.co.uk', now(), 
            'JLA@JLA.co.uk', now(), 1
            );
            
 
INSERT INTO acumen.asset_insurance_types(id, assets_id, insurance_type_id, mandatory, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
			VALUES(103, 101, 82, true, 1, 'pJones', now(), 'pJones', now(), 1 );
				
INSERT INTO acumen.insurance_policies(id, insurance_supplier_id, commencement_date, expiry_date, cost, policy_number, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
			VALUES(103, 201, '2014-05-07 00:00:00.000', '2015-05-07 00:00:00.000', '200', 'B5YY-89D1-1082-HRJ7', 1, 'pJones', now(), 'pJones', now(), 1);

INSERT INTO acumen.insurance_type_policy_links(id, insurance_policy_id, asset_insurance_type_id, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
			VALUES(103, 103, 103, 1, 'pJones', now(),  'pJones', now(), 1);
									
INSERT INTO acumen.lease_in(id, assets_id, party_id, lease_type, lease_charge, vat_code, charge_period, lease_commences, lease_expires, lease_period, lease_cost, lease_value, lease_out_margin, location_postcode, maintenance_included, warranty_included, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
			VALUES(154, 101, 201, 63, '480.00', 71, 2, '2016-05-07 00:00:00.000', '2014-05-07 00:00:00.000', '8' , '10000.00', '480.00', '111.11', 'sal', true, true, 1, 'pJones', now(),  'pJones', now(), 1);

			SELECT setval('acumen.assets_id_seq', max(id)) from acumen.assets;            
            
--Freezer             
INSERT INTO acumen.assets(
            id, asset_status_id, supplier_id, manufacturer_id, custodian_id, 
            asset_number_part_1_id, asset_number_part_2_id, asset_number_part_3_id, 
            asset_number_part_4, site_id, location_id, division_id, department_id, depreciation_code, 
            country_id, company_id, category_id, uid, name, description,
            serial_number, supplier_pn, manufacturer_pn, is_a_facility, is_equipment, 
            is_part, critical_asset, part_of_group, parts, risk_assessment, 
            bcp, tracking_device, cost, purchase_price, predicted_end_of_life_value, vat_code, date_of_purchase, installation_date, 
            commissioning_date, decommissioning_date, end_of_life_date, life_expectancy, 
            purchase_lead_time, budget_limit, tenant_id, created_by, created_date, 
            last_updated_by, last_updated_date, version_number
            )
    VALUES (103, 28, 203, NULL, 403, 
            19, 57, 61, 
            1, 16, 43, 18, 8, 96, 
            4, 7, 3, 'JLA_0003', 'Freezer', 'Commerical',
            '15321', '17','' , false, false, 
            true, true, NULL, NULL, NULL, 
            false, true, '360.00', '300.00', '262.44', 71, '12/11/2014', '12/11/2015', 
            '5/9/2014', '2/8/2015', '5/8/2017', NULL, 
            91, '400', 1, 'JLA@JLA.co.uk', now(), 
            'JLA@JLA.co.uk', now(), 1
            ); 
									
INSERT INTO acumen.lease_in(id, assets_id, party_id, lease_type, lease_charge, vat_code, charge_period, lease_commences, lease_expires, lease_period, lease_cost, lease_value, lease_out_margin, location_postcode, maintenance_included, warranty_included, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
			VALUES(155, 103, 203, 63, '360.00', 71, 2, '2016-05-07 00:00:00.000', '2014-05-07 00:00:00.000', '9' , '10000.00', '480.00', '111.11', 'sal', true, true, 1, 'pJones', now(),  'pJones', now(), 1);

			SELECT setval('acumen.assets_id_seq', max(id)) from acumen.assets;               
            
--Rotary Iron             
INSERT INTO acumen.assets(
            id, asset_status_id, supplier_id, manufacturer_id, custodian_id, 
            asset_number_part_1_id, asset_number_part_2_id, asset_number_part_3_id, 
            asset_number_part_4, site_id, location_id, division_id, department_id, depreciation_code, 
            country_id, company_id, category_id, uid, name, description,
            serial_number, supplier_pn, manufacturer_pn, is_a_facility, is_equipment, 
            is_part, critical_asset, part_of_group, parts, risk_assessment, 
            bcp, tracking_device, cost, purchase_price, predicted_end_of_life_value, vat_code, date_of_purchase, installation_date, 
            commissioning_date, decommissioning_date, end_of_life_date, life_expectancy, 
            purchase_lead_time, budget_limit, tenant_id, created_by, created_date, 
            last_updated_by, last_updated_date, version_number
            )
    VALUES (104, 28, 204, NULL, 404, 
            20, 56, 61, 
            1, 16, 43, 18, 8, 96, 
            4, 7, 3, 'JLA_0004', 'Rotary Iron', 'Commerical',
            '15321', '17','' , false, false, 
            true, false, NULL, NULL, NULL, 
            false, true, '1800.00', '1500.00', '1312.20', 71, '12/11/2014', '12/11/2015', 
            '5/9/2014', '2/8/2015', '5/9/2017', NULL, 
            91, '2000', 1, 'JLA@JLA.co.uk', '2/8/2014', 
            'JLA@JLA.co.uk', '2/8/2014', 1
            );        
            
INSERT INTO acumen.warranties(id, warranties_type_id, warranties_supplier_id, assets_id,commencement_date, expiry_date, cost, policy_number, om, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
            VALUES (104, 94, 204, 104,'2014-05-07 00:00:00.000', '2015-05-09 00:00:00.000','300','BR-PIO1029373',true, 1, 'pJones', now(),'pJones',now(),1);
 
INSERT INTO acumen.asset_insurance_types(id, assets_id, insurance_type_id, mandatory, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
			VALUES(104, 104, 80, true, 1, 'pJones', now(), 'pJones', now(), 1 );
				
INSERT INTO acumen.insurance_policies(id, insurance_supplier_id, commencement_date, expiry_date, cost, policy_number, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
			VALUES(104, 200, '2014-05-07 00:00:00.000', '2015-05-07 00:00:00.000', '1000', 'J789-6DT1-8756-9FGH0', 1, 'pJones', now(), 'pJones', now(), 1);

INSERT INTO acumen.insurance_type_policy_links(id, insurance_policy_id, asset_insurance_type_id, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
			VALUES(104, 104, 104, 1, 'pJones', now(),  'pJones', now(), 1);
									
INSERT INTO acumen.lease_in(id, assets_id, party_id, lease_type, lease_charge, vat_code, charge_period, lease_commences, lease_expires, lease_period, lease_cost, lease_value, lease_out_margin, location_postcode, maintenance_included, warranty_included, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
			VALUES(156, 104, 200, 63, '1500.00', 71, 2, '2016-05-07 00:00:00.000', '2014-05-07 00:00:00.000', '6' , '10000.00', '1800.00', '111.11', 'sal', true, true, 1, 'pJones', now(),  'pJones', now(), 1);
								
INSERT INTO acumen.lease_in(id, assets_id, party_id, lease_type, lease_charge, vat_code, charge_period, lease_commences, lease_expires, lease_period, lease_cost, lease_value, lease_out_margin, location_postcode, maintenance_included, warranty_included, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
			VALUES(157, 104, 301, 63, '3000.00', 71, 2, '2016-05-07 00:00:00.000', '2014-05-07 00:00:00.000', '6' , '10000.00', '3600.00', '1800.00', 'sal', true, true, 1, 'pJones', now(),  'pJones', now(), 1);									

			SELECT setval('acumen.assets_id_seq', max(id)) from acumen.assets;  
            
--Commercial fryer            
INSERT INTO acumen.assets(
            id, asset_status_id, supplier_id, manufacturer_id, custodian_id, 
            asset_number_part_1_id, asset_number_part_2_id, asset_number_part_3_id, 
            asset_number_part_4, site_id, location_id, division_id, department_id, depreciation_code, 
            country_id, company_id, category_id, uid, name, description,
            serial_number, supplier_pn, manufacturer_pn, is_a_facility, is_equipment, 
            is_part, critical_asset, part_of_group, parts, risk_assessment, 
            bcp, tracking_device, cost, purchase_price, predicted_end_of_life_value, vat_code, date_of_purchase, installation_date, 
            commissioning_date, decommissioning_date, end_of_life_date, life_expectancy, 
            purchase_lead_time, budget_limit, tenant_id, created_by, created_date, 
            last_updated_by, last_updated_date, version_number
            )
    VALUES (105, 28, 205, NULL, 405, 
            21, 56, 61, 
            1, 16, 43, 18, 8, 96, 
            4, 7, 3, 'JLA_0005', 'Comerical Fryer', 'Commerical',
            '19081', '11','' , false, false, 
            true, false, NULL, NULL, NULL, 
            false, true, '720.00', '600.00', '425.15', 71, '1/10/2014', '1/10/2017', 
            '9/10/2014', '2/8/2015', '9/10/2019', NULL, 
            91, '800', 1, 'JLA@JLA.co.uk', now(), 
            'JLA@JLA.co.uk', now(), 1
            );          
            
INSERT INTO acumen.asset_insurance_types(id, assets_id, insurance_type_id, mandatory, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
			VALUES(105, 105, 82, true, 1, 'pJones', now(), 'pJones', now(), 1 );
				
INSERT INTO acumen.insurance_policies(id, insurance_supplier_id, commencement_date, expiry_date, cost, policy_number, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
			VALUES(105, 205, '2014-05-07 00:00:00.000', '2015-05-07 00:00:00.000', '130', 'P9BW-45D1-1342-DLE5', 1, 'pJones', now(), 'pJones', now(), 1);

INSERT INTO acumen.insurance_type_policy_links(id, insurance_policy_id, asset_insurance_type_id, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
			VALUES(105, 105, 105, 1, 'pJones', now(),  'pJones', now(), 1);
									
INSERT INTO acumen.lease_in(id, assets_id, party_id, lease_type, lease_charge, vat_code, charge_period, lease_commences, lease_expires, lease_period, lease_cost, lease_value, lease_out_margin, location_postcode, maintenance_included, warranty_included, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
			VALUES(158, 105, 205, 63, '600.00', 71, 2, '2016-05-07 00:00:00.000', '2014-05-07 00:00:00.000', '10' , '10000.00', '720.00', '111.11', 'sal', true, true, 1, 'pJones', now(),  'pJones', now(), 1);

			SELECT setval('acumen.assets_id_seq', max(id)) from acumen.assets;             

--Fridge            
INSERT INTO acumen.assets(
            id, asset_status_id, supplier_id, manufacturer_id, custodian_id, 
            asset_number_part_1_id, asset_number_part_2_id, asset_number_part_3_id, 
            asset_number_part_4, site_id, location_id, division_id, department_id, depreciation_code, 
            country_id, company_id, category_id, uid, name, description, 
            serial_number, supplier_pn, manufacturer_pn, is_a_facility, is_equipment, 
            is_part, critical_asset, part_of_group, parts, risk_assessment, 
            bcp, tracking_device, cost, purchase_price, predicted_end_of_life_value, vat_code, date_of_purchase, installation_date, 
            commissioning_date, decommissioning_date, end_of_life_date, life_expectancy, 
            purchase_lead_time, budget_limit, tenant_id, created_by, created_date, 
            last_updated_by, last_updated_date, version_number
            )
    VALUES (106, 28, 206, NULL, 406, 
            19, 57, 60, 
            1, 16, 43, 18, 8, 96, 
            4, 7, 3, 'JLA_0003', 'Fridge', 'Commerical',
            '15321', '17','' , false, false, 
            true, true, NULL, NULL, NULL, 
            false, true, '360.00', '300.00', '291.60', 71, '12/11/2014', '12/11/2015', 
            '7/9/2014', '2/8/2015', '7/9/2016', NULL, 
            91, '400', 1, 'JLA@JLA.co.uk', now(), 
            'JLA@JLA.co.uk', now(), 1
            );     
            
INSERT INTO acumen.lease_in(id, assets_id, party_id, lease_type, lease_charge, vat_code, charge_period, lease_commences, lease_expires, lease_period, lease_cost, lease_value, lease_out_margin, location_postcode, maintenance_included, warranty_included, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
			VALUES(159, 106, 206, 63, '300.00', 71, 2, '2016-05-07 00:00:00.000', '2014-05-07 00:00:00.000', '4' , '10000.00', '360.00', '111.11', 'sal', true, true, 1, 'pJones', now(),  'pJones', now(), 1);

			SELECT setval('acumen.assets_id_seq', max(id)) from acumen.assets;  
					
--Medical Macerators            
INSERT INTO acumen.assets(
            id, asset_status_id, supplier_id, manufacturer_id, custodian_id, 
            asset_number_part_1_id, asset_number_part_2_id, asset_number_part_3_id, 
            asset_number_part_4, site_id, location_id, division_id, department_id, depreciation_code, 
            country_id, company_id, category_id, uid, name, description, 
            serial_number, supplier_pn, manufacturer_pn, is_a_facility, is_equipment, 
            is_part, critical_asset, part_of_group, parts, risk_assessment, 
            bcp, tracking_device, cost, purchase_price, predicted_end_of_life_value, vat_code, date_of_purchase, installation_date, 
            commissioning_date, decommissioning_date, end_of_life_date, life_expectancy, 
            purchase_lead_time, budget_limit, tenant_id, created_by, created_date, 
            last_updated_by, last_updated_date, version_number
            )
    VALUES (107, 29, 207, NULL, 405, 
            20, 58, 61, 
            1, 16, 43, 18, 8, 96, 
            4, 6, 3, 'JLA_0006', 'Medical Macerators', 'Commerical',
            '15321', '17','' , false, false, 
            true, true, NULL, NULL, NULL, 
            false, true, '4200.00', '3500.00', '3061.80', 71, '12/11/2014', '12/11/2015', 
            '7/11/2014', '10/11/2015', '7/11/2017', NULL, 
            91, '4500', 1, 'JLA@JLA.co.uk', now(), 
            'JLA@JLA.co.uk', now(), 1
            );    					
 
INSERT INTO acumen.asset_insurance_types(id, assets_id, insurance_type_id, mandatory, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
			VALUES(107, 107, 80, true, 1, 'pJones', now(), 'pJones', now(), 1 );
				
INSERT INTO acumen.insurance_policies(id, insurance_supplier_id, commencement_date, expiry_date, cost, policy_number, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
			VALUES(107, 207, '2014-05-07 00:00:00.000', '2015-05-07 00:00:00.000', '100', 'A3XY-45D1-1342-DLE5', 1, 'pJones', now(), 'pJones', now(), 1);

INSERT INTO acumen.insurance_type_policy_links(id, insurance_policy_id, asset_insurance_type_id, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
			VALUES(107, 107, 107, 1, 'pJones', now(),  'pJones', now(), 1);
									
INSERT INTO acumen.lease_in(id, assets_id, party_id, lease_type, lease_charge, vat_code, charge_period, lease_commences, lease_expires, lease_period, lease_cost, lease_value, lease_out_margin, location_postcode, maintenance_included, warranty_included, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
			VALUES(160, 107, 207, 63, '3500.00', 71, 2, '2016-05-07 00:00:00.000', '2014-05-07 00:00:00.000', '6' , '10000.00', '4200.00', '111.11', 'sal', true, true, 1, 'pJones', now(),  'pJones', now(), 1);
								
INSERT INTO acumen.lease_in(id, assets_id, party_id, lease_type, lease_charge, vat_code, charge_period, lease_commences, lease_expires, lease_period, lease_cost, lease_value, lease_out_margin, location_postcode, maintenance_included, warranty_included, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
			VALUES(161, 107, 301, 63, '5000.00', 71, 2, '2016-05-07 00:00:00.000', '2014-05-07 00:00:00.000', '6' , '10000.00', '6000.00', '1800.00', 'sal', true, false, 1, 'pJones', now(),  'pJones', now(), 1);									

			SELECT setval('acumen.assets_id_seq', max(id)) from acumen.assets;       
			
--Combi Oven       
INSERT INTO acumen.assets(
            id, asset_status_id, supplier_id, manufacturer_id, custodian_id, 
            asset_number_part_1_id, asset_number_part_2_id, asset_number_part_3_id, 
            asset_number_part_4, site_id, location_id, division_id, department_id, depreciation_code, 
            country_id, company_id, category_id, uid, name, description,
            serial_number, supplier_pn, manufacturer_pn, is_a_facility, is_equipment, 
            is_part, critical_asset, part_of_group, parts, risk_assessment, 
            bcp, tracking_device, cost, purchase_price, predicted_end_of_life_value, vat_code, date_of_purchase, installation_date, 
            commissioning_date, decommissioning_date, end_of_life_date, life_expectancy, 
            purchase_lead_time, budget_limit, tenant_id, created_by, created_date, 
            last_updated_by, last_updated_date, version_number
            )
    VALUES (108, 28, 206, NULL, 406, 
            19, 58, 61, 
            1, 16, 43, 18, 8, 96, 
            4, 6, 3, 'JLA_0007', 'Combi Oven', 'Commerical',
            '11781', '16','' , false, false, 
            true, false, NULL, NULL, NULL, 
            false, true, '300.00', '250.00', '243', 71, '10/10/2014', '10/10/2015', 
            '10/10/2014', '10/10/2016', '10/11/2016',NULL, 
            91, '400', 1, 'JLA@JLA.co.uk', now(), 
            'JLA@JLA.co.uk', now(), 1
            );
            
INSERT INTO acumen.lease_in(id, assets_id, party_id, lease_type, lease_charge, vat_code, charge_period, lease_commences, lease_expires, lease_period, lease_cost, lease_value, lease_out_margin, location_postcode, maintenance_included, warranty_included, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
			VALUES(162, 108, 206, 63, '250.00', 71, 2, '2016-05-07 00:00:00.000', '2014-05-07 00:00:00.000', '2' , '10000.00', '300.00', '111.11', 'sal', true, true, 1, 'pJones', now(),  'pJones', now(), 1);
								
			SELECT setval('acumen.assets_id_seq', max(id)) from acumen.assets; 
	
--Combi Oven2       
INSERT INTO acumen.assets(
            id, asset_status_id, supplier_id, manufacturer_id, custodian_id, 
            asset_number_part_1_id, asset_number_part_2_id, asset_number_part_3_id, 
            asset_number_part_4, site_id, location_id, division_id, department_id, depreciation_code, 
            country_id, company_id, category_id, uid, name, description,
            serial_number, supplier_pn, manufacturer_pn, is_a_facility, is_equipment, 
            is_part, critical_asset, part_of_group, parts, risk_assessment, 
            bcp, tracking_device, cost, purchase_price, predicted_end_of_life_value, vat_code, date_of_purchase, installation_date, 
            commissioning_date, decommissioning_date, end_of_life_date, life_expectancy, 
            purchase_lead_time, budget_limit, tenant_id, created_by, created_date, 
            last_updated_by, last_updated_date, version_number
            )
    VALUES (109, 28, 206, NULL, 405, 
            21, 58, 60, 
            2, 16, 43, 18, 8, 96, 
            4, 6, 3, 'JLA_0008', 'Combi Oven', 'Commerical',
            '21381', '16','' , false, false, 
            true, false, NULL, NULL, NULL, 
            false, true, '300.00', '250.00', '243', 71, '1/11/2014', '1/11/2016', 
            '10/10/2014', '1/11/2016', '1/11/2016', NULL, 
            91, '400', 1, 'JLA@JLA.co.uk', now(), 
            'JLA@JLA.co.uk', now(), 1
            );
            
INSERT INTO acumen.lease_in(id, assets_id, party_id, lease_type, lease_charge, vat_code, charge_period, lease_commences, lease_expires, lease_period, lease_cost, lease_value, lease_out_margin, location_postcode, maintenance_included, warranty_included, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
			VALUES(163, 109, 206, 63, '250.00', 71, 2, '2016-05-07 00:00:00.000', '2014-05-07 00:00:00.000', '2' , '10000.00', '300.00', '111.11', 'sal', true, true, 1, 'pJones', now(),  'pJones', now(), 1);
								
			SELECT setval('acumen.assets_id_seq', max(id)) from acumen.assets; 

--Combi Oven3
INSERT INTO acumen.assets(
            id, asset_status_id, supplier_id, manufacturer_id, custodian_id, 
            asset_number_part_1_id, asset_number_part_2_id, asset_number_part_3_id, 
            asset_number_part_4, site_id, location_id, division_id, department_id, depreciation_code, 
            country_id, company_id, category_id, uid, name, description, 
            serial_number, supplier_pn, manufacturer_pn, is_a_facility, is_equipment, 
            is_part, critical_asset, part_of_group, parts, risk_assessment, 
            bcp, tracking_device, cost, purchase_price, predicted_end_of_life_value, vat_code, date_of_purchase, installation_date, 
            commissioning_date, decommissioning_date, end_of_life_date, life_expectancy, 
            purchase_lead_time, budget_limit, tenant_id, created_by, created_date, 
            last_updated_by, last_updated_date, version_number
            )
    VALUES (120, 27, 204, NULL, 405, 
            19, 22, 25, 
            1, 47, 46, 51, 42, 99, 
            4, 40, 11, 'JLA_0020', 'Combi Oven', ' Heavy Commerical',
            '1200', '17','' , false, true, 
            false, false, NULL, NULL, NULL, 
            false, true, '1500.00', '1500.00', '291.60', 78, '12/08/2014', '12/08/2015', 
            '7/07/2014', '2/8/2016', '7/9/2016', NULL, 
            91, '2500', 1, 'JLA@JLA.co.uk', now(), 
            'JLA@JLA.co.uk', now(), 1
            );
  
INSERT INTO acumen.warranties(id, warranties_type_id, warranties_supplier_id, assets_id,commencement_date, expiry_date, cost, policy_number, om, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
            VALUES (120, 94, 204,120,'2014-05-07 00:00:00.000', '2015-05-09 00:00:00.000','300','KL-HGL10224233',true, 1, 'pJones', now(),'pJones',now(),1);
INSERT INTO acumen.asset_insurance_types(id, assets_id, insurance_type_id, mandatory, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
			VALUES(120, 120, 80, true, 1, 'pJones', now(), 'pJones', now(), 1 );
				
INSERT INTO acumen.insurance_policies(id, insurance_supplier_id, commencement_date, expiry_date, cost, policy_number, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
			VALUES(120, 204, '2015-05-07 00:00:00.000', '2017-05-07 00:00:00.000', '1300', 'A8KY-46H1-1342-ZXE5', 1, 'pJones', now(), 'pJones', now(), 1);

INSERT INTO acumen.insurance_type_policy_links(id, insurance_policy_id, asset_insurance_type_id, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
			VALUES(120, 120, 120, 1, 'pJones', now(),  'pJones', now(), 1);
									
--Gas Cooktop
INSERT INTO acumen.assets(
            id, asset_status_id, supplier_id, manufacturer_id, custodian_id, 
            asset_number_part_1_id, asset_number_part_2_id, asset_number_part_3_id, 
            asset_number_part_4, site_id, location_id, division_id, department_id, depreciation_code, 
            country_id, company_id, category_id, uid, name, description, 
            serial_number, supplier_pn, manufacturer_pn, is_a_facility, is_equipment, 
            is_part, critical_asset, part_of_group, parts, risk_assessment, 
            bcp, tracking_device, cost, purchase_price, predicted_end_of_life_value, vat_code, date_of_purchase, installation_date, 
            commissioning_date, decommissioning_date, end_of_life_date, life_expectancy, 
            purchase_lead_time, budget_limit, tenant_id, created_by, created_date, 
            last_updated_by, last_updated_date, version_number
            )
    VALUES (121, 28, 203, NULL, 403, 
            19, 57, 24, 
            1, 24, 13, 17, 42, 100, 
            4, 41, 11, 'JLA_0021', 'Gas CookTop', ' Light Commerical',
            '1210', '17','' , false, true, 
            false, false, NULL, NULL, NULL, 
            false, true, '200.00', '140.00', '50.60', 77, '12/08/2014', '12/08/2015', 
            '7/07/2014', '2/8/2016', '7/9/2016', NULL, 
            83, '300', 1, 'JLA@JLA.co.uk', now(), 
            'JLA@JLA.co.uk', now(), 1
            );
  
INSERT INTO acumen.warranties(id, warranties_type_id, warranties_supplier_id, assets_id,commencement_date, expiry_date, cost, policy_number, om, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
            VALUES (121, 95, 203,121,'2014-05-07 00:00:00.000', '2015-05-09 00:00:00.000','300','KL-HGL10224233',true, 1, 'pJones', now(),'pJones',now(),1);
INSERT INTO acumen.asset_insurance_types(id, assets_id, insurance_type_id, mandatory, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
			VALUES(121, 121, 82, true, 1, 'pJones', now(), 'pJones', now(), 1 );
				
INSERT INTO acumen.insurance_policies(id, insurance_supplier_id, commencement_date, expiry_date, cost, policy_number, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
			VALUES(121, 203, '2015-05-07 00:00:00.000', '2017-05-07 00:00:00.000', '900', 'B5IY-16A1-1042-SDT5', 1, 'pJones', now(), 'pJones', now(), 1);

INSERT INTO acumen.insurance_type_policy_links(id, insurance_policy_id, asset_insurance_type_id, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
			VALUES(121, 121, 121, 1, 'pJones', now(),  'pJones', now(), 1);
									
--Utensil Washer
INSERT INTO acumen.assets(
            id, asset_status_id, supplier_id, manufacturer_id, custodian_id, 
            asset_number_part_1_id, asset_number_part_2_id, asset_number_part_3_id, 
            asset_number_part_4, site_id, location_id, division_id, department_id, depreciation_code, 
            country_id, company_id, category_id, uid, name, description, 
            serial_number, supplier_pn, manufacturer_pn, is_a_facility, is_equipment, 
            is_part, critical_asset, part_of_group, parts, risk_assessment, 
            bcp, tracking_device, cost, purchase_price, predicted_end_of_life_value, vat_code, date_of_purchase, installation_date, 
            commissioning_date, decommissioning_date, end_of_life_date, life_expectancy, 
            purchase_lead_time, budget_limit, tenant_id, created_by, created_date, 
            last_updated_by, last_updated_date, version_number
            )
    VALUES (122, 27, 206, NULL, 402, 
            19, 56, 25, 
            1, 6, 14, 18, 9, 96, 
            4, 6, 31, 'JLA_0022', 'Utensil Washer', ' Heavy Commerical',
            '1220', '17','' , false, true, 
            false, true, NULL, NULL, NULL, 
            false, true, '20000.00', '14000.00', '4000.60', 77, '12/08/2014', '12/08/2015', 
            '7/07/2014', '2/8/2016', '7/9/2016', NULL, 
            92, '200000', 1, 'JLA@JLA.co.uk', now(), 
            'JLA@JLA.co.uk', now(), 1
            );
  
INSERT INTO acumen.warranties(id, warranties_type_id, warranties_supplier_id, assets_id,commencement_date, expiry_date, cost, policy_number, om, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
            VALUES (123, 95, 206,122,'2014-05-07 00:00:00.000', '2015-05-09 00:00:00.000','300','KL-HGL10224233',true, 1, 'pJones', now(),'pJones',now(),1);
INSERT INTO acumen.asset_insurance_types(id, assets_id, insurance_type_id, mandatory, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
			VALUES(123, 122, 82, true, 1, 'pJones', now(), 'pJones', now(), 1 );
				
INSERT INTO acumen.insurance_policies(id, insurance_supplier_id, commencement_date, expiry_date, cost, policy_number, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
			VALUES(123, 206, '2015-05-07 00:00:00.000', '2017-05-07 00:00:00.000', '2000', 'B1IW-46A1-1042-SLO1', 1, 'pJones', now(), 'pJones', now(), 1);

INSERT INTO acumen.insurance_type_policy_links(id, insurance_policy_id, asset_insurance_type_id, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
			VALUES(123, 123, 123, 1, 'pJones', now(),  'pJones', now(), 1);
            
INSERT INTO acumen.lease_in(id, assets_id, party_id, lease_type, lease_charge, vat_code, charge_period, lease_commences, lease_expires, lease_period, lease_cost, lease_value, lease_out_margin, location_postcode, maintenance_included, warranty_included, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
			VALUES(123, 122, 206, 63, '300.00', 71, 2, '2016-05-07 00:00:00.000', '2014-05-07 00:00:00.000', '4' , '10000.00', '360.00', '111.11', 'sal', true, true, 1, 'pJones', now(),  'pJones', now(), 1);


--Medical Macerator
INSERT INTO acumen.assets(
            id, asset_status_id, supplier_id, manufacturer_id, custodian_id, 
            asset_number_part_1_id, asset_number_part_2_id, asset_number_part_3_id, 
            asset_number_part_4, site_id, location_id, division_id, department_id, depreciation_code, 
            country_id, company_id, category_id, uid, name, description, 
            serial_number, supplier_pn, manufacturer_pn, is_a_facility, is_equipment, 
            is_part, critical_asset, part_of_group, parts, risk_assessment, 
            bcp, cost, purchase_price, predicted_end_of_life_value, vat_code, date_of_purchase, installation_date, 
            commissioning_date, decommissioning_date, end_of_life_date, life_expectancy, 
            purchase_lead_time, budget_limit, tenant_id, created_by, created_date, 
            last_updated_by, last_updated_date, version_number
            )
    VALUES (123, 28, 201, NULL, 401, 
            19, 58, 24, 
            1, 47, 13, 18, 42, 100, 
            4, 7, 12, 'JLA_0023', 'Medical Macerator', 'Commerical',
            '1230', '17','' , false, true, 
            false, true, NULL, NULL, NULL, 
            false, '100.00', '120.00', '4.60', 74, '12/08/2014', '12/08/2015', 
            '7/07/2014', '2/8/2016', '7/9/2016', NULL, 
            88, '200000', 1, 'JLA@JLA.co.uk', now(), 
            'JLA@JLA.co.uk', now(), 1
            );
  
INSERT INTO acumen.warranties(id, warranties_type_id, warranties_supplier_id, assets_id,commencement_date, expiry_date, cost, policy_number, om, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
            				VALUES (124, 95, 201,123,'2014-05-07 00:00:00.000', '2015-05-09 00:00:00.000','300','KL-HGL10224233',true, 1, 'pJones', now(),'pJones',now(),1);
INSERT INTO acumen.asset_insurance_types(id, assets_id, insurance_type_id, mandatory, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
				VALUES(124, 123, 82, true, 1, 'pJones', now(), 'pJones', now(), 1 );
				
INSERT INTO acumen.insurance_policies(id, insurance_supplier_id, commencement_date, expiry_date, cost, policy_number, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
			VALUES(124, 201, '2015-05-07 00:00:00.000', '2017-05-07 00:00:00.000', '2000', 'B1IW-46A1-1042-SLO1', 1, 'pJones', now(), 'pJones', now(), 1);

INSERT INTO acumen.insurance_type_policy_links(id, insurance_policy_id, asset_insurance_type_id, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
									VALUES(124, 124, 124, 1, 'pJones', now(),  'pJones', now(), 1);
									
INSERT INTO acumen.lease_in(id, assets_id, party_id, lease_type, lease_charge, vat_code, charge_period, lease_commences, lease_expires, lease_period, lease_cost, lease_value, lease_out_margin, location_postcode, maintenance_included, warranty_included, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
					VALUES(124, 123, 201, 63, '1000.00', 71, 2, '2016-05-07 00:00:00.000', '2014-05-07 00:00:00.000', '8' , '1000.00', '200.00', '111.11', 'sal', true, true, 1, 'pJones', now(),  'pJones', now(), 1);									

--OTEX
INSERT INTO acumen.assets(
            id, asset_status_id, supplier_id, manufacturer_id, custodian_id, 
            asset_number_part_1_id, asset_number_part_2_id, asset_number_part_3_id, 
            asset_number_part_4, site_id, location_id, division_id, department_id, depreciation_code, 
            country_id, company_id, category_id, uid, name, description,
            serial_number, supplier_pn, manufacturer_pn, is_a_facility, is_equipment, 
            is_part, critical_asset, part_of_group, parts, risk_assessment, 
            bcp, cost, purchase_price, predicted_end_of_life_value, vat_code, date_of_purchase, installation_date, 
            commissioning_date, decommissioning_date, end_of_life_date, life_expectancy, 
            purchase_lead_time, budget_limit, tenant_id, created_by, created_date, 
            last_updated_by, last_updated_date, version_number
            )
    VALUES (124, 2, 205, NULL, 406, 
            19, 56, 60, 
            1, 48, 44, 18, 42, 96, 
            37, 39, 11, 'JLA_0024', 'OTEX', 'Commerical',
            '1240', '17','' , false, true, 
            false, true, NULL, NULL, NULL, 
            false, '300.00', '150.00', '50.60', 70, '12/08/2014', '12/08/2015', 
            '7/07/2014', '2/8/2016', '7/9/2016', NULL, 
            92, '200000', 1, 'JLA@JLA.co.uk', now(), 
            'JLA@JLA.co.uk', now(), 1
            );
  
INSERT INTO acumen.warranties(id, warranties_type_id, warranties_supplier_id, assets_id,commencement_date, expiry_date, cost, policy_number, om, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
            				VALUES (125, 95, 205,124,'2014-05-07 00:00:00.000', '2015-05-09 00:00:00.000','300','KL-HGL10224233',true, 1, 'pJones', now(),'pJones',now(),1);
INSERT INTO acumen.asset_insurance_types(id, assets_id, insurance_type_id, mandatory, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
				VALUES(125, 124, 82, true, 1, 'pJones', now(), 'pJones', now(), 1 );
				
INSERT INTO acumen.insurance_policies(id, insurance_supplier_id, commencement_date, expiry_date, cost, policy_number, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
			VALUES(128, 205, '2015-05-07 00:00:00.000', '2017-05-07 00:00:00.000', '2000', 'B1IW-46A1-1042-SLO1', 1, 'pJones', now(), 'pJones', now(), 1);

INSERT INTO acumen.insurance_type_policy_links(id, insurance_policy_id, asset_insurance_type_id, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number)
									VALUES(125, 128, 125, 1, 'pJones', now(),  'pJones', now(), 1);			
			
			SELECT setval('acumen.assets_id_seq', max(id)) from acumen.assets;                 
      