package data_access;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import use_case.translation.TranslationDataAccessInterface;

// This mock DAO is based on the case where user selects the language without affecting languagePref
public class TranslationDataAccessObject implements TranslationDataAccessInterface {
    // What should this be?
    private String API_key;

    // public void MockDataBase() {
        // this.translations = new ArrayList<>();
        // populate this with mock translations later
        // List<String> emptyHistory = new ArrayList<>();
        // Translation example1 = new Translation("Hello world", "Mandarin", emptyHistory);
        // Might consider making a translation factory to create this
        // Or is a factory really necessary?
    public TranslationDataAccessObject() {
        this.API_key = "AIzaSyDGg_wMFNxhs-4Zh4FuAHe8lozim1OKH54";
        // Doesn't call the API yet
        // Not sure what to do with this part though. What should the DAO take in?
    }

    // This was originally setTargetLanguage for the translation object
    // This might not be needed since the translation object's target language won't change

    @Override
    public String translate(String language, String text) {
        // Temporary placeholder to simulate an API call
        System.out.println("calling Cloud Translation API...");

        // Return a mock translation
        // Call the API and set the desired language to translation.getTargetLanguage()
        // Translate the content of translation.getText()

        // A series of if statements to get the language codes: if language = "English", targetLanguage = "en", etc.
        String languageCode = "";
        if (language.equals("English")) {
            languageCode = "en";
        } else if (language.equals("Spanish")) {
            languageCode = "es";
        } else if (language.equals("French")) {
            languageCode = "fr";
        } else if (language.equals("Mandarin")) {
            languageCode = "zh-CN";
        }
        // Translate translate...
        Translate translate = TranslateOptions.newBuilder().setApiKey(API_key).build().getService();
        // Translation translation...
        Translation translation = translate.translate(text, Translate.TranslateOption.targetLanguage(languageCode));
        // return translation.getTranslatedText()
        return translation.getTranslatedText();
    }
}
