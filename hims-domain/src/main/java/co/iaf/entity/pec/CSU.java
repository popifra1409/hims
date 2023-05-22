package co.iaf.entity.pec;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CSU")
public class CSU extends TiersPayeur{

}
