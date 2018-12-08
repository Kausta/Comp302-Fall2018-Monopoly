package cabernet1.monopoly.utils;

public interface Observer<T> {
    void onValueChanged(T value);
}
