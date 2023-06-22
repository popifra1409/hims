package co.iaf.entity.facturation;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import co.iaf.entity.grh.Agent;
import co.iaf.entity.parametrage.Services;
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
	@MapsId("numDoc")
	@JoinColumn(name = "numero_document")
	private Document document;

	@ManyToOne
	@MapsId("serviceId")
	@JoinColumn(name = "service_id")
	private Services service;

	@ManyToOne
	@MapsId("agentId")
	@JoinColumn(name = "agent_id")
	private Agent agent;

	@ManyToOne
	@MapsId("prestationId")
	@JoinColumn(name = "prestation_id")
	private Prestation prestation;
}
