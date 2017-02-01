package org.recap.repository.jpa;

import org.recap.model.jpa.PatronEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by rajeshbabuk on 28/10/16.
 */
public interface PatronDetailsRepository extends JpaRepository<PatronEntity, Integer> {
}
