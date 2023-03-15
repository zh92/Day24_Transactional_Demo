package sg.edu.nus.iss.day24_lecture.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.nus.iss.day24_lecture.exceptions.BookException;
import sg.edu.nus.iss.day24_lecture.models.Book;
import sg.edu.nus.iss.day24_lecture.models.Resv;
import sg.edu.nus.iss.day24_lecture.models.ResvDetails;
import sg.edu.nus.iss.day24_lecture.repositories.BookRepo;
import sg.edu.nus.iss.day24_lecture.repositories.ResvDetailsRepo;
import sg.edu.nus.iss.day24_lecture.repositories.ResvRepo;

@Service
public class ReservationService {
    
    @Autowired
    BookRepo bookRepo;

    @Autowired
    ResvRepo resvRepo;

    @Autowired
    ResvDetailsRepo resvDetailsRepo;

    @Transactional(isolation = Isolation.READ_UNCOMMITTED, propagation = Propagation.REQUIRED)
    public Boolean reserveBooks(List<Book> books, String reservePersonName) {
        
        Boolean bReservationCompleted = false;
        Boolean bBookAvailable = true;

        // check for books availability by quantity
        List<Book> libBooks = bookRepo.findAll();
        for (Book book : books) {
            List<Book> filteredBook = libBooks.stream().filter(b -> b.getId().equals(book.getId())).collect(Collectors.toList());

            if (!filteredBook.isEmpty()) {
                if (filteredBook.get(0).getQuantity() == 0) {
                    bBookAvailable = false;
                    break;
                } else {
                    // QuantityUpdate Marker
                    bookRepo.update(book.getId());
                }
            } else {
                bBookAvailable = false;
                break;
            }
        }
        //test
        // for (int i = 0; i < books.size(); i++) {
        //     if (books.get(i).getQuantity() > 0)
        //     bBookAvailable = true;
        // }

        // if book(s) available
        // minus quantity from the books (requires transaction)
        if (bBookAvailable) {
            // create the reservation record (requires transaction)
        Resv reservation = new Resv();
        reservation.setFullName(reservePersonName);
        reservation.setResvDate(Date.valueOf(LocalDate.now()));
        Integer reservationId = resvRepo.createResv(reservation);

        // create the reservation details record (requires transaction)
        for (Book book : books) {
            ResvDetails reservationdetail = new ResvDetails();
            reservationdetail.setBookId(book.getId());
            reservationdetail.setResvId(reservationId);

            resvDetailsRepo.createResvDetails(reservationdetail);
        }

        bReservationCompleted = true;
        } else {
            throw new BookException("Book is not available.");
        }

        return bReservationCompleted;
    }
}
