package gredal.simon.carsrus.service;

import gredal.simon.carsrus.dto.ReservationRequest;
import gredal.simon.carsrus.dto.ReservationResponse;
import gredal.simon.carsrus.entity.Car;
import gredal.simon.carsrus.entity.Member;
import gredal.simon.carsrus.entity.Reservation;
import gredal.simon.carsrus.exception.*;
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

    public List<ReservationResponse> getReservationsByMemberId(Long id) {
        if (!memberRepository.existsById(id)) throw new MemberNotFoundException();

        List<Reservation> reservations = reservationRepository.findByMember_Id(id);
        return ReservationResponse.of(reservations);
    }

    public List<ReservationResponse> getReservationsByCarId(Long id) {
        if (!carRepository.existsById(id)) throw new CarNotFoundException();

        List<Reservation> reservations = reservationRepository.findByCar_Id(id);
        return ReservationResponse.of(reservations);
    }

    public ReservationResponse addReservation(ReservationRequest body, Long memberId, Long carId) {
        if (!memberRepository.existsById(memberId)) throw new MemberNotFoundException();
        if (!carRepository.existsById(carId)) throw new CarNotFoundException();

        boolean alreadyBooked = reservationRepository.existsByRentalDateAndCar_Id(body.getRentalDate(), carId);
        if (alreadyBooked) throw new CarAlreadyBookedException();

        Member member = memberRepository.getById(memberId);
        Car car = carRepository.getById(carId);
        Reservation reservation = body.toReservation();
        reservation.setMember(member);
        reservation.setCar(car);

        reservation = reservationRepository.save(reservation);
        return ReservationResponse.of(reservation);
    }

    public ReservationResponse editReservation(ReservationRequest body, Long reservationId,  Long memberId, Long carId) {
        if (!reservationRepository.existsById(reservationId)) throw new ReservationNotFoundException();
        if (!memberRepository.existsById(memberId)) throw new MemberNotFoundException();
        if (!carRepository.existsById(carId)) throw new CarNotFoundException();

        boolean alreadyBooked = reservationRepository.existsByRentalDateAndCar_Id(body.getRentalDate(), carId);
        if (alreadyBooked) throw new CarAlreadyBookedException();

        Reservation reservation = reservationRepository.getById(reservationId);
        Member member = memberRepository.getById(memberId);
        Car car = carRepository.getById(carId);

        if (body.getReservationDate() != null) reservation.setReservationDate(body.getReservationDate());
        if (body.getRentalDate()      != null) reservation.setRentalDate(body.getRentalDate());
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
