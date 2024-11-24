package interface_adapter.translation;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The View Model for the Select Language use case
 * @param <T>
 */
public class ViewModel<T> {

    // This was called viewName in the Lab 5 example, what does this do?
    private final String viewName;

    // What does this do? What specifically is propertyChangeSupport?
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    private T state;
    // For my case, would there be a "state"? What does that do?
    // State - the encapsulation of the UI data that represents the current "state" of the UI
    // Ensures the UI reflects the correct data even when configurations change (e.g. navigating between components)

    public ViewModel(String viewName) {
        this.viewName = viewName;
    }

    public String getViewText() {
        return this.viewName; // Is there a difference between this.instance and just the instance?
    }

    public T getState() {
        return this.state;
    }

    public void setState(T state) {
        this.state = state;
    }

    public void firePropertyChanged() {
        this.support.firePropertyChange("state", null, this.state);
    }

    public void firePropertyChanged(String propertyName) {
        this.support.firePropertyChange(propertyName, null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.support.addPropertyChangeListener(listener);
    }
}
