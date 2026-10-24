package in.redblack.crudApplicationDemo.repositiry;
import in.redblack.crudApplicationDemo.entity.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface StudentRepository extends JpaRepository<Students,Long> {
    Optional<Students> findByIdAndDeletedIsFalse(Long id);
    List<Students> findByDeletedIsFalse();

    Optional<Students> findByRollNo(Long rollNo);

    Optional<Students> findByIdAndDeletedIsTrue(Long id);

    Optional<Students> findByName(String name);

    List<Students> findByNameAndDeletedIsFalse(String name);

    Optional<Students> findByRollNoAndDeletedIsFalse(int rollNo);


    // Optional<Students> findByIdAndDeletedIsFalse(Long id);
    //List<Students> findByDeleteIsFalse();
}

