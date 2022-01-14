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
        rs.findAll().Where(new HashMap<String,String>(){
            {
                put("id","1");
            }
        });

        out.writeObject(rs);
        Respond respond = (Respond) ois.readObject();
        List<Professor> p =((List<Professor>)respond.getBody());
        System.out.println(p.get(0).getId()+" "+p.get(0).getName()+" "+p.get(0).getPrenom());
    }
}
