package co.iaf.entity.facturation;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import co.iaf.entity.generator.DocumentRefGenerator;
import co.iaf.entity.identification.Patient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "iaf_documents")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Type_document", discriminatorType = DiscriminatorType.STRING)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "docRef")
public abstract class Document {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doc_ref")
	@GenericGenerator(name = "doc_ref", strategy = "co.iaf.entity.generator.DocumentRefGenerator", parameters = {
			@Parameter(name = DocumentRefGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = DocumentRefGenerator.VALUE_PREFIX_PARAMETER, value = "DO"),
			@Parameter(name = DocumentRefGenerator.NUMBER_FORMAT_PARAMETER, value = "%06d"), })
	@Column(name = "doc_ref")
	private String docRef;

	private String intitule;

	// un document appatient à un patient
	@ManyToOne
	@JoinColumn(name = "patient_id")
	private Patient patient;

	// un document peut gérer avoirs plusieurs enregistrements de prestations
	@OneToMany(mappedBy = "document", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Collection<PrestationRegistration> prestationRegistration = new ArrayList<>();

	// un document peut avoir plusieurs reglements
	@OneToMany(mappedBy = "document", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Collection<Reglement> reglements = new ArrayList<>();

}
