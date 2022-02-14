package gredal.simon.carsrus.service;

import gredal.simon.carsrus.dto.ReservationRequest;
import gredal.simon.carsrus.dto.ReservationResponse;
import gredal.simon.carsrus.entity.Car;
import gredal.simon.carsrus.entity.Member;
import gredal.simon.carsrus.entity.Reservation;
import gredal.simon.carsrus.exception.CarNotFoundException;
import gredal.simon.carsrus.exception.MemberNotFoundException;
import gredal.simon.carsrus.exception.ReservationNotFoundException;
import gredal.simon.carsrus.repository.CarRepository;
import gredal.simon.carsrus.repository.MemberRepository;
import gredal.simon.carsrus.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final MemberRepository memberRepository;
    private final CarRepository carRepository;

    public List<ReservationResponse> getReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        return ReservationResponse.of(reservations);
    }

    public ReservationResponse getReservation(Long id) {
        if (!reservationRepository.existsById(id)) throw new ReservationNotFoundException();
        Reservation reservation = reservationRepository.getById(id);
        return ReservationResponse.of(reservation);
    }

    public ReservationResponse addReservation(ReservationRequest body, Long memberId, Long carId) {
        if (!memberRepository.existsById(memberId)) throw new MemberNotFoundException();
        if (!carRepository.existsById(carId)) throw new CarNotFoundException();

        Member member = memberRepository.getById(memberId);
        Car car = carRepository.getById(carId);
        Reservation reservation = body.toReservation();
        reservation.setMember(member);
        reservation.setCar(car);

        reservation = reservationRepository.save(reservation);
        return ReservationResponse.of(reservation);
    }

    public void deleteReservation(Long id) {
        if (!reservationRepository.existsById(id)) throw new ReservationNotFoundException();

        Reservation reservation = reservationRepository.getById(id);
        reservation.setMember(null);
        reservation.setCar(null);
        reservationRepository.save(reservation);
        reservationRepository.deleteById(id);
    }
}
