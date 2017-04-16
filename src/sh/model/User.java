package sh.model;

import javax.servlet.http.HttpServletRequest;

public class User {

    private String user;
    private String password;
    private Role role;

    public static User parseRequest(HttpServletRequest request) {
        User user = new User();
        user.setUser(request.getParameter("user"));
        user.setPassword(request.getParameter("password"));
        user.setRole(Role.valueOf(request.getParameter("role").toUpperCase()));
        return user;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
