package com.bluescripts.globaloffice.office.service;

import com.bluescripts.globaloffice.office.constants.StringConstant;
import com.bluescripts.globaloffice.office.entity.Floor;
import com.bluescripts.globaloffice.office.exception.NoRecordFoundException;
import com.bluescripts.globaloffice.office.repository.FloorRepo;
import com.bluescripts.globaloffice.office.requests.FloorRequest;
import com.bluescripts.globaloffice.office.responses.FloorResponse;
import com.bluescripts.globaloffice.office.responses.GenericDeleteResponse;
import com.bluescripts.globaloffice.office.responses.GenericResponse;
import com.bluescripts.globaloffice.office.utils.IdUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FloorService {

    private final FloorRepo floorRepo;

    private final ModelMapper modelMapper;

    public GenericResponse createFloor(FloorRequest floorRequest)
    {
        String floorId= IdUtils.generateId(StringConstant.floorId_prefix);

        Floor floor=new Floor();
        floor.setFloorId(floorId);
        floor.setCountry(floorRequest.getCountry());
        floor.setCity(floorRequest.getCity());
        floor.setBuilding(floorRequest.getBuilding());
        floor.setOffice(floorRequest.getOffice());
        floor.setCompany_name(floorRequest.getCompany_name());
        floor.setWing(floorRequest.getWing());

        floorRepo.save(floor);

        GenericResponse response=new GenericResponse();
        response.setMessage("Floor Is Created Successfully");
        return response;
    }

    public FloorResponse getFloor(String floorId)
    {
           Floor floorg= floorRepo.findByFloorId(floorId).orElseThrow(()->{
                return new NoRecordFoundException("No Record Found"+floorId);
            });
            return modelMapper.map(floorg,FloorResponse.class);
    }

    public GenericDeleteResponse deleteFloor(String floorId)
    {
        Floor floor = floorRepo.findByFloorId(floorId).orElseThrow(()->{
            return new NoRecordFoundException("No Record Found" +floorId);
        });

        GenericDeleteResponse genericDeleteResponse=new GenericDeleteResponse(floorId,true);
//        genericDeleteResponse.setDeleted(true);
        floor.setDelete(true);
        floorRepo.save(floor);
        return genericDeleteResponse;
    }

    public GenericResponse updateFloor(FloorRequest request,String floorId)
    {
        Floor floor = floorRepo.findByFloorId(floorId).orElseThrow(()->{
            return new NoRecordFoundException("No Record Found"+floorId);
        });
        floor.getFloorId();
        floor.setCompany_name(request.getCompany_name());
        floor.setCountry(request.getCountry());
        floor.setCity(request.getCity());
        floor.setOffice(request.getOffice());
        floor.setBuilding(request.getBuilding());
        floor.setWing(request.getWing());

        floorRepo.save(floor);

        GenericResponse updateReponse=new GenericResponse();
        updateReponse.setMessage("Record is updated successfully");
        return updateReponse;
    }

}
