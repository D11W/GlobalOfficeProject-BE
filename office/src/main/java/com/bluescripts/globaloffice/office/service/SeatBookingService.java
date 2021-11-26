package com.bluescripts.globaloffice.office.service;

import com.bluescripts.globaloffice.office.constants.StringConstant;
import com.bluescripts.globaloffice.office.entity.Seat;
import com.bluescripts.globaloffice.office.entity.SeatBooking;
import com.bluescripts.globaloffice.office.exception.NoRecordFoundException;
import com.bluescripts.globaloffice.office.repository.SeatBookingRepo;
import com.bluescripts.globaloffice.office.requests.SeatBookingRequest;
import com.bluescripts.globaloffice.office.responses.GenericDeleteResponse;
import com.bluescripts.globaloffice.office.responses.GenericResponse;
import com.bluescripts.globaloffice.office.responses.SeatBookingResponse;
import com.bluescripts.globaloffice.office.utils.IdUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeatBookingService {

    private final SeatBookingRepo seatBookingRepo;

    private final ModelMapper modelMapper;

    public GenericResponse createBooking(SeatBookingRequest request)
    {
        String seatBookingId= IdUtils.generateId(StringConstant.seatBookingId_prefix);

        SeatBooking seatBooking=new SeatBooking();
        seatBooking.setSeatBookingId(seatBookingId);
        seatBooking.setBookingDate(request.getBookingDate());
        seatBooking.setBookingType(request.getBookingType());
        seatBooking.setTime(request.getTime());

        seatBookingRepo.save(seatBooking);

        GenericResponse response=new GenericResponse();
        response.setMessage("Booking Successfully");
        return response;
    }

    public SeatBookingResponse getBooking(String seatBookingId)
    {
        SeatBooking booking = seatBookingRepo.findBySeatBookingId(seatBookingId).orElseThrow(()->
        {
            return new NoRecordFoundException("No Record Found"+seatBookingId);
        });
        return modelMapper.map(booking,SeatBookingResponse.class);
    }

    public GenericDeleteResponse deleteBooking(String seatBookingId)
    {
       SeatBooking seatBook= seatBookingRepo.findBySeatBookingId(seatBookingId).orElseThrow(()->
       {
           return new NoRecordFoundException("No Record Found"+seatBookingId);
       });

       GenericDeleteResponse deleteResponse=new GenericDeleteResponse(seatBookingId,true);
       deleteResponse.setDeleted(true);
       seatBookingRepo.save(seatBook);
       return deleteResponse;
    }

    public GenericResponse updateBooking(SeatBookingRequest request,String seatBookingId)
    {
       SeatBooking seatBooking = seatBookingRepo.findBySeatBookingId(seatBookingId).orElseThrow(()->
       {
           return new NoRecordFoundException("No Record Found"+seatBookingId);
       });

       seatBooking.setSeatBookingId(seatBookingId);
       seatBooking.setBookingType(request.getBookingType());
       seatBooking.setBookingDate(request.getBookingDate());
       seatBooking.setTime(request.getTime());

       seatBookingRepo.save(seatBooking);

       GenericResponse updateResponse= new GenericResponse();
       updateResponse.setMessage("Booking Updated Successfully");
       return updateResponse;
    }

    public Iterable<SeatBooking> getAllBookings()
    {
        return seatBookingRepo.findAll();
    }
}
