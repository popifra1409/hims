package co.iaf.dao.identification;

import org.springframework.data.jpa.repository.JpaRepository;

import co.iaf.entity.identification.GroupePatient;

public interface GroupePatientRepository extends JpaRepository<GroupePatient, Long>{
}
