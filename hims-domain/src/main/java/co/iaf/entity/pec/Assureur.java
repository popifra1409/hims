package co.iaf.entity.pec;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("assureur")
public class Assureur extends TiersPayeur {

	@Column(name = "taux_couverture")
	private float tauxGolbal;

	@Column(name = "is_contrat_obligatoire")
	private boolean isContratObligatoire;

	// un assureur peut gérer plusieurs prise en charge
	@OneToMany(mappedBy = "assureur", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Collection<PriseEnCharge> prisesEnCharge = new ArrayList<>();
}
