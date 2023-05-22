package co.iaf.dao.identification;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.iaf.entity.identification.Patient;

public interface PatientRepository extends JpaRepository<Patient, String> {

	// get patients by last or firstname
	List<Patient> findByPatientLastNameContainingOrPatientFirstNameContaining(String lastname, String firstname);

	// get patient by barcode
	Patient findByPatientBarCode(String codebarre);
}
