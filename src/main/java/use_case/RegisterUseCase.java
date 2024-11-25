package use_case;

import data_access.UserDataStore;
import entity.User;

public class RegisterUseCase {
    private final UserDataStore userDataStore;

    public RegisterUseCase(UserDataStore userDataStore) {
        this.userDataStore = userDataStore;
    }

    public boolean register(String username, String password) {
        if (userDataStore.userExists(username)) {
            return false;
        }
        userDataStore.addUser(username, password);
        return true;
    }
}
