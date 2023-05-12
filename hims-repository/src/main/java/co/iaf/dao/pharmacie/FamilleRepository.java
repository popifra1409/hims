package co.iaf.dao.pharmacie;

import org.springframework.data.jpa.repository.JpaRepository;

import co.iaf.entity.pharmacie.Famille;

public interface FamilleRepository extends JpaRepository<Famille, Long> {

}
