# Verano
### Java dependency injection framework

Verano provides convenient way to wire up your dependencies 
without using reflections, type castings or component scan. 

- No magic (no reflections, no type castings, no runtime component building)
- Full control of dependency life cycle
- Compile time safety
- Fully customizable framework
- Runtime dependency switch, runtime configuration updates
- Verano can be used as a library to make your code fully decoupled from
  the framework

### Quick start

Let's create a very simple model which prints items in an order.

```java
public interface Order {

    void showItem(String id);
}
```
```java
public interface Items {

    void printItem(String id);
}
```
And implementations:
```java
public class UserOrder implements Order {

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
