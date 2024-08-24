package by.it_academy.jd2.util;

public final class JspUtil {
    private JspUtil() {}

    private static final String JSP_FORMAT = "/ui/%s.jsp";

    public static String getPath(String jsp) {
        return String.format(JSP_FORMAT, jsp);
    }
}
