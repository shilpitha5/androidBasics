## **Describe different types of Services in Android**

A Service is an application component that can perform long-running operations in the background, and it doesn't provide a user interface. It can run in the background, even when the user is not interacting with your application. These are the three different types of services:

`Foreground Service`: A foreground service performs some operation that is noticeable to the user. For example, we can use a foreground service to play an audio track. 

`Background Service`: A background service performs an operation that isn’t directly noticed by the user. In Android _**API level 26 and above**_, there are restrictions to using background services and it is recommended to use **_WorkManager_** in these cases.

`Bound Service`: A service is bound when an application component binds to it by calling bindService(). A bound service offers a client-server interface that allows components to interact with the service, send requests, receive results. A bound service runs only as long as another application component is bound to it. 


## **Retained Fragments**

By default, Fragments are destroyed and recreated along with their parent Activity’s when a configuration change occurs.
Calling `setRetainInstance(true)` allows us to bypass this destroy-and-recreate cycle, signaling the system to retain the current instance of the fragment when the activity is recreated.


## **DEX FILES**

A .java file is given to the java compiler (javac) to generate the .class file. All .class files are given to dx tool to generate a single dex file. The dex file is given to the Dalvik VM to generate the final machine code. The final machine code is given to the CPU to execute.

.apk file contains .dex file in zip format which can be run on Dalvik VMs

![CnNSD](https://user-images.githubusercontent.com/94926624/173451204-0ef24f99-2d0a-4c27-a3c6-71b72bb7dd52.png)


## **Permission level in android**

`Normal` — A lower-risk permission that gives requesting applications access to isolated application-level features, with minimal risk to other applications, the system, or the user. The system automatically grants this type of permission to a requesting application at installation, without asking for the user’s explicit approval.

`Dangerous` — A higher-risk permission. Any dangerous permissions requested by an application may be displayed to the user and require confirmation before proceeding, or some other approach may be taken to avoid the user automatically allowing the use of such facilities.

`Signature` — A permission that the system grants only if the requesting application is signed with the same certificate as the application that declared the permission. If the certificates match, the system automatically grants the permission without notifying the user or asking for the user’s explicit approval.

`SignatureOrSystem` — A permission that the system grants only to applications that are in the Android system image or that are signed with the same certificate as the application that declared the permission.


## **Handler vs AsyncTask vs Thread**

The Handler class can be used to register to a thread and provides a simple channel to send data to this thread. A Handler allows you communicate back with the UI thread from other background thread.

The AsyncTask class encapsulates the creation of a background process and the synchronization with the main thread. It also supports reporting progress of the running tasks.

And a Thread is basically the core element of multithreading which a developer can use with the following disadvantage:
Handle synchronization with the main thread if you post back results to the user interface
No default for canceling the thread
No default thread pooling
No default for handling configuration changes in Android


