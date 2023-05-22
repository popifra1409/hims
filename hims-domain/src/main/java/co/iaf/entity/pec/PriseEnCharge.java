package co.iaf.entity.pec;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import co.iaf.entity.enums.TypePriseEnCharge;
import co.iaf.entity.facturation.Prestation;
import co.iaf.entity.identification.Patient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "iaf_pec")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class PriseEnCharge {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "taux_pec")
	private float tauxPEC;

	@Column(name = "date_effective")
	private Date dateEffet;

	@Column(name = "date_expiration")
	private Date dateExpiration;

	private float plafond;

	@Column(name = "contrat_tiers")
	private String contratTiers;

	@Column(name = "is_ayant_droit")
	private boolean isAyantDroit;

	@Column(name = "nom_assure")
	private String nomAssure;

	@Column(name = "type_pec")
	private TypePriseEnCharge type;

	// une prise an charge concerne un patient
	@ManyToOne
	@JoinColumn(name = "patient_nip")
	private Patient patient;

	@ManyToOne
	@JoinColumn(name = "tiers_id")
	// une prise en charge est effectuée par un tiers
	private TiersPayeur tiersPayeur;

	// une prise peut concerner plusieurs prestations
	@ManyToMany(fetch = FetchType.EAGER)
	private Collection<Prestation> prestations = new ArrayList<>();
}
