package co.iaf.service.identification;

import java.util.Collection;
import java.util.List;

import co.iaf.entity.admission.PriseParametreSoin;
import co.iaf.entity.identification.GroupePatient;
import co.iaf.entity.identification.InfosSup;
import co.iaf.entity.identification.Patient;

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

	// recupérer la liste des tous les patients
	public List<Patient> getAllPatients();

	// fusionner les patients
	public Patient mergePatient(String patientPrincipalid, Collection<Long> patientsSecondaireId);

	// Rechercher les patients par nom ou prénom
	public List<Patient> getPatientsByLastNameOrFirstName(String lastname, String firstname);

	// rechercher un patient par codebarre
	public Patient getPatientByBarCode(String codebarre);

	// generer le QrCode du patient
	public String getQRCode(Patient patient);

	// generate Barcode du patient
	public String getBarCode(Patient patient);

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
	public List<InfosSup> getAllInfosSup(Patient patient);

	/* ============ Gestion des parametres de soin du patient ============= */
	// ajouter une liste des parametres de soins à un patient
	public Collection<PriseParametreSoin> addNewParamToPatient(Collection<PriseParametreSoin> params, String patientId);

	// liste des paramètres soins du patient
	public List<PriseParametreSoin> getParamSoinsByPatient(String patientId);

	/* ============ Gestion des groupes de patient ============= */
	// recupérer la liste des tous les patients
	public List<GroupePatient> getAllGroupesPatients();

}
