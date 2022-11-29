package com.example.piBack.Controller;
import com.example.piBack.Model.Client;
import com.example.piBack.Model.Reservation;
import com.example.piBack.Model.User;
import com.example.piBack.Service.ClientService;
import com.example.piBack.Service.ReservationService;
import com.example.piBack.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("reservation")
public class ReservationController {

    private ReservationService reservationService;
    private UserService userService;
    private ClientService clientService;

    @Autowired
    public ReservationController(ReservationService reservationService, UserService userService, ClientService clientService) {
        this.reservationService = reservationService;
        this.userService = userService;
        this.clientService = clientService;

    }
    //add reservation
    @PostMapping
    public ResponseEntity<Object> addReservation(@RequestBody Reservation reservation) {
        User user = userService.getUserFromSecurityContextHolder();
        Client client = clientService.getOrCreateClientByUser(user);
        reservation.setClient(client);
        try {
            Reservation newReservation = reservationService.addReservation(reservation);
            return new ResponseEntity<>("Reservation ID: " + newReservation.getId() + " created", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //get all reservations
    @GetMapping
    public ResponseEntity<Collection<Reservation>> listReservation() {
        return ResponseEntity.ok(reservationService.listReservation());
    }
    //get reservation by id
    @GetMapping("/{id}")
    public ResponseEntity<Reservation> findReservation(@PathVariable Long id) {
        if (reservationService.findReservation(id).isPresent()) {
            return ResponseEntity.ok(reservationService.findReservation(id).get());
        } else {
            return new ResponseEntity("Reservation with id " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }
    //get reservations by product id
    @GetMapping("/productId/{productId}")
    public ResponseEntity<Collection<Reservation>>  findReservationsByProductId(@PathVariable Long productId) {
        if(reservationService.findReservationsByProductId(productId) != null){
            return ResponseEntity.ok(reservationService.findReservationsByProductId(productId));
        }else {
            return new ResponseEntity("The product you are trying to get reservations from does not exist", HttpStatus.NOT_FOUND);
        }
}
    //update reservation by id
    @PutMapping("/{id}")
    public ResponseEntity<Reservation> editReservation(@PathVariable("id") long id, @RequestBody Reservation reservation) {
        Optional<Reservation> reservation_ = reservationService.findReservation(id);

        if (reservation_.isPresent()) {
            return new ResponseEntity<>(reservationService.editReservation(reservation), HttpStatus.OK);
        } else {
            return new ResponseEntity("Reservation with id "+id+" not found", HttpStatus.NOT_FOUND);
        }
    }
    //delete reservation by id
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
