package com.project.trav.ifrastructure.rest;

import com.project.trav.application.services.RaceService;
import com.project.trav.ifrastructure.dto.RaceDto;
import com.project.trav.ifrastructure.mapper.RaceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/races")
@RequiredArgsConstructor
public class RaceController {
    private final RaceMapper raceMapper;
    private final RaceService raceService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RaceDto> getRaces(){return raceMapper.toRaceDtos(raceService.getRaces());}

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RaceDto getRace(@PathVariable Long id){return raceMapper.toRaceDto(raceService.getRace(id));}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addRace(@RequestBody RaceDto raceDto){raceService.addRace(raceMapper.toRace(raceDto));}

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRace(@PathVariable Long id){raceService.deleteRace(id);}

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateRace(@RequestBody RaceDto raceDto, @PathVariable Long id){
        raceService.updateRace(raceMapper.toRace(raceDto),id);}
}