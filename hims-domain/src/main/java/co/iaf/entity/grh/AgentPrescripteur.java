package co.iaf.entity.grh;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import co.iaf.entity.facturation.PrestationRegistration;


@Entity
@DiscriminatorValue("prescripteur")
public class AgentPrescripteur extends Agent {

	// un agent prrescripteur peut avoir plusieurs enregistrements de prestations
	@OneToMany(mappedBy = "agentPrescripteur", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Collection<PrestationRegistration> prestationRegistration = new ArrayList<>();
}
