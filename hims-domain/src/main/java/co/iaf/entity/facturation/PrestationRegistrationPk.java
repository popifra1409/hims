package co.iaf.entity.facturation;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrestationRegistrationPk implements Serializable {

	private String numFacture;
	private String numPrefacture;
	private String numDevis;
	private Long serviceDemandeurId;
	private Long serviceRealisateurId;
	private Long agentPrescripteurId;
	private Long agentRealisateurId;
}
