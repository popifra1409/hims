package co.iaf.dao.pharmacie;

import org.springframework.data.jpa.repository.JpaRepository;

import co.iaf.entity.pharmacie.Emplacement;

public interface EmplacementRepository extends JpaRepository<Emplacement, Long> {

}
