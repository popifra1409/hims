package co.iaf.entity.facturation;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("devis")
public class Devis extends Document {
}
