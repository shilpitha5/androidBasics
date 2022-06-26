
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


Ref: https://0202gaurav.medium.com/subjects-rxjava-f08902f25dca
