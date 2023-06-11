package co.iaf.entity.parametrage;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("fournisseur")
public class ServiceFournisseur extends Services {

}
