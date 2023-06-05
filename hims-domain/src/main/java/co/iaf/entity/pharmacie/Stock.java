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
@Table(name="iaf_stock")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Stock {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="stock_initial")
	private int stockInit;
	
	@Column(name="stock_final")
	private int stockFin;
	
	@Column(name="ecart_stock")
	private int ecart;
}
