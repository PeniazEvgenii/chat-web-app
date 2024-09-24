package by.it_academy.jd2.util;

public final class PathUtil {
    private PathUtil() {}

    public final static String REGISTRATION_SERVLET = "/api/user";
    public final static String LOGIN_SERVLET = "/api/login";
    public final static String MESSAGE_SERVLET = "/api/message";
    public final static String STATISTICS_SERVLET = "/api/admin/statistics";
    public final static String LOGOUT_SERVLET = "/api/logout";

    public final static String PATTERN_URL_USER_UI = "/ui/user/*";
    public final static String PATTERN_URL_ADMIN_UI = "/ui/admin/*";
    public final static String PATTERN_URL_ADMIN_API = "/api/admin/*";

    public static final String LOGIN_JSP = "signIn";
    public static final String REGISTRATION_JSP = "signUp";

    private static final String JSP_FORMAT = "/ui/%s.jsp";

    public static String getPathJsp(String jsp) {
        return String.format(JSP_FORMAT, jsp);
    }
}
