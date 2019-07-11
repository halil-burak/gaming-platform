package com.turkcell.playcell.gamingplatform.cms.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageDTO<T>  {

    private List<T> content;

    private long totalElements;

    private long totalPages;

}
