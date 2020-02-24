package xyz.intellij.streamsys.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.intellij.streamsys.entity.StreamEntity;

import java.util.stream.Stream;

public interface StreamListRepo extends JpaRepository<StreamEntity,Long> {
}
