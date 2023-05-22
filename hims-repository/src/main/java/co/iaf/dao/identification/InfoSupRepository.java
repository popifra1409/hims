package co.iaf.dao.identification;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.iaf.entity.identification.InfosSup;
import co.iaf.entity.identification.Patient;

public interface InfoSupRepository extends JpaRepository<InfosSup, Long>{
	
	List<InfosSup> findByPatient(Patient patient);
}
