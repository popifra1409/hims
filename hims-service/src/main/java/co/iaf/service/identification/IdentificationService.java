package co.iaf.service.identification;

import java.util.Collection;
import java.util.List;

import co.iaf.entity.admission.PriseParametreSoin;
import co.iaf.entity.identification.GroupePatient;
import co.iaf.entity.identification.GroupeRegistration;
import co.iaf.entity.identification.InfosSup;
import co.iaf.entity.identification.Patient;
import co.iaf.entity.identification.QrCodePatient;

public interface IdentificationService {

	/* ============ Gestion des patient ============= */
	// créer patient
	public Patient addNewPatient(Patient patient);

	// mis à jour patient
	public Patient updatePatient(String patientId, Patient patient);

	// delete patient
	public void deletePatient(Patient patient);

	// recupérer un patient par son Id
	public Patient getPatientById(String patientId);

	// recuperer une collection de patients par leur ids
	public Collection<Patient> getPatientsByIds(List<String> patientIds);

	// recupérer la liste des tous les patients
	public List<Patient> getAllPatients();

	// fusionner les patients
	public void mergePatient(Patient patientPrincipal, Collection<Patient> patientsSecondaires);

	// Rechercher les patients par nom ou prénom
	public List<Patient> getPatientsByLastNameOrFirstName(String lastname, String firstname);

	// recuperer un patient par son QrCode
	public Patient getPatientByQrCode(QrCodePatient qrCodePatient);

	/* ============ Gestion du qrcode du patient ============= */

	// créer un qr code d'un patient
	public void addNewQrCodePatient(QrCodePatient qrCodePatient, String patientId);

	// recupérer un qrcode par son ID
	public QrCodePatient getQrCodePatientById(Long id);

	// recupérer le Qrcode d'un patient
	public QrCodePatient getQrCodeByPatient(Patient patient);

	/* ============ Gestion des infos supplementaires du patient ============= */
	// ajouter un info supplementaire au patient
	public InfosSup addNewInfoToPatient(InfosSup info, String patientId);

	// recupérer une info supplémentaire par son id
	public InfosSup getInfosSupById(Long infosSupId);

	// mettre à jour une infosup
	public InfosSup updateInfosSup(Long infoId, InfosSup info);

	// supprimer une coordonnée
	public void deleteInfosSup(InfosSup info);

	// liste des infos supplémentaires d'un patient
	public List<InfosSup> getAllInfosSup(String patientId);

	/* ============ Gestion des parametres de soin du patient ============= */

	// ajouter une liste des parametres de soins à un patient
	public Collection<PriseParametreSoin> addNewParamToPatient(Collection<PriseParametreSoin> params, String patientId);

	// liste des paramètres soins du patient
	public List<PriseParametreSoin> getParamSoinsByPatient(String patientId);

	/* ============ Gestion des groupes de patient ============= */

	// recupérer la liste des tous les patients
	public List<GroupePatient> getAllGroupesPatients();

	// créer un nouveau groupe de patients
	public GroupePatient addNewGroupePatient(GroupePatient groupePatient);

}
