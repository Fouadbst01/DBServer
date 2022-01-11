package Routes;

import DBConnection.ConnectionDB;
import Data.Professor;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class POSTRoute {

    static public Map<String, Function<Request, Respond>> mapPOSTRequest = new HashMap<>();

    static {
        Connection con = ConnectionDB.getConnection();
        //POST a professor instance
        mapPOSTRequest.put("PROFESSOR",request -> {
            System.out.println("yes I DO");
            Professor p =null;
            Respond respond=null;

            try {
                PreparedStatement pstm = con.prepareStatement("INSERT INTO PROFESSOR(name,prenom) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);
                p=(Professor)request.getBody().get("value");

                pstm.setString(1,p.getName());
                pstm.setString(2,p.getPrenom());
                pstm.executeUpdate();
                ResultSet res =pstm.getGeneratedKeys();
                res.next();
                int generatedeId = res.getInt(1);
                p.setId(generatedeId);
                respond = new Respond(Respond.SUCCESSFUL,p);
            } catch (SQLException e) {
                respond = new Respond(Respond.DATA_BASE_PROBLEM,e.getMessage());
                e.printStackTrace();
            }
            return respond;
        });
    }
}
