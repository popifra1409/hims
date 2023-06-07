package co.iaf.entity.admission;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import co.iaf.entity.identification.Patient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "iaf_prise_parametre_soins")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class PriseParametreSoin {

	@EmbeddedId
	private PriseParametreSoinPk id = new PriseParametreSoinPk();
	
	private String valeur;
	private String commentaire;
	private Date date;
	
	//Plusieurs prises peuvent être effectué pour un paramètre de soin
	@ManyToOne
	@MapsId("parametreSoinId")
	@JoinColumn(name="parametreSoin_id")
	private ParametreDeSoin parametreDeSoin;
	
	//plusieurs paramètres peuvent concerner un même patient
	@ManyToOne
	@MapsId("patientId")
	@JoinColumn(name="patient_id")
	private Patient patient;
}
