package use_case.translation;

/**
 * DAO for the select_language usecase.
 */
public interface TranslationDataAccessInterface {

    // Add a method to check if username exists? But if the client is able to
    // get to this use case they are already logged in


    // Would the user need to be here? Or should I create a separate usecase to set the default language?
    // /**
    // * Returns the current language preference with the given user. It is also the default target language.
    // * @param user the current logged-in user
    // * @return the user's current language preference
    // */
    // String getLanguagePref(User user);
    // May or may not need to add/replace this with getTargetLanguage from Translation Entity

    // /**
    // * Updates the translation's target language.
    // * @param translation the current translation that needs to be processed
    // * @param language the user's new selected language
    // */
    // void setTargetLanguage(Translation translation, String language);

    // This might also not be needed since the translation object is only

    /**
     * Returns the translated text of the given gesture's transcription
     * @param language the language that the text is translated into
     * @param text the text that needs to be translated
     * @return the translated version of the text
     */
    String translate(String language, String text);
    // This usecase only has the translation method, it does not care about
    // The user, the default language and settings will be for another usecase
}
