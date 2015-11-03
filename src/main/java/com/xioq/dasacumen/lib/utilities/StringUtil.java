package com.xioq.dasacumen.lib.utilities;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

/**
 * This is a utility class for holding custom string methods that maybe specific to Bright Future developments.
 * 
 * @author mwalsh
 */
public class StringUtil extends StringUtils
{
  /** Constant defining a space: ' '. */
  public final static String SPACE = " ";

  /** Constant defining an empty string: ''. */
  public final static String EMPTY = "";

  /** Constant defining a new line: '\n'. */
  public final static String NEW_LINE = "\n";

  /** Constant defining a new line carrige return: '\r'. */
  public final static String NEW_LINE_CARRIGE_RETURN = "\r\n";
  
  /** Constant defining a new line carrige return: '\r'. */
  public final static String DOUBLE_NEW_LINE_CARRIGE_RETURN = "\r\n\n";
  
  /** Constant defining a carriage return */
  public final static String CARRIAGE_RETURN = "\r";
  
  /** Constant defining a URL encoded space (i.e. can be used in a URL path) */
  public final static String URL_ENCODED_SPACE = "%20";
  
  /**
   * Constant defining a comma followed by a space: ', '. Useful for building up
   * toString
   */
  public final static String COMMA_SPACE = ", ";
  
  /** Constant defining a comma */
  public final static String COMMA = ",";
  
  /** Constant defining a fullstop */
  public final static String FULLSTOP = ".";
  
  /** Constant defining a dash */
  public final static String DASH = "-";
  
  /** Constant defining a ampersand (&) */
  public final static String AMPERSAND  = "&";
  
  public final static String DOUBLE_SPACE_REG_PATTERN = "\\s+";
  
  private static final String REGULAR_EXP_VALID_POSTCODE
  = "([A-PR-UWYZ0-9][A-HK-Y0-9][AEHMNPRTVXY0-9]?[ABEHMNPRVWXY0-9]? {1,2}[0-9][ABD-HJLN-UW-Z]{2}|GIR 0AA)$";

  public final static String FORWARD_SLASH = "\\";
    
  /**
   * Tests is the string represents a Yes value. ie 'Y'
   * 
   * @param s The string to test
   * @return true if the string equals 'Y', false otherwise
   */
  public static boolean isCharTrue(String s)
  {
    if (s != null && s.equals("Y"))
    {
      return true;
    }
    else
    {
      return false;
    }
  }
  
  /**
   * Takes the string parameter and splits it into words (using either a space,
   * tab, new line, carriage return or the form feed characters as deliminators.
   * Each word is then capitalised, ie the first letter is made upper case the
   * rest lower. If the word starts with a bracket then, then first lette within
   * the bracket is maqde upper the rest lower.
   * 
   * @param s The string to convert
   * @return the original string capitalised.
   */
  public static String initCap(String s)
  {
    if (s == null || s.length() == 0)
    {
      return "";
    }
    StringBuffer sb = new StringBuffer();
    StringTokenizer st = new StringTokenizer(s);
    while (st.hasMoreTokens())
    {
      String word = st.nextToken();
      if (sb.length() != 0)
      {
        sb.append(' ');
      }
      // Handle words in brackets, numbers/codes (inc postcode), etc.
      if (!Character.isLetter(word.charAt(0)) && word.length() > 1)
      {
        if (Character.isDigit(word.charAt(0)))
        {
          sb.append(word);
        }
        else
        {
          sb.append(word.substring(0, 2).toUpperCase());
          if (word.length() > 2)
          {
            sb.append(word.substring(2).toLowerCase());
          }
        }
      }
      else
      {
        sb.append(word.substring(0, 1).toUpperCase());
        if (word.length() > 1)
        {
          sb.append(word.substring(1).toLowerCase());
        }
      }
    }
    return sb.toString();
  }



  /**
   * Truncates a string to a specified length. If the string is shorter than the
   * length then the original string is returned.
   * 
   * @param s The string to truncate
   * @param length The length required
   * @return null if s is null, else the truncated string
   */
  public static String rtrunc(String s, int length)
  {
    if (s == null)
    {
      return null;
    }
    if (s.length() > length)
    {
      return s.substring(0, length);
    }
    return s;
  }

  public static String trim(String s)
  {
    char[] ca = s.toCharArray();
    for (int i = 0; i < ca.length; i++)
    {
      if (Character.isSpaceChar(ca[i]))
      {
        ca[i] = ' ';
      }
    }
    return new String(ca).trim();
  }

  /**
   * Forms a string from the array of strings, using default quote and delim
   * strings of '' (nothing) and ',' (comma) respectively.
   * 
   * @see StringUtil#toList(String[], String, String)
   * @param a The string array to parse
   */
  public static String toList(String[] a)
  {
    return toList(a, "", ",");
  }

  /**
   * Forms a single string from the array of strings. Uses a default deliminator
   * between the stirngs of ',' (comma) and the 'quote' parameter to surround
   * each string of the array.
   * 
   * @see StringUtil#toList(String[], String, String)
   * @param a The string array to parse
   * @param quote The string to surround each element of the array
   * @return
   */
  public static String toList(String[] a, String quote)
  {
    return toList(a, quote, ",");
  }

  /**
   * Takes a string array and creates single string of each element of the array
   * surrounded by the <code>quote</code> parameter and seperated by the
   * <code>delim</code> parameter.
   * 
   * @param a The strig array to make the list from
   * @param quote The string to surround each element of the array
   * @param delim The string to seperate each element of the array
   * @return null if the array is null, else the parsed string.
   */
  public static String toList(String[] a, String quote, String delim)
  {
    if (a == null) return null;

    StringBuffer sb = new StringBuffer();

    for (int i = 0; i < a.length; i++)
    {
      if (i != 0) sb.append(delim + " ");
      sb.append(quote + a[i] + quote);
    }

    return sb.toString();
  }

  public static String replace(String s, String searchFor, String replaceWith)
  {
    int i = s.indexOf(searchFor);
    if (replaceWith.length() > 0)
    {
      int j = s.indexOf(replaceWith);
      while (i == j && i >= 0)
      {
        i = s.indexOf(searchFor, i + replaceWith.length());
        j = s.indexOf(replaceWith, j + replaceWith.length());
      }
    }
    if (i >= 0)
    {
      StringBuffer sb = new StringBuffer();
      sb.append(s.substring(0, i));
      sb.append(replaceWith);
      sb.append(s.substring(i + searchFor.length()));
      return replace(sb.toString(), searchFor, replaceWith);
    }
    else
      return s;
  }

  public static String globalReplace(String s, String searchFor, String replaceWith)
  {
    //Search for the string
    int i = s.indexOf(searchFor);

    //Check that we have a replace String that we are going to use
    if (replaceWith.length() > 0)
    {
      //Only loop if we have found a string that we are searching for
      while ((i = s.indexOf(searchFor)) >= 0)
      {
        //int j = searchFor.indexOf(replaceWith); //Not used
        
        //Get the String up to the searchFor string we are looking for
        String s1 = s.substring(0, i);
        
        //From the init location were the search string was found + the search
        //string length, to the max length of the entire string we are searching on
        //return the string without the searchFor string
        String s2 = s.substring(i + searchFor.length(), s.length());
        
        //Create the new String containing 1) String up to the searchFor +
        //2)The string to replace the searchFor +
        //3)The String after the searchFor
        s = s1 + replaceWith + s2;
      }
    }

    return s;
  }
   
  //Start of String Display
  //=======================
  /**
   * @param wordsToSearch String that will be searched.
   * @param wordToFind String that will be searched for.
   * @param replacingWord String that will replace the whole word occurrance of wordToFind.
   * @return the result of the wholeWordReplace.
   */
  public static String wholeWordReplace(String wordsToSearch, 
                                        String wordToFind, 
                                        String replacingWord)
  {
    if ((wordsToSearch==null)||
        (wordsToSearch.length()==0)||
        (wordToFind==null)||
        (wordToFind.length()==0)||
        (replacingWord==null)||
        (replacingWord.length()==0))return wordsToSearch; 
 
    //Trim to ensure that white spaces are removed
    wordsToSearch = wordsToSearch.trim().toUpperCase();
    wordToFind    = wordToFind.trim().toUpperCase();
    replacingWord = replacingWord.trim().toUpperCase();
        
    String regex = "\\b"+wordToFind+"\\b";
    Pattern p = Pattern.compile(regex);   // Compiles regular expression into Pattern.
    Matcher matcher = p.matcher(wordsToSearch);       // Creates Matcher with subject s and Pattern p.
    return matcher.replaceAll(replacingWord);
  }
  
  public static String wholeWordReplace(String wordsToSearch, 
                                        String[] wordToFind, 
                                        String replacingWord)
  {
    Set set = new HashSet();
    String result = null;
    
    if ((wordToFind==null)||(wordToFind.length==0)) return wordsToSearch;
    for (int cnt=0; cnt<wordToFind.length; cnt++)
    {
      //To prevent duplicate search parameters use a set 
      if (set.add(wordToFind[cnt]))
      {
         //The result is the wordToFind[cnt] replaced with replacingWord
         result= wholeWordReplace(wordsToSearch,wordToFind[cnt],replacingWord);
         
         //Setup the next word string to search
         wordsToSearch=result;
      }
    }//for
    
    return wordsToSearch;
  }
  //End of String Display
  //=====================
  
  
  /**
   * @param s The string to test.
   * @param ifNull If s is null, this is returned.
   * @return Either s or ifNull, spending if s is null.
   */
  public static String nvl(String s, String ifNull)
  {
    return s != null ? s : ifNull;
  }

  public static String makeFriendly(String id)
  {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < id.length(); i++)
    {
      char c = id.charAt(i);
      if (i > 1 && Character.isUpperCase(c))
      {
        sb.append(" " + c);
      }
      else
      {
        sb.append(c);
      }
    }
    return initCap(sb.toString());
  }

  /**
   * @param s The string to test.
   * @return true if null, empty or just contains white space. false otherwise.
   */
  public static boolean isEmpty(String s)
  {
    return s == null || s.trim().length() == 0;
  }

  /**
   * Checks to see if the string array has any none null, none empty strings within it.
   * @param param
   * @return true if the array is null, of length 0 or none of the string have a value.
   * @see #isEmpty(String)
   */
  public static boolean isEmpty(String [] param)
  {
    if(null != param && param.length > 0)
    {
      for (int i = 0; i < param.length; i++)
      {
        if(!StringUtil.isEmpty(param[i]))
          return false;
      }
    }
    return true;
  }
  
  
  
  public static boolean checkCharSet(String s, char[][] set)
  {
    char[] ca = s.toCharArray();

    for (int i = 0; i < ca.length; i++)
    {
      // Check if char is within the char set
      if (!inCharSet(ca[i], set))
      {
        // If the char is not within the char set then end
        return false;
      }
    }
    // If char is within the set then return true
    return true;
  }

  private static boolean inCharSet(char c, char[][] set)
  {
    char from;
    char to;
    for (int i = 0; i < set.length; i++)
    {
      from = set[i][0];
      to = set[i][1];

      if ((c >= from) && (c <= to)) return true;

    }
    return false;
  }

  public static String lpad(String a, int padWidth, char c)
  {
    int append_length = padWidth - a.length();
    String append_string = charToString(padWidth, c);
    if (append_length > 0)
    {
      // Create prefix padded string from 0 to append_length
      append_string = append_string.substring(0, append_length);
      a = append_string + a;
    }

    return a;
  }

  private static String charToString(int width, char c)
  {
    // Build String from char
    String result = "";
    for (int i = 0; i < width; i++)
    {
      result = result + c;
    }
    return result;
  }

  public static String lpad(String a, int padWidth)
  {
    return lpad(a, padWidth, '0');
  }

  public static boolean isIntegerString(String s)
  {
    return (s.length() == 0) ? true : (inCharSet(s.charAt(0), new char[][]
    {
    {'0', '9'}}) ? isIntegerString(s.substring(1, s.length())) : false);
  }

  /**
   * isValidEMail Performs a highly simplified check on an email address,
   * basically that there is a local name and a domain name separated by the '@'
   * character. This is simplistic and should really be refined to conform to
   * RFC822.
   * 
   * @param a the email address
   * @return true if the address is valid
   */
  public static boolean isValidEMail(String s)
  {
    return new StringTokenizer(s, "@").countTokens() == 2;
  }

  public static boolean isValidTelephone(String s)
  {
    return checkCharSet(s, new char[][]
    {
    {'0', '9'},
    {' ', ' '},
    {'(', '('},
    {')', ')'},
    {'%', '%'} });
  }

  public static boolean isValidName(String s)
  {
    return checkCharSet(s, new char[][]
    {
    {'0', '8'},
    {'%', '%'},
    {'a', 'z'},
    {'A', 'Z'},
    {'_', '_'},
    {'=', '='},
    {'{', '{'},
    {'}', '}'},
    {'|', '|'},
    {'&', '&'},
    {'~', '~'}});
  }

  public static boolean isValidNameWithApos(String s)
  {
    return checkCharSet(s, new char[][]
    {
    {'0', '8'},
    {'%', '%'},
    {'a', 'z'},
    {'A', 'Z'},
    {'_', '_'},
    {'=', '='},
    {'{', '{'},
    {'}', '}'},
    {'|', '|'},
    {'&', '&'},
    {'~', '~'},
    {'\'', '\''}});
  }

 

  public static String stripNonPrinting(String s)
  {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < s.length(); i++)
    {
      char c = s.charAt(i);
      if (c >= 32 && c < 127)
      {
        sb.append(c);
      }
    }
    return sb.toString();
  }

  public static boolean isValidStaffPin(String s)
  {
    return checkCharSet(s, new char[][]
    {
    {'0', '9'}});
  }

  /**
   * Method: makeString Mmethod that add's 'commas' and 'ands' to strings that
   * are in a collection. Each word that you want to be formated must be a
   * different element in the collection. For example if the collection
   * contained element (1) = cat, element (2) = dog and element (3) = rabbit
   * this would result in the below method returning cat, dog and rabbit.
   */

  public static String makeString(Collection c)
  {
    if (c == null) return null;
    Iterator i = c.iterator();
    int maxNoOfItems = c.size();
    int count = 0;

    if (maxNoOfItems == 1)
    {
      return (String) i.next();
    }

    StringBuffer sb = new StringBuffer();

    while (maxNoOfItems != count + 2)
    {
      sb.append((String) i.next());
      sb.append(", ");
      count++;
    }

    if (maxNoOfItems == count + 2)
    {
      sb.append((String) i.next());
      sb.append(" and ");
      count++;
      sb.append((String) i.next());
      count++;
    }

    return sb.toString();
  }

  public static boolean isValidCharacter(String s)
  {
    return checkCharSet(s, new char[][]{{'A', 'Z'}});
  }

  
  
  public static int getNoValidNumeric(String s)
  {
    int count=0;
    if (isEmpty(s))return count;
    char set[][] =  new char[][]{{'0', '9'}};
    char[] ca = s.toCharArray();
    for (int i=0; i<ca.length; i++) if (inCharSet(ca[i], set)) count++;
    return count;
  }
  
  
  
  
  /**
   * This method calculates the no of occurances of a pattern within a given String.
   * @param stringToSearch
   * @param pattern
   * @return count no of occurances of a given pattern
   */
  public static int count(String stringToSearch, String pattern)
  {
     StringTokenizer token = new StringTokenizer(stringToSearch, pattern);
     int count=token.countTokens()-1; //The StringTokenizer countTokens() method returns the no of
                                      //occurances of a pattern. before it generates an 
                                      //exception. Hence -1 to obtain the correct no of occurances of the pattern.
     token=null;
     return count;
  }



  
  /** 
   * This method will take a String and look for commas and then strip them out and populate the seperated values
   * into a String [] so it can be used to search for multiple fields.
   * 
   * It is used for multiple searches as it accepts a string (any string) containing a comma that it will strip & 
   * return into a string[] containing the values.
   * 
   * @param form.
   * @return form containing populated String[].
   */
  public static String[] checkAndSeperateMultipleField(String fieldToStrip) 
  {
	String[] fieldArray = null;
	if (fieldToStrip !=null) {
	  String line = fieldToStrip;    // String to be segmented 
	  int count = 0;          //Number of substrings 
	  char separator = ',';   //Substring separator 
	
	  int index = 0;          //Determine the number of substrings 
	  do {
	    ++count;              //Increment count of substrings i.e. "100,102" would be 2.
	    ++index;              //Move past last position 
	    index = line.indexOf(separator, index); 
	  } while (index != -1);  // -1 means no more seperator found!
	
	  if (count > 1) {   //if true extract the substring into an array..
		String[] subStr = new String[count]; // Allocate for substrings 
		index = 0; //Substring start index 
		int endIndex = 0; //Substring end index 
		
		for(int i = 0; i < count; i++) {
		  endIndex = line.indexOf(separator,index); //Find next separator 
		
		if(endIndex == -1) {                  //If it is not found 
		  subStr[i] = line.substring(index);  //extract to the end 
		} else {                              //otherwise write first srn to subStr [0].
		      subStr[i] = line.substring(index, endIndex); 
		        if (count > 1) {
		          endIndex++;
		          index = endIndex;
		        }
		      }
		    }
		  fieldArray = subStr;
	    }
	}
	return fieldArray;
  }

	/**
	 * Removes Identifier Ignorable characters from a String. The following
	 * Unicode characters are ignorable in a Java identifier or a Unicode
	 * identifier:
	 * 
	 * 0x0000 to 0x0008, 0x000E to 0x001B, and 0x007F to 0x009F - ISO control
	 * characters that are not whitespace
	 * 
	 * 0x200C to 0x200F - join controls
	 * 
	 * 0x200A to 0x200E - bidirectional controls
	 * 
	 * 0x206A to 0x206F - format controls
	 * 
	 * 0xFEFF - zero-width no-break space
	 * 
	 * @param s
	 * @return
	 */
	public static String removeIgnorable(String s) {
		if (!StringUtil.isEmpty(s)) {
			String r = EMPTY;
			for (int i = 0; i < s.length(); i++) {
				if (!Character.isIdentifierIgnorable(s.charAt(i))) {
					r += s.charAt(i);
				}
			}
			return r;
		}
		return null;
	}
	
	public static String removeCarriageReturn(String s){
		if(!StringUtil.isEmpty(s)){
			s = s.replaceAll(StringUtil.CARRIAGE_RETURN, StringUtil.SPACE);
			s = s.replaceAll(StringUtil.NEW_LINE, StringUtil.EMPTY);
		}
		return s;
	}
	
	 /**
   * Method to determine if an item in a String array contains an item of the
   * same value in another String array. nulls and "" are ignored.
   * 
   * @param stringToCheck The String array to check.
   * @param toMatchString The String array to match an element of.
   * @return true if any one String from the stringToCheck array exists within 
   *           the toMatchString array, false if it doesn't exist.
   */
  public static boolean contains(String stringToCheck[], String[] toMatchString)
  {
    for (int i = 0; i < stringToCheck.length; i++)
    {
      for (int j = 0; j < toMatchString.length; j++)
      {
        if ( !isEmpty(stringToCheck[i]) 
            && !isEmpty(toMatchString[j]) 
            && stringToCheck[i].equals(toMatchString[j]))
        {
          return true;
        }
      }
    }
    return false;
  }
  
  /**
   * Method to determine if an item is in a String array
   * 
   * @param stringToCheck The String array to check against.
   * @param toMatchString The String to match the pattern of.
   * @return true 
   */
  public static boolean contains(String stringToCheck[], String toMatchString)
  {
    for (int i = 0; i < stringToCheck.length; i++)
    {
      if (null != stringToCheck[i] && stringToCheck[i].equals(toMatchString))
      {
        return true;
      }
    }
    return false;
  }
  
  /**
	 * Utility method which will add the passed String value to the end of the
	 * passed String[] array.
	 * 
	 * @param strArray
	 * @param str
	 * @return
	 */
  public static String[] addStringToStringArray(String[] strArray, String str){
	  StringBuilder sb = new StringBuilder();
	  
	  if(null!=strArray){
  	  for(String strArrayValue : strArray){
  		  if(sb.length()>0){
  			  sb.append(COMMA);
  		  }
  		  sb.append(strArrayValue);
  	  }  
	  }
	  
	  if(sb.length()>0){
		  sb.append(COMMA);
	  }
	  sb.append(str);
	  
	  String[] returnStrArray = sb.toString().split(COMMA);
	  
	  return returnStrArray;
  }
  
  /**
   * Converts carriage returns and line breaks to <br>
   * tags (useful for displaying CLOBs on jsp. There is a hibernate type
   * (HtmlStringType) that does the same, but this will always convert the
   * carraige returns to html break tags tags which is not always appropriate
   * (i.e. in textboxes)
   * 
   * @param str
   * @return String
   */
  public static String convertBreaksToHtml(String str) {
    String htmlStr = EMPTY;
    if(null!=str){
      htmlStr = str.replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
    }
    return htmlStr;
  }
  

  /**
   * Remove rouge commas from a String.
   * 
   * The definition of a String containing rouge commas is any, or all of the
   * following. The String begins with a comma, ends with a comma or contains
   * multiple commas without a character separating each comma.
   * 
   * i.e ,358 Chorlton Road,, Manchester, this would be converted to 358
   * Chorlton Road, Manchester
   * 
   * @param theString The String to convert and to make rouge comma free :-)
   * @return a beautiful String :-)
   */
  public static String removeRougeCommasFromString (String theString)
  {
    if (null == theString)
    {
      throw new NullPointerException ("Failed in the method, removeRougeCommasFromString, the passed " +
      		"in String is null.");
    }
    
    theString = theString.replaceAll("(,)\\1+", "$1"); // removes any double commas from the String
    theString = theString.replaceAll("\\,$", ""); // removes the final trailing comma from the String
    theString = theString.replaceAll("^,", ""); // if the String starts with a comma then this is replaced.
    return theString;
  }
  

  /**
   * 
   * Removes all occurrences of @thePatternToReplace in the String @theString.
   * 
   * @param theString the String to check.
   * @param thePatternToReplace the pattern to replace in the String.
   * @return the passed in String @thePatternToReplace with all occurrences of @theString
   *         removed.
   */
  public static String removeFromString (String theString, String thePatternToReplace)
  {
    Pattern pattern = Pattern.compile(thePatternToReplace);
    Matcher matcher = pattern.matcher(theString);
    theString = matcher.replaceAll(EMPTY);
    
    // removes any double spaces
    pattern = Pattern.compile(DOUBLE_SPACE_REG_PATTERN);
    matcher = pattern.matcher(theString);
    theString = matcher.replaceAll(SPACE);
    
    
    return theString.trim();
  }
  
  /**
   * Checks to see if the passed in post code is valid.
   * 
   * @param postCode the post code to check.
   * @return true if valid false if not.
   */
  public static final boolean isValidPostCode(String postCode)
  {
    if (null == postCode)
    {
      return false;
    }
    
    Pattern pattern = Pattern.compile(REGULAR_EXP_VALID_POSTCODE);
    Matcher matcher = pattern.matcher(postCode);
    return matcher.matches();
  }
  
  /**
   * Checks to see if the passed in String contains a valid post code and if so return the post code.
   * 
   * @param address info or any other string in which to check.
   * @return the matched post code value OR NULL.
   */
  public static final String findValidPostCode(String postCode)
  {
    if (null == postCode)
    {
      return null;
    }
    Pattern pattern = Pattern.compile(REGULAR_EXP_VALID_POSTCODE);
    Matcher matcher = pattern.matcher(postCode);
    
    if (matcher.find()) {
    	return matcher.group();
    }
    return null;
  }

  /**
   * Converts feet to meters.
   * 
   * @param - Height entered as a String in Feet format (String height).
   * @return - Converted height String containing metres value (String convertedHeight).
   */
  public static String convertFeetToMetric(String heightFeet, String heightInches) 
  {
  	if ("0".equals(heightFeet))
  		return heightFeet;
  	
		double feet = Double.parseDouble(heightFeet);
		double inches = Double.parseDouble(heightInches);
		double feetIntoCM = (feet * 30.48) + (inches * 2.54);
		
		String centimetres = String.valueOf(feetIntoCM).replace(".", "");
		
		return centimetres.substring(0, 3); //Just return first 3 chars, could be done several ways like using double instead of float. 
  }
 
  /**
   * Converts Metric string value to feet and inches.
   * 
   * @param - Height entered as a String in metric format (String height).
   * @return - Converted height String containing feet value (String convertedHeight).
   */
  public static String convertMetricToFeet(String heightMetric) 
  {
  	if("0".equals(heightMetric))
  		return heightMetric;

    double lengthMetres = Double.parseDouble(heightMetric);  
    double heightInFeet = Math.floor(lengthMetres / 30.48);
    double heightInInches = (lengthMetres / 2.54);
    
    double inchesRemaining = heightInInches - (heightInFeet * 12);
    String heightConverted = String.valueOf(heightInFeet).substring(0, 1).concat(String.valueOf(inchesRemaining).substring(0, 2).replace(".", ""));
    
		return heightConverted;
  }

  /**
   * Converts the passed in string containing stone value to metric.
   * 
   * @param - Weight entered as a String in Stone format (String weight).
   * @return - Converted weight String containing metric value (String convertedWeight).
   */
  public static String convertStoneToMetric(String weight) 
  {
  	if("0".equals(weight))
  		return weight;
  	
  	double convertedWeightInMetric;
  	double lengthStone = Double.parseDouble(weight.substring(0,2));
  	if (weight.length()>2) {
      double lengthLbs = Double.parseDouble(weight.substring(2,weight.length()));
       convertedWeightInMetric = (lengthLbs * 0.45359 + lengthStone * 6.35026); 	
  	}
  	else {
      convertedWeightInMetric = (lengthStone * 6.35026); 
  	}
  	//No point returning 0.0 conversions as can handle it here to save calling class doing so,
  	if(0.0==convertedWeightInMetric)
  		return weight;

  	return String.valueOf(convertedWeightInMetric).substring(0, 4);
  }
  
  /**
   * Converts the passed in string containing Metric value to stone/lbs.
   * 
   * @param - Weight entered as a String in Metric format (String weight).
   * @return - Converted weight String containing Stone value (String convertedWeight).
   */
  public static String convertMetricToStone(String weightMetric) 
  {
  	if ("0".equals(weightMetric))
  		return weightMetric;

  	double convertedWeightInStone;
  	double convertedWeightInPounds;
  	String convertedWeight = null;
  	
  	double weightKG = Double.parseDouble(weightMetric);
  	convertedWeightInStone = (weightKG / 6.35026);
  	double floorWeightStone = Math.floor(convertedWeightInStone);
  	convertedWeightInPounds = (weightKG / 0.45359);
  	
  	double poundsRemaining = convertedWeightInPounds - (floorWeightStone * 14);
  	
  	double poundsRounded = Math.round(poundsRemaining);

  	if(String.valueOf(poundsRounded).endsWith("0")){
  	  
  	  String poundsSubString = String.valueOf(poundsRounded).substring(0, 1);
  	  
  	  if(poundsSubString.contains("1")) {
  	    convertedWeight = String.valueOf(floorWeightStone).substring(0, 2).concat(String.valueOf(poundsRounded).replace(".", "").substring(0, 2));
  	  }else {
  	    convertedWeight = String.valueOf(floorWeightStone).substring(0, 2).concat(String.valueOf(poundsRounded).replace(".", "").substring(0, 1));
  	  }
  	}
    return convertedWeight;

  }
  
  /**
   * Converts a String[] into a String. The elements are separated by
   * the passed in @param seperates.
   * 
   * @param strArrayToConvert The String[] to convert.
   * @param separates the elements in the array by this value.
   * @return A String representative of the passed in String[].
   */
  public static String convertStringArrayToString (String[] strArrayToConvert, String seperator)
  {
    StringBuffer vals = new StringBuffer();
    for (int i = 0; i < strArrayToConvert.length; i++)
    {
      if(!StringUtil.isEmpty(strArrayToConvert[i]))
      {
        if(vals.length() != 0)
        {
          vals.append(seperator);
        }
        vals.append(strArrayToConvert[i]);
      }
    }
    return vals.toString();
  }
}
