package com.pet.planet.domain.utils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.io.Serializable;


/**
 * Created by gabrieldyck on 19/03/17.
 */
public interface JpaRepositoryBySpecification<T, S extends Serializable> extends JpaRepository<T, S>, JpaSpecificationExecutor<T> {

}
