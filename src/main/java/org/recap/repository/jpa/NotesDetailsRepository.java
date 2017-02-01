package org.recap.repository.jpa;

import org.recap.model.jpa.NotesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by rajeshbabuk on 28/10/16.
 */
public interface NotesDetailsRepository extends JpaRepository<NotesEntity, Integer> {

    NotesEntity findByItemId(Integer itemId);
}