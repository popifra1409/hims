package co.iaf.entity.grh;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("prescripteur")
public class AgentPrescripteur extends Agent {
}
