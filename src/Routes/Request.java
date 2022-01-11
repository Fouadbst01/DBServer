package Routes;

import java.io.Serializable;
import java.util.Map;

public class Request implements Serializable{

    private String type;
    private String Route;
    private Map<String,Object> body;

    public Request(String type, String route, Map<String, Object> body) {
        this.type = type;
        Route = route;
        this.body = body;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRoute() {
        return Route;
    }

    public void setRoute(String route) {
        Route = route;
    }

    public Map<String, Object> getBody() {
        return body;
    }

    public void setBody(Map<String, Object> body) {
        this.body = body;
    }
}
