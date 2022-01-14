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


public class Routes {
    public static Map<String, Function<Request, Respond>> mapRequest;
    static {
        Connection con = ConnectionDB.getConnection();
        mapRequest = new HashMap<>();

        //GET_ALL_PROF
        mapRequest.put("GET",request ->{
            List<Object> l = new ArrayList<>();

            Statement stm = null;
            Respond respond=null;
            System.out.println("|"+request.BuildSQLStatment(request)+"|");
            try {
                stm = con.createStatement();
                ResultSet rs =stm.executeQuery(request.BuildSQLStatment(request));

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

        //PUT
        mapRequest.put("PUT",request ->{
           return null;
        });

        //POST
        mapRequest.put("POST",request ->{
            return null;
        });

        //DELETE
        mapRequest.put("DELETE",request ->{
            return null;
        });
    }
}
