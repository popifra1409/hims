package co.iaf.entity.grh;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("realisateur")
public class AgentRealisateur extends Agent {

}
