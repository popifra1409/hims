package co.iaf.dao.admission;

import org.springframework.data.jpa.repository.JpaRepository;

import co.iaf.entity.admission.Intervention;

public interface InterventionRepository extends JpaRepository<Intervention, Long>{

}
