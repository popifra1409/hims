package co.iaf.entity.pharmacie;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="iaf_reception_interne")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ReceptionInterne {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="nom_reception")
	private String nmroRecep;
	
	@Column(name="observation")
	private boolean observation;
	
	@Column(name="is_hospitalisation")
	private boolean isHospitalisation;
	
	@Column(name="is_ambulatoire")
	private boolean isAmbulatoire;
	
	@Column(name="is_service")
	private boolean isService;
}
