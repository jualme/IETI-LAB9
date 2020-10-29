package eci.ieti.data;

import eci.ieti.data.model.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface TodoRepository  extends CrudRepository<Todo, Long> {

    Page<Todo> findByDescriptionContaining(String description, Pageable pageable);

    Page<Todo>findByResponsible(String responsible, Pageable pageable);

  @Query("{\"dueDate\":{\"$lt\": new ISODate(?0)}}")
  List<Todo> findAllByDueDate(LocalDate dueDate);

  @Query("{\"priority\": {$gte : 5} , \"responsible\": {$eq : ?0}}")
  List<Todo> findAllByResponsibleAndPriority(String responsible);

 @Query("{\"description\": {$regex : \"[a-zA-Z ]{30,}\"}}")
  List<Todo> findAllWithABigDescription();
}
