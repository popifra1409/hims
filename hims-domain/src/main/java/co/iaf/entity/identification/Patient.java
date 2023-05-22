package co.iaf.entity.identification;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import co.iaf.entity.admission.Hospitalisation;
import co.iaf.entity.admission.PriseParametreSoin;
import co.iaf.entity.enums.Sexe;
import co.iaf.entity.generator.PatientNipGenerator;
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

	@Column(name = "code_barre", unique = true, length = 100000)
	private String patientBarCode;

	@Column(name = "empreinte_digitale", updatable = false, unique = true)
	private String patientFingerPrints;

	@Column(name = "nom", nullable = false)
	private String patientLastName;

	@Column(name = "prenom")
	private String patientFirstName;

	@Column(name = "date_naissance", nullable = false)
	private String patientBirthDay;

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

	// Un patient peut avoir plusieurs informations supplémentaires
	@OneToMany(mappedBy = "patient", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private Collection<InfosSup> infosSup = new ArrayList<>();

	// Un patient peut avoir plusieurs prises de paramètres
	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Collection<PriseParametreSoin> prisesParametreSoin = new ArrayList<>();

	// un patient peut subir plusieurq hospitalisations
	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Collection<Hospitalisation> hospitalisations = new ArrayList<>();

	// un patient peut être présent dans plusieurs enregistrements de groupes pour
	// des motifs différents
	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Collection<GroupeRegistration> groupesRegistration = new ArrayList<>();
}
