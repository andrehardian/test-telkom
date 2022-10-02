package app.test.testtelkom.con;

import app.test.testtelkom.BuildConfig;

public class URL {
    private final static String BASE_URL = BuildConfig.API_BASE_URL;
    public final static String LIST = BASE_URL + "topstories.json?print=pretty";

    public static String detail(Long id) {
        return BASE_URL + "item/" + id + ".json?print=pretty";
    }
}
