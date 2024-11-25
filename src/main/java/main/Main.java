package main;

import data_access.UserDataStore;
import view.LoginScreen;

public class Main {
    public static void main(String[] args) {

        UserDataStore userDataStore = new UserDataStore();

        new LoginScreen(userDataStore);
    }
}
