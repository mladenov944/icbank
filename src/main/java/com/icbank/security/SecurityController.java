package com.icbank.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController("securityController")
@RequestMapping("/security")
public class SecurityController {

	@Autowired
	private SecurityService securityService;

//	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@RequestMapping(value = "/register", method = RequestMethod.POST, params = { "username", "password" })
	public ResponseEntity<SecurityResponseDTO> registerUser(String firstName, String secondName, String email,
			String surname, String username, String password) {
		try {
			securityService.register(firstName, secondName, email, surname, username, password);
			return new ResponseEntity<SecurityResponseDTO>(new SecurityResponseDTO("User Created"), HttpStatus.OK);
		} catch (Throwable e) {
			e.printStackTrace();
			return new ResponseEntity<SecurityResponseDTO>(new SecurityResponseDTO(e),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST, params = { "username", "password" })
	public ResponseEntity<SecurityResponseDTO> changePassword(String username, String password) {
		try {
			securityService.changePassword(username, password);
			return new ResponseEntity<SecurityResponseDTO>(new SecurityResponseDTO("Password Changed"), HttpStatus.OK);
		} catch (Throwable e) {
			e.printStackTrace();
			return new ResponseEntity<SecurityResponseDTO>(new SecurityResponseDTO(e),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//	@PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
//	@RequestMapping(value = "/listUsers", method = RequestMethod.GET)
//	public ResponseEntity<List<UserDTO>> listUsers() {
//		return convertToDtoList(securityService.list());
//	}
}
