package com.turkcell.playcell.gamingplatform.api.controller;

import com.turkcell.playcell.gamingplatform.api.dto.ComplaintDTO;
import com.turkcell.playcell.gamingplatform.api.enumtypes.ResponseCodeStrings;
import com.turkcell.playcell.gamingplatform.api.response.DataResponse;
import com.turkcell.playcell.gamingplatform.api.service.ComplaintService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v2.0")
public class ComplaintController {

    private final ComplaintService complaintService;

    @PostMapping("/platforms/{platformName}/games/{gameId}/complaints")
    public ResponseEntity<Object> saveComplaint(@PathVariable(name = "platformName") String platformName, @PathVariable(name = "gameId") Long gameId, @RequestBody ComplaintDTO complaintDTO) {
        complaintService.saveComplaint(platformName, gameId, complaintDTO);
        DataResponse<Object> response = DataResponse.createResponse(null, true, ResponseCodeStrings.SUCCESS, "Complaint is successfully saved.");
        log.info("Complaint is successfully saved.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
