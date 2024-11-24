package interface_adapter.translation;

/**
 * The View Model for the Select Language View
 */
public class TranslationViewModel extends ViewModel<TranslationState> {
    public TranslationViewModel() {
        // Still don't really know what this does
        super("translate");
        setState(new TranslationState());
    }
}
