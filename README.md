# Verano
### Java dependency injection framework

Verano provides convenient way to wire up dependencies 
without using reflections, type castings, annotations or component scan. 

It relies on wiring dependencies through a main entry point of an application
and provides a set of tools 

It takes different approach to classic DI containers and relies on
wiring dependencies through main entry point of application.
User is provided a set of  


- No magic (no reflections, no type castings, no runtime component building)
- Full control of dependency life cycle
- Compile time safety
- Fully customizable framework
- Runtime dependency switch, runtime configuration updates
- Verano can be used as a library to make your code fully decoupled from
  the framework

### Quick Start
#### Profiles

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
Verano caches component instances so they will behave like singletons. 
If you want control over instance lifecycle view chapter xx.
 
Note that `OrderComponent` does not need to know how to construct `Items` 
it just uses `ItemsComponent` and let that component build it.

#### Profile-Specific Properties
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
