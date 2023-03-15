package sg.edu.nus.iss.day24_lecture.models;

import java.sql.Date;

import org.springframework.data.annotation.Id;

public class Resv {
    
    @Id
    private Integer id;
    private Date resvDate;
    private String fullName;
    
    public Resv() {
    }
    
    public Resv(Integer id, Date resvDate, String fullName) {
        this.id = id;
        this.resvDate = resvDate;
        this.fullName = fullName;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Date getResvDate() {
        return resvDate;
    }
    public void setResvDate(Date resvDate) {
        this.resvDate = resvDate;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
}
