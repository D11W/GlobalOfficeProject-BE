package com.bluescripts.globaloffice.office.service;

import com.bluescripts.globaloffice.office.constants.StringConstant;
import com.bluescripts.globaloffice.office.entity.Seat;
import com.bluescripts.globaloffice.office.exception.NoRecordFoundException;
import com.bluescripts.globaloffice.office.repository.SeatRepo;
import com.bluescripts.globaloffice.office.requests.SeatRequest;
import com.bluescripts.globaloffice.office.responses.GenericDeleteResponse;
import com.bluescripts.globaloffice.office.responses.GenericResponse;
import com.bluescripts.globaloffice.office.responses.SeatResponse;
import com.bluescripts.globaloffice.office.utils.IdUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeatService {

    private final SeatRepo seatRepo;
    private final ModelMapper modelMapper;

    public GenericResponse createSeats(SeatRequest request)
    {
        String seatId = IdUtils.generateId(StringConstant.seatId_prefix);

        Seat seat = new Seat();
        seat.setSeatId(seatId);
        seat.setFloorId(request.getFloorId());

        seatRepo.save(seat);

        GenericResponse response=new GenericResponse();
        response.setMessage("Created Succressfully");
        return response;
    }

    public GenericResponse updateSeat(SeatRequest seatRequest,String seatId)
    {
        Seat updatedSeat= seatRepo.findBySeatId(seatId).orElseThrow(()->{
            return new NoRecordFoundException("No Record Found"+seatId);
        });

        updatedSeat.setFloorId(seatRequest.getFloorId());
        updatedSeat.setSeatId(seatId);

        seatRepo.save(updatedSeat);

        GenericResponse updatedResponse=new GenericResponse();
        updatedResponse.setMessage("Updated Successfully");
        return updatedResponse;

    }

    public SeatResponse getSeat(String seatId)
    {
        Seat seat= seatRepo.findBySeatId(seatId).orElseThrow(()->
        {
            return new NoRecordFoundException("No Record Found"+seatId);
        });

        return modelMapper.map(seat,SeatResponse.class);
    }

    public GenericDeleteResponse deleteSeat(String seatId)
    {
        Seat seatdelete = seatRepo.findBySeatId(seatId).orElseThrow(()->
        {
            return new NoRecordFoundException("No Record Found"+seatId);
        });

        GenericDeleteResponse deleteResponse=new GenericDeleteResponse(seatId,true);
        deleteResponse.setDeleted(true);
        seatRepo.save(seatdelete);
        return deleteResponse;
    }

    public Iterable<Seat> getAllSeats()
    {
        return seatRepo.findAll();
    }
}
