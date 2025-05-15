**Builder Pattern**

1. Intent
   Make it easy to construct a complex object step by step, especially when it has many optional parts.
2. Simple Java Example (Pizza Builder)

```java
// 1) The complex object we want to build:
public class Pizza {
    private String dough;
    private String cheese;
    private String topping;

    // Private constructor: force use of Builder
    private Pizza() { }

    // 2) Static nested Builder class
    public static class Builder {
        // The Pizza instance we assemble
        private Pizza pizza = new Pizza();

        // 3) Each method sets one part and returns this Builder
        public Builder buildDough(String dough) {
            pizza.dough = dough;
            return this;  // allow chaining
        }
        public Builder buildCheese(String cheese) {
            pizza.cheese = cheese;
            return this;
        }
        public Builder buildTopping(String topping) {
            pizza.topping = topping;
            return this;
        }

        // 4) Final method to return the completed Pizza
        public Pizza build() {
            return pizza;
        }
    }

    // 5) Example of using the built object
    @Override
    public String toString() {
        return "Pizza with " + dough + ", " + cheese + ", " + topping;
    }
}

// 6) Client code: step-by-step construction
public class Main {
    public static void main(String[] args) {
        Pizza pizza = new Pizza.Builder()    // create a new Builder
            .buildDough("thin crust")        // set dough
            .buildCheese("mozzarella")       // set cheese
            .buildTopping("pepperoni")       // set topping
            .build();                        // finalize and get Pizza

        System.out.println(pizza);
        // Output: Pizza with thin crust, mozzarella, pepperoni
    }
}

```

# Why each part

- private Pizza(): hide direct construction, forcing use of Builder.
- static class Builder: groups building logic inside Pizza.
- buildX() methods: set one piece and return the same Builder so calls chain.
- build(): returns the fully assembled Pizza.
- toString(): lets us easily see what was built.
- Client chaining: reads like a recipe—easy to follow and flexible.
