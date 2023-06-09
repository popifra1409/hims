package co.iaf.entity.facturation;

import java.util.Collection;

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

import co.iaf.entity.parametrage.Domaine;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "iaf_prestations")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Prestation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "code_prestation", unique = true)
	private String codePrestation;

	@Column(name = "designation_usuelle")
	private String designationUsuelle;

	@Column(name = "designation_conventionnelle")
	private String desingationConventionnelle;

	@Column(name = "tarif_de_base")
	private double trafiBase;

	@Column(name = "is_desactive")
	private boolean isDesactive;

	@Column(name = "is_masque")
	private boolean isMasque;

	@Column(name = "tarif_fixe")
	private double tarifFixe;

	@Column(name = "lettre_cle")
	private String lettreCle;

	private int coefficient;

	@Column(name = "is_acte_hospit")
	private boolean isActeHospit;

	@Column(name = "is_exonere")
	private boolean isExonere;

	@Column(name = "is_prise_en_charge")
	private boolean isPriseEnCharge;

	private double quotepart;

	@Column(name = "nombre_prestation_jour")
	private int nbrePrestParJour;

	@Column(name = "nombre_tube")
	private int nbreTube;

	@Column(name = "delai_execution")
	private int delaiExecution;

	@Column(name = "is_suivi_medical")
	private boolean isSuiviMedical;

	private String commentaire;

	@Column(name = "is_tarif_formule")
	private boolean isTarifFormule;

	@Column(name = "is_quantifiable")
	private boolean isQuantifiable;

	@Column(name = "is_produit_pharmacie")
	private boolean isProduitpharmacie;

	// une prestation appartient à un domaine

	@ManyToOne
	@JoinColumn(name = "domaine_prestation_id")
	private Domaine domaine;

	// une prestation contribue à une ligne d'imputation
	@ManyToOne
	@JoinColumn(name = "imputation_id")
	private Imputation imputation;

	//une prestation peut avoir plusieurs paramètres private
	@OneToMany(mappedBy = "imputation", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	Collection<ParametrePrestation> parametres;
	 
}
