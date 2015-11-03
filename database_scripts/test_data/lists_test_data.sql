-- List with contents - Type must match the Entity type name see com.xioq.dasacumen.model.constants.EntityTyped
-- An asset list 
INSERT INTO acumen.lists(
            id, users_id, name, entity_type, tenant_id, created_by, created_date, 
            last_updated_by, last_updated_date, version_number)
    VALUES (1, 1, 'Parts List', 'ASSETS', 1, 'list_script', now(), 
            'list_script', now(), 0);
-- Add two assets - depends on the asset script
INSERT INTO acumen.list_contents(
            id, lists_id, entity_id, tenant_id, created_by, created_date)
    VALUES (1, 1, 1, 1, 'list_script', now());
INSERT INTO acumen.list_contents(
            id, lists_id, entity_id, tenant_id, created_by, created_date)
    VALUES (2, 1, 2, 1, 'list_script', now());

