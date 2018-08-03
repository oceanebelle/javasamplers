package com.oceanebelle.javasamplers.entitiessample.dataaccess;

import com.oceanebelle.javasamplers.entitiessample.entity.Notebook;
import com.oceanebelle.javasamplers.entitiessample.repositories.NotebookRepository;

import javax.transaction.Transactional;

@Transactional
public class NotebookDataService {

    private final NotebookRepository repository;

    public NotebookDataService(NotebookRepository repository) {
        this.repository = repository;
    }

    public void save(Notebook notebook) {
        this.repository.save(notebook);
    }

    public Notebook find(Long id) {
        return this.repository.findOne(id);
    }

    public void saveThenThrowError(Notebook notebook) throws Exception {
        this.repository.save(notebook);
        throw new IllegalStateException("Cancelled the record should not be in the DB");
    }
}
