package co.iaf.dao.pharmacie;

import org.springframework.data.jpa.repository.JpaRepository;

import co.iaf.entity.pharmacie.MouvementStock;

public interface MouvementStockRepository extends JpaRepository<MouvementStock, Long> {

}
