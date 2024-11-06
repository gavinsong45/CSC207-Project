package interface_adapters;

import entity.HandLandmark;
import frameworks_and_drivers.ModelService;

public class ModelPredictor implements ModelInterface {

    private ModelService modelService;

    public ModelPredictor(ModelService modelService) {
        this.modelService = modelService;
    }

    @Override
    public String predict(HandLandmark input) {
        float[] modelInput = InputPreprocessor.preprocess(input);
        float[] rawOutput = modelService.predict(modelInput);
        return OutputPostprocessor.postprocess(rawOutput);
    }
}