package sg.edu.nus.iss.day24_lecture.models;

import org.springframework.data.annotation.Id;

public class ResvDetails {
    
    @Id
    private Integer id;
    private Integer bookId;
    private Integer resvId;
    
    public ResvDetails() {
    }
    
    public ResvDetails(Integer id, Integer bookId, Integer resvId) {
        this.id = id;
        this.bookId = bookId;
        this.resvId = resvId;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getBookId() {
        return bookId;
    }
    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }
    public Integer getResvId() {
        return resvId;
    }
    public void setResvId(Integer resvId) {
        this.resvId = resvId;
    }
    
}
