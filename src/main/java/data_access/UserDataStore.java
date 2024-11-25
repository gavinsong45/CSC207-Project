package data_access;

import entity.User;

import java.util.HashMap;
import java.util.Map;

public class UserDataStore {
    private final Map<String, User> userDatabase = new HashMap<>();
    private User currentLoggedInUser = null;

    public boolean userExists(String username) {
        return userDatabase.containsKey(username);
    }

    public boolean validateCredentials(String username, String password) {
        User user = userDatabase.get(username);
        return user != null && user.getPassword().equals(password);
    }

    public void addUser(String username, String password) {
        userDatabase.put(username, new User(username, password));
    }

    public void setCurrentUser(String username) {
        currentLoggedInUser = userDatabase.get(username);
    }

    public User getCurrentUser() {
        return currentLoggedInUser;
    }

    public void logout() {
        currentLoggedInUser = null;
    }
}
