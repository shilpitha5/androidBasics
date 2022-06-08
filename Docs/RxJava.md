![rxjava-cheat-sheet.pdf](https://github.com/shilpitha5/androidBasics/files/8865636/rxjava-cheat-sheet.pdf)



**RxJava**<br/>
RxJava is an implementation of the reactive extensions, a library for composing asynchronous and event-based programs using observable sequences.
As such, RxJava offers the implementations of the abstractions to create streams of events and items that you can subscribe to, in your code and react to the data flowing through those streams.
<br/>

**Basic RxJava Classes**
<br/>`Observable` - emits 0 or n items of T and terminates with complete or an error.
<br/>`Flowable` - It acts as an Observable, and emits 0 or n items. It terminates with complete or an error. But it also supports **back pressure**, which lets you control how fast the source emits items. It's really handy when you're building complex systems, since a fast producer of data cannot flood the system with items waiting to be processed.
<br/>`Single`  -  an Observable, which either emits a single item or throws an error. 
<br/>`Maybe`. - succeeds with either an item, no item, or errors. 
<br/>`Completable` - either completes or returns an error. It never return items. 
<br/>These are responsible for creating the streams and producing the data.
<br/>On the other side, data is processed via Observers. We'll talk about them in a moment.
<br/>

**Observables in RxJava**
<br/>Observable.just("RebelLabs");
<br/>Observable.fromIterable(iterable);
<br/>Observable.fromCallable(callable);
<br/>

**Subscribing to Observables**
<br/> Observers provide a mechanism for receiving data and notifications from Observables using the following API:
<br/>
<br/>`onNext(T t)` - provides the Observer with a new item to observe.
<br/>`onError(Throwable e) `- notifies the Observer that the Observable has experienced an error condition.
<br/>`onComplete() `- notifies the Observer that the Observable has finished sending push-based notifications.
<br/><br/>To subscribe to the observables you just implement the Observer interface, put your business logic into the onNext method while using the other two manage the lifecycle notifications from the observable.
<br/>

**Publishing to Multiple Observers in RxJava**
<br/>` publish()`  subscribe multiple Observers to an Observable. It won't emit any items until you call the connect method. 
<br/>
```
_Observable topic = Observable.publish().autoConnect(2); 
<br/>topic.subscribe(subscriber1); 
<br/>topic.subscribe(subscriber2);_
```
 `autoConnect(int)` Number of observers it is going to connect
 <br/>
 
**Data Processing Functions in RxJava**
<br/>
`map(Function<? super T,? extends R> mapper)` - applies a function to each of the items, and emits the returned values.
<br/>
`filter(Predicate<? super T> predicate)` - emits only the items satisfying a predicate.
<br/>
`buffer(int count)` - emits lists of the items of the specified size.
<br/>
`zip(ObservableSource s1, ObservableSource s2, BiFunction<T1, T2, R> f)` - applies a function to the items from multiple observables and emits the returned value.
<br/>
`flatMap(Function<? super T,? extends ObservableSource<? extends R>> mapper)` - takes a function from items to an Observable, emits the items of the resulting Observables
<br/>
`groupBy(Function<? super T,? extends K> keySelector)` - emits items grouped by a specified key selector function
<br/>
`timeout(long timeout, TimeUnit timeUnit)` - emits items of the original observable. If the next item isn't emitted within the specified timeout, a TimeoutException occurs.
</br>

**Testing Observables in RxJava**
`TestSubscriber` is a subscriber that records events that you can make assertions upon.
`TestObserver` is an Observer that records events that you can make assertions upon.

```
TestSubscriber ts = Flowable.range(1, 5).test();
// assert properties of the flow
assertThat(ts.values()).hasSize(5); 
 ```






















Reference Link:
https://www.jrebel.com/blog/rxjava-cheat-sheet
