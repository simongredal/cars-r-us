package gredal.simon.carsrus.api;

import gredal.simon.carsrus.dto.ReservationRequest;
import gredal.simon.carsrus.dto.ReservationResponse;
import gredal.simon.carsrus.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@AllArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    // Should be accessible by admin
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<ReservationResponse> getReservations() {
        return reservationService.getReservations();
    }

    // Should be accessible by admin
    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ReservationResponse getReservation(@PathVariable Long id) {
        return reservationService.getReservation(id);
    }

    // Should be accessible by admin and user itself
    @GetMapping(params = "memberId", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<ReservationResponse> getReservationByMemberId(@RequestParam Long memberId) {
        return reservationService.getReservationsByMemberId(memberId);
    }

    // Should be accessible by admin and user itself
    @GetMapping(params = "carId", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<ReservationResponse> getReservationByCarId(@RequestParam Long carId) {
        return reservationService.getReservationsByCarId(carId);
    }

    // Should be accessible by admin and user itself
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ReservationResponse addReservation(@RequestBody ReservationRequest body,
                                              @RequestParam Long memberId, @RequestParam Long carId) {
        return reservationService.addReservation(body, memberId, carId);
    }

    // Should be accessible by admin and user itself
    @PutMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ReservationResponse editReservation(@RequestBody ReservationRequest body,
                                               @RequestParam Long reservationId, @RequestParam Long memberId, @RequestParam Long carId) {
        return reservationService.editReservation(body, reservationId, memberId, carId);
    }

    // Should be accessible by admin and user itself
    @DeleteMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public void deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
    }
}
