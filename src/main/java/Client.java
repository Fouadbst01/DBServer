import Data.Classe;
import Data.Compte;
import Routes.Request;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.Socket;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Client {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        /*Socket socket = new Socket("localhost",8080);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());*/

        ObjectMapper mapper = new ObjectMapper();
        Socket socket = new Socket("192.168.0.109",8083);
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        DataInputStream din = new DataInputStream(socket.getInputStream());
        //ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        //ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();

        Request request= new Request("ELEMENT");
        request.PUT(new HashMap<>(){
            {
                put("id_element","1");
                put("nbHeur","2");
            }
        });

        out.writeUTF(mapper.writeValueAsString(request));

        //Respond respond = mapper.readValue(din.readUTF(),Respond.class);
        /*Map<String, List<Classe>> m = mapper.readValue(din.readUTF(),
                new TypeReference<Map<String, List<Classe>>>() {});*/

        System.out.println(din.readUTF());
        /*Compte cmpt = mapper.readValue(((List<Object>)respond.getBody()).get(0).toString(),Compte.class);
        System.out.println(cmpt.getUsername());*/

        out.flush();
        socket.close();

        /*rs.PUT(new HashMap<String,String>(){
            {
                put("name","'3alale'");
                put("prenom","'ahmed'");
            }
        }).Where(new HashMap<String,String>(){
            {
                put("id","5");
            }
        });*/
        /*rs.POST(new HashMap<>(){
                {
                    put("name","'toti'");
                    put("prenom","'titi'");
                }
        });*/
        /*rs.DELETE().Where(new HashMap<>(){
            {
                put("id","7");
            }
        });*/
        /*out.writeObject(rs);
        Respond respond = (Respond) ois.readObject();
        List<Professor> p =((List<Professor>)respond.getBody());
        for(int i=0;i<p.size();i++)
            System.out.println(p.get(i).getId()+" "+p.get(i).getName()+" "+p.get(i).getPrenom());*/
    }
}
