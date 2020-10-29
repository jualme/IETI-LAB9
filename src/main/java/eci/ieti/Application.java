package eci.ieti;

import eci.AppConfiguration;
import eci.ieti.data.CustomerRepository;
import eci.ieti.data.ProductRepository;
import eci.ieti.data.TodoRepository;
import eci.ieti.data.UserRepository;
import eci.ieti.data.model.*;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {

  @Autowired private CustomerRepository customerRepository;

  @Autowired private ProductRepository productRepository;

  @Autowired private TodoRepository todoRepository;

  @Autowired private UserRepository userRepository;

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Override
  public void run(String... args) throws Exception {

    ApplicationContext applicationContext =
        new AnnotationConfigApplicationContext(AppConfiguration.class);
    MongoOperations mongoOperation = (MongoOperations) applicationContext.getBean("mongoTemplate");

    // legacy
    customerRepository.deleteAll();

    customerRepository.save(new Customer("Alice", "Smith"));
    customerRepository.save(new Customer("Bob", "Marley"));
    customerRepository.save(new Customer("Jimmy", "Page"));
    customerRepository.save(new Customer("Freddy", "Mercury"));
    customerRepository.save(new Customer("Michael", "Jackson"));

    System.out.println("Customers found with findAll():");
    System.out.println("-------------------------------");

    customerRepository.findAll().stream().forEach(System.out::println);
    System.out.println();

    productRepository.deleteAll();

    productRepository.save(new Product(1L, "Samsung S8", "All new mobile phone Samsung S8"));
    productRepository.save(
        new Product(2L, "Samsung S8 plus", "All new mobile phone Samsung S8 plus"));
    productRepository.save(new Product(3L, "Samsung S9", "All new mobile phone Samsung S9"));
    productRepository.save(
        new Product(4L, "Samsung S9 plus", "All new mobile phone Samsung S9 plus"));
    productRepository.save(new Product(5L, "Samsung S10", "All new mobile phone Samsung S10"));
    productRepository.save(
        new Product(6L, "Samsung S10 plus", "All new mobile phone Samsung S10 plus"));
    productRepository.save(new Product(7L, "Samsung S20", "All new mobile phone Samsung S20"));
    productRepository.save(
        new Product(8L, "Samsung S20 plus", "All new mobile phone Samsung S20 plus"));
    productRepository.save(
        new Product(9L, "Samsung S20 ultra", "All new mobile phone Samsung S20 ultra"));

    System.out.println("Paginated search of products by criteria:");
    System.out.println("-------------------------------");

    productRepository.findByDescriptionContaining("plus", PageRequest.of(0, 2)).stream()
        .forEach(System.out::println);
    System.out.println();

    // New info

    // Users
    userRepository.deleteAll();

    userRepository.save(new User("1a", "Juan", "juan@mail.com"));
    userRepository.save(new User("2a", "Nicolas", "nicolas@mail.com"));
    userRepository.save(new User("3a", "Paola", "paola@mail.com"));
    userRepository.save(new User("4a", "Alice", "alice@mail.com"));
    userRepository.save(new User("5a", "Freddy", "freddy@mail.com"));
    userRepository.save(new User("6a", "Michael", "michael@mail.com"));
    userRepository.save(new User("7a", "Steve", "steve@mail.com"));
    userRepository.save(new User("8a", "Charles", "charles@mail.com"));
    userRepository.save(new User("9a", "Jimmy", "jimmy@mail.com"));
    userRepository.save(new User("10a", "bob", "bob@mail.com"));

    System.out.println("Users found with findAll():");
    System.out.println("-------------------------------");
    userRepository.findAll().stream().forEach(System.out::println);
    System.out.println();

    // To-do
    todoRepository.deleteAll();

    todoRepository.save(
        Todo.builder()
            .id(1L)
            .description("Christmas Time")
            .priority(7)
            .dueDate(LocalDate.of(2020, 12, 1))
            .responsible("juan@mail.com")
            .status("Pending")
            .build());

    todoRepository.save(
        Todo.builder()
            .id(2L)
            .description("Resist temptation")
            .priority(10)
            .dueDate(LocalDate.of(2021, 2, 5))
            .responsible("nicolas@mail.com")
            .status("Pending")
            .build());

    todoRepository.save(
        Todo.builder()
            .id(3L)
            .description("Capture Pokemon")
            .priority(5)
            .dueDate(LocalDate.of(2020, 11, 2))
            .responsible("paola@mail.com")
            .status("Pending")
            .build());

    todoRepository.save(
        Todo.builder()
            .id(4L)
            .description("Manipulate evidence")
            .priority(10)
            .dueDate(LocalDate.of(2020, 10, 5))
            .responsible("alice@mail.com")
            .status("Done")
            .build());
    todoRepository.save(
        Todo.builder()
            .id(5L)
            .description("Bombard the headquarters")
            .priority(2)
            .dueDate(LocalDate.of(2021, 2, 15))
            .responsible("freddy@mail.com")
            .status("Not gonna do")
            .build());
    todoRepository.save(
        Todo.builder()
            .id(6L)
            .description("Invade switzerland")
            .priority(1)
            .dueDate(LocalDate.of(1937, 2, 2))
            .responsible("michael@mail.com")
            .status("Done")
            .build());
    todoRepository.save(
        Todo.builder()
            .id(7L)
            .description("Kill the president")
            .priority(6)
            .dueDate(LocalDate.of(1963, 11, 22))
            .responsible("steve@mail.com")
            .status("Done")
            .build());
    todoRepository.save(
        Todo.builder()
            .id(8L)
            .description("Revive the dead")
            .priority(8)
            .dueDate(LocalDate.of(2022, 8, 20))
            .responsible("charles@mail.com")
            .status("Pending")
            .build());
    todoRepository.save(
        Todo.builder()
            .id(9L)
            .description("Entreat the angels")
            .priority(6)
            .dueDate(LocalDate.of(2021, 6, 11))
            .responsible("jimmy@mail.com")
            .status("Pending")
            .build());
    todoRepository.save(
        Todo.builder()
            .id(10L)
            .description("Chase the devil")
            .priority(6)
            .dueDate(LocalDate.of(2022, 4, 30))
            .responsible("bob@mail.com")
            .status("Not gonna do")
            .build());

    // second
    todoRepository.save(
        Todo.builder()
            .id(11L)
            .description("Deliver a message to God")
            .priority(10)
            .dueDate(LocalDate.of(2025, 10, 12))
            .responsible("juan@mail.com")
            .status("Pending")
            .build());

    todoRepository.save(
        Todo.builder()
            .id(12L)
            .description("Capture the flag")
            .priority(9)
            .dueDate(LocalDate.of(2020, 12, 3))
            .responsible("nicolas@mail.com")
            .status("In progress")
            .build());

    todoRepository.save(
        Todo.builder()
            .id(13L)
            .description("Capture a Mine")
            .priority(8)
            .dueDate(LocalDate.of(2020, 12, 31))
            .responsible("paola@mail.com")
            .status("In progress")
            .build());

    todoRepository.save(
        Todo.builder()
            .id(14L)
            .description("Contend with the horsemen")
            .priority(6)
            .dueDate(LocalDate.of(2024, 1, 12))
            .responsible("alice@mail.com")
            .status("Pending")
            .build());
    todoRepository.save(
        Todo.builder()
            .id(15L)
            .description("Sell a kidney")
            .priority(5)
            .dueDate(LocalDate.of(2022, 10, 25))
            .responsible("freddy@mail.com")
            .status("Not gonna do")
            .build());
    todoRepository.save(
        Todo.builder()
            .id(16L)
            .description("Battle a snail")
            .priority(8)
            .dueDate(LocalDate.of(2020, 11, 15))
            .responsible("michael@mail.com")
            .status("Done")
            .build());
    todoRepository.save(
        Todo.builder()
            .id(17L)
            .description("Invade Poland")
            .priority(6)
            .dueDate(LocalDate.of(2025, 3, 12))
            .responsible("steve@mail.com")
            .status("Pending")
            .build());
    todoRepository.save(
        Todo.builder()
            .id(18L)
            .description("Hunt ghosts")
            .priority(5)
            .dueDate(LocalDate.of(2021, 8, 20))
            .responsible("charles@mail.com")
            .status("Pending")
            .build());
    todoRepository.save(
        Todo.builder()
            .id(19L)
            .description("Blow up the Vatican")
            .priority(9)
            .dueDate(LocalDate.of(2020, 12, 31))
            .responsible("jimmy@mail.com")
            .status("Pending")
            .build());
    todoRepository.save(
        Todo.builder()
            .id(20L)
            .description("Injure my ego")
            .priority(6)
            .dueDate(LocalDate.of(2020, 5, 28))
            .responsible("bob@mail.com")
            .status("Not gonna do")
            .build());

    // 5 more

    todoRepository.save(
        Todo.builder()
            .id(21L)
            .description("Battle my mother")
            .priority(8)
            .dueDate(LocalDate.of(2025, 3, 15))
            .responsible("michael@mail.com")
            .status("Pending")
            .build());
    todoRepository.save(
        Todo.builder()
            .id(22L)
            .description("Strike")
            .priority(6)
            .dueDate(LocalDate.of(2021, 3, 1))
            .responsible("steve@mail.com")
            .status("Pending")
            .build());
    todoRepository.save(
        Todo.builder()
            .id(23L)
            .description("Quarrel with my boss")
            .priority(5)
            .dueDate(LocalDate.of(2021, 1, 10))
            .responsible("charles@mail.com")
            .status("Pending")
            .build());
    todoRepository.save(
        Todo.builder()
            .id(24L)
            .description("Besiege Trojan")
            .priority(9)
            .dueDate(LocalDate.of(2025, 9, 10))
            .responsible("jimmy@mail.com")
            .status("Work in progress")
            .build());
    todoRepository.save(
        Todo.builder()
            .id(25L)
            .description("Patrol the sandwich and test if the mayonnaise is good")
            .priority(6)
            .dueDate(LocalDate.of(2020, 5, 28))
            .responsible("bob@mail.com")
            .status("Done")
            .build());

    System.out.println();

    // Todos where the dueDate has expired
    Query query = new Query();
    query.addCriteria(Criteria.where("dueDate").lt(LocalDate.now()));
    List<Todo> task_Expired = mongoOperation.find(query, Todo.class);

    System.out.println("--------Expired tasks--------");
    System.out.println("Number of tasks expired = " + task_Expired.size());

    // Todos that are assigned to given user and have priority greater equal to 5
    query = new Query();
    query.addCriteria(
        Criteria.where("responsible")
            .is("juan@mail.com")
            .andOperator(Criteria.where("priority").gte(5)));
    List<Todo> tasksAssignedPriority = mongoOperation.find(query, Todo.class);

    System.out.println(
        "Number of tasks assigned to user juan and priority > 5 = " + tasksAssignedPriority.size());

    // Users that have assigned more than 2 Todos.
    Aggregation agg =
        Aggregation.newAggregation(
            Aggregation.group("responsible").count().as("count"),
            Aggregation.project("count").and("_id").previousOperation(),
            Aggregation.match(Criteria.where("count").gt(2)));
    List<TodoCount> moreAssignes =
        mongoOperation.aggregate(agg, "todo", TodoCount.class).getMappedResults();

    System.out.println("Users that have assigned more than 2 Todos = " + moreAssignes.size());

    // Todos that contains a description with a length greater than 30 characters
    Aggregation agg2 =
        Aggregation.newAggregation(
            Aggregation.project(
                    "_id", "description", "priority", "dueDate", "responsible", "status")
                .andExpression("strLenBytes(description)")
                .as("strLength"),
            Aggregation.match(Criteria.where("strLength").gt(30)));
    List<Document> infoDescriptionLength =
            mongoOperation.aggregate(agg2, "todo", Document.class).getMappedResults();
        // Print result
    System.out.println(
        "Todos that have description grater than 30 characters = " + infoDescriptionLength.size());
  }
}
