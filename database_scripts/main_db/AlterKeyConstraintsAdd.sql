ALTER TABLE acumen.assets ALTER asset_status_id set not null,
                ALTER supplier_id set not null,
				ALTER manufacturer_id set not null,
				ALTER custodian_id set not null,
				ALTER asset_number_part_1_id set not null,
				ALTER asset_number_part_2_id set not null,
				ALTER asset_number_part_3_id set not null,
				ALTER site_id set not null,
				ALTER location_id set not null,
				ALTER division_id set not null,
				ALTER department_id set not null,
				ALTER depreciation_code set not null,
				ALTER country_id set not null,
				ALTER category_id set not null,
				ALTER uid set not null,
				ALTER name set not null,
				ALTER tenant_id set not null;
				
ALTER TABLE acumen.user_data ALTER user_data_types_id set not null;
				
