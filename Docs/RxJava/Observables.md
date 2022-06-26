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



Ref: https://0202gaurav.medium.com/hot-and-cold-observable-rxjava-5a0d87821423
