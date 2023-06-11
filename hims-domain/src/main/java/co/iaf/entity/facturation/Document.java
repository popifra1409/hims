package co.iaf.entity.facturation;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import co.iaf.entity.generator.PatientNipGenerator;
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
			@Parameter(name = PatientNipGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = PatientNipGenerator.VALUE_PREFIX_PARAMETER, value = "DO"),
			@Parameter(name = PatientNipGenerator.NUMBER_FORMAT_PARAMETER, value = "%06d"), })
	@Column(name = "doc_ref")
	private String docRef;
	
	private String intitule;
	
	//un document appartient à un patient
	private Patient patient;
	
	//un document contient plusieurs enregistrement prestations
}
