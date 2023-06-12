package co.iaf.entity.facturation;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("facture")
public class Facture extends Document {

	// une facture peut gérer avoirs plusieurs enregistrements de prestations
	@OneToMany(mappedBy = "facture", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Collection<PrestationRegistration> prestationRegistration = new ArrayList<>();
}
