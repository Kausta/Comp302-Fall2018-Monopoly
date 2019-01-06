package cabernet1.monopoly.utils;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Observable<T extends Serializable> implements Serializable {
    private static final long serialVersionUID = -3305024786301652216L;
    private transient final Set<Observer<T>> observers = new HashSet<>();
    private T value;

    public Observable() {

    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
        this.notifyObservers();
    }

    public void notifyObservers() {
        final T value = this.value;
        for (Observer<T> observer : observers) {
            this.notifyObserver(observer, value);
        }
    }

    protected void notifyObserver(Observer<T> observer, T value) {
        observer.onValueChanged(value);
    }

    public void addObserver(Observer<T> observer) {
        this.observers.add(observer);
    }

    public void removeObserver(Observer<T> observer) {
        this.observers.remove(observer);
    }

    public void removeAllObservers() {
        this.observers.clear();
    }
}
