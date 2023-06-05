package co.iaf.dao.parametrage;

import org.springframework.data.jpa.repository.JpaRepository;

import co.iaf.entity.parametrage.Etablissement;

public interface EtablissementRepository extends JpaRepository<Etablissement, Long>{
}
