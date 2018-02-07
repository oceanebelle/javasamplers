package com.oceanebelle.javasamplers.entitiessample.repositories;

import com.oceanebelle.javasamplers.entitiessample.entity.Notebook;
import org.springframework.data.repository.CrudRepository;

/**
 * JPA repository for the Notebook entity
 */
public interface NotebookRepository extends CrudRepository<Notebook, Long> {
}
