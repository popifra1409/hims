package co.iaf.entity.parametrage;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("demandeur")
public class ServiceDemandeur extends Services{

}
