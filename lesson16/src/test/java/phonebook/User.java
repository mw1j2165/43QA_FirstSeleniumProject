package phonebook;

public class User {
    private String email;
    private String password;

// В методах мы будем использовать getters, а в тестах - setters

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}