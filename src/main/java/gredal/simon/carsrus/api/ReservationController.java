package gredal.simon.carsrus.api;

import gredal.simon.carsrus.dto.ReservationRequest;
import gredal.simon.carsrus.dto.ReservationResponse;
import gredal.simon.carsrus.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@AllArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @GetMapping
    public List<ReservationResponse> getReservations() {
        return reservationService.getReservations();
    }

    @GetMapping("/{id}")
    public ReservationResponse getReservation(@PathVariable Long id) {
        return reservationService.getReservation(id);
    }

    @PostMapping
    public ReservationResponse addReservation(@RequestBody ReservationRequest body,
                                              @RequestParam Long memberId, @RequestParam Long carId) {
        return reservationService.addReservation(body, memberId, carId);
    }

    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
    }
}
