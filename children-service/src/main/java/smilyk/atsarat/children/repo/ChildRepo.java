package smilyk.atsarat.children.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import smilyk.atsarat.children.model.ChildrenEntity;

import java.util.Optional;
/**
 * {@link org.springframework.data.repository.Repository} for handling with {@link ChildrenEntity} entity.
 */
public interface ChildRepo extends JpaRepository<ChildrenEntity, Long> {
    Optional<ChildrenEntity> findByTzAndDeleted(String tz, boolean deleted);


    Optional<ChildrenEntity> findByUuidChildAndDeleted(String uuidChild, boolean b);
}
