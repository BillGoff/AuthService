package snaplogic.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Standard home controller.  Can be used to force logins.
 * @author bgoff
 * @since 5 Aug 2024
 */
@RestController
public class HomeController {

	/**
	 * UnSecured login.
	 * @return String the unsecure login greating.
	 */
	@GetMapping("/home")
	public String home() {
		return "hello, home";
	}
	
	/**
	 * Secured login.
	 * @return String the secure login greating.
	 */
	@GetMapping("/secured")
	public String secured() {
		return "hello, secured!";
	}
}
