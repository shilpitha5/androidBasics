**Android Data Binding**

The Data Binding Library is a support library that allows you to bind UI components in your layouts to data sources in your app using a declarative format rather than programmatically.

Layouts are often defined in activities with code that calls UI framework methods. For example, the code below calls findViewById() to find a TextView widget and bind it to the userName property of the viewModel variable:

```
TextView textView = findViewById(R.id.sample_text);
textView.setText(viewModel.getUserName());
```

The following example shows how to use the Data Binding Library to assign text to the widget directly in the layout file. This removes the need to call any of the Java code shown above.

```
<TextView
    android:text="@{viewmodel.userName}" />
```
    
The pros of using Android Data Binding:

Reduces boilerplate code which in turns brings
Less coupling
Stronger readability
Powerful, easy to implement custom attribute and custom view
Even faster than findViewById - The binding does a single pass on the View hierarchy, extracting the Views with IDs. This mechanism can be faster than calling findViewById for several Views.

-----------------------------------------------------------------------------------------------------------------------------

**What is the ViewHolder pattern? Why should we use it?**

Every time when the adapter calls getView() method, the findViewById() method is also called. This is a very intensive work for the mobile CPU and so affects the performance of the application and the battery consumption increases. ViewHolder is a design pattern which can be applied as a way around repeated use of findViewById().

A ViewHolder holds the reference to the id of the view resource and calls to the resource will not be required after you "find" them: Thus performance of the application increases.

View.setTag(Object) allows you to tell the View to hold an arbitrary object. If we use it to hold an instance of our ViewHolder after we do our findViewById(int) calls, then we can use View.getTag() on recycled views to avoid having to make the calls again and again.





