package com.mikescherbakov.jobinterviewbase.runner;

import com.fasterxml.jackson.databind.*;
import com.mikescherbakov.jobinterviewbase.model.*;
import com.mikescherbakov.jobinterviewbase.repository.*;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.boot.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;


@Component
@Slf4j
@RequiredArgsConstructor
public class RunnerFlushModeAuto implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final CourseRepository courseRepository;
    private final BookRepository bookRepository;
    private final ObjectMapper mapper;

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Set FlushMode to AUTO
        // Set FlushMode to COMMIT
        entityManager.setFlushMode(FlushModeType.COMMIT);

        // Create a new User entity
        Author user = new Author();
        user.setName("John Doe");
        entityManager.persist(user);

        // Since FlushMode is set to COMMIT, the session will not be flushed before the query
        List<Author> users = entityManager.createQuery("FROM Author", Author.class).getResultList();

        // The newly persisted user is not yet flushed, so it won't be in the list
        System.out.println("Number of users: " + users.size());  // This will NOT include the newly persisted user

        // Now, when the transaction commits, the session is flushed, and the user is saved to the database
    }
}
