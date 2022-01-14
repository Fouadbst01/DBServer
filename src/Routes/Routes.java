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
            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = con.prepareStatement(request.BuildSQLPUT());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
           return null;
        });

        //POST
        mapRequest.put("POST",request ->{
            List<Object> l = new ArrayList<>();

            try {
                PreparedStatement pr = con.prepareStatement(request.BuildSQLPOST(),Statement.RETURN_GENERATED_KEYS);
                System.out.println(request.BuildSQLPOST());
                pr.executeUpdate();
                ResultSet rsID = pr.getGeneratedKeys();
                rsID.next();
                Professor professor = new Professor(rsID.getInt(1),request.getBody().get("DATA").get("name"),request.getBody().get("DATA").get("prenom"));
                l.add(professor);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return new Respond(Respond.SUCCESSFUL,l);
        });

        //DELETE
        mapRequest.put("DELETE",request ->{
            return new Respond(Respond.SUCCESSFUL);
        });
    }
}
