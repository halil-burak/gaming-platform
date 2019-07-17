package com.turkcell.playcell.gamingplatform.cms.controller;

import com.turkcell.playcell.gamingplatform.cms.dto.ComplaintDTO;
import com.turkcell.playcell.gamingplatform.cms.service.ComplaintService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/complaints")
@RequiredArgsConstructor
public class ComplaintController {

    private final ComplaintService complaintService;

    @GetMapping
    public List<ComplaintDTO> getComplaints() {
        return complaintService.getComplaints();
    }
}