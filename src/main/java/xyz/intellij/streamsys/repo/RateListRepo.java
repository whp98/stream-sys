package xyz.intellij.streamsys.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.intellij.streamsys.entity.RateEntity;
import xyz.intellij.streamsys.entity.RateEntityPK;

public interface RateListRepo extends JpaRepository<RateEntity, RateEntityPK> {
}
