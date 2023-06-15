package co.iaf.entity.facturation;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import co.iaf.entity.grh.AgentPrescripteur;
import co.iaf.entity.grh.AgentRealisateur;
import co.iaf.entity.parametrage.ServiceDemandeur;
import co.iaf.entity.parametrage.ServiceRealisateur;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "iaf_prestation_resgistration")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class PrestationRegistration {

	@EmbeddedId
	private PrestationRegistrationPk id = new PrestationRegistrationPk();
	
	private int quantite;
	
	private double ticketModerateur;
	
	private double tarif;
	
	private double tva;
	
	@ManyToOne
	@MapsId("numFacture")
	@JoinColumn(name = "numero_facture")
	private Facture facture;

	@ManyToOne
	@MapsId("numPrefacture")
	@JoinColumn(name = "numero_prefacture")
	private Prefacture prefacture;
	
	@ManyToOne
	@MapsId("numDevis")
	@JoinColumn(name = "numero_devis")
	private Devis devis;
	
	@ManyToOne
	@MapsId("serviceDemandeurId")
	@JoinColumn(name = "service_demandeur_id")
	private ServiceDemandeur serviceDemandeur;
	
	@ManyToOne
	@MapsId("serviceRealisateurId")
	@JoinColumn(name = "service_realisateur_id")
	private ServiceRealisateur serviceRealisateur;
	
	@ManyToOne
	@MapsId("agentPrescripteurId")
	@JoinColumn(name = "agent_prescripteur_id")
	private AgentPrescripteur agentPrescripteur;
	
	@ManyToOne
	@MapsId("agentRealisateurId")
	@JoinColumn(name = "agent_realisateur_id")
	private AgentRealisateur agentRealisateur;
	
	@ManyToOne
	@MapsId("prestationId")
	@JoinColumn(name = "prestation_id")
	private Prestation prestation;
}
