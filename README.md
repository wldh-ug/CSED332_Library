# Homework 2
> **[Jio Gim](mailto:jio.gim@postech.edu)**, Creative IT Engineering, POSTECH  
> **Student ID:** 20160087, **Povis ID:** iknowme

## String Representations
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

<<<<<<< HEAD
+ **Library** (Data Save & Load)  
=======
+ **Library** (Data Save & Load)
>>>>>>> d6de434a5a0dc874800e1870393315e1d9bded41
    I used [Kryo](https://github.com/EsotericSoftware/kryo) and [Jasypt](http://www.jasypt.org/). Because I don't like DB to be exposed.  

## Additional Functions
- `Book.escape` A function for escape semicolons to make string representation.  
- `Book.unescape` A function for unescape semicolons to interpret string representation.  
- `Library.addCollection` A function for addition of collections to specific library. 

## Troubleshoot
#### If characters from test result is broken
Please set your console's code page to **UTF-8(65001)**.

#### `mvn jacoco:report` successfully executed, but no reports created
Please move repository to the path which does not cotain non-ASCII characters.  

#### `mvn test` success message does not appears
- **Option 1:** Please turn off [UTF-8(Beta)](https://superuser.com/questions/1332086/input-corrupted-on-windows-10-after-1083-update-ascii-chars-are-replaced-with) feature on latest Windows 10. It's not stable, so Microsoft basically disabled by default this feature for now.  
- **Option 2:** Please use [WSL](https://docs.microsoft.com/ko-kr/windows/wsl/install-win10) shell instead of CMD or PowerShell.  
