package co.iaf.entity.identification;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import co.iaf.entity.admission.Hospitalisation;
import co.iaf.entity.admission.PriseParametreSoin;
import co.iaf.entity.enums.Sexe;
import co.iaf.entity.facturation.Devis;
import co.iaf.entity.facturation.Facture;
import co.iaf.entity.facturation.Prefacture;
import co.iaf.entity.facturation.Reglement;
import co.iaf.entity.generator.PatientNipGenerator;
import co.iaf.entity.pec.PriseEnCharge;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "iaf_patients")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "patientId")
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patient_nip")
	@GenericGenerator(name = "patient_nip", strategy = "co.iaf.entity.generator.PatientNipGenerator", parameters = {
			@Parameter(name = PatientNipGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = PatientNipGenerator.VALUE_PREFIX_PARAMETER, value = "PA"),
			@Parameter(name = PatientNipGenerator.NUMBER_FORMAT_PARAMETER, value = "%06d"), })
	@Column(name = "nip")
	private String patientId;

	@Column(name = "empreinte_digitale", updatable = false, unique = true)
	private String patientFingerPrints;

	@Column(name = "nom", nullable = false)
	private String patientLastName;

	@Column(name = "prenom")
	private String patientFirstName;

	@Column(name = "date_naissance", nullable = false)
	private Date patientBirthDay;

	@Column(name = "lieu_naissance")
	private String patientPlaceOfBirth;

	@Column(name = "sexe", nullable = false)
	private Sexe patientSex;

	@Column(name = "age")
	private int patientAge;

	@Column(name = "profession")
	private String patientProfession;

	@Column(name = "religion")
	private String patientReligion;

	@Column(name = "nationalite")
	private String patientNationalite;

	private String email;

	private String telephone;

	private String adresse;

	// un patient a un seuk qr code généré
	@OneToOne(mappedBy = "patient")
	private QrCodePatient qrCodePatient;

	// Un patient peut avoir plusieurs informations supplémentaires
	@OneToMany(mappedBy = "patient", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private Collection<InfosSup> infosSup = new ArrayList<>();

	// Un patient peut avoir plusieurs prises de paramètres
	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Collection<PriseParametreSoin> prisesParametreSoin = new ArrayList<>();

	// un patient peut avoir plusieurs factures
	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private Collection<Facture> factures = new ArrayList<>();

	// un patient peut avoir plusieurs préfactures
	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private Collection<Prefacture> prefactures = new ArrayList<>();

	// un patient peut avoirs plusieurs devis
	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private Collection<Devis> deviss = new ArrayList<>();

	// un patient peut subir plusieurq hospitalisations
	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private Collection<Hospitalisation> hospitalisations = new ArrayList<>();

	// un patient peut être présent dans plusieurs enregistrements de groupes pour
	// des motifs différents
	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private Collection<GroupeRegistration> groupesRegistration = new ArrayList<>();

	// un patient peut avoir multiples prises en charge
	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private Collection<PriseEnCharge> prisesEnCharge = new ArrayList<>();

	// un patient peut avoir plusieurs reglements de facture
	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private Collection<Reglement> reglements = new ArrayList<>();
}
