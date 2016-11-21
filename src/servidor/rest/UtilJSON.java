package servidor.rest;

import servidor.json.JSONObject;

public class UtilJSON {

    public static String limpaJSON(JSONObject bd) {
        String f = bd.toString();
        f = f.replace("\\", "");
        f = f.replace(":\"[", ":[");
        f = f.replace("]\"}", "]}");
        f = f.replace("]\"", "]");
        f = f.replace(", {", ",{");
        return f;
    }
}
