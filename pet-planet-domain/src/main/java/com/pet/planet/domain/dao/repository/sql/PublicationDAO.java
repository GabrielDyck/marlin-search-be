package com.pet.planet.domain.dao.repository.sql;

import com.pet.planet.domain.dao.model.sql.Publication;
import com.pet.planet.domain.utils.JpaRepositoryBySpecification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.List;

/**
 * Created by gabrieldyck on 14/05/17.
 */
@Repository
@Transactional
public interface PublicationDAO extends JpaRepositoryBySpecification<Publication,Long> {


    public Publication findById(Long id);
    List<Publication> findByUserAccount_Id(long id);

}
