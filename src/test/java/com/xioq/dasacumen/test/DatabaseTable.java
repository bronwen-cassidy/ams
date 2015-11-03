package com.xioq.dasacumen.test;

public enum DatabaseTable {
    ADDRESSES("addresses"),
    ASSETS("assets"),
    ASSET_PARTS("asset_parts"),
    ASSET_PROPOSAL("asset_proposal"),
    ASSET_PROPOSAL_EXTRAS("asset_proposal_extras"),
    ASSET_SCHEDULE("asset_schedule"),
    DOCS("docs"),
    EMAILS("emails"),
    MAINTENANCE("maintenance"),
    PARTY_ADDRESSES("party_addresses"),
    TELEPHONE("tel_numbers"),
    PARTY_TELEPHONE("party_telephone_numbers"),
    PARTIES("parties"),
    TENANTS("tenants"),
    USERS("users"),
    WARRANTY_POLICIES("warranty_policies"),
    INSURANCE_POLICIES("insurance_policies"),
    INSURANCE_TYPE_POLICY_LINKS("insurance_type_policy_links"),
    USER_DATA("user_data");
   
    private final String text;

    private DatabaseTable(final String text) {
        this.text = text;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }
}
