package co.iaf.dao.pharmacie;

import org.springframework.data.jpa.repository.JpaRepository;

import co.iaf.entity.pharmacie.ReceptionInterne;

public interface ReceptionInterneRepository extends JpaRepository <ReceptionInterne, Long> {

}
