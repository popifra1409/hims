package co.iaf.entity.pec;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriseEnChargePk implements Serializable {

	private Long assureurId;
	private Long societeId;
	private Long prestationId;
	private String patientId;
	private Long groupePatientId;
}
