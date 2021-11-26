package com.bluescripts.globaloffice.office.controllers;

import com.bluescripts.globaloffice.office.requests.FloorRequest;
import com.bluescripts.globaloffice.office.responses.FloorResponse;
import com.bluescripts.globaloffice.office.responses.GenericDeleteResponse;
import com.bluescripts.globaloffice.office.responses.GenericResponse;
import com.bluescripts.globaloffice.office.service.FloorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/floor")
@CrossOrigin(origins = "*")
public class FloorController {

    @Autowired
    private FloorService floorService;

    @PostMapping("/floorId")
    public GenericResponse createFloor(@RequestBody FloorRequest floorRequest)
    {
        return floorService.createFloor(floorRequest);
    }

    @GetMapping("/floorId")
    public FloorResponse getFloor(@RequestParam String floorId)
    {
        return floorService.getFloor(floorId);
    }

    @PutMapping("/floorId")
    public GenericResponse updateFloor(@RequestBody FloorRequest request,@RequestParam String floorId)
    {
        return floorService.updateFloor(request,floorId);
    }

    @DeleteMapping("/floorId")
    public GenericDeleteResponse deleteFloor(@RequestParam String floorId)
    {
        return floorService.deleteFloor(floorId);
    }

}
