package com.pet.planet.domain.dao.repository.sql;

import com.pet.planet.domain.dao.model.sql.HomeTransit;
import com.pet.planet.domain.dao.model.sql.TransitType;
import com.pet.planet.domain.utils.JpaRepositoryBySpecification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface TransitTypeDAO extends JpaRepositoryBySpecification<TransitType,Long> {


    List<TransitType> findAllByHomeTransit(long homeTransit);

    TransitType findById(long id);

}

