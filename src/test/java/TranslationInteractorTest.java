import data_access.TranslationDataAccessObject;
import org.junit.jupiter.api.Test;
import use_case.translation.*;

import static org.junit.jupiter.api.Assertions. *;

class TranslationInteractorTest {
    @Test
    void successTest() {
        // Create the translation object for testing
        // This whole part might be in the interactor
        // CreateTranslationFactory factory = new CreateTranslationFactory();
        // List<String> empty = new ArrayList<>();
        // Translation translation = factory.create("Hello World", "Mandarin", empty);

        // Create the input data
        TranslationInputData inputData = new TranslationInputData("Mandarin", "Hello World");
        // Create the DAO
        TranslationDataAccessInterface translate = new TranslationDataAccessObject();

        // In the interactor - Creates the translation object that needs to be processed. Then process it by calling API


        //Creates the presenter for the successful case
        TranslationOutputBoundary successPresenter = new TranslationOutputBoundary() {
            @Override
            public void prepareSuccessView(TranslationOutputData translatedText) {
                // Check if the translation matches the desired meaning
                assertEquals("你好世界", translatedText.getTranslatedText());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                // Report the error message if the user fails the test
                fail("Select Language use case failure is unexpected");
            }
        };

        TranslationInputBoundary interactor = new TranslationInteractor(translate, successPresenter);
        interactor.execute(inputData);
    }
    // For some reason, even though I didn't finish implementing the execute method, this test still passes?
    // If it's not failing, how am I to test if the execution is correct?
}
