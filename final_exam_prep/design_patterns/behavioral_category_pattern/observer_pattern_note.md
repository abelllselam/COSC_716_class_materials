**Pattern: Observer**

# Intent:

    Define a one-to-many dependency between objects so that when one object (the Subject) changes state, all its dependents (the Observers) are notified and updated automatically.

# Explanation:

    Imagine a storyteller (the Subject) and a group of friends (the Observers). Friends can sign up or leave at any time. Whenever the storyteller has news, they simply shout it out, and every signed-up friend hears and reacts—without the storyteller needing to know who those friends are or what they’ll do with the news.

```java
// 1) Subject: the storyteller who holds news and tells all friends.
public interface Subject {
    void attach(Observer o);       // “Friend, I’ll remember you for future news”
    void detach(Observer o);       // “Okay, I’ll forget you now”
    void notifyAllObservers();     // “Gather ‘round—time for the news!”
}

// 2) Observer: the friend who listens for news.
public interface Observer {
    void update(String news);      // “When you have news, I’ll catch it and do something”
}

// 3) ConcreteSubject: the real storyteller maintaining a list of friends.
import java.util.ArrayList;
import java.util.List;

public class NewsAgency implements Subject {
    private List<Observer> observers = new ArrayList<>(); // friends list
    private String latestNews;                             // the current story

    @Override
    public void attach(Observer o) {
        observers.add(o);   // new friend joins
    }

    @Override
    public void detach(Observer o) {
        observers.remove(o); // friend leaves
    }

    @Override
    public void notifyAllObservers() {
        for (Observer o : observers) {
            o.update(latestNews); // “Here’s the news!”
        }
    }

    // When we have new news, we remember it and tell all friends:
    public void setNews(String news) {
        this.latestNews = news;    // store the story
        notifyAllObservers();      // broadcast it
    }
}

// 4) ConcreteObserver: a friend who reacts whenever there’s news.
public class NewsChannel implements Observer {
    private String channelNews;   // remembers the last story heard

    @Override
    public void update(String news) {
        this.channelNews = news;   // “I just heard this!”
        System.out.println("Channel heard: " + channelNews);
    }
}

// 5) Client: sets up the storyteller and the friends, then shares news.
public class Main {
    public static void main(String[] args) {
        NewsAgency agency = new NewsAgency();      // the storyteller
        NewsChannel channelA = new NewsChannel();  // friend A
        NewsChannel channelB = new NewsChannel();  // friend B

        agency.attach(channelA);  // A signs up
        agency.attach(channelB);  // B signs up

        agency.setNews("Observer pattern explained!");
        // Both A and B hear and print the news.

        agency.detach(channelB);  // B leaves

        agency.setNews("More news!");
        // Only A hears and prints this time.
    }
}

```
