package Routes;

import DBConnection.ConnectionDB;
import Data.Professor;

import java.sql.*;
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

            try {
                stm = con.createStatement();
                ResultSet rs =stm.executeQuery(request.BuildSQLGET());

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
            System.out.println(request.BuildSQLPUT());
            //PreparedStatement preparedStatement = con.prepareStatement();
           return null;
        });

        //POST
        mapRequest.put("POST",request ->{
            try {
                PreparedStatement pr = con.prepareStatement(request.BuildSQLPOST(),Statement.RETURN_GENERATED_KEYS);
                pr.executeUpdate();
                //ResultSet rs = pr.getGeneratedKeys();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        });

        //DELETE
        mapRequest.put("DELETE",request ->{
            return null;
        });
    }
}
