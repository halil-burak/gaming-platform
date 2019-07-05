package com.turkcell.playcell.gamingplatform.api.response;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.turkcell.playcell.gamingplatform.api.dto.CategoryDTO;
import com.turkcell.playcell.gamingplatform.api.dto.GameDTO;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameResponse implements Serializable {

    private LocalDateTime timestamp;
    private String thumbnailDomain;
    private Map<String, List<Long>> tags;
    private List<CategoryDTO> categories;
    private List<GameDTO> games;
    
    private static final long serialVersionUID = 7156526077883281623L;
}
