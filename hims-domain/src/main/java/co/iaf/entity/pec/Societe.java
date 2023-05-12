package co.iaf.entity.pec;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SOCIETE")
public class Societe extends TiersPayeur{

}
