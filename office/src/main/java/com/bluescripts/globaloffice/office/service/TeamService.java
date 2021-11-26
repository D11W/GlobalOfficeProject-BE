package com.bluescripts.globaloffice.office.service;

import com.bluescripts.globaloffice.office.constants.StringConstant;
import com.bluescripts.globaloffice.office.entity.Team;
import com.bluescripts.globaloffice.office.exception.NoRecordFoundException;
import com.bluescripts.globaloffice.office.repository.TeamRepo;
import com.bluescripts.globaloffice.office.requests.TeamRequest;
import com.bluescripts.globaloffice.office.responses.GenericDeleteResponse;
import com.bluescripts.globaloffice.office.responses.GenericResponse;
import com.bluescripts.globaloffice.office.responses.TeamResponse;
import com.bluescripts.globaloffice.office.utils.IdUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamService {

    private TeamRepo teamRepo;

    private ModelMapper modelMapper;

    public GenericResponse createTeam(TeamRequest teamRequest)
    {
        String teamId = IdUtils.generateId(StringConstant.teamId_prefix);

        Team team = new Team();
        team.setTeamId(teamId);
        team.setFloorId(teamRequest.getFloorId());

        teamRepo.save(team);

        GenericResponse genericResponse=new GenericResponse();
        genericResponse.setMessage("Created Record Successfully");
        return genericResponse;
    }

    public GenericResponse updateTeam(TeamRequest request,String teamId)
    {
        Team teamupdate=teamRepo.findByTeamId(teamId).orElseThrow(()->{
            return new NoRecordFoundException("No Record Found"+teamId);
        });

//        teamupdate.setFloorId(request.getFloorId());
        teamupdate.setTeamName(request.getTeamName());
        teamRepo.save(teamupdate);

        GenericResponse response = new GenericResponse();
        response.setMessage("Record Updated Successfully");
        return response;
    }

    public TeamResponse getTeam(String teamId)
    {
        Team teamgetting=teamRepo.findByTeamId(teamId).orElseThrow(()->{
           return new NoRecordFoundException("No Record Found"+teamId);
        });
        return modelMapper.map(teamgetting,TeamResponse.class);
    }

    public GenericDeleteResponse deleteTeam(String teamId)
    {
        Team deleteTeam=teamRepo.findByTeamId(teamId).orElseThrow(()->{
            return new NoRecordFoundException("Record Deleted Successfully"+teamId);
        });

        GenericDeleteResponse deleteResponse=new GenericDeleteResponse(teamId,true);
        deleteResponse.setDeleted(true);
        teamRepo.save(deleteTeam);
        return deleteResponse;
    }

    public Iterable<Team> getAllTeam(){
        return teamRepo.findAll();
    }
}
