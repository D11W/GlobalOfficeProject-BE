package com.bluescripts.globaloffice.office.controllers;

import com.bluescripts.globaloffice.office.entity.Seat;
import com.bluescripts.globaloffice.office.requests.SeatRequest;
import com.bluescripts.globaloffice.office.responses.GenericDeleteResponse;
import com.bluescripts.globaloffice.office.responses.GenericResponse;
import com.bluescripts.globaloffice.office.responses.SeatResponse;
import com.bluescripts.globaloffice.office.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/seat")
@CrossOrigin(origins = "*")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @PostMapping("/seatId")
    public GenericResponse createSeat(@RequestBody SeatRequest request)
    {
        return seatService.createSeats(request);
    }

    @PutMapping("/seatId")
    public GenericResponse updateSeat(@RequestBody SeatRequest request,@RequestParam String seatId)
    {
        return seatService.updateSeat(request,seatId);
    }

    @GetMapping("/seatId")
    public SeatResponse getSeat(@RequestParam String seatId)
    {
        return seatService.getSeat(seatId);
    }

    @DeleteMapping("/seatId")
    public GenericDeleteResponse deleteSeat(@RequestParam String seatId)
    {
        return seatService.deleteSeat(seatId);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/all")
    public Iterable<Seat> getAllSeats()
    {
        return seatService.getAllSeats();
    }

}
