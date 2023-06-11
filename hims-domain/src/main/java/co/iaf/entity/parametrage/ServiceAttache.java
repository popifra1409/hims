package co.iaf.entity.parametrage;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import co.iaf.entity.grh.Agent;

@Entity
@DiscriminatorValue("attache")
public class ServiceAttache extends Services {

	// un service attache peut avoir plusieurs agent affectés
	@OneToMany(mappedBy = "serviceAttache", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Collection<Agent> agents = new ArrayList<>();
}
