package com.mills.recipes.repositories;

import com.mills.recipes.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

/**
 * Created by rachelmills on 14/7/19.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryIT {

        @Autowired
        private UnitOfMeasureRepository unitOfMeasureRepository;

        @Before
        public void setUp() throws Exception {

        }

        @Test
        public void findByDescription() throws Exception {
            Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
            assertEquals("Teaspoon", unitOfMeasureOptional.get().getDescription());
        }

    @Test
    public void findByDescriptionCup() throws Exception {
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Cup");
        assertEquals("Cup", unitOfMeasureOptional.get().getDescription());
    }

}