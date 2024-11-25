package frameworks_and_drivers;

import entity.User;
import interface_adapter.PredictionInterface;
import interface_adapter.translation.TranslationViewModel;
import presenter.SignLanguagePresenter;
import use_case.PredictionService;
import view.SignLanguageView;
import controller.SignLanguageController;
import controller.SpeechtoTextController;
import frameworks_and_drivers.Speech2Text.GoogleSpeechRecognizer;
import use_case.Speech2Text.ProcessSpeechInputUseCase;

import data_access.TranslationDataAccessObject;
import interface_adapter.translation.TranslationController;
import interface_adapter.translation.TranslationPresenter;
import use_case.translation.TranslationInputBoundary;
import use_case.translation.TranslationInteractor;
import use_case.translation.TranslationOutputBoundary;

import java.io.IOException;

public class RunSignLanguageApp {

    public static void start(User user) {
        System.out.println("Main application is running...");
        System.out.println("Welcome, " + user.getUsername());

        try {
            String pythonInterpreter = "python3";
            String scriptPath = "src/main/python/hand_gesture_recognition.py";

            GoogleSpeechRecognizer speechRecognizer = new GoogleSpeechRecognizer();
            ProcessSpeechInputUseCase speechInputUseCase = new ProcessSpeechInputUseCase(speechRecognizer);
            SpeechtoTextController speechToTextController = new SpeechtoTextController(speechInputUseCase);

            TranslationViewModel translationViewModel = new TranslationViewModel();
            SignLanguageView signLanguageView = new SignLanguageView(speechToTextController, translationViewModel);

            TranslationOutputBoundary translationOutputBoundary = new TranslationPresenter(translationViewModel);
            TranslationDataAccessObject languageDataAccessObject = new TranslationDataAccessObject();
            TranslationInputBoundary selectLanguageInteractor = new TranslationInteractor(
                    languageDataAccessObject, translationOutputBoundary);
            TranslationController translationController = new TranslationController(selectLanguageInteractor);
            signLanguageView.setSelectLanguageController(translationController);

            PredictionInterface predictor = new PredictionService(pythonInterpreter, scriptPath);

            SignLanguagePresenter signLanguagePresenter = new SignLanguagePresenter(signLanguageView, predictor);
            SignLanguageController signLanguageController = new SignLanguageController(signLanguagePresenter);

            signLanguageController.startRecognition();
        } catch (IOException | InterruptedException e) {
            System.err.println("Failed to start the main application: " + e.getMessage());
        }
    }
}
