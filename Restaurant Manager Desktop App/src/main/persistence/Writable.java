package persistence;

import org.json.JSONArray;
import org.json.JSONObject;

public interface Writable {

    //EFFECTS: return this as JSON object
    //JSONObject toJson();

    //EFFECTS: returns this as JSON array
    JSONArray toJsonArray();


}
