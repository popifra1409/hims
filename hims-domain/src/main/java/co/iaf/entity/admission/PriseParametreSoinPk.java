package co.iaf.entity.admission;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriseParametreSoinPk implements Serializable {

	private String patientId;
	private Long parametreSoinId;
}
