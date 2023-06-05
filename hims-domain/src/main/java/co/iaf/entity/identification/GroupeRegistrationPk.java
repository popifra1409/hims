package co.iaf.entity.identification;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupeRegistrationPk implements Serializable {

	@Column(name = "patient_id")
	private String patientId;

	@Column(name = "groupe_patient_id")
	private Long groupePatientId;
}
