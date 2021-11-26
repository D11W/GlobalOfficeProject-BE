package com.bluescripts.globaloffice.office.controllers;

import com.bluescripts.globaloffice.office.entity.Seat;
import com.bluescripts.globaloffice.office.entity.SeatBooking;
import com.bluescripts.globaloffice.office.requests.SeatBookingRequest;
import com.bluescripts.globaloffice.office.responses.GenericDeleteResponse;
import com.bluescripts.globaloffice.office.responses.GenericResponse;
import com.bluescripts.globaloffice.office.responses.SeatBookingResponse;
import com.bluescripts.globaloffice.office.service.SeatBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/seatBooking")
@CrossOrigin(origins = "*")
public class SeatBookingController {

    @Autowired
    private SeatBookingService seatBookingService;

    @PostMapping("/booking")
    public GenericResponse createSeatBooking(@RequestBody SeatBookingRequest seatBookingRequest)
    {
        return seatBookingService.createBooking(seatBookingRequest);
    }

    @PutMapping("/booking")
    public GenericResponse updateSeatBooking(@RequestBody SeatBookingRequest seatBookingRequest,@RequestParam String seatBookingId)
    {
        return seatBookingService.updateBooking(seatBookingRequest,seatBookingId);
    }

    @GetMapping("/booking")
    public SeatBookingResponse getSeatBooking(@RequestParam String seatBookingId)
    {
        return seatBookingService.getBooking(seatBookingId);
    }

    @DeleteMapping("/booking")
    public GenericDeleteResponse deleteBooking(@RequestParam String seatBookingId)
    {
        return seatBookingService.deleteBooking(seatBookingId);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/all")
    public Iterable<SeatBooking> getAllBookings()
    {
        return seatBookingService.getAllBookings();
    }
}
