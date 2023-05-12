package co.iaf.service.admission;

import java.util.Date;
import java.util.List;

import co.iaf.entity.admission.Batiment;
import co.iaf.entity.admission.Chambre;
import co.iaf.entity.admission.Hospitalisation;
import co.iaf.entity.admission.Lit;
import co.iaf.entity.admission.ParametreDeSoin;
import co.iaf.entity.admission.PriseParametreSoin;
import co.iaf.entity.admission.Sejour;
import co.iaf.entity.admission.TransfertHospit;
import co.iaf.entity.parametrage.Services;

public interface AdmissionService {

	/* ============ GESTION DES BATIMENTS ============= */

	// créer d'un nouveau batiment
	public Batiment addNewBatiment(Batiment batiment);

	// mis à jour d'un batiment
	public Batiment updateBatiment(Batiment batiment, Long batimentId);

	// suppression d'un batiment
	public void deleteBatiment(Long batimentId);

	// recupérer un batiment par son id
	public Batiment getBatimentById(Long batimentId);

	// recupérer tous les batiments
	public List<Batiment> getAllBatiments();

	/* ============ GESTION DES CHAMBRES ============= */
	// créer une nouvelle chambre
	public Chambre addNewChambre(Chambre chambre, Long batimentId);

	// mis à jour d'une chambre
	public Chambre updateChambre(Chambre chambre, Long chambreId);

	// suppression d'une chambre
	public void deleteChambre(Long chambreId);

	// recupérer une chambre par son Id
	public Chambre getChambreById(Long chambreId);

	// recupérer toutes les chambres
	public List<Chambre> getAllChambres();

	/* ============ GESTION DES LITS ============= */
	// créer un nouveau lit
	public Lit addNewLit(Lit lit, Long chambreId);

	// mis à jour d'un lit
	public Lit updateLit(Lit lit, Long litId);

	// suppression d'un lit
	public void deleteLit(Long litId);

	// recupérer un lit par son Id
	public Lit getLitById(Long litId);

	// recupérer tous les lits
	public List<Lit> getAllLits();

	/* ============ GESTION DES PARAMETRES DE SOIN ============= */
	// créer un paramètre de premier soin
	public ParametreDeSoin addNewParametreDeSoin(ParametreDeSoin parametreDeSoin);

	// mis à jour d'un paramètre de soin
	public ParametreDeSoin updateParametreDeSoin(ParametreDeSoin parametreDeSoin, Long parametreDeSoinId);

	// suppression d'un paramètre de soin
	public void deleteParametreDeSoin(Long parametreDeSoinId);

	// recupérer un paramètre de soin par son Id
	public ParametreDeSoin getParametreDeSoinById(Long parametreDeSoinId);

	// recupérer tous les paramètres de soin
	public List<ParametreDeSoin> getAllParametreDeSoins();

	/* ============ GESTION DES PRISES DES PARAMETRES DES SOINS ============= */

	// créer un paramètre de premier soin
	public PriseParametreSoin addNewPriseParametreSoin(PriseParametreSoin priseParametreSoin, String patientId);

	// mis à jour d'un parametre de soin
	public PriseParametreSoin updatePriseParametreSoin(PriseParametreSoin priseParametreSoin, Long priseParametreSoinId);

	// suppression d'un parametre de soin
	public void deletePriseParametreSoin(Long priseParametreSoinId);

	// recupérer un parametre de soin
	public PriseParametreSoin getPriseParametreSoinById(Long priseParametreSoinId);

	// recupérer toutes les prises de parametres
	public List<PriseParametreSoin> getAllPriseParametreSoins();
	
	
	
	/* ============ GESTION DES SEJOURS ============= */
	// créer un nouveau sejour
	public Sejour addNewSejour(Sejour sejour);

	// mis à jour d'un sejour
	public Sejour updateSejour(Sejour sejour, Long sejourId);

	// suppression d'un sejour
	public void deleteSejour(Long sejourId);

	// recupérer un sejour par son Id
	public Sejour getSejourById(Long sejourId);

	// recupérer tous les sejours
	public List<Sejour> getAllSejours();
	
	

	/* ============ GESTION DES HOSPITALISATIONS ============= */

	// hospitaliser un patient
	public Hospitalisation addNewHospit(Hospitalisation hospit, Long sejourId, Long serviceId, String patientId,
			Long litId);

	// modifier une hospitalisation
	public Hospitalisation updateHospit(Long hospitId, Hospitalisation hospit);

	// rechercher une hospitalisation par son code
	public Hospitalisation getHospitByCode(String codeHospit);

	// rechercher une hospitalisation par son id
	public Hospitalisation getHospitById(Long hospiId);
	
	// supprimer une hospitalisation
	public void deleteHospit(Long hospitId);

	// liste des hospitalisations
	public List<Hospitalisation> getAllHospits();

	// liste des hospitalisation d'un sejour
	public List<Hospitalisation> getHospitsBySejour(String codeSejour);

	// rechecher les hopitalisations date d'entree
	public List<Hospitalisation> getHospitsByDateEntree(Date dateEntree);

	// rechecher les hopitalisations date de sortie
	public List<Hospitalisation> getHospitsByDateSortie(Date dateSortie);

	// les hospitalisations d'un séjour
	public List<Hospitalisation> getHospitsBySejour(Sejour sejour);

	// les hospitalisation en cours dans un service
	public List<Hospitalisation> getHospitsEncoursByService(Services service);

	// liste des hospitalisations cloturées
	public List<Hospitalisation> getClosedHospits();

	// liste des hospitalisations non cloturées
	public List<Hospitalisation> getUnClosedHospits();

	// créer un avis d'hospitalisation
	public Hospitalisation sendAvisHospit(Hospitalisation hospit, Long sejourId, Long serviceId, String patientId);

	// liste des hospitalisations en avis
	public List<Hospitalisation> getAvisHospits();

	// transformer un avis d'hospitalisation en hospitalisation
	public Hospitalisation avisToHospit(Long hospitId, Hospitalisation hospit);

	// annuler un avis d'hospitalisation
	public void deleteAvisHospit(Long hospitId);

	// transfert d'hospitalisation
	public Hospitalisation transfertHospit(TransfertHospit transfert, Long hospitId, Long serviceId);

	// annuler un transfert d'hospit
	public void deleteTransfertHospit(Long transfertHospitId);

}
