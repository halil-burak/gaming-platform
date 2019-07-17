package com.turkcell.playcell.gamingplatform.api.service.impl;

import com.turkcell.playcell.gamingplatform.api.dto.ComplaintDTO;
import com.turkcell.playcell.gamingplatform.api.service.ComplaintService;
import com.turkcell.playcell.gamingplatform.common.entity.Complaint;
import com.turkcell.playcell.gamingplatform.common.entity.GameDetail;
import com.turkcell.playcell.gamingplatform.common.repository.ComplaintRepository;
import com.turkcell.playcell.gamingplatform.common.repository.GameDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository complaintRepository;

    private final GameDetailRepository gameDetailRepository;

    @Override
    public void saveComplaint(String platformName, Long gameId, ComplaintDTO complaintDTO) {
        GameDetail gameDetail = gameDetailRepository.findByPlatformNameEqualsIgnoreCaseAndGameId(platformName,gameId).get();
        Complaint complaint = new Complaint();
        complaint.setAgent(complaintDTO.getAgent());
        complaint.setComplaint(complaintDTO.getComplaint());
        complaint.setGameDetail(gameDetail);
        complaintRepository.save(complaint);
    }
}
