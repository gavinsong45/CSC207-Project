package use_case;

import data_access.UserDataStore;
import entity.User;

public class LoginUseCase {
    private final UserDataStore userDataStore;

    public LoginUseCase(UserDataStore userDataStore) {
        this.userDataStore = userDataStore;
    }

    public User login(String username, String password) {
        if (userDataStore.validateCredentials(username, password)) {
            userDataStore.setCurrentUser(username);
            return userDataStore.getCurrentUser();
        }
        return null;
    }
}
