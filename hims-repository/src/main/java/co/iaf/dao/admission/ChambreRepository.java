package co.iaf.dao.admission;

import org.springframework.data.jpa.repository.JpaRepository;

import co.iaf.entity.admission.Chambre;

public interface ChambreRepository extends JpaRepository<Chambre, Long>{

}
