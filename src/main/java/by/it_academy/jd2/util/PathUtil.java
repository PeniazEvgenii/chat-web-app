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

    public static final String LOGIN_JSP = "/ui/signIn.jsp";
    public static final String REGISTRATION_JSP = "/ui/signUp.jsp";
    public static final String MESSAGE_JSP = "/ui/user/message.jsp";
    public static final String STATISTICS_JSP = "/ui/admin/statistics.jsp";
    public static final String CHATS_JSP = "/ui/user/chats.jsp";


    private static final String JSP_FORMAT = "/ui/%s.jsp";

    public static String getPathJsp(String jsp) {
        return String.format(JSP_FORMAT, jsp);
    }
}
