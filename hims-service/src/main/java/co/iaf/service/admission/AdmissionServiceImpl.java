package co.iaf.service.admission;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import co.iaf.dao.admission.BatimentRepository;
import co.iaf.dao.admission.ChambreRepository;
import co.iaf.dao.admission.HospitalisationRepository;
import co.iaf.dao.admission.LitRepository;
import co.iaf.dao.admission.ParametreDeSoinRepository;
import co.iaf.dao.admission.PriseParametreSoinRepository;
import co.iaf.dao.admission.SejourRepository;
import co.iaf.dao.admission.TransfertHospitRepository;
import co.iaf.dao.exceptions.ResourceNotFoundException;
import co.iaf.dao.identification.PatientRepository;
import co.iaf.entity.admission.Batiment;
import co.iaf.entity.admission.Chambre;
import co.iaf.entity.admission.Hospitalisation;
import co.iaf.entity.admission.Lit;
import co.iaf.entity.admission.ParametreDeSoin;
import co.iaf.entity.admission.PriseParametreSoin;
import co.iaf.entity.admission.Sejour;
import co.iaf.entity.admission.TransfertHospit;
import co.iaf.entity.identification.Patient;
import co.iaf.entity.parametrage.ServiceAttache;
import co.iaf.service.identification.IdentificationService;
import co.iaf.service.parametrage.ParametrageService;

@Service
@Transactional
public class AdmissionServiceImpl implements AdmissionService {

	private BatimentRepository batimentRepository;
	private ChambreRepository chambreRepository;
	private LitRepository litRepository;
	private ParametreDeSoinRepository parametreDeSoinRepository;
	private PriseParametreSoinRepository priseParametreSoinRepository;
	private PatientRepository patientRepository;
	private IdentificationService identificationService;
	private SejourRepository sejourRepository;
	private ParametrageService parametrageService;
	private HospitalisationRepository hospitalisationRepository;
	private TransfertHospitRepository transfertRespository;

	public AdmissionServiceImpl(BatimentRepository batimentRepository, ChambreRepository chambreRepository,
			LitRepository litRepository, ParametreDeSoinRepository parametreDeSoinRepository,
			PriseParametreSoinRepository priseParametreSoinRepository, IdentificationService identificationService,
			SejourRepository sejourRepository, ParametrageService parametrageService,
			HospitalisationRepository hospitalisationRepository, TransfertHospitRepository transfertRespository) {
		this.batimentRepository = batimentRepository;
		this.chambreRepository = chambreRepository;
		this.litRepository = litRepository;
		this.parametreDeSoinRepository = parametreDeSoinRepository;
		this.priseParametreSoinRepository = priseParametreSoinRepository;
		this.identificationService = identificationService;
		this.sejourRepository = sejourRepository;
		this.parametrageService = parametrageService;
		this.hospitalisationRepository = hospitalisationRepository;
		this.transfertRespository = transfertRespository;
	}

	/* ============ GESTION DES BATIMENTS ============= */

	// créer d'un nouveau batiment
	public Batiment addNewBatiment(Batiment batiment) {
		Collection<Chambre> chambres = batiment.getChambres();
		Collection<ServiceAttache> services = batiment.getServiceAttache();

		Batiment newBatiment = this.batimentRepository.save(batiment);
		// s'il existe des chambres associées
		if (chambres != null && !chambres.isEmpty()) {
			for (Chambre chambre : chambres) {
				chambre.setBatiment(newBatiment);
			}
			this.chambreRepository.saveAll(chambres);
		}

		if (services != null && !services.isEmpty()) {
			for (ServiceAttache service : services) {
				service.setBatiment(newBatiment);
			}
		}
		return newBatiment;
	}

	// mis à jour d'un batiment
	public Batiment updateBatiment(Batiment batiment, Long batimentId) {
		if (getBatimentById(batimentId) != null) {
			batiment.setId(batimentId);
			return batimentRepository.save(batiment);
		}
		return null;
	}

	// suppression d'un batiment
	public void deleteBatiment(Long batimentId) {
		batimentRepository.deleteById(batimentId);
	}

	// recupérer un batiment par son id
	public Batiment getBatimentById(Long batimentId) {
		return batimentRepository.findById(batimentId)
				.orElseThrow(() -> new ResourceNotFoundException("Batiment", "Batiment Id", 0));
	}

	// recupérer tous les batiments
	public List<Batiment> getAllBatiments() {
		return batimentRepository.findAll();
	}

	/* ============ GESTION DES CHAMBRES ============= */
	// créer une nouvelle chambre
	public Chambre addNewChambre(Chambre chambre, Long batimentId) {
		// récupération du bâtiment concerné
		Batiment batiment = getBatimentById(batimentId);
		// si le bâtiment existe
		if (chambre != null && batiment != null) {
			chambre.setBatiment(batiment);
			Chambre chambreSave = chambreRepository.save(chambre);
			Collection<Lit> lits = chambre.getLits();
			// s'il existe des lits associées
			if (lits != null) {
				lits.forEach(lit -> {
					litRepository.save(new Lit(null, lit.getLibelle(), lit.getDescription(), chambre, null));
				});
			}
			return chambreSave;
		}
		return null;
	}

	// mis à jour d'une chambre
	public Chambre updateChambre(Chambre chambre, Long chambreId) {
		// si le la chambre existe
		if (chambre != null && getChambreById(chambreId) != null) {
			chambre.setId(chambreId);
			return chambreRepository.save(chambre);
		}
		return null;
	}

	// suppression d'une chambre
	public void deleteChambre(Long chambreId) {
		chambreRepository.deleteById(chambreId);
	}

	// recupérer une chambre par son Id
	public Chambre getChambreById(Long chambreId) {
		return chambreRepository.findById(chambreId)
				.orElseThrow(() -> new ResourceNotFoundException("Chambre", "Chambre Id", 0));
	}

	// recupérer toutes les chambres
	public List<Chambre> getAllChambres() {
		return chambreRepository.findAll();
	}

	/* ============ GESTION DES LITS ============= */
	// créer un nouveau lit
	public Lit addNewLit(Lit lit, Long chambreId) {
		// récupération du bâtiment concerné
		Chambre chambre = getChambreById(chambreId);
		if (lit != null && chambre != null) {
			lit.setChambre(chambre);
			return litRepository.save(lit);
		}
		return null;
	}

	// mis à jour d'un lit
	public Lit updateLit(Lit lit, Long litId) {
		// si le le lit existe
		if (lit != null && getLitById(litId) != null) {
			lit.setId(litId);
			return litRepository.save(lit);
		}
		return null;
	}

	// suppression d'un lit
	public void deleteLit(Long litId) {
		litRepository.deleteById(litId);
	}

	// recupérer un lit par son Id
	public Lit getLitById(Long litId) {
		return litRepository.findById(litId).orElseThrow(() -> new ResourceNotFoundException("Lit", "Lit Id", 0));
	}

	// recupérer tous les lits
	public List<Lit> getAllLits() {
		return litRepository.findAll();
	}

	/* ============ GESTION DES PARAMETRES DE SOIN ============= */
	// créer un paramètre de premier soin
	public ParametreDeSoin addNewParametreDeSoin(ParametreDeSoin parametreDeSoin) {
		// si les paramètres existent
		if (parametreDeSoin != null)
			return parametreDeSoinRepository.save(parametreDeSoin);
		return null;
	}

	// mis à jour d'un paramètre de soin
	public ParametreDeSoin updateParametreDeSoin(ParametreDeSoin parametreDeSoin, Long parametreDeSoinId) {
		if (getParametreDeSoinById(parametreDeSoinId) != null) {
			parametreDeSoin.setId(parametreDeSoinId);
			return parametreDeSoinRepository.save(parametreDeSoin);
		}
		return null;
	}

	// suppression d'un paramètre de soin
	public void deleteParametreDeSoin(Long parametreDeSoinId) {
		parametreDeSoinRepository.deleteById(parametreDeSoinId);
	}

	// recupérer un paramètre de soin par son Id
	public ParametreDeSoin getParametreDeSoinById(Long parametreDeSoinId) {
		return parametreDeSoinRepository.findById(parametreDeSoinId)
				.orElseThrow(() -> new ResourceNotFoundException("ParametreDeSoin", "ParametreDeSoin Id", 0));
	}

	// recupérer tous les paramètres de soin
	public List<ParametreDeSoin> getAllParametreDeSoins() {
		return parametreDeSoinRepository.findAll();
	}

	/* ============ GESTION DES PRISES DES PARAMETRES DES SOINS ============= */

	// créer une prise de paramètre de premier soin
	public PriseParametreSoin addNewPriseParametreSoin(PriseParametreSoin priseParametreSoin, String patientId) {
		// récupération du patient concerné
		Patient patient = identificationService.getPatientById(patientId);
		// si le patient existe et si la prise de soin existe
		if (priseParametreSoin != null && patient != null) {
			priseParametreSoin.setPatient(patient);
			return priseParametreSoinRepository.save(priseParametreSoin);
		}
		return null;
	}

	// mis à jour d'une prise de paramètre de soins
	public PriseParametreSoin updatePriseParametreSoin(PriseParametreSoin priseParametreSoin,
			Long priseParametreSoinId) {
		/*
		 * // si la prise de soins existent existe if (priseParametreSoin != null &&
		 * priseParametreSoin != null && getPriseParametreSoinById(priseParametreSoinId)
		 * != null) { priseParametreSoin.setId(priseParametreSoinId); return
		 * priseParametreSoinRepository.save(priseParametreSoin); }
		 */
		return null;
	}

	// suppression d'une prise de paramètre de soins
	public void deletePriseParametreSoin(Long priseParametreSoinId) {
		priseParametreSoinRepository.deleteById(priseParametreSoinId);
	}

	// recupérer une prise de paramètre de soins
	public PriseParametreSoin getPriseParametreSoinById(Long priseParametreSoinId) {
		return priseParametreSoinRepository.findById(priseParametreSoinId)
				.orElseThrow(() -> new ResourceNotFoundException("PriseParametreSoin", "PriseParametreSoin Id", 0));
	}

	// recupérer toutes les prise de paramètre de soins
	public List<PriseParametreSoin> getAllPriseParametreSoins() {
		return priseParametreSoinRepository.findAll();
	}

	/* ============ GESTION DES SEJOURS ============= */
	// créer un nouveau sejour
	public Sejour addNewSejour(Sejour sejour) {
		return sejourRepository.save(sejour);
	}

	// mis à jour d'un sejour
	public Sejour updateSejour(Sejour sejour, Long sejourId) {
		if (getSejourById(sejourId) != null) {
			sejour.setId(sejourId);
			return sejourRepository.save(sejour);
		}
		return null;
	}

	// suppression d'un sejour
	public void deleteSejour(Long sejourId) {
		sejourRepository.deleteById(sejourId);
	}

	// recupérer un sejour par son Id
	public Sejour getSejourById(Long sejourId) {
		return sejourRepository.findById(sejourId)
				.orElseThrow(() -> new ResourceNotFoundException("Sejour", "Sejour Id", 0));
	}

	// recupérer un sejour par son code
	public Sejour getSejourByCode(String codeSejour) {
		return sejourRepository.findSejourByCodeSejour(codeSejour);
	}

	// recupérer tous les sejours
	public List<Sejour> getAllSejours() {
		return sejourRepository.findAll();
	}

	/* ============ GESTION DES HOSPITALISATIONS ============= */

	// Ajout d'une nouvelle hospitalisation
	@Override
	public Hospitalisation addNewHospit(Hospitalisation hospit, Long sejourId, Long serviceId, String patientId,
			Long litId) {
		/*
		 * // récupération du séjour concerné Sejour sejour = getSejourById(sejourId);
		 * // récupération du service concerné co.iaf.entity.parametrage.Services
		 * serviceAccueil = parametrageService.getServiceById(serviceId); //
		 * récupération du patient concerné Patient patient =
		 * identificationService.getPatientById(patientId); // récupération du lit
		 * concerné Lit lit = getLitById(litId);
		 * 
		 * // si le séjour, le service, le patient, le lit existent if (sejour != null
		 * && serviceAccueil != null && patient != null && lit != null) {
		 * hospit.setSejour(sejour); hospit.setPatient(patient);
		 * hospit.setServiceAccueil(serviceAccueil); hospit.setLit(lit); return
		 * hospitalisationRepository.save(hospit); }
		 */
		return null;
	}

	// mise à jour d'une hospitalisation
	@Override
	public Hospitalisation updateHospit(Long hospitId, Hospitalisation hospit) {
		// récupération de l'hospitablisation concernée à partir de son id
		Hospitalisation hospitUpdate = getHospitById(hospitId);
		if ((hospitUpdate) != null) {
			hospit.setId(hospitId);
			return hospitalisationRepository.save(hospit);
		}
		return null;
	}

	// rechercher une hospitalisation par son id
	public Hospitalisation getHospitById(Long hospitId) {
		return hospitalisationRepository.findById(hospitId)
				.orElseThrow(() -> new ResourceNotFoundException("Hospitalisation", "Hospitalisation Id", 0));
	}

	@Override
	public Hospitalisation getHospitByCode(String codeHospit) {
		return hospitalisationRepository.findByCodeHospit(codeHospit);
	}

	@Override
	public void deleteHospit(Long hospitId) {
		hospitalisationRepository.deleteById(hospitId);

	}

	@Override
	public List<Hospitalisation> getAllHospits() {
		return hospitalisationRepository.findAll();
	}

	@Override
	public List<Hospitalisation> getHospitsBySejour(String codeSejour) {
		return hospitalisationRepository.findBySejour(getSejourByCode(codeSejour));
	}

	@Override
	public List<Hospitalisation> getHospitsByDateEntree(Date dateEntree) {
		return hospitalisationRepository.findByDateEntree(dateEntree);
	}

	@Override
	public List<Hospitalisation> getHospitsByDateSortie(Date dateSortie) {
		return hospitalisationRepository.findByDateSortie(dateSortie);
	}

	@Override
	public List<Hospitalisation> getHospitsBySejour(Sejour sejour) {
		return hospitalisationRepository.findBySejour(sejour);
	}

	@Override
	public List<Hospitalisation> getHospitsEncoursByService(co.iaf.entity.parametrage.Services service) {
		return hospitalisationRepository.findByServiceAccueilAndIsHospitClotureFalse(service);
	}

	@Override
	public List<Hospitalisation> getClosedHospits() {
		return hospitalisationRepository.findByIsHospitClotureTrue();
	}

	@Override
	public List<Hospitalisation> getUnClosedHospits() {
		return hospitalisationRepository.findByIsHospitClotureFalse();
	}

	@Override
	public Hospitalisation sendAvisHospit(Hospitalisation hospit, Long sejourId, Long serviceId, String patientId) {
		/*
		 * // récupération du séjour concerné Sejour sejour = getSejourById(sejourId);
		 * // récupération du service concerné co.iaf.entity.parametrage.Services
		 * serviceAccueil = parametrageService.getServiceById(serviceId); //
		 * récupération du patient concerné Patient patient =
		 * identificationService.getPatientById(patientId);
		 * 
		 * // si le bâtiment existe if (sejour != null && serviceAccueil != null &&
		 * patient != null) { hospit.setSejour(sejour); hospit.setPatient(patient);
		 * hospit.setServiceAccueil(serviceAccueil); hospit.setAvisHospit(true); return
		 * hospitalisationRepository.save(hospit); }
		 */
		return null;
	}

	@Override
	public List<Hospitalisation> getAvisHospits() {
		return hospitalisationRepository.findByIsAvisHospitTrue();
	}

	@Override
	public Hospitalisation avisToHospit(Long hospitId, Hospitalisation hospit) {
		// récupération de l'hospitablisation concernée à partir de son id
		Hospitalisation hospitUpdate = getHospitById(hospitId);
		if ((hospitUpdate) != null) {
			hospit.setId(hospitId);
			hospit.setAvisHospit(false);
			return hospitalisationRepository.save(hospit);
		}
		return null;
	}

	@Override
	public void deleteAvisHospit(Long hospitId) {
		Hospitalisation hospit = hospitalisationRepository.findById(hospitId)
				.orElseThrow(() -> new ResourceNotFoundException("Hospitalisation", "Hospitalisation Id", 0));
		// vérifie s'il s'agit d'un avis d'hospitalisation
		if (hospit.isAvisHospit())
			hospitalisationRepository.deleteById(hospitId);
	}

	@Override
	public Hospitalisation transfertHospit(TransfertHospit transfert, Long hospitId, Long serviceId) {
		/*
		 * Hospitalisation hospitalisation =
		 * hospitalisationRepository.findById(hospitId) .orElseThrow(() -> new
		 * ResourceNotFoundException("Hospitalisation", "Hospitalisation Id", 0));
		 * co.iaf.entity.parametrage.Services service =
		 * parametrageService.getServiceById(serviceId); if (hospitalisation != null &&
		 * service != null) { transfert.setHospit(hospitalisation);
		 * transfert.setService(service); transfertRespository.save(transfert); return
		 * hospitalisation; }
		 */
		return null;
	}

	@Override
	public void deleteTransfertHospit(Long transfertHospitId) {
		transfertRespository.deleteById(transfertHospitId);

	}

}
