- Upload app with app bundles
- Use R8
- 

## App Bundles
App bundles are publishing format, whereas APK (Android application Package) is the packaging format which eventually will be installed on device.

Google uses app bundle to generate and serve optimized APKs for each user’s device configuration, so they download only the code and resources they need to run your app. Therefore, users can get smaller and more optimized downloads.

Following are benefits of Android App Bundle over APK on PlayStore:
- Smaller Download Size
- On Demand App features
- Asset-only modules

## R8
Tool to optmize app for release and also converts our java byte code into an optimized dex code
- remove unsed functions, class and methods. _(eg: while using multiple libraries it has lot of code, it will only add the code whatever necessary for the app)_
- remove unused resources 
- optimize the code. _(perform direct fuction by removing unnecessay loops)_
- obfuscate the code. _(converts the code to unreadable names, so the user who reverse engineer the app could not be able to understand the classes)_
- faster than the proguard
- reduce size by 10% where as proguard reduces 8.5%
- The conversion process of converting apps code to Optimized Dalvik bytecode is as follows : Apps code > Java bytecode > Optimized Dalvik bytecode. where as in proguard its 4 steps: Apps Code > Java bytecode > Optimized java bytecode > Optimized Dalvik bytecode

## Setup Proguard

```
buildTypes{
  release{
      minifyenabled true    // obfuscate the code remove all unused things
      shrinkResources true  // removes unused resources
      proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'

  }
}
```
Here for debug we dont apply R8 since developer need to understand where the error has occures by reading in stacktrace. If applied, then the names will be shown obfuscated in stacktrace

In proguard we can declare classes that dont need obfuscate or optimize \
For example we should never obfuscate `data` class, since while performing networking calls we want the field name same as json fields, if obfuscated then the field will be renamed and it will never be known which data class belongs to given json response


We can use @keep annotation to class for not obfuscating the code. This annotation can be used for particular class.

If we want to kepp all the model classes we can define as below
```
-keep class pakagename.models.*
-keep class pakagename.models.* { funName}.    // it will only keep the specific function and the rest will be obfuscated
```



## Read obfuscate code from the stacktrace
R8 will generate mapping file which is the text file and explains which class, funs or fields are renamed to what etc. This file can be uploaded to google along with the AAB and google play will translate error message from user to the developer in a readable format.

It will be found in app/build/output while building the aab. It is named as mapping.txt file inside mapping/release and in that file we can see how the data are mapped. We need to take backup of these files when placing the release build in google play. since it will generate differently on different builds.


