package com.turkcell.playcell.gamingplatform.cms.service.impl;

import com.turkcell.playcell.gamingplatform.cms.dto.ComplaintDTO;
import com.turkcell.playcell.gamingplatform.cms.service.ComplaintService;
import com.turkcell.playcell.gamingplatform.common.entity.Complaint;
import com.turkcell.playcell.gamingplatform.common.repository.ComplaintRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository complaintRepository;

    @Override
    public List<ComplaintDTO> getComplaints() {
        List<Complaint> complaints = complaintRepository.findAll();
        return complaints.stream().map(complaint -> {
            ComplaintDTO complaintDTO = new ComplaintDTO();
            complaintDTO.setAgent(complaint.getAgent());
            complaintDTO.setComplaint(complaint.getComplaint());
            complaintDTO.setGameName(complaint.getGameDetail().getGame().getName());
            complaintDTO.setPlatformName(complaint.getGameDetail().getPlatform().getName());
            complaintDTO.setDate(complaint.getCreatedDate());
            return complaintDTO;
        }).collect(Collectors.toList());
    }
}
