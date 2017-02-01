package org.recap.repository.jpa;

import org.junit.Test;
import org.recap.BaseTestCase;
import org.recap.model.jpa.NotesEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by rajeshbabuk on 29/10/16.
 */
public class NotesDetailsRepositoryUT extends BaseTestCase {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    NotesDetailsRepository notesDetailsRepository;

    @Test
    public void saveNotesEntity() throws Exception {
        NotesEntity notesEntity = new NotesEntity();
        notesEntity.setItemId(1);
        notesEntity.setRequestId(1);
        notesEntity.setNotes("Test Notes");

        NotesEntity savedNotesEntity = notesDetailsRepository.save(notesEntity);
        entityManager.refresh(savedNotesEntity);
        assertNotNull(savedNotesEntity);
        assertNotNull(savedNotesEntity.getNotesId());
    }

    @Test
    public void getNotes() throws Exception{
        NotesEntity notesEntity = notesDetailsRepository.findByItemId(1);
        assertNotNull(notesEntity);
        assertEquals("Test Notes",notesEntity.getNotes());
    }
}