package interface_adapters;

public interface ModelInterface {
    /**
     * receive input data and return model prediction
     *
     * @param input asl input data, e.g. hand landmark
     * @return result of prediction
     */
    String predict(HandLandmark input);
}