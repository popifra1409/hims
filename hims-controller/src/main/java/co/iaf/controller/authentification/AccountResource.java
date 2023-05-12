package co.iaf.controller.authentification;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.iaf.entity.authentification.Role;
import co.iaf.entity.authentification.Utilisateur;
import co.iaf.payloads.authentification.RoleUser;
import co.iaf.service.authentification.AccountService;

@RestController
@CrossOrigin(origins = "*")
@Transactional
@RequestMapping("/hims")
public class AccountResource {

	private AccountService accountService;

	public AccountResource(AccountService accountService) {
		this.accountService = accountService;
	}

	// list of users
	@GetMapping(path = "/users/all")
	public ResponseEntity<?> listusers() {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<Utilisateur> listusers = accountService.listUtilisateurs();
		if (!listusers.isEmpty()) {
			map.put("status", 1);
			map.put("data", listusers);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} else {
			map.clear();
			map.put("status", 0);
			map.put("message", "Aucune information trouvée");
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	// save user
	@PostMapping(path = "/users")
	public ResponseEntity<?> saveUser(@RequestBody Utilisateur utilisateur) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		accountService.addNewUtilisateur(utilisateur);
		map.put("status", 1);
		map.put("data", "Utilisateur enregistré avec succès");
		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}

	// save role
	@PostMapping(path = "/roles")
	public Role saveRole(@RequestBody Role role) {
		return accountService.addNewRole(role);
	}

	// add role to user
	@PostMapping(path = "/roleToUser")
	public void addRoleToUser(@RequestBody RoleUser roleUser) {
		accountService.addRoleToUser(roleUser.getUserName(), roleUser.getRoleName());
	}
}
