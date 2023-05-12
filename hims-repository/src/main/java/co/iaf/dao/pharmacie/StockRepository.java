package co.iaf.dao.pharmacie;

import org.springframework.data.jpa.repository.JpaRepository;

import co.iaf.entity.pharmacie.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {

}
