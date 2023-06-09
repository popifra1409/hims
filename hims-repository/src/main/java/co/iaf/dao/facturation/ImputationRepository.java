package co.iaf.dao.facturation;

import org.springframework.data.jpa.repository.JpaRepository;

import co.iaf.entity.facturation.Imputation;

public interface ImputationRepository extends JpaRepository<Imputation, Long> {

}
