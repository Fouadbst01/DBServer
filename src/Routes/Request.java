package Routes;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Request implements Serializable{

    private String type;
    private String Table;
    private Map<String,String> body;

    public Request(String route) {
        Table = route;
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

    public Map<String, String> getBody() {
        return body;
    }

    public void setBody(Map<String, String> body) {
        this.body = body;
    }


    public Request findAll(){
        this.type="GET";
        return this;
    }

    public Request findByID(){
        return this;
    }

    public Request Where(Map<String,String> map){
        body = map;
        return this;
    }

    public String BuildSQLStatment(Request rs){

        StringBuilder Querry = new StringBuilder("SELECT * FROM "+rs.getTable());

        AtomicInteger i= new AtomicInteger();

        if(!rs.getBody().isEmpty()){
            Querry.append(" WHERE ");
            rs.getBody().forEach((k,v)->{
                if(i.get() ==rs.getBody().size()-1)
                    Querry.append(k+"="+v);
                else
                    Querry.append(k+"="+v+" AND ");
                i.getAndIncrement();
            });
        }
        return Querry.toString();
    }

}
