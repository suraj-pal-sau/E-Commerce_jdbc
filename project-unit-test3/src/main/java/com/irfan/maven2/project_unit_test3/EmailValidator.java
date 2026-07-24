package com.irfan.maven2.project_unit_test3;

public class EmailValidator
{
	public boolean isValidEmail(String email)
	{
		String regex = "^[a-zA-Z0-9._]+@gmail\\.com$";
		
		if (email == null)
			return false;

		email = email.trim();

		if (email.isEmpty())
			return false;

		if (email.length() > 254)
			return false;

		if (email.contains(" "))
			return false;

		if (email.startsWith("."))
			return false;

		if (email.endsWith("."))
			return false;

		if (email.contains(".."))
			return false;

		int firstAt = email.indexOf('@');
		int lastAt = email.lastIndexOf('@');

		if (firstAt == -1 || firstAt != lastAt)
			return false;

		String username = email.substring(0, firstAt);
		String domain = email.substring(firstAt + 1);

		if (username.isEmpty())
			return false;

		if (domain.isEmpty())
			return false;
		
		if (!domain.contains("."))
            return false;
		
		return email.matches(regex);
	}

}
