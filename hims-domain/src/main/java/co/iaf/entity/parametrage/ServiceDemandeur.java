package co.iaf.entity.parametrage;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import co.iaf.entity.facturation.PrestationRegistration;

@Entity
@DiscriminatorValue("demandeur")
public class ServiceDemandeur extends Services {
	
	// un service demandeur peut avoir plusieurs enregistrements de prestations
	@OneToMany(mappedBy = "serviceDemandeur", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Collection<PrestationRegistration> prestationRegistration = new ArrayList<>();
}
