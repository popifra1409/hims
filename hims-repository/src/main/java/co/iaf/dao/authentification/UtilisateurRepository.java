package co.iaf.dao.authentification;

import org.springframework.data.jpa.repository.JpaRepository;

import co.iaf.entity.authentification.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long>{
	
	//find user by username 
	 Utilisateur findByUsername(String username);
}
