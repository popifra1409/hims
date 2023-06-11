package co.iaf.entity.pec;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("societe")
public class Societe extends TiersPayeur {

	// une societe peut gérer plusierus prise en charge
	@OneToMany(mappedBy = "societe", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Collection<PriseEnCharge> prisesEnCharge = new ArrayList<>();
}
