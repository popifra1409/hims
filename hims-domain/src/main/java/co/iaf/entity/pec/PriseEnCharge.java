package co.iaf.entity.pec;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import co.iaf.entity.enums.TypePriseEnCharge;
import co.iaf.entity.facturation.Prestation;
import co.iaf.entity.identification.GroupePatient;
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

	@EmbeddedId
	private PriseEnChargePk id = new PriseEnChargePk();

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

	@ManyToOne
	@MapsId("prestationId")
	@JoinColumn(name = "prestation_id")
	private Prestation prestation;

	@ManyToOne
	@MapsId("patientId")
	@JoinColumn(name = "patient_id")
	private Patient patient;

	@ManyToOne
	@MapsId("groupePatientId")
	@JoinColumn(name = "groupe_patient_id")
	private GroupePatient groupePatient;

	@ManyToOne
	@MapsId("assureurId")
	@JoinColumn(name = "assureur_id")
	private Assureur assureur;

	@ManyToOne
	@MapsId("societeId")
	@JoinColumn(name = "societe_id")
	private Societe societe;
}
