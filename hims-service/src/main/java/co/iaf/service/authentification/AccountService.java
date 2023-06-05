package co.iaf.service.authentification;

import java.util.List;

import co.iaf.entity.authentification.Role;
import co.iaf.entity.authentification.Utilisateur;

public interface AccountService {

	//add a new user
	Utilisateur addNewUtilisateur(Utilisateur utilisateur);
	
	//add a role
	Role addNewRole(Role role);
	
	//add role to a user
	void addRoleToUser(String username, String roleName);
	
	//return a user
	Utilisateur loadUserByUserName(String username);
	
	//return all users
	List<Utilisateur> listUtilisateurs();
}
