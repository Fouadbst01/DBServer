package Routes;

import DBConnection.ConnectionDB;
import Data.*;

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
        mapRequest.put("GETCOMPTE",request ->{
            List<Compte> l = new ArrayList<>();

            Statement stm = null;
            Respond respond=null;

            try {
                stm = con.createStatement();
                ResultSet rs =stm.executeQuery(request.BuildSQLGET());

                while (rs.next()){
                    Compte Compte =
                            new Compte(rs.getInt("id_cmpt"),rs.getString("username"),rs.getString("pswd"));
                    l.add(Compte);
                }
                respond = new Respond(l);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return respond;
        });

        //GET_ALL_PROF
        mapRequest.put("GETPROFESSEUR",request ->{
            List<Professeur> l = new ArrayList<>();

            Statement stm = null;
            Respond respond=null;

            try {
                stm = con.createStatement();
                ResultSet rs =stm.executeQuery(request.BuildSQLGET());

                while (rs.next()){
                    Professeur professor1 =
                            new Professeur(rs.getInt("id_prof"),
                                    rs.getString("nom"),
                                    rs.getString("prenom"),
                                    rs.getString("cin"),
                                    rs.getInt("id_cmpt"),rs.getInt("id_dept"));
                    l.add(professor1);
                }
                respond = new Respond(l);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return respond;
        });

        mapRequest.put("GETETUDIANT",request ->{
            List<Etudiant> l = new ArrayList<>();

            Statement stm = null;
            Respond respond=null;

            try {
                stm = con.createStatement();
                ResultSet rs =stm.executeQuery(request.BuildSQLGET());


                while (rs.next()){
                    Etudiant etudiant =
                            new Etudiant(rs.getInt("id_etu"),
                                    rs.getString("nom"),
                                    rs.getString("prenom"),
                                    rs.getString("cne"),
                                    rs.getInt("id_class"));
                    l.add(etudiant);
                }
                respond = new Respond(l);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return respond;
        });

        mapRequest.put("GETCLASSEPROF",request ->{
            List<Classe> l = new ArrayList<>();

            PreparedStatement stm = null;
            Respond respond=null;

            try {
                stm = con.prepareStatement("SELECT DISTINCT classe.id_class,intitule,annee FROM classe JOIN enseigne where id_prof=?");
                stm.setInt(1,Integer.parseInt(request.getBody().get("WHERE").get("id_prof")));

                ResultSet rs =stm.executeQuery();
                while (rs.next()){
                    Classe classe =
                            new Classe(rs.getInt("id_class"),
                                    rs.getString("intitule"),
                                    rs.getInt("annee"));
                    l.add(classe);
                }
                respond = new Respond(l);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return respond;
        });

        mapRequest.put("GETCLASSEPROFELEMENT",request ->{
            List<Element> l = new ArrayList<>();

            PreparedStatement stm = null;
            Respond respond=null;

            try {
                stm = con.prepareStatement("SELECT element.id_element,intitule,nbHeur,totaleh" +
                        " FROM enseigne join element where enseigne.id_element=element.id_element and id_class=? and id_prof=?");
                stm.setInt(1,Integer.parseInt(request.getBody().get("WHERE").get("id_class")));
                stm.setInt(2,Integer.parseInt(request.getBody().get("WHERE").get("id_prof")));

                ResultSet rs =stm.executeQuery();
                while (rs.next()){
                    Element element =
                            new Element(rs.getInt("id_element"),
                                    rs.getString("intitule"),
                                    rs.getInt("nbHeur"),
                                    rs.getInt("totaleh"));
                    l.add(element);
                }
                respond = new Respond(l);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return respond;
        });

        mapRequest.put("GETCLASSEPROFELEMENT",request ->{
            List<Element> l = new ArrayList<>();

            PreparedStatement stm = null;
            Respond respond=null;

            try {
                stm = con.prepareStatement("SELECT element.id_element,intitule,nbHeur,totaleh" +
                        " FROM enseigne join element where enseigne.id_element=element.id_element and id_class=? and id_prof=?");
                stm.setInt(1,Integer.parseInt(request.getBody().get("WHERE").get("id_class")));
                stm.setInt(2,Integer.parseInt(request.getBody().get("WHERE").get("id_prof")));

                ResultSet rs =stm.executeQuery();
                while (rs.next()){
                    Element element =
                            new Element(rs.getInt("id_element"),
                                    rs.getString("intitule"),
                                    rs.getInt("nbHeur"),
                                    rs.getInt("totaleh"));
                    l.add(element);
                }
                respond = new Respond(l);
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
           return new Respond(null);
        });

        mapRequest.put("PUTELEMENT",request ->{
            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = con.prepareStatement("UPDATE ELEMENT SET nbHeur=? WHERE id_element=?");
                preparedStatement.setInt(1,Integer.parseInt(request.getBody().get("DATA").get("nbHeur")));
                preparedStatement.setInt(2,Integer.parseInt(request.getBody().get("DATA").get("id_element")));
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return new Respond(null);
        });

        //POST
        mapRequest.put("POSTABSENCE",request ->{
            List<Object> l = new ArrayList<>();

            try {
                PreparedStatement pr = con.prepareStatement(request.BuildSQLPOST());
                System.out.println(request.BuildSQLPOST());
                pr.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return new Respond(l);
        });

        //DELETE
        mapRequest.put("DELETE",request ->{
            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = con.prepareStatement(request.BuildSQLDELETE());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return new Respond(Respond.SUCCESSFUL);
        });

        //Ahuthentication
        mapRequest.put("AUTH",request -> {
            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = con.prepareStatement(request.BuildSQLDELETE());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return new Respond(Respond.SUCCESSFUL);
        });
    }
}
