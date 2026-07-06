package kegly.organisation.nutrition.repository;

import kegly.organisation.nutrition.entity.Target;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TargetRepository extends JpaRepository<Target, Long> {
}