package com.pet.planet.domain.dao.repository.sql;

import com.pet.planet.domain.dao.model.sql.UserAccount;
import com.pet.planet.domain.utils.JpaRepositoryBySpecification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by gabrieldyck on 19/03/17.
 */
@Repository
@Transactional
public interface UserAccountDao extends JpaRepositoryBySpecification<UserAccount,Long> {

    public UserAccount findByUsername(String username);

    public UserAccount findByMail(String mail);
}
