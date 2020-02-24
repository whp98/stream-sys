package xyz.intellij.streamsys.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.intellij.streamsys.entity.UserEntity;

import java.util.List;

public interface UserListRepo extends JpaRepository<UserEntity,Long> {
    List<UserEntity> findByUserEmail(String email);
}
