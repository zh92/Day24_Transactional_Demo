package sg.edu.nus.iss.day24_lecture.payloads;

import java.util.List;

import sg.edu.nus.iss.day24_lecture.models.Book;

public class ReservationRequest {
    
    private List<Book> books;
    private String fullName;
    
    public ReservationRequest() {
    }
    
    public ReservationRequest(List<Book> books, String fullName) {
        this.books = books;
        this.fullName = fullName;
    }

    public List<Book> getBooks() {
        return books;
    }
    public void setBooks(List<Book> books) {
        this.books = books;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    // @Override
    // public String toString() {
    //     return "ReservationRequest [books=" + books + ", fullName=" + fullName + "]";
    // }
    
    
}
