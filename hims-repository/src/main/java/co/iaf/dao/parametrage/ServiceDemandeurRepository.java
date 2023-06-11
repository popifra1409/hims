package co.iaf.dao.parametrage;

import org.springframework.data.jpa.repository.JpaRepository;

import co.iaf.entity.parametrage.ServiceDemandeur;

public interface ServiceDemandeurRepository extends JpaRepository<ServiceDemandeur, Long> {

}
