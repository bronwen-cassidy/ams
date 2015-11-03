package com.xioq.dasacumen.model.constants;

/**
 * File to hold constant/lookup values for any search information.
 * 
 * @author mwalsh
 *
 */
public class PartyTypeConstants {

 /*
  * Enum constant variables used for searching on a specific type of party, namely client,supplier or custodian.
  */
  public enum partyTypes {SUPPLIER, CLIENT, CUSTODIAN} //These values must tie up with the party_type.type_name.

  public enum partylinkTypes {CONTACT} //These values must tie up with the party_links.type.

  public static final String PERSON = "PERSON";
}
