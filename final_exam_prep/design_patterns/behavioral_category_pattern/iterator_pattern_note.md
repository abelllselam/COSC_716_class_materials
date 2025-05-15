**Pattern: Iterator**

# Intent:

    Provide a way to access the elements of a collection (like a list or custom container) sequentially, without exposing its internal structure.

# Explanation:

    Imagine you have a box of toy cars and you want to show them one by one to a friend. Instead of rummaging inside the box yourself (and revealing how the box is built), you hand your friend a magic “iterator” that knows how to go through each car, one at a time, saying “Here’s the next car!”

```java
// 1) Iterator interface: the magic helper that walks through items.
//    It has two jobs: tell if there’s a next toy, and give the next toy.
public interface Iterator<T> {
    boolean hasNext();  // “Do I still have another toy to show?”
    T next();           // “Here’s the next toy!”
}

// 2) Container interface: anything that can hand out an iterator.
public interface Container<T> {
    Iterator<T> getIterator();  // “Give me your special iterator!”
}

// 3) Concrete container: a simple box that holds Strings (toy names).
public class NameContainer implements Container<String> {
    private String[] names;  // our toys are just names

    public NameContainer(String[] names) {
        this.names = names;  // put these names into our box
    }

    @Override
    public Iterator<String> getIterator() {
        // Return a brand-new iterator for this box:
        return new NameIterator();
    }

    // 4) Inner class: the actual iterator that knows how to walk our array.
    private class NameIterator implements Iterator<String> {
        private int index = 0;  // start at the first toy

        @Override
        public boolean hasNext() {
            // “Is my index still inside the box?”
            return index < names.length;
        }

        @Override
        public String next() {
            // “Give the toy at my index and move forward one spot”
            String name = names[index];
            index++;
            return name;
        }
    }
}

// 5) Client code: our playroom where we ask the box for its iterator
public class Main {
    public static void main(String[] args) {
        // Make a box of toy names:
        String[] toys = { "Car", "Doll", "Train", "Plane" };
        NameContainer box = new NameContainer(toys);

        // Ask the box for a helper that walks through the toys:
        Iterator<String> it = box.getIterator();

        // Keep asking “Do you have another toy?” and “Show me!” until done
        while (it.hasNext()) {
            String toy = it.next();
            System.out.println("Toy: " + toy);
        }
        // Output:
        // Toy: Car
        // Toy: Doll
        // Toy: Train
        // Toy: Plane
    }
}

```

# What’s happening?

- Iterator is our magic helper interface with hasNext() and next().
- Container promises “I can give you an iterator.”
- NameContainer holds an array of names and returns a NameIterator.
- NameIterator remembers where it is (index) and knows how to check for and fetch the next element.
- The client never touches the array directly—it just uses the iterator’s two simple methods to see and get each toy in turn.
