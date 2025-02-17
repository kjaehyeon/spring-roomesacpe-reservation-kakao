package roomescape.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import roomescape.dto.ReservationRequest;
import roomescape.dto.ReservationResponse;
import roomescape.service.ReservationService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<Void> createReservation(
            @RequestBody ReservationRequest reservationRequest,
            HttpServletRequest httpServletRequest
    ) {
        Long reservationId = reservationService.createReservation(reservationRequest) ;
        String currentURL = httpServletRequest.getRequestURL().toString();

        return ResponseEntity.created(
                UriComponentsBuilder.fromHttpUrl(currentURL)
                        .path("/{id}")
                        .buildAndExpand(reservationId)
                        .toUri()
        ).build();
    }

    @GetMapping("/{reservationId}")
    public ResponseEntity<ReservationResponse> getReservation(
            @PathVariable Long reservationId
    ) {
        return ResponseEntity.ok(
                reservationService.getReservation(reservationId)
        );
    }

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<Void> deleteReservation(
            @PathVariable Long reservationId
    ) {
        reservationService.deleteReservation(reservationId);
        return ResponseEntity.noContent().build();
    }
}
