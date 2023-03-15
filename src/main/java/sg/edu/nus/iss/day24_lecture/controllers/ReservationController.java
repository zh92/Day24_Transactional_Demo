package sg.edu.nus.iss.day24_lecture.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.iss.day24_lecture.payloads.ReservationRequest;
import sg.edu.nus.iss.day24_lecture.services.ReservationService;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @PostMapping
    public ResponseEntity<Boolean> reserveBooks(@RequestBody ReservationRequest resvReq) {

        Boolean bReserveSuccess = reservationService.reserveBooks(resvReq.getBooks(), resvReq.getFullName());

        if (bReserveSuccess) {
            return new ResponseEntity<Boolean>(bReserveSuccess, HttpStatus.OK);
        } else {
            return new ResponseEntity<Boolean>(bReserveSuccess, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

}
