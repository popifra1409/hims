package co.iaf.entity.facturation;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("prefacture")
public class Prefacture extends Document {

}
