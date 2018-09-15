# Homework 2
> **[Jio Gim](mailto:jio.gim@postech.edu)**, Creative IT Engineering, POSTECH  
> **Student ID:** 20160087, **Povis ID:** iknowme

## Data Save & String Representations
#### String Representations
+ **Book**  
    I used `;(semicolon)` instead of JSON. Because of time lack so I didn't tested about this, but I think that using JSON stringifier/parser on every Book instance may use a lot of memory resources when if there're astronomical amounts of books.  
    ```prolog
    Title: "The Restless Wave: Good Times, Just Causes, Great Fights, and Other Appreciations"
    Authors: "John McCain" and "Mark Salter"
     = 'The Restless Wave: Good Times, Just Causes, Great Fights, and Other Appreciations;John McCain\;Mark Salter'
    
    Title: "Null Book"
    Authors: "AuthorA" and "A;u;t;h;o;r;B"
     = 'Null Book;AuthorA\;A\\;u\\;t\\;h\\;o\\;r\\;B'
    ```  
+ **Collection**  
    Amounts of collections are highly limited, and structure is more complicated, so I think it is more efficient to use JSON in this part.  

#### Data Save & Load (SR of Library)
I used [Kryo](https://github.com/EsotericSoftware/kryo).

## Troubleshoot
#### If characters from test result is broken
Please set your console's code page to **UTF-8(65001)**.
