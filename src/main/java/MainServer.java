import Routes.Routes;
import Routes.Request;
import Routes.Respond;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.lang.reflect.*;
import java.net.ServerSocket;
import java.net.Socket;


public class MainServer{

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {


        try {
            ServerSocket socket = new ServerSocket(8083);
            while (true){
                Socket sk =socket.accept();
                ObjectMapper mapper = new ObjectMapper();
                InputStream in =sk.getInputStream();
                OutputStream os=sk.getOutputStream();
                DataOutputStream dou = new DataOutputStream(os);
                DataInputStream din = new DataInputStream(in);

                //get client reques
                String  ms =din.readUTF();

                Request req = mapper.readValue(ms,Request.class);


                Respond respond=null;

                //req type : GET PUT POST DELETE
                System.out.println(req.getType()+req.getTable().toUpperCase());
                respond = Routes.mapRequest.get(req.getType()+req.getTable().toUpperCase()).apply(req);
                String res=mapper.writeValueAsString(respond);
                System.out.println(res);
                //sending response
                dou.writeUTF(res);
                dou.close();
                in.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}






















