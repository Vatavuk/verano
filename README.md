# Verano
### Java dependency injection framework

Verano provides convenient way to wire up dependencies 
without using reflections, type castings, annotations or component scan. It takes 
different approach to classic DI containers and relies on wiring dependencies through 
main entry point of application. To simplify/enhance wiring process it provides 
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

    public UserOrder(final Items items) {
        this.items = items;
    }

    @Override
    public void showItem(final String id) {
        this.items.printItem(id);
    }
}
```
```java
public class RealItems implements Items {

    @Override
    public void printItem(final String id) {
        System.out.println(String.format("Real item %s", id));
    }
}
```
```java
public class TestItems implements Items {

    @Override
    public void printItem(final String id) {
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

    public ItemsComponent(final AppContext context) {
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

    public OrderComponent(final AppContext context) {
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
        final AppContext context = new VrAppContext(args);
        final Order order = new OrderComponent(context).instance();
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
Component is a base building block for configuring interface implementations. 
It acts as a factory class that determines which implementation is suitable 
for wiring based on users input. The difference between classic factory class 
is that is not globally accessible and the logic of choosing the right instance 
is hidden from users. 

Verano provides two types of components, `VrComponent` which manages all its
instances like singletons and `VrRefreshableComponent` for which user can
control instance lifecycle.



### Profile-Specific Properties
You can externalise configuration property files and make it available only
when specific profile is set. This functionality is very similar to Spring profiles.
Base configuration should be stored in app.properties files and rest of the 
property files should follow app-${profile}.properties convention.
Verano will read property file that matches current active profile and use
app.properties as baseline.

Simple example of properties injection
```java


```

```java
public class OrderComponent extends VrComponent<Order> {

    public OrderComponent(final AppContext context) {
        super(context,
            new VrInstance<>(
                () -> new MyOrder(new ItemsComponent(context).instance())
            )
        );
    }
}
```

### 


### Qualifiers

### Component lifecycle management
