
CREATE EXTENSION citext;

CREATE TABLE acumen.users (
  id BIGSERIAL  NOT NULL ,
  user_name VARCHAR(254) NOT NULL ,
  password VARCHAR(254) NOT NULL   ,
  enabled BOOLEAN NOT NULL,
  surname VARCHAR(30)    ,
  forenames VARCHAR(30)    ,
  tenant_id INTEGER  NOT NULL,
  created_by VARCHAR(254) NOT NULL,
  created_date TIMESTAMP  NOT NULL,
  last_updated_by VARCHAR(254)  NOT NULL  ,
  last_updated_date TIMESTAMP NOT NULL   ,
  version_number INTEGER NOT NULL      ,
  CONSTRAINT user_name_uk UNIQUE (user_name),
PRIMARY KEY(id));

CREATE TABLE acumen.authorities (
  id BIGSERIAL  NOT NULL ,
  users_id BIGINT NOT NULL,
  authority character varying(45) NOT NULL,
  tenant_id INTEGER  NOT NULL,
  created_by VARCHAR(254) NOT NULL,
  created_date TIMESTAMP  NOT NULL,
  last_updated_by VARCHAR(254)  NOT NULL  ,
  last_updated_date TIMESTAMP NOT NULL   ,
  version_number INTEGER NOT NULL,
  CONSTRAINT uq_authorities UNIQUE(users_id, authority),
PRIMARY KEY(id));

CREATE TABLE acumen.tel_numbers (
  id BIGSERIAL  NOT NULL ,
  tel_no VARCHAR(30)    ,
  tenant_id INTEGER    ,
  created_by VARCHAR(254)    ,
  created_date TIMESTAMP    ,
  last_updated_by VARCHAR(254)    ,
  last_updated_date TIMESTAMP    ,
  version_number INTEGER NOT NULL      ,
PRIMARY KEY(id));

CREATE TABLE acumen.groups (
  id BIGSERIAL   NOT NULL ,
  name citext   NOT NULL ,
  tenant_id INTEGER    ,
  created_by VARCHAR(254)    ,
  created_date TIMESTAMP    ,
  last_updated_by VARCHAR(254)    ,
  last_updated_date TIMESTAMP    ,
  version_number INTEGER NOT NULL      ,
PRIMARY KEY(id));


CREATE TABLE acumen.emails (
  id BIGSERIAL  NOT NULL ,
  email_address VARCHAR(254)    ,
  tenant_id INTEGER    ,
  created_by VARCHAR(254)    ,
  created_date TIMESTAMP    ,
  last_updated_by VARCHAR(254)    ,
  last_updated_date TIMESTAMP    ,
  version_number INTEGER NOT NULL      ,
PRIMARY KEY(id));


CREATE TABLE acumen.user_data_cat (
  id BIGSERIAL  NOT NULL ,
  user_data_cat_parent_id BIGINT    ,
  name VARCHAR(50)    ,
  description VARCHAR(150)    ,
  language_code VARCHAR(50)    ,
  desc_code VARCHAR(50)      ,
  created_by VARCHAR(254)    ,
  created_date TIMESTAMP    ,
  last_updated_by VARCHAR(254)    ,
  last_updated_date TIMESTAMP    ,
  version_number INTEGER      ,
PRIMARY KEY(id)  ,
  FOREIGN KEY(user_data_cat_parent_id)
    REFERENCES acumen.user_data_cat(id));

CREATE INDEX user_data_cat_FKIndex1 ON acumen.user_data_cat (user_data_cat_parent_id);

CREATE INDEX IFK_udatac_to_udatac ON acumen.user_data_cat (user_data_cat_parent_id);


CREATE TABLE acumen.user_data_types (
  id BIGSERIAL  NOT NULL ,
  parent_id BIGINT ,
  user_data_cat_id BIGINT   NOT NULL ,
  name VARCHAR(50)    ,
  value_required BOOLEAN NOT NULL,
  user_data_value_type VARCHAR(15),
  description VARCHAR(150)    ,
  lang_code VARCHAR(60)    ,
  desc_code VARCHAR(50)      ,
  created_by VARCHAR(254)    ,
  created_date TIMESTAMP    ,
  last_updated_by VARCHAR(254)    ,
  last_updated_date TIMESTAMP    ,
  version_number INTEGER      ,
PRIMARY KEY(id)    ,
  FOREIGN KEY(user_data_cat_id)
    REFERENCES acumen.user_data_cat(id),
  FOREIGN KEY(parent_id)
    REFERENCES acumen.user_data_types(id));


CREATE INDEX user_data_types_FKIndex1 ON acumen.user_data_types (user_data_cat_id);
CREATE INDEX user_data_types_FKIndex2 ON acumen.user_data_types (parent_id);


CREATE INDEX IFK_udc_to_udt ON acumen.user_data_types (user_data_cat_id);
CREATE INDEX IFK_udt_to_udt ON acumen.user_data_types (parent_id);


CREATE TABLE acumen.user_data (
  id BIGSERIAL  NOT NULL ,
  user_data_types_id BIGINT   NOT NULL ,
  name VARCHAR(60)    ,
  value VARCHAR(30),
  ud_order INTEGER    ,
  active BOOL    ,
  tenant_id INTEGER    ,
  created_by VARCHAR(254)    ,
  created_date TIMESTAMP    ,
  last_updated_by VARCHAR(254)    ,
  last_updated_date TIMESTAMP    ,
  version_number INTEGER NOT NULL      ,
PRIMARY KEY(id)  ,
  FOREIGN KEY(user_data_types_id)
    REFERENCES acumen.user_data_types(id));



CREATE TABLE acumen.parties (
  id BIGSERIAL  NOT NULL ,
  reference_for_us VARCHAR(50) ,
  reference_for_them VARCHAR(50) ,
  currency_accepted_id BIGINT , 
  uid VARCHAR(30) NOT NULL ,
  tenant_id INTEGER    ,
  created_by VARCHAR(254)    ,
  created_date TIMESTAMP    ,
  last_updated_by VARCHAR(254)    ,
  last_updated_date TIMESTAMP    ,
  version_number INTEGER NOT NULL      ,
PRIMARY KEY(id) ,
FOREIGN KEY(currency_accepted_id)
    REFERENCES acumen.user_data(id));

CREATE INDEX parties_FKIndex1 ON acumen.parties (currency_accepted_id);

CREATE INDEX IFK_ud_to_parties ON acumen.parties (currency_accepted_id);




CREATE TABLE acumen.addresses (
  id BIGSERIAL  NOT NULL ,
  address_line_1 VARCHAR(30)    ,
  address_line_2 VARCHAR(30)    ,
  address_line_3 VARCHAR(30)    ,
  zip_post_code VARCHAR(30)    ,
  city VARCHAR(30),
  country VARCHAR(30)    ,
  type_of_place VARCHAR(30)    ,
  tenant_id INTEGER    ,
  created_by VARCHAR(254)    ,
  created_date TIMESTAMP    ,
  last_updated_by VARCHAR(254)    ,
  last_updated_date TIMESTAMP    ,
  version_number INTEGER NOT NULL      ,
PRIMARY KEY(id));


CREATE TABLE acumen.websites (
  id BIGSERIAL  NOT NULL ,
  website_url VARCHAR(2048),
  tenant_id INTEGER    ,
  created_by VARCHAR(254)    ,
  created_date TIMESTAMP    ,
  last_updated_by VARCHAR(254)    ,
  last_updated_date TIMESTAMP    ,
  version_number INTEGER NOT NULL      ,
PRIMARY KEY(id));

CREATE TABLE acumen.bank_details (
  id BIGSERIAL  NOT NULL ,
  bank_name VARCHAR(30) NOT NULL    ,
  bank_address VARCHAR(30) NOT NULL    ,
  account_number VARCHAR(15) NOT NULL    ,
  sort_code VARCHAR(10) NOT NULL    ,
  tenant_id INTEGER NOT NULL    ,
  created_by VARCHAR(254) NOT NULL    ,
  created_date TIMESTAMP NOT NULL   ,
  last_updated_by VARCHAR(254) NOT NULL    ,
  last_updated_date TIMESTAMP NOT NULL    ,
  version_number INTEGER NOT NULL      ,
PRIMARY KEY(id));

CREATE TABLE acumen.tenants (
  id BIGSERIAL NOT NULL ,
  bucket_name VARCHAR(150)  NOT NULL  ,
  company_name VARCHAR(150)  NOT NULL  ,
  tenant_id INTEGER	,
  created_by VARCHAR(254)  NOT NULL  ,
  created_date TIMESTAMP  NOT NULL  ,
  last_updated_by VARCHAR(254)  NOT NULL  ,
  last_updated_date TIMESTAMP  NOT NULL   ,
  version_number INTEGER  NOT NULL,
PRIMARY KEY(id));


CREATE TABLE acumen.system_info (
  property VARCHAR(100)  NOT NULL ,
  val_str VARCHAR    ,
  val_date DATE    ,
  val_num INTEGER      ,
PRIMARY KEY(property));


CREATE TABLE acumen.party_type (
  id BIGSERIAL  NOT NULL ,
  parties_id BIGINT   NOT NULL ,
  type_name VARCHAR(30) ,
  type_of_supplier VARCHAR(30)    ,
  tenant_id INTEGER    ,
  created_by VARCHAR(254)    ,
  created_date TIMESTAMP    ,
  last_updated_by VARCHAR(254)    ,
  last_updated_date TIMESTAMP    ,
  version_number INTEGER NOT NULL      ,
PRIMARY KEY(id)  ,
  FOREIGN KEY(parties_id)
    REFERENCES acumen.parties(id));


CREATE INDEX parties_with_party_type_FKIndex1 ON acumen.party_type (parties_id);


CREATE INDEX IFK_parties_to_pt ON acumen.party_type (parties_id);


CREATE TABLE acumen.people (
  parties_id BIGINT   NOT NULL ,
  position VARCHAR(30)    ,
  forenames citext ,
  surname citext ,
  gender VARCHAR(30)    ,
  tenant_id INTEGER    ,
  created_by VARCHAR(254)    ,
  created_date TIMESTAMP    ,
  last_updated_by VARCHAR(254)    ,
  last_updated_date TIMESTAMP    ,
PRIMARY KEY(parties_id)  ,
  FOREIGN KEY(parties_id)
    REFERENCES acumen.parties(id));


CREATE INDEX people_FKIndex1 ON acumen.people (parties_id);


CREATE INDEX IFK_parties_to_people ON acumen.people (parties_id);

CREATE TABLE acumen.insurance_policies (
  id BIGSERIAL  NOT NULL ,
  insurance_supplier_id BIGINT   NOT NULL ,
  commencement_date DATE   NOT NULL ,
  expiry_date DATE   NOT NULL ,
  cost NUMERIC    ,
  policy_number VARCHAR(30)   NOT NULL ,
  tenant_id INTEGER   NOT NULL ,
  created_by VARCHAR(254)    ,
  created_date TIMESTAMP    ,
  last_updated_by VARCHAR(254)    ,
  last_updated_date TIMESTAMP    ,
  version_number INTEGER NOT NULL      ,
PRIMARY KEY(id)  ,
  FOREIGN KEY(insurance_supplier_id)
    REFERENCES acumen.parties(id));

CREATE INDEX insurance_policies_FKIndex1 ON acumen.insurance_policies (insurance_supplier_id);
CREATE INDEX IFK_Rel_57 ON acumen.insurance_policies (insurance_supplier_id);

CREATE TABLE acumen.drafts (
  id BIGSERIAL  NOT NULL ,
  users_id BIGINT   NOT NULL ,
  name VARCHAR(254)     ,
  object_type VARCHAR(100) NOT NULL,
  object_data TEXT NOT NULL,
  tenant_id INTEGER    ,
  created_by VARCHAR(254)    ,
  created_date TIMESTAMP    ,
  last_updated_by VARCHAR(254)    ,
  last_updated_date TIMESTAMP    ,
  version_number INTEGER NOT NULL      ,
PRIMARY KEY(id)  ,
  FOREIGN KEY(users_id)
    REFERENCES acumen.users(id));

CREATE INDEX drafts_FKIndex1 ON acumen.drafts (users_id);
CREATE INDEX IFK_users_to_drafts ON acumen.drafts (users_id);

CREATE TABLE acumen.companies (
  parties_id BIGINT   NOT NULL ,
  name citext  ,
  reg_no VARCHAR(30)    ,
  vat_no VARCHAR(30)    ,
  tenant_id INTEGER    ,
  created_by VARCHAR(254)    ,
  created_date TIMESTAMP    ,
  last_updated_by VARCHAR(254)    ,
  last_updated_date TIMESTAMP    ,
PRIMARY KEY(parties_id)  ,
  FOREIGN KEY(parties_id)
    REFERENCES acumen.parties(id));


CREATE INDEX companies_FKIndex1 ON acumen.companies (parties_id);


CREATE INDEX IFK_parties_to_companies ON acumen.companies (parties_id);

CREATE TABLE acumen.docs (
  id BIGSERIAL   NOT NULL ,
  tenants_id INTEGER   NOT NULL ,
  object_key VARCHAR(150)   NOT NULL ,
  doc_type VARCHAR(150) NOT NULL,
  tenant_id INTEGER   NOT NULL ,
  created_by VARCHAR(254)   NOT NULL ,
  created_date TIMESTAMP   NOT NULL ,
  last_updated_by VARCHAR(254)   NOT NULL ,
  last_updated_date TIMESTAMP   NOT NULL ,
  version_number INTEGER   NOT NULL   ,
PRIMARY KEY(id)  ,
  FOREIGN KEY(tenants_id)
    REFERENCES acumen.tenants(id));


CREATE INDEX docs_FKIndex1 ON acumen.docs (tenants_id);


CREATE INDEX IFK_tenants_to_docs ON acumen.docs (tenants_id);



CREATE TABLE acumen.doc_links (
  id BIGSERIAL   NOT NULL ,
  docs_id INTEGER   NOT NULL ,
  entity_id BIGINT   NOT NULL ,
  entity_type VARCHAR(100)   NOT NULL ,
  doc_type VARCHAR(150) NOT NULL,
  tenant_id INTEGER   NOT NULL ,
  created_by VARCHAR(254)   NOT NULL ,
  created_date TIMESTAMP   NOT NULL ,
  last_updated_by VARCHAR(254)   NOT NULL ,
  last_updated_date TIMESTAMP   NOT NULL ,
  version_number INTEGER   NOT NULL   ,
PRIMARY KEY(id)  ,
  FOREIGN KEY(docs_id)
    REFERENCES acumen.docs(id));


CREATE INDEX doc_links_FKIndex1 ON acumen.doc_links (docs_id);


CREATE INDEX IFK_docs_to_dl ON acumen.doc_links (docs_id);

CREATE TABLE acumen.other_details (
  id BIGSERIAL  NOT NULL ,
  parties_id BIGINT   NOT NULL ,
  field VARCHAR(254)    ,
  value VARCHAR(254)    ,
  tenant_id INTEGER    ,
  created_by VARCHAR(254)    ,
  created_date TIMESTAMP    ,
  last_updated_by VARCHAR(254)    ,
  last_updated_date TIMESTAMP    ,
  version_number INTEGER NOT NULL      ,
PRIMARY KEY(id)  ,
  FOREIGN KEY(parties_id)
    REFERENCES acumen.parties(id));


CREATE INDEX other_details_FKIndex1 ON acumen.other_details (parties_id);


CREATE INDEX IFK_party_to_od ON acumen.other_details (parties_id);


CREATE TABLE acumen.internal_resources (
  parties_id BIGINT   NOT NULL ,
  report_to VARCHAR(30)    ,
  position VARCHAR(30)    ,
  forenames VARCHAR(30)    ,
  surname VARCHAR(30)    ,
  gender VARCHAR(30)    ,
  tenant_id INTEGER    ,
  created_by VARCHAR(254)    ,
  created_date TIMESTAMP    ,
  last_updated_by VARCHAR(254)    ,
  last_updated_date TIMESTAMP    ,
PRIMARY KEY(parties_id)  ,
  FOREIGN KEY(parties_id)
    REFERENCES acumen.parties(id));


CREATE INDEX internal_resources_FKIndex1 ON acumen.internal_resources (parties_id);


CREATE INDEX IFK_parties_to_IR ON acumen.internal_resources (parties_id);


CREATE TABLE acumen.party_addresses (
  id BIGSERIAL  NOT NULL ,
  addresses_id BIGINT   NOT NULL ,
  parties_id BIGINT   NOT NULL ,
  address_type VARCHAR(30)    ,
  tenant_id INTEGER    ,
  created_by VARCHAR(254)    ,
  created_date TIMESTAMP    ,
  last_updated_by VARCHAR(254)    ,
  last_updated_date TIMESTAMP    ,
  version_number INTEGER NOT NULL      ,
PRIMARY KEY(id)    ,
  FOREIGN KEY(parties_id)
    REFERENCES acumen.parties(id),
  FOREIGN KEY(addresses_id)
    REFERENCES acumen.addresses(id));


CREATE INDEX parties_with_addresses_2_FKIndex1 ON acumen.party_addresses (parties_id);
CREATE INDEX parties_with_addresses_2_FKIndex2 ON acumen.party_addresses (addresses_id);


CREATE INDEX IFK_parties_to_pwa ON acumen.party_addresses (parties_id);
CREATE INDEX IFK_addresses_to_pwt ON acumen.party_addresses (addresses_id);


CREATE TABLE acumen.party_bank_details (
  id BIGSERIAL  NOT NULL ,
  bank_details_id INTEGER   NOT NULL ,
  parties_id BIGINT   NOT NULL ,
  account_type VARCHAR(30)    ,
  tenant_id INTEGER    ,
  created_by VARCHAR(254)    ,
  created_date TIMESTAMP    ,
  last_updated_by VARCHAR(254)    ,
  last_updated_date TIMESTAMP    ,
  version_number INTEGER NOT NULL      ,
PRIMARY KEY(id)    ,
  FOREIGN KEY(parties_id)
    REFERENCES acumen.parties(id),
  FOREIGN KEY(bank_details_id)
    REFERENCES acumen.bank_details(id));


CREATE INDEX party_bank_details_FKIndex1 ON acumen.party_bank_details (parties_id);
CREATE INDEX party_bank_details_FKIndex2 ON acumen.party_bank_details (bank_details_id);


CREATE INDEX IFK_party_to_pbd ON acumen.party_bank_details (parties_id);
CREATE INDEX IFK_bd_to_pbd ON acumen.party_bank_details (bank_details_id);


CREATE TABLE acumen.party_links (
  id BIGSERIAL  NOT NULL ,
  parties_id_1 BIGINT   NOT NULL ,
  parties_id_2 BIGINT   NOT NULL ,
  type VARCHAR(50)    ,
  description VARCHAR(254)    ,
  tenant_id INTEGER    ,
  created_by VARCHAR(254)    ,
  created_date TIMESTAMP    ,
  last_updated_by VARCHAR(254)    ,
  last_updated_date TIMESTAMP    ,
  version_number INTEGER NOT NULL      ,
PRIMARY KEY(id)    ,
  FOREIGN KEY(parties_id_1)
    REFERENCES acumen.parties(id),
  FOREIGN KEY(parties_id_2)
    REFERENCES acumen.parties(id));


CREATE INDEX party_links_FKIndex1 ON acumen.party_links (parties_id_1);
CREATE INDEX party_links_FKIndex2 ON acumen.party_links (parties_id_2);


CREATE INDEX IFK_parties_to_party_id_1 ON acumen.party_links (parties_id_1);
CREATE INDEX IFK_parties_to_party_id_2 ON acumen.party_links (parties_id_2);


CREATE TABLE acumen.party_telephone_numbers (
  id BIGSERIAL  NOT NULL ,
  parties_id BIGINT   NOT NULL ,
  tel_numbers_id BIGINT   NOT NULL ,
  tel_numbers_type bigint    ,
  tenant_id INTEGER    ,
  created_by VARCHAR(254)    ,
  created_date TIMESTAMP    ,
  last_updated_by  VARCHAR(254)    ,
  last_updated_date TIMESTAMP    ,
  version_number INTEGER NOT NULL      ,
PRIMARY KEY(id)    ,
  FOREIGN KEY(tel_numbers_id)
    REFERENCES acumen.tel_numbers(id),
  FOREIGN KEY(parties_id)
    REFERENCES acumen.parties(id));


CREATE INDEX parties_with_telephone_numbers_FKIndex1 ON acumen.party_telephone_numbers (tel_numbers_id);
CREATE INDEX parties_with_telephone_numbers_FKIndex2 ON acumen.party_telephone_numbers (parties_id);


CREATE INDEX IFK_tel_numbers_to_pwt ON acumen.party_telephone_numbers (tel_numbers_id);
CREATE INDEX IFK_parties_to_pwt ON acumen.party_telephone_numbers (parties_id);

CREATE TABLE acumen.lists (
  id BIGSERIAL   NOT NULL ,
  users_id BIGINT   NOT NULL ,
  name VARCHAR(50)   NOT NULL ,
  entity_type VARCHAR(50)   NOT NULL ,
  tenant_id INTEGER    ,
  created_by VARCHAR(254)    ,
  created_date TIMESTAMP    ,
  last_updated_by VARCHAR(254)    ,
  last_updated_date TIMESTAMP    ,
  version_number INTEGER NOT NULL      ,
PRIMARY KEY(id)  ,
  FOREIGN KEY(users_id)
    REFERENCES acumen.users(id));


CREATE INDEX lists_FKIndex1 ON acumen.lists (users_id);


CREATE INDEX IFK_users_to_lists ON acumen.lists (users_id);

CREATE TABLE acumen.party_websites (
  id BIGSERIAL   NOT NULL ,
  parties_id BIGINT   NOT NULL ,
  websites_id BIGINT   NOT NULL ,
  website_type VARCHAR(30)    ,
  tenant_id INTEGER,
  created_by VARCHAR(254)    ,
  created_date TIMESTAMP    ,
  last_updated_by VARCHAR(254)    ,
  last_updated_date TIMESTAMP    ,
  version_number INTEGER NOT NULL      ,
PRIMARY KEY(id)    ,
  FOREIGN KEY(websites_id)
    REFERENCES acumen.websites(id),
  FOREIGN KEY(parties_id)
    REFERENCES acumen.parties(id));


CREATE INDEX party_websites_FKIndex1 ON acumen.party_websites (websites_id);
CREATE INDEX party_websites_FKIndex2 ON acumen.party_websites (parties_id);


CREATE INDEX IFK_web_to_pweb ON acumen.party_websites (websites_id);
CREATE INDEX IFK_party_to_pweb ON acumen.party_websites (parties_id);

CREATE TABLE acumen.party_emails (
  id BIGSERIAL  NOT NULL ,
  parties_id BIGINT   NOT NULL ,
  emails_id BIGINT   NOT NULL ,
  emails_type bigint    ,
  tenant_id INTEGER    ,
  created_by VARCHAR(254)    ,
  created_date TIMESTAMP    ,
  last_updated_by VARCHAR(254)    ,
  last_updated_date TIMESTAMP    ,
  version_number INTEGER NOT NULL      ,
PRIMARY KEY(id)    ,
  FOREIGN KEY(parties_id)
    REFERENCES acumen.parties(id),
  FOREIGN KEY(emails_id)
    REFERENCES acumen.emails(id));


CREATE INDEX parties_with_emails_FKIndex1 ON acumen.party_emails (parties_id);
CREATE INDEX parties_with_emails_FKIndex2 ON acumen.party_emails (emails_id);


CREATE INDEX IFK_parties_to_pwe ON acumen.party_emails (parties_id);
CREATE INDEX IFK_emails_to_pwe ON acumen.party_emails (emails_id);


CREATE TABLE acumen.party_contact_details (
  id BIGSERIAL  NOT NULL ,
  parties_id BIGINT   NOT NULL ,
  party_addresses_id BIGINT  ,
 -- party_websites_id BIGINT ,
  party_telephone_numbers_id BIGINT ,
  party_emails_id BIGINT ,
  tenant_id INTEGER    ,
  created_by VARCHAR(254)    ,
  created_date TIMESTAMP    ,
  last_updated_by VARCHAR(254)    ,
  last_updated_date TIMESTAMP    ,
  version_number INTEGER NOT NULL      ,
PRIMARY KEY(id)        ,
  FOREIGN KEY(party_telephone_numbers_id)
    REFERENCES acumen.party_telephone_numbers(id),
  FOREIGN KEY(party_emails_id)
    REFERENCES acumen.party_emails(id),
  FOREIGN KEY(party_addresses_id)
    REFERENCES acumen.party_addresses(id),
 -- FOREIGN KEY(party_websites_id)
 --   REFERENCES acumen.party_websites(id),
  FOREIGN KEY(parties_id)
    REFERENCES acumen.parties(id));


CREATE INDEX parties_with_contact_details_FKIndex2 ON acumen.party_contact_details (party_telephone_numbers_id);
CREATE INDEX parties_with_contact_details_FKIndex3 ON acumen.party_contact_details (party_emails_id);
CREATE INDEX parties_with_contact_details_FKIndex4 ON acumen.party_contact_details (party_addresses_id);
--CREATE INDEX parties_with_contact_details_FKIndex5 ON acumen.party_contact_details (party_websites_id);
CREATE INDEX party_contact_details_FKIndex4 ON acumen.party_contact_details (parties_id);


CREATE INDEX IFK_contact_to_pwt ON acumen.party_contact_details (party_telephone_numbers_id);
CREATE INDEX IFK_contact_to_pwe ON acumen.party_contact_details (party_emails_id);
CREATE INDEX IFK_contact_to_pwa ON acumen.party_contact_details (party_addresses_id);
--CREATE INDEX IFK_contact_to_pww ON acumen.party_contact_details (party_websites_id);
CREATE INDEX IFK_party_to_pcd ON acumen.party_contact_details (parties_id);





CREATE INDEX user_data_FKIndex1 ON acumen.user_data (user_data_types_id);
CREATE INDEX IFK_date_type_to_data ON acumen.user_data (user_data_types_id);

CREATE TABLE acumen.user_data_links (
  id BIGSERIAL  NOT NULL ,
  descendant_id BIGINT   NOT NULL ,
  ancestor_id BIGINT    ,
  tenant_id INTEGER    ,
  created_by VARCHAR(254)    ,
  created_date TIMESTAMP    ,
  last_updated_by VARCHAR(254)    ,
  last_updated_date TIMESTAMP    ,
  version_number INTEGER NOT NULL      ,
PRIMARY KEY(id)    ,
  FOREIGN KEY(ancestor_id)
    REFERENCES acumen.user_data(id),
  FOREIGN KEY(descendant_id)
    REFERENCES acumen.user_data(id));


CREATE INDEX user_data_links_FKIndex1 ON acumen.user_data_links (ancestor_id);
CREATE INDEX user_data_links_FKIndex2 ON acumen.user_data_links (descendant_id);


CREATE INDEX IFK_ud_to_udl_a ON acumen.user_data_links (ancestor_id);
CREATE INDEX IFK_ud_to_udl_d ON acumen.user_data_links (descendant_id);


CREATE TABLE acumen.assets (
  id BIGSERIAL  NOT NULL ,
  asset_status_id BIGINT   NOT NULL ,
  supplier_id BIGINT   NOT NULL ,
  manufacturer_id BIGINT    ,
  custodian_id BIGINT   NOT NULL ,
  asset_number_part_1_id BIGINT   NOT NULL ,
  asset_number_part_2_id BIGINT   NOT NULL ,
  asset_number_part_3_id BIGINT   NOT NULL ,
  asset_number_part_4 INTEGER    ,
  site_id BIGINT   NOT NULL ,
  location_id BIGINT   NOT NULL ,
  division_id BIGINT   NOT NULL ,
  department_id BIGINT   NOT NULL ,
  depreciation_code BIGINT   NOT NULL ,
  country_id BIGINT   NOT NULL ,
  company_id BIGINT  NOT NULL,
  cost NUMERIC,
  category_id BIGINT   NOT NULL ,
  uid VARCHAR(30)   NOT NULL ,
  name citext NOT NULL ,
  description VARCHAR(150)    ,
  serial_number VARCHAR(30)    ,
  supplier_pn VARCHAR(30)    ,
  manufacturer_pn VARCHAR(30) ,
  is_a_facility BOOL    ,
  is_equipment BOOL    ,
  is_part BOOL    ,
  critical_asset BOOL    ,
  part_of_group BOOL    ,
  parts BOOL    ,
  risk_assessment BOOL    ,
  bcp BOOL    ,
  tracking_device BOOL    ,
  purchase_price NUMERIC    ,
  predicted_end_of_life_value NUMERIC	, 
  vat_code BIGINT    ,
  date_of_purchase DATE    ,
  installation_date DATE    ,
  commissioning_date DATE    ,
  decommissioning_date DATE    ,
  end_of_life_date DATE    ,
  life_expectancy INTEGER    ,
  purchase_lead_time INTEGER    ,
  budget_limit NUMERIC    ,
  tenant_id INTEGER   NOT NULL ,
  created_by VARCHAR(254)    ,
  created_date TIMESTAMP    ,
  last_updated_by VARCHAR(254)    ,
  last_updated_date TIMESTAMP    ,
  version_number INTEGER NOT NULL      ,
PRIMARY KEY(id),
  FOREIGN KEY(depreciation_code)
    REFERENCES acumen.user_data(id),
  FOREIGN KEY(category_id)
    REFERENCES acumen.user_data(id),
  FOREIGN KEY(division_id)
    REFERENCES acumen.user_data(id),
  FOREIGN KEY(country_id)
    REFERENCES acumen.user_data(id),
  FOREIGN KEY(location_id)
    REFERENCES acumen.user_data(id),
  FOREIGN KEY(site_id)
    REFERENCES acumen.user_data(id),
  FOREIGN KEY(vat_code)
    REFERENCES acumen.user_data(id),  
  FOREIGN KEY(department_id)
    REFERENCES acumen.user_data(id),
  FOREIGN KEY(supplier_id)
    REFERENCES acumen.parties(id),
  FOREIGN KEY(custodian_id)
    REFERENCES acumen.parties(id),
  FOREIGN KEY(manufacturer_id)
    REFERENCES acumen.parties(id),
  FOREIGN KEY(asset_number_part_1_id)
    REFERENCES acumen.user_data(id),
  FOREIGN KEY(asset_number_part_2_id)
    REFERENCES acumen.user_data(id),
  FOREIGN KEY(asset_number_part_3_id)
    REFERENCES acumen.user_data(id),
  FOREIGN KEY(asset_status_id)
    REFERENCES acumen.user_data(id),
  FOREIGN KEY(company_id)
    REFERENCES acumen.user_data(id));

CREATE SEQUENCE acumen.asset_part4_seq START 1;

CREATE INDEX assets_FKIndex1 ON acumen.assets (depreciation_code);
CREATE INDEX assets_FKIndex2 ON acumen.assets (category_id);
CREATE INDEX assets_FKIndex3 ON acumen.assets (division_id);
CREATE INDEX assets_FKIndex4 ON acumen.assets (country_id);
CREATE INDEX assets_FKIndex5 ON acumen.assets (location_id);
CREATE INDEX assets_FKIndex6 ON acumen.assets (site_id);
CREATE INDEX assets_FKIndex7 ON acumen.assets (department_id);
CREATE INDEX assets_FKIndex8 ON acumen.assets (asset_number_part_1_id);
CREATE INDEX assets_FKIndex9 ON acumen.assets (asset_number_part_2_id);
CREATE INDEX assets_FKIndex10 ON acumen.assets (asset_number_part_3_id);
CREATE INDEX assets_FKIndex11 ON acumen.assets (asset_status_id);
CREATE INDEX assets_FKIndex12 ON acumen.assets (supplier_id);
CREATE INDEX assets_FKIndex13 ON acumen.assets (custodian_id);
CREATE INDEX assets_FKIndex14 ON acumen.assets (manufacturer_id);
CREATE INDEX assets_FKIndex15 ON acumen.assets (company_id);
CREATE INDEX assets_FKIndex16 ON acumen.assets (vat_code);


CREATE INDEX IFK_ud_to_asset_dc ON acumen.assets (depreciation_code);
CREATE INDEX IFK_ud_to_asset_cat ON acumen.assets (category_id);
CREATE INDEX IFK_ud_to_asset_div ON acumen.assets (division_id);
CREATE INDEX IFK_ud_to_asset_country ON acumen.assets (country_id);
CREATE INDEX IFK_ud_to_asset_location ON acumen.assets (location_id);
CREATE INDEX IFK_ud_to_asset_site_id ON acumen.assets (site_id);
CREATE INDEX IFK_ud_to_asset_dep ON acumen.assets (department_id);
CREATE INDEX IFK_party_to_asset_supplier ON acumen.assets (supplier_id);
CREATE INDEX IFK_party_to_asset_custodian ON acumen.assets (custodian_id);
CREATE INDEX IFK_party_to_asset_manufacture ON acumen.assets (manufacturer_id);
CREATE INDEX IFK_ud_to_assets_p_1 ON acumen.assets (asset_number_part_1_id);
CREATE INDEX IFK_ud_to_assets_p_2 ON acumen.assets (asset_number_part_2_id);
CREATE INDEX IFK_ud_to_assets_p_3 ON acumen.assets (asset_number_part_3_id);
CREATE INDEX IFK_ud_to_asset_status_id ON acumen.assets (asset_status_id);
CREATE INDEX IFK_ud_to_asset_company ON acumen.assets (company_id);
CREATE INDEX IFK_ud_to_asset_vat_code ON acumen.assets (vat_code);

CREATE TABLE acumen.warranty_policies (
  id BIGSERIAL   NOT NULL ,
  warranties_supplier_id BIGINT   NOT NULL ,
  warranties_type_id BIGINT NOT NULL	,
  commencement_date DATE   NOT NULL ,
  expiry_date DATE   NOT NULL ,
  cost NUMERIC    ,
  policy_number VARCHAR(30)    ,
  om BOOL    ,
  tenant_id INTEGER   NOT NULL ,
  created_by VARCHAR(254)    ,
  created_date TIMESTAMP    ,
  last_updated_by VARCHAR(254)    ,
  last_updated_date TIMESTAMP    ,
  version_number INTEGER NOT NULL      ,
PRIMARY KEY(id)      ,
  FOREIGN KEY(warranties_supplier_id)
    REFERENCES acumen.parties(id),
  FOREIGN KEY(warranties_type_id)
    REFERENCES acumen.user_data(id));

CREATE INDEX warranty_FKIndex1 ON acumen.warranty_policies (warranties_supplier_id);
CREATE INDEX warranty_FKIndex2 ON acumen.warranty_policies (warranties_type_id);

CREATE INDEX IFK_party_to_wp ON acumen.warranty_policies (warranties_supplier_id);
CREATE INDEX IFK_ud_to_wp ON acumen.warranty_policies (warranties_type_id);

CREATE TABLE acumen.asset_warranties (
  id BIGSERIAL  NOT NULL	,
  assets_id BIGINT	,
  warranty_policy_id BIGINT   NOT NULL	,
  asset_warranties_type_id BIGINT NOT NULL	,
  tenant_id INTEGER   NOT NULL ,
  created_by VARCHAR(254)    ,
  created_date TIMESTAMP      ,
  last_updated_by VARCHAR(254)    ,
  last_updated_date TIMESTAMP    ,
  version_number INTEGER NOT NULL      ,
PRIMARY KEY(id)    ,
  FOREIGN KEY(warranty_policy_id)
    REFERENCES acumen.warranty_policies(id),
  FOREIGN KEY(assets_id)
    REFERENCES acumen.assets(id),
  FOREIGN KEY(asset_warranties_type_id)
    REFERENCES acumen.user_data(id));

CREATE INDEX asset_warranties_FKIndex1 ON acumen.asset_warranties (warranty_policy_id);
CREATE INDEX asset_warranties_FKIndex2 ON acumen.asset_warranties (assets_id);
CREATE INDEX asset_warranties_FKIndex3 ON acumen.asset_warranties (asset_warranties_type_id);

CREATE INDEX IFK_wp_to_aw ON acumen.asset_warranties (warranty_policy_id);
CREATE INDEX IFK_asset_to_aw ON acumen.asset_warranties (assets_id);
CREATE INDEX IFK_ud_to_aw ON acumen.asset_warranties (asset_warranties_type_id);

CREATE TABLE acumen.lease_in (
  id BIGSERIAL   NOT NULL ,
  assets_id BIGINT NOT NULL ,
  party_id BIGINT   NOT NULL ,
  lease_type BIGINT    ,
  lease_charge NUMERIC    ,
  vat_code BIGINT    ,
  charge_period	BIGINT		,
  lease_commences DATE    ,
  lease_expires DATE    ,
  lease_period VARCHAR(30)	,
  lease_cost NUMERIC    ,
  lease_value NUMERIC    ,
  lease_out_margin NUMERIC    ,
  location_postcode VARCHAR(30)    ,
  maintenance_included BOOLEAN	,
  warranty_included BOOLEAN		,
  tenant_id INTEGER   ,
  created_by VARCHAR(254)  NOT NULL  ,
  created_date TIMESTAMP  NOT NULL  ,
  last_updated_by VARCHAR(254)  NOT NULL  ,
  last_updated_date TIMESTAMP  NOT NULL  ,
  version_number INTEGER   NOT NULL ,
PRIMARY KEY(id)          ,
  FOREIGN KEY(party_id)
    REFERENCES acumen.parties(id),
  FOREIGN KEY(vat_code)
    REFERENCES acumen.user_data(id),
  FOREIGN KEY(charge_period)
    REFERENCES acumen.user_data(id),
  FOREIGN KEY(lease_type)
    REFERENCES acumen.user_data(id),
  FOREIGN KEY(assets_id)
    REFERENCES acumen.assets(id));

CREATE INDEX leaseIn_FKIndex1 ON acumen.lease_in (party_id);
CREATE INDEX leaseIn_FKIndex2 ON acumen.lease_in (vat_code);
CREATE INDEX leaseIn_FKIndex3 ON acumen.lease_in (charge_period);
CREATE INDEX leaseIn_FKIndex4 ON acumen.lease_in (lease_type);
CREATE INDEX leaseIn_FKIndex7 ON acumen.lease_in (assets_id);

CREATE INDEX IFK_parties_to_leaseIn ON acumen.lease_in (party_id);
CREATE INDEX IFK_ud_to_Tvat ON acumen.lease_in (vat_code);
CREATE INDEX IFK_ud_to_charge_period ON acumen.lease_in (charge_period);
CREATE INDEX IFK_ud_to_lease_type ON acumen.lease_in (lease_type);
CREATE INDEX IFK_assets_to_leaseIn ON acumen.lease_in (assets_id);

CREATE TABLE acumen.lease_out (
  id BIGSERIAL   NOT NULL ,
  assets_id BIGINT NOT NULL ,
  lease_type BIGINT    ,
  lease_charge NUMERIC    ,
  vat_code BIGINT    ,
  charge_period	BIGINT		,
  lease_commences DATE    ,
  lease_expires DATE    ,
  lease_period VARCHAR(30)	,
  lease_cost NUMERIC    ,
  lease_value NUMERIC    ,
  lease_out_margin NUMERIC    ,
  location_postcode VARCHAR(30)    ,
  tenant_id INTEGER   ,
  created_by VARCHAR(254)  NOT NULL  ,
  created_date TIMESTAMP  NOT NULL  ,
  last_updated_by VARCHAR(254)  NOT NULL  ,
  last_updated_date TIMESTAMP  NOT NULL  ,
  version_number INTEGER   NOT NULL ,
PRIMARY KEY(id)          ,
  FOREIGN KEY(vat_code)
    REFERENCES acumen.user_data(id),
  FOREIGN KEY(charge_period)
    REFERENCES acumen.user_data(id),
  FOREIGN KEY(lease_type)
    REFERENCES acumen.user_data(id),
 FOREIGN KEY(assets_id)
    REFERENCES acumen.assets(id));

CREATE INDEX leaseOut_FKIndex2 ON acumen.lease_out (vat_code);
CREATE INDEX leaseOut_FKIndex3 ON acumen.lease_out (charge_period);
CREATE INDEX leaseOut_FKIndex4 ON acumen.lease_out (lease_type);
CREATE INDEX leaseOut_FKIndex7 ON acumen.lease_out (assets_id);

CREATE INDEX IFK_ud_to_Tvat_leaseOut ON acumen.lease_out (vat_code);
CREATE INDEX IFK_ud_to_charge_period_leaseOut ON acumen.lease_out (charge_period);
CREATE INDEX IFK_ud_to_lease_type_leaseOut ON acumen.lease_out (lease_type);
CREATE INDEX IFK_assets_to_leaseOut ON acumen.lease_out (assets_id);

CREATE TABLE acumen.lease_out_extras (
  id BIGSERIAL   NOT NULL ,
  extras BIGINT   NOT NULL ,
  lease_out_id BIGINT   NOT NULL ,
  extra_cost NUMERIC	,
  tenant_id INTEGER   ,
  created_by VARCHAR(254)  NOT NULL  ,
  created_date TIMESTAMP  NOT NULL  ,
  last_updated_by VARCHAR(254)  NOT NULL  ,
  last_updated_date TIMESTAMP  NOT NULL  ,
  version_number INTEGER   NOT NULL ,
PRIMARY KEY(id)          ,
  FOREIGN KEY(extras)
    REFERENCES acumen.user_data(id),
  FOREIGN KEY(lease_out_id)
    REFERENCES acumen.lease_out(id));

CREATE INDEX lease_out_extras_FKIndex1 ON acumen.lease_out_extras (extras);
CREATE INDEX lease_out_extras_FKIndex2 ON acumen.lease_out_extras (lease_out_id);

CREATE INDEX IFK_ud_to_lease_out_extras_fk ON acumen.lease_out_extras (extras);
CREATE INDEX IFK_lease_out_to_lease_out_extras_fk ON acumen.lease_out_extras (lease_out_id);


CREATE TABLE acumen.asset_insurance_types (
  id BIGSERIAL   NOT NULL ,
  assets_id BIGINT   NOT NULL ,
  insurance_type_id BIGINT   NOT NULL ,
  mandatory BOOL   NOT NULL ,
  tenant_id INTEGER   NOT NULL ,
  created_by VARCHAR(254)    ,
  created_date TIMESTAMP    ,
  last_updated_by VARCHAR(254)    ,
  last_updated_date TIMESTAMP    ,
  version_number INTEGER NOT NULL      ,
PRIMARY KEY(id)    ,
  FOREIGN KEY(insurance_type_id)
    REFERENCES acumen.user_data(id),
  FOREIGN KEY(assets_id)
    REFERENCES acumen.assets(id));


CREATE INDEX insurance_type_FKIndex1 ON acumen.asset_insurance_types (insurance_type_id);
CREATE INDEX insurance_types_FKIndex2 ON acumen.asset_insurance_types (assets_id);


CREATE INDEX IFK_ud_to_it ON acumen.asset_insurance_types (insurance_type_id);
CREATE INDEX IFK_assets_to_it ON acumen.asset_insurance_types (assets_id);

CREATE TABLE acumen.insurance_type_policy_links (
  id BIGSERIAL  NOT NULL ,
  insurance_policy_id BIGINT   NOT NULL ,
  asset_insurance_type_id BIGINT   NOT NULL ,
  tenant_id INTEGER   NOT NULL ,
  created_by VARCHAR(254)    ,
  created_date TIMESTAMP      ,
  last_updated_by VARCHAR(254)    ,
  last_updated_date TIMESTAMP    ,
  version_number INTEGER NOT NULL      ,
PRIMARY KEY(id)    ,
  FOREIGN KEY(asset_insurance_type_id)
    REFERENCES acumen.asset_insurance_types(id),
  FOREIGN KEY(insurance_policy_id)
    REFERENCES acumen.insurance_policies(id));

CREATE INDEX type_policies_FKIndex1 ON acumen.insurance_type_policy_links (asset_insurance_type_id);
CREATE INDEX type_policies_FKIndex2 ON acumen.insurance_type_policy_links (insurance_policy_id);

CREATE INDEX IFK_it_to_tp ON acumen.insurance_type_policy_links (asset_insurance_type_id);
CREATE INDEX IFK_ip_to_tp ON acumen.insurance_type_policy_links (insurance_policy_id);

CREATE TABLE acumen.user_data_sets (
  id BIGSERIAL   NOT NULL ,
  assets_id BIGINT   NOT NULL ,
  user_data_id BIGINT   NOT NULL ,
  user_data_type_id BIGINT   NOT NULL ,
  tenant_id INTEGER    ,
  created_by VARCHAR(254)    ,
  created_date TIMESTAMP    ,
  last_updated_by VARCHAR(254)    ,
  last_updated_date TIMESTAMP    ,
  version_number INTEGER NOT NULL      ,
PRIMARY KEY(id)    ,
  FOREIGN KEY(assets_id)
    REFERENCES acumen.assets(id),
  FOREIGN KEY(user_data_id)
    REFERENCES acumen.user_data(id),
  FOREIGN KEY(user_data_type_id)
    REFERENCES acumen.user_data_types(id));


CREATE INDEX fault_codes_FKIndex1 ON acumen.user_data_sets (assets_id);
CREATE INDEX user_data_sets_FKIndex2 ON acumen.user_data_sets (user_data_id);
CREATE INDEX user_data_types_FKIndex3 ON acumen.user_data_sets (user_data_type_id);



CREATE INDEX IFK_assets_to_uds ON acumen.user_data_sets (assets_id);
CREATE INDEX IFK_ud_to_uds ON acumen.user_data_sets (user_data_id);
CREATE INDEX IFK_udt_to_uds ON acumen.user_data_sets (user_data_type_id);


CREATE TABLE acumen.asset_parts (
  id BIGSERIAL   NOT NULL ,
  assets_child_id BIGINT   NOT NULL ,
  assets_parent_id BIGINT   NOT NULL ,
  tenant_id INTEGER    ,
  created_by VARCHAR(254)    ,
  created_date TIMESTAMP    ,
  last_updated_by VARCHAR(254)    ,
  last_updated_date TIMESTAMP    ,
  version_number INTEGER NOT NULL      ,
PRIMARY KEY(id)    ,
  FOREIGN KEY(assets_parent_id)
    REFERENCES acumen.assets(id),
  FOREIGN KEY(assets_child_id)
    REFERENCES acumen.assets(id));


CREATE INDEX asset_parts_FKIndex1 ON acumen.asset_parts (assets_parent_id);
CREATE INDEX asset_parts_FKIndex2 ON acumen.asset_parts (assets_child_id);


CREATE INDEX IFK_asset_to_part_parent ON acumen.asset_parts (assets_parent_id);
CREATE INDEX IFK_asset_to_part_child ON acumen.asset_parts (assets_child_id);

CREATE TABLE acumen.groups_assets (
  id BIGSERIAL   NOT NULL ,
  assets_id BIGINT   NOT NULL ,
  groups_id BIGINT   NOT NULL ,
  tenant_id INTEGER    ,
  created_by VARCHAR(254)    ,
  created_date TIMESTAMP      ,
  last_updated_by VARCHAR(254)    ,
  last_updated_date TIMESTAMP    ,
PRIMARY KEY(id)    ,
  FOREIGN KEY(groups_id)
    REFERENCES acumen.groups(id),
  FOREIGN KEY(assets_id)
    REFERENCES acumen.assets(id));


CREATE INDEX groups_assets_FKIndex1 ON acumen.groups_assets (groups_id);
CREATE INDEX groups_assets_FKIndex2 ON acumen.groups_assets (assets_id);


CREATE INDEX IFK_groups_to_gassets ON acumen.groups_assets (groups_id);
CREATE INDEX IFK_asset_to_gasset ON acumen.groups_assets (assets_id);

CREATE TABLE acumen.list_contents (
  id BIGSERIAL   NOT NULL ,
  lists_id BIGINT   NOT NULL ,
  entity_id BIGINT   NOT NULL ,
  tenant_id INTEGER    ,
  created_by VARCHAR(254)    ,
  created_date TIMESTAMP      ,
  last_updated_by VARCHAR(254)    ,
  last_updated_date TIMESTAMP    ,
  CONSTRAINT list_entity_constraint unique (lists_id , entity_id),
PRIMARY KEY(id)  ,
  FOREIGN KEY(lists_id)
    REFERENCES acumen.lists(id));

CREATE INDEX list_contents_FKIndex1 ON acumen.list_contents (lists_id);

CREATE INDEX IFK_lists_to_lcontent ON acumen.list_contents (lists_id);


CREATE TABLE acumen.other_system_ref (
  id BIGSERIAL  NOT NULL ,
  assets_id BIGINT   NOT NULL ,
  other_system_name_id BIGINT   NOT NULL ,
  other_system_id VARCHAR(100)   NOT NULL ,
  tenant_id INTEGER    ,
  created_by VARCHAR(254)    ,
  created_date TIMESTAMP    ,
  last_updated_by VARCHAR(254)    ,
  last_updated_date TIMESTAMP    ,
  version_number INTEGER NOT NULL      ,
PRIMARY KEY(id)    ,
  FOREIGN KEY(other_system_name_id)
    REFERENCES acumen.user_data(id),
  FOREIGN KEY(assets_id)
    REFERENCES acumen.assets(id));

CREATE INDEX other_system_ref_FKIndex1 ON acumen.other_system_ref (other_system_name_id);
CREATE INDEX other_system_ref_FKIndex2 ON acumen.other_system_ref (assets_id);

CREATE INDEX IFK_ud_to_osr ON acumen.other_system_ref (other_system_name_id);
CREATE INDEX IFK_assets_to_osr ON acumen.other_system_ref (assets_id);


CREATE TABLE acumen.asset_schedule (
  id BIGSERIAL  NOT NULL ,
  lease_commences DATE    ,
  lease_expires DATE , 
  date_collected_deployed DATE ,
  asset_id BIGINT   NOT NULL ,
  tenant_id INTEGER  NOT NULL  ,
  created_by VARCHAR(254) NOT NULL   ,
  created_date TIMESTAMP  NOT NULL  ,
  last_updated_by VARCHAR(254)  NOT NULL  ,
  last_updated_date TIMESTAMP   NOT NULL ,
  version_number INTEGER NOT NULL      ,
PRIMARY KEY(id),
  FOREIGN KEY(asset_id)
    REFERENCES acumen.assets(id)
);

CREATE INDEX asset_schedule_FKIndex1 ON acumen.asset_schedule (asset_id);


CREATE TABLE acumen.asset_proposal (
  id BIGSERIAL  NOT NULL ,
  asset_id BIGINT   NOT NULL ,
  asset_schedule_id BIGINT   NOT NULL ,
  address_id BIGINT   ,
  email_id BIGINT   ,
  telephone_id BIGINT   ,
  client_party_id BIGINT   ,
  contact_party_id BIGINT   ,
  current_status VARCHAR(100), 
  charge_period	BIGINT	   ,
  standard_cost	NUMERIC	   ,
  periodic_cost NUMERIC    ,
  total_cost NUMERIC    ,
  invoicing_schedule BIGINT,
  decline_comment VARCHAR(254)	,
  tenant_id INTEGER    ,
  created_by VARCHAR(254)  NOT NULL  ,
  created_date TIMESTAMP   NOT NULL ,
  last_updated_by VARCHAR(254)  NOT NULL  ,
  last_updated_date TIMESTAMP   NOT NULL  ,
  version_number INTEGER NOT NULL      ,
PRIMARY KEY(id),
  FOREIGN KEY(asset_id)
    REFERENCES acumen.assets(id),
  FOREIGN KEY(address_id)
    REFERENCES acumen.addresses(id),
  FOREIGN KEY(email_id)
    REFERENCES acumen.emails(id),
  FOREIGN KEY(telephone_id)
    REFERENCES acumen.tel_numbers(id),
  FOREIGN KEY(client_party_id)
    REFERENCES acumen.parties(id),
  FOREIGN KEY(contact_party_id)
    REFERENCES acumen.parties(id),
  FOREIGN KEY(asset_schedule_id)
    REFERENCES acumen.asset_schedule(id),
  FOREIGN KEY(invoicing_schedule)
    REFERENCES acumen.user_data(id)
);

CREATE INDEX asset_proposal_FKIndex1 ON acumen.asset_proposal (asset_id);
CREATE INDEX asset_proposal_FKIndex2 ON acumen.asset_proposal (address_id);
CREATE INDEX asset_proposal_FKIndex3 ON acumen.asset_proposal (email_id);
CREATE INDEX asset_proposal_FKIndex4 ON acumen.asset_proposal (telephone_id);
CREATE INDEX asset_proposal_FKIndex5 ON acumen.asset_proposal (client_party_id);
CREATE INDEX asset_proposal_FKIndex6 ON acumen.asset_proposal (asset_schedule_id);
CREATE INDEX asset_proposal_FKIndex7 ON acumen.asset_proposal (invoicing_schedule);


CREATE TABLE acumen.asset_proposal_extras (
  id BIGSERIAL  NOT NULL ,
  asset_id BIGINT   NOT NULL ,
  proposal_id BIGINT  NOT NULL ,
  lease_out_extras_id BIGINT NOT NULL  ,
  cost NUMERIC    ,
  tenant_id INTEGER  NOT NULL  ,
  created_by VARCHAR(254)  NOT NULL  ,
  created_date TIMESTAMP   NOT NULL ,
  last_updated_by VARCHAR(254)  NOT NULL  ,
  last_updated_date TIMESTAMP   NOT NULL ,
  version_number INTEGER NOT NULL      ,
PRIMARY KEY(id),
  FOREIGN KEY(asset_id)
    REFERENCES acumen.assets(id),
  FOREIGN KEY(proposal_id)
    REFERENCES acumen.asset_proposal(id),
  FOREIGN KEY(lease_out_extras_id)
    REFERENCES acumen.lease_out_extras(id)
);

CREATE INDEX asset_proposal_extras_FKIndex1 ON acumen.asset_proposal_extras (asset_id);
CREATE INDEX asset_proposal_extras_FKIndex2 ON acumen.asset_proposal_extras (proposal_id);
CREATE INDEX asset_proposal_extras_FKIndex3 ON acumen.asset_proposal_extras (lease_out_extras_id);


CREATE TABLE acumen.maintenance(
	id BIGSERIAL NOT NULL ,
	assets_id BIGINT NOT NULL ,
	tag BOOLEAN ,
	tag_note VARCHAR(100) ,
	ppm_required BOOLEAN ,
	ppm_required_note VARCHAR(100) ,
	maintenance_mandatory BOOLEAN ,
	maintenance_mandatory_note VARCHAR(100) ,
	maintenance_document_type BOOLEAN ,
	third_party_provider BOOLEAN ,
	third_party_supplier_id BIGINT NOT NULL ,
	fault_code_category_id BIGINT NOT NULL ,
	fault_code_id BIGINT NOT NULL ,
	doc_type VARCHAR(150)	 ,
	tenant_id INTEGER    ,
	created_by VARCHAR(254) ,
	created_date TIMESTAMP	,
	last_updated_by VARCHAR(254)	,
	last_updated_date TIMESTAMP	,
	version_number INTEGER NOT NULL	,
PRIMARY KEY(id) ,
	FOREIGN KEY(assets_id)
    	REFERENCES acumen.assets(id) ,
    FOREIGN KEY(third_party_supplier_id)
    	REFERENCES acumen.parties(id) ,
    FOREIGN KEY(fault_code_category_id)
    	REFERENCES acumen.user_data(id) ,
    FOREIGN KEY(fault_code_id)
    	REFERENCES acumen.user_data(id));

CREATE INDEX assets_id_FKIndex1 ON acumen.maintenance(assets_id);
CREATE INDEX tpsid_FKIndex2 ON acumen.maintenance(third_party_supplier_id);
CREATE INDEX fccid_FKIndex3 ON acumen.maintenance(fault_code_category_id);
CREATE INDEX fault_code_id_FKIndex4 ON acumen.maintenance(fault_code_id);

CREATE INDEX IFK_maintenance_to_assid ON acumen.maintenance(assets_id);
CREATE INDEX IFK_party_to_maintenance ON acumen.maintenance(third_party_supplier_id);
CREATE INDEX IFK_udata1_to_maintenance ON acumen.maintenance(fault_code_category_id);
CREATE INDEX IFK_udata2_to_maintenance ON acumen.maintenance(fault_code_id);

