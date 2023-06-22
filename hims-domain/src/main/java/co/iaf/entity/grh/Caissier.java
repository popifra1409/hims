package co.iaf.entity.grh;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("caissier")
public class Caissier extends Agent {
}
