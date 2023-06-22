package co.iaf.entity.facturation;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("facture")
public class Facture extends Document {
}
