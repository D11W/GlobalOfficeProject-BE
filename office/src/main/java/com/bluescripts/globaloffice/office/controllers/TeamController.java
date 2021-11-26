package com.bluescripts.globaloffice.office.controllers;

import com.bluescripts.globaloffice.office.entity.Team;
import com.bluescripts.globaloffice.office.requests.TeamRequest;
import com.bluescripts.globaloffice.office.responses.GenericDeleteResponse;
import com.bluescripts.globaloffice.office.responses.GenericResponse;
import com.bluescripts.globaloffice.office.responses.TeamResponse;
import com.bluescripts.globaloffice.office.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/team")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class TeamController {

    @Autowired
    private TeamService teamService;

    @PostMapping("/teamId")
    public GenericResponse createTeam(@RequestBody TeamRequest request)
    {
        return teamService.createTeam(request);
    }

    @PutMapping("/teamId")
    public GenericResponse updateTeam(@RequestBody TeamRequest teamRequest,@RequestParam String teamId)
    {
        return teamService.updateTeam(teamRequest,teamId);
    }

    @GetMapping("/teamId")
    public TeamResponse getTeam(@RequestParam String teamId)
    {
        return teamService.getTeam(teamId);
    }

    @DeleteMapping("/teamId")
    public GenericDeleteResponse deleteTeam(@RequestParam String teamId)
    {
        return teamService.deleteTeam(teamId);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/all")
    public Iterable<Team> getAllTeam()
    {
        return teamService.getAllTeam();
    }

}
