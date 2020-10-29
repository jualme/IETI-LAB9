# 2.3 JPA with MongoDB
Create a Spring Boot Application that connects with MongoDB.

## Part 1: Basic Mongo DB configuration and Spring Boot Integration
1. Create a MongoDB Atlas account on [https://www.mongodb.com/atlas-signup-from-mlab](https://www.mongodb.com/atlas-signup-from-mlab):

    **Done**

6. Run the project and verify that the connection to the database works properly. Answer the following questions:

![](img/lab/Conection.png)

- How many customers were created in the database?

  **5 customers were created**
  ![](img/lab/Customers1.png)
- Where is the *findAll* method implemented?

  **The "FindAll" method is implemented by MongoRepository** 
  ![](img/lab/MongoRepository.png)
- Suppose you have more than 1000 products in your database. How would you implement a method for supporting pagination and return pages of 50 products to your frontend?
  
  
- How many products contain the "plus" word in their description?
 **4**
  ![](img/lab/plusFilter.png)

- How many products are returned by the *findByDescriptionContaining* query? Why?
  **2**
  ![](img/lab/PaginatedResults.png)
  
  Because the page is set to show only 2 records per page

- Which are the collection names where are the objects stored? Where are those names assigned?

  The Collection names are: Customer and Product
  
  The objects are volatile, after the information is sent to the front the object is removed to avoid dirty information
  
  These names are assigned based on the names of the classes in the model folder 

5. Create two more models (User and Todo) with the following structure:

    User
    ````Javascript
        
        {
            "id": "12354",
            "name": "Charles Darwin",
            "email": "charles@natural.com"
        }
        
     
    ````     
    
    Todo
    ````Javascript
        
        {
            "description": "travel to Galapagos",
            "priority": 10,
            "dueDate": "Jan 10 - 1860"
            "responsible": "charles@natural.com"
            "status": "pending"
        }
    ````                  
    
    
6. Create a repository Users and Todo

**Done**

## Part 2: Custom configuration and Queries

7. Create the following queries using the Query class:

    * Todos where the dueDate has expired
    
    ```Number of tasks expired = 5```
    
    * Todos that are assigned to given user and have priority greater equal to 5
    
    ```Number of tasks assigned to user juan and priority > 5 = 2```
    
    * Users that have assigned more than 2 Todos.
    
    ```Users that have assigned more than 2 Todos = 5```
    
    * Todos that contains a description with a length greater than 30 characters     
    
    ```Todos that have description grater than 30 characters = 1```   

8. Implement the queries of the previous step using *derived query methods* in your repository interface. Is it possible to implement all of them?

**Done**

 ![](img/lab/Querries.png)
 
 - the third, counting requires aggregations, therefore it cannot be done with normal queries