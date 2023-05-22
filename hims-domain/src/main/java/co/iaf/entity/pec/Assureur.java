package co.iaf.entity.pec;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("ASSUREUR")
public class Assureur extends TiersPayeur{

	@Column(name = "taux_couverture")
	private float tauxGolbal;
	
	@Column(name = "is_contrat_obligatoire")
	private boolean isContratObligatoire;
}
