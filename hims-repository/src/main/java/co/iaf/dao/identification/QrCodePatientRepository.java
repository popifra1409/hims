package co.iaf.dao.identification;

import org.springframework.data.jpa.repository.JpaRepository;

import co.iaf.entity.identification.Patient;
import co.iaf.entity.identification.QrCodePatient;

public interface QrCodePatientRepository extends JpaRepository<QrCodePatient, Long>{

	//Recuperer le QrCode d'un patient
	QrCodePatient findByPatient(Patient patient);
}
