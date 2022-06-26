
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


