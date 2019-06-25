package com.turkcell.playcell.gamingplatform.api.service;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.turkcell.playcell.gamingplatform.api.dto.CategoryDto;
import com.turkcell.playcell.gamingplatform.api.dto.GameDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameResponse implements Serializable {

    private LocalDateTime timestamp;
    private String thumbnailDomain;
    private Map<String, List<Long>> tags;
    private List<CategoryDto> categories;
    private List<GameDto> games;
    
    private static final long serialVersionUID = 7156526077883281623L;
  
}
