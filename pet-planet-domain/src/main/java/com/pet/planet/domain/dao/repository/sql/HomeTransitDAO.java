package com.pet.planet.domain.dao.repository.sql;

import com.pet.planet.domain.dao.model.sql.HomeTransit;
import com.pet.planet.domain.utils.JpaRepositoryBySpecification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface HomeTransitDAO extends JpaRepositoryBySpecification<HomeTransit,Long>{
}
