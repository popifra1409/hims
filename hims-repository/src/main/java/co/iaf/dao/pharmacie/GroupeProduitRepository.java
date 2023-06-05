package co.iaf.dao.pharmacie;

import org.springframework.data.jpa.repository.JpaRepository;

import co.iaf.entity.pharmacie.GroupeProduit;
import co.iaf.entity.pharmacie.GroupeProduitPk;

public interface GroupeProduitRepository extends JpaRepository<GroupeProduit, GroupeProduitPk>{

}
