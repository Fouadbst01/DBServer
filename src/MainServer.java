import DBConnection.ConnectionDB;
import Data.Professor;
import Routes.GETRoute;
import Routes.POSTRoute;
import Routes.Request;
import Routes.Respond;

import java.io.*;
import java.lang.reflect.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;


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
                switch (req.getType()){
                    case "GET" :
                        respond = GETRoute.mapGetRequest.get(req.getRoute()).apply(req);
                        System.out.println();
                        break;
                    case "POST":
                        respond = POSTRoute.mapPOSTRequest.get(req.getRoute()).apply(req);
                        break;
                    case "PUT":
                        break;
                    case "DELETE":
                        break;
                }
                oos.writeObject(respond);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}






















