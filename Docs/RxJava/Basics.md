## RxJava:
RxJava is a JVM library that uses observable sequences to perform asynchronous and event-based programming. <br/>
Its primary building blocks are triple O’s, which stand for Operator, Observer, and Observables. <br/>
And we use them to complete asynchronous tasks in our project. It greatly simplifies multithreading in our project. It assists us in determining which thread we want to run the task on. </br>

In simple words:
- Observable:- able to be noticed or perceived
- Observer:- a person who watches or notices something

Observable is something that can be observed. Or observable is a source that we can observe for something like data

For what observable can be observed, it could be any data. For example, you are watching movies on your laptop, so here your computer is observable that is emitting movies, and you are the observer who is receiving that data.

Similarly, in RxJava, Observable is something that emits some data or event, and an observer is something that receives that data or event.

```
val observable: Observable<T> = Observable.just(item : T). // Here observable will emit item T
```

### How do Observable works?
An Observable works through its onNext(), onCompleted(), and onError() calls.
At the highest level, an Observable works by passing three types of events:
- onNext(T):- used to emit item(of type T) one at a time all the way down to the observer
- onComplete():- communicates that all data has been emitted or indicates that no item will be emitted after this call.
- onError():- communicates an error

```
val observable: Observable<String> = Observable.just("Hello")
```
Observer will receive data by subscribing to it
```
observable.subscribe { s ->
    // received string s here
    Log.d(TAG, "received string:- $s") // output: Hello
}
```

`Observable.create()` is used to create an observable and `onNext` is used to emit data
```
val observable: Observable<String> = Observable.create<String> {
    it.onNext("hello")
    it.onNext("Kotlin")
    it.onComplete()
}
```
`subscribe` is used to recieve the data
```
observable.subscribe { s ->
   Log.d(TAG, "received string:- $s")  // received string:- hello.    received string:- Kotlin
}
```

**Subscribe** is the method to attach an observer to an observable. For that subscribe method, accept observer object as a parameter.

### How do we create an observer?

**The Observer interface**
```
public interface Observer<T> {
  void onSubscribe(Disposable d);
  void onNext(T value);
  void onError(Throwable e);
  void onComplete();
}
```

- `onNext(T value)`       :   receives T value emitted by observable.
- `onError(Throwable e)`  :   Used for error handling
- `onComplete()`          :   Called when observable is done emitting items.
- `onSubscribe()`         :   here we get disposable, which will be used to dispose of the stream, or we can say to unsubscribe the observable.

Now let’s get back to the previous example :
Observable:
```
val observable: Observable<String> = Observable.create<String> {
    it.onNext("hello")
    it.onNext("Kotlin")
    it.onComplete()
}
```
Create Observer:
```
val observer: Observer<String> = object : Observer<String> {
    override fun onComplete() {
        // onComplete called of observer
    }
    override fun onSubscribe(d: Disposable) {
       // onSubscribe called of observer 
    }
    override fun onNext(t: String) {
       // onNext called of observer
Log.d(TAG, "received string: $t ")
    }
    override fun onError(e: Throwable) {
        // onError called of observer
    }
}
```
Pass this observer to subscribe :
```
observable.subscribe(observer).  // received string:- hello   received string:- Kotlin
```

Instead of creating observer like above, shortened the observer by lambda:
```
observable.subscribe(
{ // onNext called of observer
   Log.d(TAG, "received string: $t ")
  },
{ // onError called of observer
  },
{ // onComplete called of observer
  },
{ // onSubscribe called of observer
  })
 ```
Shortened the observer with only required method
```
observable.subscribe{ s ->
   Log.d(TAG, "received string:- $s")
}
```
Here we are implementing only onNext .

### RxAndroid
RxAndroid is a RxJava for Android extension that is only used in Android applications. RxAndroid added the Android-required Main Thread. We will need the Looper and Handler for Main Thread execution in order to work with multithreading in Android.  AndroidSchedulers are provided by RxAndroid.<br/>

mainThread() returns a scheduler, which aids in performing tasks on the main UI thread, which is primarily used in the Android project. So, here we have AndroidSchedulers. mainThread() is used to gain access to the application’s main thread so that we can perform actions such as updating the UI. Because updating the UI from the background thread is technically not possible in Android, we can use AndroidSchedulers.mainThread() to update anything on the main thread. Internally, it makes use of the Handler and Looper concepts to perform the action on the main thread.


References:
https://0202gaurav.medium.com/observable-vs-observer-rxjava-8739b7612a54
