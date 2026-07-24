package com.irfan.maven2.project_unit_test2;

public class EmailValidator
{
	public boolean isEmailValid(String email)
	{
		/**
		 * // Check if ends with ".pdf" or ".docx"
        	Pattern pattern = Pattern.compile(".*\\.(pdf|docx)$", Pattern.CASE_INSENSITIVE);
        	Matcher matcher = pattern.matcher(text);
        	if(matcher.matches())
        
        	

        // Regex to check if string ends with digits
        String regex = ".*\\d+$"; // .* = any chars, \\d+ = one or more digits, $ = end of string
        	if(text.matches(regex))
		 */
		
		String regex = "^[a-zA-Z0-9._]+@gmail\\.com$";

		if (email == null) {
            return false;
        }


		return email.matches(regex);
	}

}
