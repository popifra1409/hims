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
@Table(name="iaf_mouvement_stock")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class MouvementStock {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="date_mouvement")
	private int dateMvt;
	
	@Column(name="quantite_mouvement")
	private int qteMvt;
}
