package co.iaf.entity.facturation;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import co.iaf.entity.enums.ModeReglement;
import co.iaf.entity.generator.ReglementNumGenerator;
import co.iaf.entity.grh.Agent;
import co.iaf.entity.identification.Patient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "iaf_reglements")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "numReglement")
public class Reglement {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "numReglement")
	@GenericGenerator(name = "numReglement", strategy = "co.iaf.entity.generator.ReglementNumGenerator", parameters = {
			@Parameter(name = ReglementNumGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = ReglementNumGenerator.VALUE_PREFIX_PARAMETER, value = "RG"),
			@Parameter(name = ReglementNumGenerator.NUMBER_FORMAT_PARAMETER, value = "%06d"), })
	@Column(name = "numero_reglement")
	private String numReglement;

	@Column(name = "date_reglement")
	private Date dateReglement;

	@Column(name = "motif")
	private String motifReglement;

	@Column(name = "mode_reglement")
	private ModeReglement modeReglement;

	@Column(name = "montant")
	private double montantReglement;

	// un reglement concerne un document
	@ManyToOne
	@JoinColumn(name = "document_id")
	private Document document;

	// un reglement est effectué par un patient
	@ManyToOne
	@JoinColumn(name = "patient_id")
	private Patient patient;

	// un reglement est enregistré par un caissier
	@ManyToOne
	@JoinColumn(name = "agent_id")
	private Agent agent;
}
