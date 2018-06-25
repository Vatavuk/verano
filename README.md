# Verano
### Java dependency injection framework
[![EO principles respected here](http://www.elegantobjects.org/badge.svg)](http://www.elegantobjects.org)
[![DevOps By Rultor.com](http://www.rultor.com/b/Vatavuk/verano)](http://www.rultor.com/p/Vatavuk/verano)

Verano provides convenient way to wire up dependencies 
without using reflections, type castings, annotations or component scan. It takes 
different approach to classic DI containers and relies on wiring dependencies through 
a main entry point of an application. To simplify/enhance this wiring process it provides 
a set of objects through user can declare dependencies and conditions.

Core features:
- Advanced wiring using profiles and qualifiers
- Profile-Specific configuration management
- Swapping implementations at runtime
- Full control of dependency life cycle
- Compile time safety

Every part of the framework is open for extension and modification due to
its OOP nature. It was built using objects only. There is no single 
if/for/while/throw/catch or similar statement present in the codebase.

### Quick Start

Let's create a very simple model which prints items in an order. 
```java
public class MyOrder implements Order {

    private final Items items;

    public UserOrder(Items items) {
        this.items = items;
    }

    @Override
    public void showItem(String id) {
        this.items.printItem(id);
    }
}
```
```java
public class RealItems implements Items {

    @Override
    public void printItem(String id) {
        System.out.println(String.format("Real item %s", id));
    }
}
```
```java
public class TestItems implements Items {

    @Override
    public void printItem(String id) {
        System.out.println(String.format("Test item %s", id));
    }
}
```
We have two implementations of `Items`, one for test environment and one
for production. In order to inject the right implementation into `MyOrder` we
will create `ItemsComponent` and `OrderComponent` using Verano's component
system.
```java
public class ItemsComponent extends VrComponent<Items> {

    public ItemsComponent(AppContext context) {
        super(context,
            new VrInstance<>(
                () -> new RealItems(),
                new ProfileWire("prod")
            ),
            new VrInstance<>(
                () -> new TestItems(),
                new ProfileWire("test")
            )
        );
    }
}
```
```java
public class OrderComponent extends VrComponent<Order> {

    public OrderComponent(AppContext context) {
        super(context,
            new VrInstance<>(
                () -> new MyOrder(new ItemsComponent(context).instance())
            )
        );
    }
}
```
Finally we hook things up int the main class and start the application with
argument --profile=prod or --profile=test.
```java
public class Main {

    public static void main(String[] args) throws Exception {
        AppContext context = new VrAppContext(args);
        Order order = new OrderComponent(context).instance();
        order.showItem("123");
    }
}
```
OTUPUT:

"Real item 123"  => profile=prod

"Test item 123"  => profile=test

Note that `OrderComponent` does not need to know how to construct `Items` 
it just uses `ItemsComponent` and let that component build it.

### Components
Component is a base building block for managing implementation objects. 
It acts as a factory class that determines which implementation is suitable 
for wiring based on users input. The difference between classic factory class 
is that it is not globally accessible and the logic of choosing the right instance 
is hidden from users. 

Verano provides two types of components, `VrComponent` which manages all its
instances like singletons and `VrRefreshableComponent` for which user can
control instance lifecycle.
In order to create a component simply extend `VrComponent` and provide desired
implementations.
```java
public class ItemsComponent extends VrComponent<Items> {

    public ItemsComponent(AppContext context) {
        super(context,
            new VrInstance<>(
                () -> new MyItems()  
            )
        );
    }
}
```
```java
public class Main {

    public static void main(String[] args) throws Exception {
        AppContext context = new VrAppContext(args);
        Items items = new ItemsComponent(context).instance();
    }
}
```
Calling method `instance` will return singleton instance.

#### Component Lifecycle control
In order to gain direct control of an instance lifecycle extend `VrRefreshableComponent`
and define instances using `VrCloseableInstance`:
```java
public class ItemsComponent extends VrRefreshableComponent<Items> {

    public ItemsComponent(AppContext context) {
        super(context,
            new VrCloseableInstance<>(
                () -> new MyItems() // implements Closeable
            )
        );
    }
}
```
```java
public class Main {

    public static void main(String[] args) throws Exception {
        AppContext context = new VrAppContext(args);
        VrRefreshableComponent<Items> component = new ItemsComponent(context);
        Items items = component.instance();
        Items refreshed = component.refreshed(); // creates new MyItems instance
                                                 // and closes previous one
    }
}
```

### Profiles and Qualifiers
For wiring instances conditionally, Verano provides you `ProfileWire` and `QualifierWire`.
`ProfileWire` condition wiring based on profile set through command line interface
via argument `--profile=${profile}`.
With `QualifierWire` user can specify, by a name, which instance will be used.
Lets observe the following example using both of those wires:
```java
public class ItemsComponent extends VrComponent<Items> {

    public ItemsComponent(AppContext context) {
        super(context,
            new VrInstance<>(
                () -> new RealItems(),
                new ProfileWire("prod"),
                new QualifierWire("realItems")
            ),
            new VrInstance<>(
                () -> new TestItems(),
                new ProfileWire("test"),
                new QualifierWire("testItems")
            )
        );
    }
}
```
```java
public class Main {

    public static void main(String[] args) throws Exception {
        AppContext context = new VrAppContext(args);
        VrComponent<Items> component = new ItemsComponent(context);
        Items items = component.instance(); // Returns instance based on profile 
                                            // specified in command line
        Items testItems = component.with(new QualifierWire("test")).instance(); 
                                            // Returns TestItems instance
    }
}
```
If we run this application with parameter --profile=prod the first instance
retrieved will be `RealItems` and the second `TestItems`.

#### Qualifier Management through XML
We can specify qualifiers for each class through `qualifiers.xml` file.
Let's use the following configuration for the previous example:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<classes>
    <class name="com.example.ItemsComponent">
        <qualifier>realItems</qualifier>
    </class>
</classes>
```
```java
public class Main {

    public static void main(String[] args) throws Exception {
        AppContext context = new VrAppContext(args);
        Items items = new ItemsComponent(context).instance(); // RealItems instance
                                           
    }
}
```

### Profile-Specific Properties
You can externalise configuration property files and make it available only
if specific profile is set. This functionality is very similar to Spring profiles.
Base configuration should be stored in app.properties files and rest of the 
property files should follow app-${profile}.properties convention.
Verano will read property file that matches current active profile and use
app.properties as baseline.

Simple example of properties injection
```java
public class MyItems implements Items {

    private final Props props;
    
    public RealItems(Props props) {
        this.props = props;
    }

    @Override
    public void printItem(final String id) {
        System.out.println(
            String.format("Fetched via db url %s", this.props.value("db.url"))
        );
    }
}
```
```java
public class ItemsComponent extends VrRefreshableComponent<Items> {

    public ItemsComponent(AppContext context) {
        super(context,
            new VrCloseableInstance<>(
                () -> new MyItems(new AppPropsOf(context))
            )
        );
    }
}
```
### Runtime implementation swap

