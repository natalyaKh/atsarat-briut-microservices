package smilyk.atsarat.sceduler.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import smilyk.atsarat.sceduler.models.PlanEntity;

import java.util.Optional;
/**
 * {@link org.springframework.data.repository.Repository} for handling with {@link PlanEntity} entity.
 */
public interface PlanRepo extends JpaRepository<PlanEntity, Long> {


    Optional<PlanEntity> findByUuidPlanAndDeleted(String uuidPlanDetails, boolean b);
}
