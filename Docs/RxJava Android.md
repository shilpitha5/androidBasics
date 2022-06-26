## RxJava:

The major components of RxJava are Observer, Observable, Operator </br>

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

## Rx Operators
#### Observable.merge()
It is used to merge two to four observables which emit the same type of items T. The output will not be in sequential format. 

```
val observable1 : Observable<String> =  Observable.just("Hello")
val observable2 : Observable<String> =  Observable.just("World")
val observable3 : Observable<String> =  Observable.just("!!!")
val observable4 : Observable<String> =  Observable.just("Welcome")
val observable5 : Observable<String> =  Observable.just("To")
val observable6 : Observable<String> =  Observable.just("RxJava")
```
```
Observable.merge(observable1, observable2)
    .subscribe {item->
        Log.d(TAG, "received : $item") // Output: received : "Hello" received : "World"
    }
 ```
#### Observable.concat()
It is used to merge two to four observables which emit the same type of items T. The output will be always in sequential format

#### Observable.mergeArray()
Used to merge more than 4 number of observables
```
Observable.mergeArray(observable1,
                      observable2,
                      observable3, 
                      observable4,
                      observable5)
          .subscribe {item->
               Log.d(TAG, "received string : $item")
           }
```

### Cold Observable

Cold Observables are much like a music CD. As you know, a music CD can be replayed to each listener so that each person can hear all the tracks at any time. Similarly, cold Observables will emit the same emissions to each observer, ensuring that all Observers get all the data. Most data-driven Observables are cold, and this includes the Observable.just() and Observable.fromIterable() factories.
```
val coldObservable : Observable<String> = Observable.just("1", "2", "3", "4")
                                         .doOnComplete {
                                              Log.e(TAG, "Completed emitting streams")
                                         }
```
Now attach two different observer to it
```
// First observer
val firstObserver= coldObservable.subscribe {
    Log.d(TAG," observer 1 receveied: = $it")
}
// second Observer
val secondObserver= coldObservable.subscribe {
    Log.d(TAG," observer 2 receveied: = $it")
}
```
So let’s understand what happens here :
We have two Observers subscribed to one Observable. The Observable will first emit all the emissions to the first observer and then call onComplete(). Then, it will emit all the emissions again to the second observer and call onComplete(). They both receive the same datasets by getting two separate streams each, which is typical behavior for a cold Observable.

### Hot Observable :
Let’s understand the hot observable by a radio station example. Let’s say a radio station broadcasting some message, a listener (or observer) comes and starts listening to that message. As the radio station continues broadcasting, a second listener (or observer) arrives and starts listening. Here second listener will miss the previous message that was broadcasted before he came. He will only receive the later part of the message.
Similarly, hot observable will not replay the same emission again to the second observer. Here we have only one stream of data. If the second observer comes, let’s say after 3 seconds, then the second observer will miss the initial emission that was done before 3 seconds and will only listen to later emissions.
Let’s understand this by a example :

```
val hotObservable = Observable.interval(1, TimeUnit.SECONDS)
                   .doOnComplete {
                      Log.e(TAG, "Completed emitting streams")
                   }.publish()
hotObservable.connect()
```
```
val firstObserver = hot.subscribe {
    Log.e(TAG," observer 1 receveied: = $it")
}
Thread.sleep(3000);
val secondObserver = hot.subscribe {
    Log.e(TAG," observer 2 receveied: = $it")
}
```
Output: <br/>
observer 1 receveied: = 0 <br/>
observer 1 receveied: = 1 <br/>
observer 1 receveied: = 2 <br/>
observer 1 receveied: = 3 <br/>
observer 2 receveied: = 3 <br/>
observer 1 receveied: = 4... <br/>

Here what’s happening, after 3 seconds, both observer listening to the same stream in an interleaved fashion. The second observer misses the starting three emissions. <br/>
Now let’s see what publish and connect doing here. <br/>
Well publish the operator is used to convert observable to a ConnectableObservable. And ConnectableObservable is an observable source that waits to emit the items until its connect method is called.
It will take any observable, even if it is cold, and make it hot so that all emissions are played to all observers at once. To force each emission to go to all observers simultaneously is known as multicasting.


### Subjects: Rxjava
Subjects act as an observer as well as observable. It means you can call onNext(), onComplete(), and onError() on a Subject, and it will, in turn, pass those events downstream toward its Observers.

Let’s discuss what the types of subjects are and how and where to use them.

#### PublishSubject:
It’s the simplest form of the subject; there are other subjects that changes its behavior based on their implementation. PublishSubject acts as hot observable. 

Let’s see how we can create a publish subject. Let’s do for the string.
```
val basicSubject: PublishSubject<String> = PublishSubject.create()
//now this basicSubject is an observable, we can subscribe to it and receive the emitted strings.
val observer = basicSubject.subscribe({
    Log.d(TAG, "received string:= $it")
}, {
    it.printStackTrace()
})
```
Now let’s call onNext on observable:
```
basicPublishSubject.onNext("hello")
basicPublishSubject.onNext("Kotlin")
basicPublishSubject.onComplete()
```
Output:
received string:= hello
received string:= Kotlin


Now let’s see what happens if we subscribe to this observable after calling onNext
```
//subject declaration 
val basicSubject: PublishSubject<String> = PublishSubject.create()
basicSubject.onNext("hello")
//attach subscriber to it
val observer = basicPublishSubject.subscribe({
    Log.d(TAG, "received string:= $it")
}, {
    it.printStackTrace()
})
basicPublishSubject.onNext("Kotlin")
```
So what do you think what will be the output here, as it’s hot observable, it will receive emission that are emitted after subscription. <br/>
Output:
received string:= Kotlin


Now let’s see how subjects work as an observer. We can pass the subjects to subscribe() like in the below example:
```
val observableSource1 : Observable<String> =  Observable.just("1", "2", "3", "4")
val basicSubject: PublishSubject<String> = PublishSubject.create()
basicSubject.subscribe({
    Log.d(TAG, "received string:= $it")
},{
    it.printStackTrace()
})
//pass this subject as a observer to subscribe method
observableSource1.subscribe(basicSubject)
```
Output:<br/>
received string:= 1<br/>
received string:= 2<br/>
received string:= 3<br/>
received string:= 4<br/>

### BehaviorSubject
It’s somewhat similar to publish subject, but it will replay the last emitted item to each newly attached Observer.
```
val behaviorSubject: BehaviorSubject<String> = BehaviorSubject.create()
behaviorSubject.subscribe{ Log.d(TAG, "observer 1 received string:= $it") }
behaviorSubject.onNext("hello")
behaviorSubject.onNext("there")
behaviorSubject.onNext("Kotlin")
behaviorSubject.subscribe { Log.d(TAG, "observer 2 received string:= $it") }
behaviorSubject.subscribe { Log.d(TAG, "observer 3 received string:= $it") }
```
Output:<br/>
observer 1 received string:= hello<br/>
observer 1 received string:= there<br/>
observer 1 received string:= Kotlin<br/>
observer 2 received string:= Kotlin<br/>
observer 3 received string:= Kotlin<br/>
Here Observer two and Observer three are subscribing after all onNext called. That’s why Observer two and observer three received the last emitted item. If it was the publish subject, Observer two and Observer three will not receive anything, as they are called after all emissions.

### ReplaySubject
As the name suggests, it will replay all the emissions to the newly attached Observer. Replay subject will cache all the emissions, and then whenever the Observer is attached, it will replay all those emissions.
Output:<br/>
observer 1 received string:= hello<br/>
observer 1 received string:= there<br/>
observer 1 received string:= Kotlin<br/>
observer 2 received string:= hello<br/>
observer 2 received string:= there<br/>
observer 2 received string:= Kotlin<br/>
You need to be careful with this subject in case of infinite observable sources, as it will cache all the data and will take memory.

### AsyncSubject:
Async Subject will only emit the last received item before onComplete called.
Be careful in case of the infinite observable source as it will emit the last item that is received before onComplete called.

```
val asyncSubject: AsyncSubject<String> = AsyncSubject.create()
asyncSubject.onNext("hello")
asyncSubject.onNext("there")
asyncSubject.onNext("Kotlin")
asyncSubject.onComplete()
asyncSubject.subscribe {
 Log.d(TAG, "observer 1 received string:= $it") 
}
asyncSubject.subscribe {
 Log.d(TAG, "observer 2 received string:= $it")
 }
asyncSubject.subscribe {
 Log.d(TAG, "observer 3 received string:= $it") 
}
```
Output:<br/>
observer 1 received string:= Kotlin<br/>
observer 2 received string:= Kotlin<br/>
observer 3 received string:= Kotlin<br/>

### UnicastSubject
This subject will buffer all the emissions it receives until an Observer subscribes to it. After that, it will immediately release all the items to the Observer and then clear it’s cache. The unicast subject can only be subscribed by one Observer, if you try to attach another Observer to it, it will receive an error.
```
val unicastSubject: UnicastSubject<String> = UnicastSubject.create()
 val observable = Observable.interval(300, TimeUnit.MILLISECONDS)
     .map {
         "${(it+1)*300} milliseconds"
     }.subscribe(unicastSubject)

 Thread.sleep(2000);

 unicastSubject.subscribe { Log.d(TAG, "observer 1 received string:= $it") }
 Thread.sleep(2000);
 ```
Output:<br/>
observer 1 received string:= 300 milliseconds<br/>
observer 1 received string:= 600 milliseconds<br/>
observer 1 received string:= 900 milliseconds<br/>
observer 1 received string:= 1200 milliseconds<br/>
observer 1 received string:= 1500 milliseconds<br/>
observer 1 received string:= 1800 milliseconds<br/>
observer 1 received string:= 2100 milliseconds<br/>
observer 1 received string:= 2400 milliseconds<br/>
observer 1 received string:= 2700 milliseconds<br/>
observer 1 received string:= 3000 milliseconds<br/>
observer 1 received string:= 3300 milliseconds<br/>
observer 1 received string:= 3600 milliseconds<br/>
observer 1 received string:= 3900 milliseconds<br/>

What happened here?<br/>
Here, after 2 seconds, the first six items will be released immediately to the Observer when it subscribes. After that, it will receive one by one.






References:
https://0202gaurav.medium.com/observable-vs-observer-rxjava-8739b7612a54
