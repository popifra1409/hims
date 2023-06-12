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
@DiscriminatorValue("realisateur")
public class ServiceRealisateur extends Services{
	
	// un service réalisateur peut avoir plusieurs enregistrements de prestations
	@OneToMany(mappedBy = "serviceRealisateur", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Collection<PrestationRegistration> prestationRegistration = new ArrayList<>();
}
