package musicstore.musicselling.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import musicstore.musicselling.Entity.UserEntity;
import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);

    UserEntity findByUserId(Long userId);
}
