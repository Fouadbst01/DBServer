package Routes;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Request implements Serializable{

    private String type;
    private String Table;
    private Map<String,Map<String,String>> body;

    public Request(String tableName) {
        body=new HashMap<>();
        Table = tableName;
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

    public Map<String,Map<String,String>> getBody() {
        return body;
    }

    public void setBody(Map<String,Map<String,String>> body) {
        this.body = body;
    }

    //FOR CLIENT SIDE
    public Request findAll(){
        this.type="GET";
        return this;
    }

    public Request findByID(){
        return this;
    }

    public Request Where(Map<String,String> map){
        body.put("WHERE",map);
        return this;
    }

    public void POST(Map<String,String> map){
        this.type="POST";
        body.put("DATA",map);
    }

    public Request PUT(Map<String,String> map){
        this.type="PUT";
        body.put("DATA",map);
        return this;
    }

    //SERVER SIDE
    public String BuildSQLGET(){

        StringBuilder Querry = new StringBuilder("SELECT * FROM "+Table);

        AtomicInteger i= new AtomicInteger();

        if(!body.isEmpty()){
            Querry.append(" WHERE ");
            body.get("WHERE").forEach((k,v)->{
                if(i.get() ==body.size()-1)
                    Querry.append(k+"="+v);
                else
                    Querry.append(k+"="+v+" AND ");
                i.getAndIncrement();
            });
        }
        return Querry.toString();
    }

    public String BuildSQLPOST(){
        StringBuilder Querry = new StringBuilder("INSERT INTO "+Table);
        AtomicInteger i =new AtomicInteger();
        StringBuilder Q2=new StringBuilder(" VALUES (");
        if(!body.isEmpty()){
            Querry.append("(");
            body.get("DATA").forEach((k,v)->{
                if(i.get() == body.size()-1){
                    Q2.append(v+")");
                    Querry.append(k+")");
                }
                else{
                    Q2.append(v+",");
                    Querry.append(k+",");
                }
                i.getAndIncrement();
            });
        }

        return Querry.append(Q2).toString();
    }

    public String BuildSQLPUT(){
        StringBuilder Querry = new StringBuilder("UPDATE "+Table +" SET ");
        AtomicInteger i =new AtomicInteger();
        if(!body.isEmpty()){
            /*body.get("DATA").forEach((k,v)->{
                if(i.get() == body.size()-1){
                    Querry.append(k+"="+v);
                }
                else{
                    Querry.append(k+"="+v+",");
                }
                i.getAndIncrement();
            });*/
            Querry.append(BuildDATA(body.get("DATA")));
            Querry.append(BuildWhere(body.get("WHERE")));
        }
        return Querry.toString();
    }

    public String BuildSQLDELETE(){
        StringBuilder Querry = new StringBuilder("DELETE FROM "+Table +" WHERE ");
        AtomicInteger i =new AtomicInteger();
        /*body.get("WHERE").forEach((k,v)->{
            if(i.get() ==body.size()-1)
                Querry.append(k+"="+v);
            else
                Querry.append(k+"="+v+" AND ");
            i.getAndIncrement();
        });*/
        Querry.append(BuildWhere(body.get("WHERE")));
        return Querry.toString();
    }

    public StringBuilder BuildWhere(Map<String,String> map){
        StringBuilder wr = new StringBuilder(" WHERE ");
        AtomicInteger i =new AtomicInteger();
        map.forEach((k,v)->{
            if(i.get() ==body.get("WHERE").size()-1)
                wr.append(k+"="+v);
            else
                wr.append(k+"="+v+" AND ");
            i.getAndIncrement();
        });
        return wr;
    }

    public StringBuilder BuildDATA(Map<String,String> map){
        AtomicInteger i =new AtomicInteger();
        StringBuilder data = new StringBuilder();
        map.forEach((k,v)->{
            if(i.get() == body.size()-1){
                data.append(k+"="+v);
            }
            else{
                data.append(k+"="+v+",");
            }
            i.getAndIncrement();
        });
        return data;
    }

}
