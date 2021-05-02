package smilyk.atsarat.tsofim.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import smilyk.atsarat.tsofim.model.TsofimDetails;

import java.util.Optional;
/**
 * {@link org.springframework.data.repository.Repository} for handling with {@link TsofimDetails} entity.
 */
public interface TsofimDetailsRepo extends JpaRepository<TsofimDetails, Long> {

    Optional<TsofimDetails> findByUuidChildAndDeleted(String uuidChild, boolean deleted);

    Optional<TsofimDetails> findByUuidTsofimDetailsAndDeleted(String uuidTsofimDetails, boolean b);
}
