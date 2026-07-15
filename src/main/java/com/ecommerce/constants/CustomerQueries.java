<<<<<<< HEAD

package com.ecommerce.constants;

public class CustomerQueries {
	
	private CustomerQueries() {
		
	}
=======
package com.ecommerce.constants;

public class CustomerQueries {
>>>>>>> 0c28ce0c5a9f54c97c06002d7b6dbb8b6849e914

	public String userSignUpQuery = "INSERT INTO User(user_id, username, email, password, created_at, role) values(?,?,?,?,?,?)";

	public String userLoginQuery = "SELECT * FROM User";

}