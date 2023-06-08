package co.iaf.entity.identification;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupeRegistrationPk implements Serializable {

	private String patientId;
	private Long groupePatientId;
}
