package app;

import entity.AudioSettingsFactory;
import frameworks_and_drivers.customize_voice.VoiceDataAccessObject;
import interface_adapter.customize_voice.CustomizeVoiceController;
import interface_adapter.customize_voice.CustomizeVoiceDataAccessInterface;
import interface_adapter.customize_voice.CustomizeVoicePresenter;
import use_case.customize_voice.CustomizeVoiceInputBoundary;
import use_case.customize_voice.CustomizeVoiceInteractor;
import use_case.customize_voice.CustomizeVoiceOutputBoundary;
import view.GestureBridgeView;
import view.VoiceSettingsView;

/**
 * This module initializes the Voice Customization functionality, including the controller and related components.
 */
public class VoiceCustomizationModule {

    /**
     * Initializes the Voice Customization module, including the controller and services.
     *
     * @param gestureBridgeView the main application view for communication with presenters.
     * @param settingsView the settings view for voice configuration.
     * @return the CustomizeVoiceController instance.
     */
    public static CustomizeVoiceController initialize(
            GestureBridgeView gestureBridgeView, VoiceSettingsView settingsView) {
        final AudioSettingsFactory audioSettingsFactory = new AudioSettingsFactory();
        final CustomizeVoiceDataAccessInterface dataAccessObject = new VoiceDataAccessObject();
        final CustomizeVoiceOutputBoundary outputBoundary = new CustomizeVoicePresenter(
                gestureBridgeView, settingsView);
        final CustomizeVoiceInputBoundary interactor = new CustomizeVoiceInteractor(
                dataAccessObject, outputBoundary, audioSettingsFactory);
        return new CustomizeVoiceController(interactor);
    }
}
