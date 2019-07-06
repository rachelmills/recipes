package com.mills.recipes.repositories;

import com.mills.recipes.domain.Notes;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by rachelmills on 6/7/19.
 */
public interface NotesRepository extends CrudRepository<Notes, Long> {
}
