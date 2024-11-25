package use_case;

import data_access.UserDataStore;

public class LogoutUseCase {
    private final UserDataStore userDataStore;

    public LogoutUseCase(UserDataStore userDataStore) {
        this.userDataStore = userDataStore;
    }

    public void logout() {
        userDataStore.logout();
    }
}
