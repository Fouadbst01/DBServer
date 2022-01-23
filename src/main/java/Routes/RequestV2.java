package Routes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestV2 {
    @JsonProperty
    private String type;
    @JsonProperty
    private String Table;
    @JsonProperty
    private Map<String,String> Querry;
    @JsonProperty
    private Map<String,String> body1;
    public RequestV2(){}
    public RequestV2(String type, String table, Map<String, String> querry, Map<String, String> body1) {
        this.type = type;
        Table = table;
        Querry = querry;
        this.body1 = body1;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTable() {
        return Table;
    }

    public void setTable(String table) {
        Table = table;
    }

    public Map<String, String> getQuerry() {
        return Querry;
    }

    public void setQuerry(Map<String, String> querry) {
        Querry = querry;
    }

    public Map<String, String> getBody1() {
        return body1;
    }

    public void setBody1(Map<String, String> body1) {
        this.body1 = body1;
    }
}
