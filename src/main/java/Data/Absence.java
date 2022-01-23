package Data;

import java.sql.Date;

public class Absence {
    private int id_abs;
    private int id_etu;
    private int id_element;
    private Date date_abs;

    public Absence() {
    }

    public Absence(int id_abs, int id_etu, int id_element, Date date_abs) {
        this.id_abs = id_abs;
        this.id_etu = id_etu;
        this.id_element = id_element;
        this.date_abs = date_abs;
    }

    public int getId_abs() {
        return id_abs;
    }

    public void setId_abs(int id_abs) {
        this.id_abs = id_abs;
    }

    public int getId_etu() {
        return id_etu;
    }

    public void setId_etu(int id_etu) {
        this.id_etu = id_etu;
    }

    public int getId_element() {
        return id_element;
    }

    public void setId_element(int id_element) {
        this.id_element = id_element;
    }

    public Date getDate_abs() {
        return date_abs;
    }

    public void setDate_abs(Date date_abs) {
        this.date_abs = date_abs;
    }
}
