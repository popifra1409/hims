package co.iaf.dao.pharmacie;

import org.springframework.data.jpa.repository.JpaRepository;

import co.iaf.entity.pharmacie.Inventaire;

public interface InventaireRepository extends JpaRepository<Inventaire, Long> {

}
