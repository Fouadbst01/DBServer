import Routes.Routes;
import Routes.Request;
import Routes.Respond;

import java.io.*;
import java.lang.reflect.*;
import java.net.ServerSocket;
import java.net.Socket;


public class MainServer {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {


        try {
            ServerSocket socket = new ServerSocket(8080);
            while (true){
                Socket sk =socket.accept();
                InputStream in =sk.getInputStream();
                OutputStream os=sk.getOutputStream();
                ObjectInputStream ois = new ObjectInputStream(in);
                ObjectOutputStream oos = new ObjectOutputStream(os);
                //get client reques
                Request req = (Request)ois.readObject();
                Respond respond=null;

                //req type : GET PUT POST DELETE
                respond = Routes.mapRequest.get(req.getType()).apply(req);

                //sending response
                oos.writeObject(respond);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}






















