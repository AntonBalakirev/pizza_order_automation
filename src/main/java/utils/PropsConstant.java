package utils;

public class PropsConstant {
    private PropsConstant() {
        throw new IllegalStateException("Utility class");
    }
    public static final String APP_URL = "app.url";
    public static final String TYPE_BROWSER = "type.browser";
    public static final String PATH_CHROME_DRIVER = "path.chrome.driver";
    public static final String PATH_GEKO_DRIVER = "path.gecko.driver";
    public static final String IMPLICITLY_WAIT = "implicitly.wait";
    public static final String EXPLICITLY_WAIT_LIMIT = "explicitly.wait";
    public static final String EXPLICITLY_WAIT_POLLING_TIMEOUT = "explicitly.wait.polling.timeout";
    public static final String PAGE_LOAD_TIMEOUT = "page.load.timeout";
}
