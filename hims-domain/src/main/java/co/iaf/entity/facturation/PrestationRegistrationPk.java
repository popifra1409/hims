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

	private String numDoc;
	private Long serviceId;
	private Long agentId;
	private Long prestationId;
}
