package smilyk.atsarat.user.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import smilyk.atsarat.user.models.Users;

import java.util.Optional;

public interface UserRepo extends JpaRepository<Users, Long> {


    Optional<Users> findByMainEmail(String mainEmail);

    Optional<Users> findUserByConfirmEmailToken(String token);
}
