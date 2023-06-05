package co.iaf.entity.admission;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import co.iaf.entity.parametrage.Services;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "iaf_tranferts")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class TransfertHospit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "date_transfert")
	private Date dateTransfert;

	@Column(name = "motif_transfert")
	private String motifTransfert;

	// un transfer concerne une hospitalisation
	@ManyToOne
	@JoinColumn(name = "code_hospit")
	private Hospitalisation hospit;

	// un transfert est effectué vers un service
	@ManyToOne
	@JoinColumn(name = "service_id")
	private Services service;
}
