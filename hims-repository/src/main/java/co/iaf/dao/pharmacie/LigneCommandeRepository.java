package co.iaf.dao.pharmacie;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.iaf.entity.pharmacie.LigneCommande;

public interface LigneCommandeRepository extends JpaRepository<LigneCommande, Long> {

	//liste des commandes par date
	List<LigneCommande>findByDateCde(Date dateCde);
}
