package co.iaf.dao.pharmacie;

import org.springframework.data.jpa.repository.JpaRepository;

import co.iaf.entity.pharmacie.Livraison;

public interface LivraisonRepository extends JpaRepository<Livraison, Long> {

}
