package smilyk.atsarat.sceduler.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import smilyk.atsarat.sceduler.models.PlanEntity;

import java.util.Optional;

public interface PlanRepo extends JpaRepository<PlanEntity, Long> {



}
