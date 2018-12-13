package cabernet1.monopoly.utils;

import javax.swing.*;

public class UIObservable<T> extends Observable<T> {

    @Override
    protected void notifyObserver(Observer<T> observer, T value) {
        SwingUtilities.invokeLater(() -> observer.onValueChanged(value));
    }
}
