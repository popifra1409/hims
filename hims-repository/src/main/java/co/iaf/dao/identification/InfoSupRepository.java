package co.iaf.dao.identification;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.iaf.entity.identification.InfosSup;

public interface InfoSupRepository extends JpaRepository<InfosSup, Long>{
	
	 List<InfosSup> findByPatientPatientId(String patientNip);
}
