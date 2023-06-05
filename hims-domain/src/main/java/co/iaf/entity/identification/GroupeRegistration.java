package co.iaf.entity.identification;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "iaf_groupe_resgistration")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class GroupeRegistration {

	@EmbeddedId
	private GroupeRegistrationPk id = new GroupeRegistrationPk();

	private String motif;

	@ManyToOne
	@MapsId("patientId")
	@JoinColumn(name = "patient_id")
	private Patient patient;

	@ManyToOne
	@MapsId("groupePatientId")
	@JoinColumn(name = "groupe_patient_id")
	@JsonIgnore
	private GroupePatient groupePatient;
}
