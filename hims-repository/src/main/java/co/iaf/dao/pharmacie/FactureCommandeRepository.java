package co.iaf.dao.pharmacie;

import org.springframework.data.jpa.repository.JpaRepository;

import co.iaf.entity.pharmacie.FactureCommande;

public interface FactureCommandeRepository extends JpaRepository<FactureCommande, Long> {

}
