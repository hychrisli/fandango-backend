package cmpe273.fandango.response;

import io.swagger.annotations.ApiModel;

import java.util.HashMap;
import java.util.Map;

public class JsonResponse extends HashMap<String, Object> {

    /**
     * 
     */
    private static final long serialVersionUID = 9147128840079087125L;

    public JsonResponse() {
    }

    public JsonResponse(String key, Object value) {
	addPair(key, value);
    }

    public JsonResponse addPair(String key, Object value) {
	    this.put(key, value);
	    return this;
    }

    public Map<String, Object> getResponseData() {
	return this;
    }
}
