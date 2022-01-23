package Data;

import java.io.Serializable;

public class Professor1 implements Serializable {
    private int id;
    private String Name;
    private String prenom;

    public Professor1(int id, String name, String prenom) {
        this.id = id;
        Name = name;
        this.prenom = prenom;
    }

    public Professor1(String name, String prenom) {
        this.id = id;
        Name = name;
        this.prenom = prenom;
    }

    public Professor1(String Name){
        this.Name=Name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
