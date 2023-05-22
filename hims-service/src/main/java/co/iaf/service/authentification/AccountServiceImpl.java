package co.iaf.service.authentification;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import co.iaf.dao.authentification.RoleRepository;
import co.iaf.dao.authentification.UtilisateurRepository;
import co.iaf.entity.authentification.Role;
import co.iaf.entity.authentification.Utilisateur;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	private UtilisateurRepository utilisateurRepo;
	private RoleRepository roleRepo;
	private PasswordEncoder passwordEncoder;

	//constructeur qui reçoit les 2 interface. Bcp mieux que l'injection par @Autowired
	public AccountServiceImpl(UtilisateurRepository utilisateurRepo, RoleRepository roleRepo,
			PasswordEncoder passwordEncoder) {
		this.utilisateurRepo = utilisateurRepo;
		this.roleRepo = roleRepo;
		this.passwordEncoder = passwordEncoder;
	}

	public Utilisateur addNewUtilisateur(Utilisateur utilisateur) {
		//on recupère le mot de passe saisi par l'utilisateur
		String pw = utilisateur.getPassword();
		//encodage du mot de passe
		utilisateur.setPassword(passwordEncoder.encode(pw));
		return utilisateurRepo.save(utilisateur);
	}

	@Override
	public Role addNewRole(Role role) {
		return roleRepo.save(role);
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
		//on recupere d'abord l'utilisateur de la base de données
		Utilisateur utilisateur = utilisateurRepo.findByUsername(username);
		//on recupere le role à affecter à l'utilisateur
		Role role = roleRepo.findByRoleName(roleName);
		//on ajoute le role à la collection des roles de l'utilisateur
		utilisateur.getRoles().add(role);
	}

	@Override
	public Utilisateur loadUserByUserName(String username) {
		return utilisateurRepo.findByUsername(username);
	}

	@Override
	public List<Utilisateur> listUtilisateurs() {
		return utilisateurRepo.findAll();
	}

}
