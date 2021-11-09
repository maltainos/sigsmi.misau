package mz.gov.misau.sigsmi.ws.io.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mz.gov.misau.sigsmi.ws.io.model.UserLevelEntity;

@Repository
public interface UserLevelRepository extends JpaRepository<UserLevelEntity, Long> {

}
