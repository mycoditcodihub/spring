package com.ibeka.spring5recipeapp.repositories;

import com.ibeka.spring5recipeapp.model.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

/*
 * This is how to do integration test.
 */
@RunWith(SpringRunner.class)
@DataJpaTest //This brings up embedded database as well as configure Spring data jpa
public class UnitOfMeasureRepositoryIT {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository; //We want to test this as per our integration test. Note that we are autowiring this unlike the unit tests where the class or interface we want to test itself does not have any autowiring

    @Before
    public void setUp() throws Exception {
    }

    //This is the first integration test
    @Test
    public void findByDescription() throws Exception{
        Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByUom("Teaspoon");

        assertEquals("Teaspoon", uomOptional.get().getUom());

//        assertEquals("Teaspoonss", uomOptional.get().getUom()); //This will fail because there is nothing like Teaspoonss in the embedded database
    }

    //If we run both tests by running the class, the context will not be reloaded for this second method causing shorter run time for this second test
    @Test
    public void findByDescriptionCup() throws Exception{
        Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByUom("Cup");

        assertEquals("Cup", uomOptional.get().getUom());
    }
}