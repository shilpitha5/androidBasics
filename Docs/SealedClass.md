## Enum Class
An enum is a special "class" that represents a group of constants (unchangeable variables, like final variables). Enum classes are clearly useful when it comes to handling state.

```
enum class Response{
    LOADING,
    SUCCESS,
    FAILURE
}
```

## Abstract Class
An abstract class is a class whose functionality has not yet been implemented. It can be used to create specific objects that conform to its protocol.

```
abstract class Response

data class Success(val data:String?):Response()
data class FAILURE(val exception: Exception):Response()
object Loading:Response()
```

## Sealed Class
A sealed class is an abstract class with a restricted class hierarchy. Classes that inherit from it have to be in the same file as the sealed class

```
sealed class Response(){
    data class Success(val data:String?):Response()
    data class FAILURE(val exception: Exception):Response()
    object Loading:Response()
}
```

### Sealed class vs abstract class
The only difference between an abstract class and a sealed class is that the compiler generates some metadata for sealed classes and can warn you about the missing branches when you use the when keyword which can't be done using abstract classes.


