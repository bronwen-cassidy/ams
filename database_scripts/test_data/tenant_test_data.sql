-->Tenants
INSERT INTO acumen.tenants(id, bucket_name, company_name, created_by, created_date, last_updated_by, last_updated_date, version_number)
VALUES (1, 'brightfuture.co.uk-das-jla', 'jla', 'tenant_scripts', now(), 'tenant_scripts', now(), 1);

-- Users
INSERT INTO acumen.users(
            id, user_name, surname, forenames, tenant_id, created_by, created_date, last_updated_by, last_updated_date, version_number, password, enabled)
    VALUES (1, 'pJones', 'Jones', 'Peter', 1, 'tenant_script', now(), 'tenant_script', now(), 0, 'password', 'true');

INSERT INTO acumen.tenants(id, bucket_name, company_name, created_by, created_date, last_updated_by, last_updated_date, version_number)
VALUES (2, 'brightfuture.co.uk-test-bucket', 'aTenant', 'tenant_scripts', now(), 'tenant_scripts', now(), 1);

