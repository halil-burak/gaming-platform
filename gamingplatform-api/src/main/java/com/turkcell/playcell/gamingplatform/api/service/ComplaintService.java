package com.turkcell.playcell.gamingplatform.api.service;

import com.turkcell.playcell.gamingplatform.api.dto.ComplaintDTO;

public interface ComplaintService {

    void saveComplaint(String platformName, Long gameId, ComplaintDTO complaintDTO);
}
