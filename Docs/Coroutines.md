![1*zTLNOiNabCI7VE4b12dfwg](https://user-images.githubusercontent.com/94926624/172740151-a07130cb-0473-4467-be19-c653ef9e9d9b.png)

**Coroutines**
Concurrency is vital in programming. Running different tasks on different threads. 
But sometimes, we as developers can get carried away and create too many Thread objects which would eventually consume a lot of memory and time. Thankfully, Kotlin has introduced Coroutines which are light-weight threads.
Here, Co means cooperation and Routines means functions.
It means that when functions cooperate with each other, we call it as Coroutines.

we can start coroutine in three ways :
GlobalScope.launch{} - lifecycle of entire application
runBlocking{} - run coroutine in blocking way
coroutineScope.launch{} - does not complete until all children coroutine complete

`runblocking` used for unit testing or to call other suspend functions

`launch` is a coroutine builder. 
It launches a new coroutine concurrently with the rest of the code, which continues to work independently. 

`delay` is a special suspending function. It suspends the coroutine for a specific time. Suspending a coroutine does not block the underlying thread, but allows other coroutines to run and use the underlying thread for their code.

`Dispatchers` help coroutines in deciding the thread on which the work has to be done. There are majorly three types of Dispatchers which are as IO, Default, and Main. IO dispatcher is used to do the network and disk-related work. Default is used to do the CPU intensive work. Main is the UI thread of Android

`Suspend` function is a function that could be started, paused, and resume

`withContext` is nothing but another way of writing the async where we do not have to write await().


**The thumb-rules:**

Use withContext when you do not need the parallel execution.
Use async only when you need the parallel execution.
Both withContext and async can be used to get the result which is not possible with the launch.
Use withContext to return the result of a single task.
Use async for results from multiple tasks that run in parallel.


**Exception Handling**

While NOT using async, we can go ahead with the try-catch or the CoroutineExceptionHandler and achieve anything based on our use-cases.
While using async, in addition to try-catch, we have two options: coroutineScope and supervisorScope.
With async, use supervisorScope with the individual try-catch for each task in addition to the top-level try-catch, when you want to continue with other tasks if one or some of them have failed.
With async, use coroutineScope with the top-level try-catch, when you do NOT want to continue with other tasks if any of them have failed.



**Launch vs Async**

The difference is that the launch{} does not return anything and the async{}returns an instance of Deferred<T>, which has an await()function that returns the result of the coroutine like we have future in Java in which we do future.get() to the get the result.
  

launch: fire and forget
async: perform a task and return a result
 
  
  
**Flow**
n coroutines, a flow is a type that can emit multiple values sequentially, as opposed to suspend functions that return only a single value. For example, you can use a flow to receive live updates from a database.
  
  
  

  
References:
  https://blog.mindorks.com/mastering-kotlin-coroutines-in-android-step-by-step-guide
  
  
  
  
