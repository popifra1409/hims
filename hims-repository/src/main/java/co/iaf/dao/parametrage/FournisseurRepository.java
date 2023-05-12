package co.iaf.dao.parametrage;

import org.springframework.data.jpa.repository.JpaRepository;

import co.iaf.entity.parametrage.Fournisseur;

public interface FournisseurRepository extends JpaRepository<Fournisseur, Long> {

}
