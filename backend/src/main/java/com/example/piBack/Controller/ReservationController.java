package com.example.piBack.Controller;

import com.example.piBack.Model.Reservation;
import com.example.piBack.Service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ResponseEntity<Object> addReservation(@RequestBody Reservation reservation) {
        try {
            Reservation newReservation = reservationService.addReservation(reservation);
            return new ResponseEntity<>("Reservation ID: "+newReservation.getId()+" created", HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>("Server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<Collection<Reservation>> listReservation() {
        return ResponseEntity.ok(reservationService.listReservation());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> findReservation(@PathVariable Long id){
        if(reservationService.findReservation(id).isPresent()) {
            return ResponseEntity.ok(reservationService.findReservation(id).get());
        }else{
            return new ResponseEntity("Reservation with id "+id+" not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reservation> editReservation(@PathVariable("id") long id, @RequestBody Reservation reservation) {
        Optional<Reservation> reservation_ = reservationService.findReservation(id);

        if (reservation_.isPresent()) {
            return new ResponseEntity<>(reservationService.editReservation(reservation), HttpStatus.OK);
        } else {
            return new ResponseEntity("Reservation with id "+id+" not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteReservation (@PathVariable Long id) {
        try {
            reservationService.deleteReservation(id);
            return new ResponseEntity("Reservation deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Reservation with id" + id + " not found", HttpStatus.NOT_FOUND);
        }
    }
}