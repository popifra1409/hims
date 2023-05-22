package co.iaf.dao.pharmacie;

import org.springframework.data.jpa.repository.JpaRepository;

import co.iaf.entity.pharmacie.LigneInventaire;

public interface LignInventaireRepository extends JpaRepository<LigneInventaire, Long> {

}
