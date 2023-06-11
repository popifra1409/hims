package co.iaf.entity.parametrage;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("realisateur")
public class ServiceRealisateur extends Services{

}
