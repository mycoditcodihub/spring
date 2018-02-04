package com.ibeka.spring5recipeapp.repositories;

import com.ibeka.spring5recipeapp.model.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/*
 * Spring Data with Spring 5 is now using Optional ie (Optional<T> findById(ID var1);)
 * To see the query methods in spring data, place your cursor on CrudRepository and then command + enter
 */
public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long>{

    /*
     * Created one finder.
     * Make sure that whatever you add to findBy must-
     * 1. have the same name as whatever you have inside the argument eg Uom in findByUom must match the String name uom inside (String uom)
     * 2. Uom must be upper case in findByUom. Note, this follows Java's camelCase naming convention
     * 3. In the entity class UnitOfMeasure, the Id variable type must be the same type as what is defined in CrudRepository<UnitOfMeasure, Long> ie, both types are Long.
     * 4. In the entity class, UnitOfMeasure, the variable name you want to use to match ie "uom" in  private String uom; must match as per point 1 and 2 described above.
     */
    Optional<UnitOfMeasure> findByUom(String uom);
}
