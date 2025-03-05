package utils;

import dao.UserDAO;
import dto.UserDTO;
import javax.servlet.http.HttpSession;

public class AuthUtils {

    private static final String FOUNDER_USER = "Founder";
    private static final String MEMBER_USER = "Team Member";

    public static UserDTO getUser(String usName) {
        UserDAO usDao = new UserDAO();
        return usDao.readByUSName(usName);
    }

    public static boolean verifyUser(String usName, String password) {
        UserDTO us = getUser(usName);
        return us != null && us.getPassword().equals(password);
    }

    public static boolean isLoggedIn(HttpSession session) {
        return session.getAttribute("user") != null;
    }

    public static UserDTO getUser(HttpSession session) {
        if (!isLoggedIn(session)) {
            return null;
        }
        return (UserDTO) session.getAttribute("user");
    }

    public static boolean isFounder(HttpSession session) {
        if (!isLoggedIn(session)) {
            return false;
        }
        UserDTO us = getUser(session);
        return us.getRole().equals(FOUNDER_USER);
    }
}
