package Routes;

import DBConnection.ConnectionDB;
import Data.Professor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;


public class GETRoute {
    public static Map<String, Function<Request, Respond>> mapGetRequest;
    static {
        Connection con = ConnectionDB.getConnection();
        mapGetRequest = new HashMap<>();

        //GET_ALL_PROF
        mapGetRequest.put("PROFESSOR",request ->{
            List<Object> l = new ArrayList<>();
            Statement stm = null;
            Respond respond=null;
            String Querry ="SELECT * FROM professor";
            try {
                stm = con.createStatement();
                ResultSet rs =stm.executeQuery(Querry);

                while (rs.next()){
                    Professor professor = new Professor(rs.getInt("id"),rs.getString("name"),rs.getString("prenom"));
                    l.add(professor);
                }
                respond = new Respond(Respond.SUCCESSFUL,l);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return respond;
        });

        //GET_ALL_STUDENT
        mapGetRequest.put("STUDENT",request ->{
           return null;
        });
    }
}
