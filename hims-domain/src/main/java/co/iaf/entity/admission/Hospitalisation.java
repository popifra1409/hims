package co.iaf.entity.admission;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import co.iaf.entity.enums.TypeHospitalisation;
import co.iaf.entity.identification.Patient;
import co.iaf.entity.parametrage.Services;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "iaf_hospitalisations")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Hospitalisation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "code_hospit")
	private String codeHospit;

	@Column(name = "date_entree")
	private Date dateEntree;

	@Column(name = "date_sortie")
	private Date dateSortie;

	@Column(name = "date_sortie_estimee")
	private Date dateSortimeEstimee;

	@Column(name = "motif_entree")
	private String motifEntree;

	@Column(name = "motif_sortie")
	private String motifSortie;

	@Column(name = "type_hospit")
	private TypeHospitalisation typeHospit;

	@Column(name = "mode_entree")
	private String modeEntree;

	@Column(name = "is_avis_hospit")
	private boolean isAvisHospit;

	@Column(name = "nombre_jour")
	private int nbreDeJour;

	@Column(name = "is_hospit_cloture")
	private boolean isHospitCloture;

	// le service d'accueil de l'hospitalisation
	@ManyToOne
	@JoinColumn(name = "service_id")
	private Services serviceAccueil;

	// un hospitalisation est dans un sejour
	@ManyToOne
	@JoinColumn(name = "sejour_id")
	private Sejour sejour;

	// une hospitalisation concerne un patient
	@ManyToOne
	@JoinColumn(name = "patient_nip")
	private Patient patient;

	// une hospitalisation ocuppe un lit
	@ManyToOne
	@JoinColumn(name = "lit_id")
	private Lit lit;

	// une hospitalisation peut subir une ou plusieurs interventions
	@OneToMany(mappedBy = "hospit", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Collection<Intervention> interventions = new ArrayList<>();

	// les tranferts d'une hospitalisations
	@OneToMany(mappedBy = "hospit", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Collection<TransfertHospit> transfertsHospit = new ArrayList<>();
}
