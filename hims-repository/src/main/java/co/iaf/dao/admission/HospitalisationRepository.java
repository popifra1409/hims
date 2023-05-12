package co.iaf.dao.admission;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.iaf.entity.admission.Hospitalisation;
import co.iaf.entity.admission.Sejour;
import co.iaf.entity.parametrage.Services;

public interface HospitalisationRepository extends JpaRepository<Hospitalisation, Long> {

	// rechercher une hositalisation par son code
	public Hospitalisation findByCodeHospit(String codeHospit);

	// rechercher les hospitalisations par date d'entree
	public List<Hospitalisation> findByDateEntree(Date dateEntree);

	// rechercher les hospitalisations par date de sortie
	public List<Hospitalisation> findByDateSortie(Date dateSortie);

	// liste des hospitalisations cloturées
	public List<Hospitalisation> findByIsHospitClotureTrue();

	// liste des hospitalisations non cloturées
	public List<Hospitalisation> findByIsHospitClotureFalse();

	// liste des hospitalisations en avis
	public List<Hospitalisation> findByIsAvisHospitTrue();

	// rechercher les hospitalisation d'un sejour
	public List<Hospitalisation> findBySejour(Sejour sejour);

	// liste des hospitalisations encours d'un service
	public List<Hospitalisation> findByServiceAccueilAndIsHospitClotureFalse(Services service);

}
