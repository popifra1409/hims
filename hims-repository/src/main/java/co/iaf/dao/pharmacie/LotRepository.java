package co.iaf.dao.pharmacie;

import org.springframework.data.jpa.repository.JpaRepository;

import co.iaf.entity.pharmacie.Lot;

public interface LotRepository extends JpaRepository<Lot, Long> {

}
