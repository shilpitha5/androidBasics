# Android Basics

**ACTIVITY**

An Android activity is one screen of the Android app's user interface


**ACTIVITY LIFE CYCLE**


![1*THx3BAsedNkEd_LcSWzuRA](https://user-images.githubusercontent.com/94926624/169598271-da059c72-f0d9-4c6e-822c-8ae7876979c6.png)

**Scenerio based question**

When the First activity is launched

<img width="378" alt="Screen Shot 2022-05-20 at 3 56 41 PM" src="https://user-images.githubusercontent.com/94926624/169602022-bec7b6b8-a614-41dd-9b3c-295f7dc921fc.png">

When the Second Activity is launched


<img width="379" alt="Screen Shot 2022-05-20 at 3 58 03 PM" src="https://user-images.githubusercontent.com/94926624/169602164-308d9d36-ac6b-4451-99e5-eaca8bf6b284.png">


When the back button is pressed from second activity to first activity

<img width="401" alt="Screen Shot 2022-05-20 at 3 59 02 PM" src="https://user-images.githubusercontent.com/94926624/169602282-dd167e51-f470-42e8-94e8-7297cc961945.png">


When the home button is called from first activity

<img width="363" alt="Screen Shot 2022-05-20 at 3 59 33 PM" src="https://user-images.githubusercontent.com/94926624/169602342-ca995ca8-be55-4026-aad4-9e41d96ca86d.png">

When the activity launched from recent app (Coming back to app from home screen)

<img width="405" alt="Screen Shot 2022-05-20 at 4 00 21 PM" src="https://user-images.githubusercontent.com/94926624/169602443-12d16c2b-c0fd-44cb-ba19-9e694fe5dfc4.png">

When the semi transparent activity is launched

<img width="369" alt="Screen Shot 2022-05-20 at 4 01 38 PM" src="https://user-images.githubusercontent.com/94926624/169602589-ef2d96bb-8c7e-44b4-a751-3d93d33a8a74.png">




In simple words [refered from SO https://stackoverflow.com/a/9113112]

onCreateAndPrepareToDisplay()   [instead of onCreate() ]
onPrepareToDisplay()            [instead of onRestart() ]
onVisible()                     [instead of onStart() ]
onBeginInteraction()            [instead of onResume() ]
onPauseInteraction()            [instead of onPause() ]
onInvisible()                   [instead of onStop]
onDestroy()                     [no change] 


Uses:
OnPause : The data needs to be saved in this state, since sometimes the proccess is killed by the system due to memory issue. onStop is not guaranted to call everytime.


In Depth
**onStart to onresume** at this stage ，Activity created, layout loaded ， but the interface has not been drawn yet, so it can be said that the interface does not exist.

**onPause to onstop**. at this stage  Activity there is an interface, but it is obscured by the new interface. ， which means it's not at the front desk.

so combining the two stages, we put this kind of Activity created or displayed, but not at the front desk ， the state in between is called visible status.


**onStart and onstop** ， it is designed from the point of view of whether activity is visible or not. 。
**onResume and onpause** ， it is designed from the point of view of whether the activity is located at the front desk. 。
so Activity the life cycle can be interpreted as ：

be created （onCreate）——> visible （onStart）——> located at the front desk （onResume）——> visible but not at the front desk （onPause）


Links Referred:
https://developpaper.com/how-to-understand-onstart-visible-but-not-interactive/





