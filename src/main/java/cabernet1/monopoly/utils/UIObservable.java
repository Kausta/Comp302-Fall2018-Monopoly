package cabernet1.monopoly.utils;

import javax.swing.*;
import java.io.Serializable;

public class UIObservable<T extends Serializable> extends Observable<T> {

    private static final long serialVersionUID = -5154550441366244548L;

    @Override
    protected void notifyObserver(Observer<T> observer, T value) {
        SwingUtilities.invokeLater(() -> observer.onValueChanged(value));
    }
}
