package smilyk.atsarat.user.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import smilyk.atsarat.user.models.Users;

import java.util.Optional;
/**
 * {@link org.springframework.data.repository.Repository} for handling with {@link Users} entity.
 */
public interface UserRepo extends JpaRepository<Users, Long> {


    Optional<Users> findByMainEmail(String mainEmail);

    Optional<Users> findUserByConfirmEmailToken(String token);

    Optional<Users> findByUuidUserAndDeleted(String uuidUser, boolean b);
}
