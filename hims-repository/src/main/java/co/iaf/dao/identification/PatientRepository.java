package co.iaf.dao.identification;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.iaf.entity.identification.Patient;
import co.iaf.entity.identification.QrCodePatient;

public interface PatientRepository extends JpaRepository<Patient, String> {

	// get patients by last or firstname
	List<Patient> findByPatientLastNameContainingOrPatientFirstNameContaining(String lastname, String firstname);

	// get patient by qrcode
	Patient findByQrCodePatient(QrCodePatient qrcode);
}
