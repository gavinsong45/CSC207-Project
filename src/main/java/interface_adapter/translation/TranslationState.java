package interface_adapter.translation;

/**
 * The state for the select language view model
 */
public class TranslationState {
    private String translation = "";

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }
    // Should there be a setter for the translation language though? Since the translation
    // Yes, this setter will be used by the presenter, not the client, to make the variables appear
    // on the corresponding view.
}
