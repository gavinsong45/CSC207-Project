package frameworks_and_drivers;

import entity.HandLandmark;

public class ModelService {

    private Object model;

    public ModelService(String modelPath) {
        this.model = loadModel(modelPath);
    }

    private Object loadModel(String modelPath) {
    }

    public float[] predict(float[] modelInput) {
}