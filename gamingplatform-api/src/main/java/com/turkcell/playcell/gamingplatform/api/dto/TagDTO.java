package com.turkcell.playcell.gamingplatform.api.dto;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class TagDTO implements Serializable {
	private static final long serialVersionUID = 682481735386557634L;
	private Map<String, List<Long>> tags;
}
