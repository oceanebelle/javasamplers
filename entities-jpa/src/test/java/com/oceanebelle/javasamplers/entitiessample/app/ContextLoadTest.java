package com.oceanebelle.javasamplers.entitiessample.app;

import com.oceanebelle.javasamplers.entitiessample.dataaccess.NotebookDataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class ContextLoadTest {

    @Autowired
    NotebookDataService notebookData;

    @Test
    public void testBeans() {
        assertNotNull(notebookData);
    }
}
