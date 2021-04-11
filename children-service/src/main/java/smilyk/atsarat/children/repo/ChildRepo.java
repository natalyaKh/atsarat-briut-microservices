package smilyk.atsarat.children.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import smilyk.atsarat.children.model.ChildrenEntity;

import java.util.Optional;

public interface ChildRepo extends JpaRepository<ChildrenEntity, Long> {
    Optional<ChildrenEntity> findByTzAndDeleted(String tz, boolean deleted);


}
