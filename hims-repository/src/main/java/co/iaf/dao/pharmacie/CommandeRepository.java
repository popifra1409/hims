package co.iaf.dao.pharmacie;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.iaf.entity.pharmacie.Commande;

public interface CommandeRepository extends JpaRepository<Commande, Long> {

	// liste des commandes transmises
	List<Commande> findByIsTransmisTrue();

	// liste des commandes livrées
	List<Commande> findByIsLivreTrue();
}
