import Data.Professor;
import Routes.Request;
import Routes.Respond;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Client {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("localhost",8080);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

        Request rs = new Request("PROFESSOR");
        /*rs.findAll().Where(new HashMap<String,String>(){
            {
                put("id","1");
            }
        });*/

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
        rs.POST(new HashMap<>(){
                {
                    put("name","'toti'");
                    put("prenom","'titi'");
                }
        });
        out.writeObject(rs);
        Respond respond = (Respond) ois.readObject();
        List<Professor> p =((List<Professor>)respond.getBody());
        for(int i=0;i<p.size();i++)
            System.out.println(p.get(i).getId()+" "+p.get(i).getName()+" "+p.get(i).getPrenom());
    }
}
