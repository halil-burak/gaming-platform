package com.turkcell.playcell.gamingplatform.cms.controller;

import com.turkcell.playcell.gamingplatform.cms.dto.tariff.TariffCreateDTO;
import com.turkcell.playcell.gamingplatform.cms.dto.tariff.TariffDTO;
import com.turkcell.playcell.gamingplatform.cms.service.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tariffs")
public class TariffController {

    @Autowired
    private TariffService tariffService;

    @PostMapping("")
    public Long saveTariff(@RequestBody @Valid TariffCreateDTO tariffCreateDTO) {
        return tariffService.saveTariff(tariffCreateDTO);
    }

    @PutMapping("/{id}")
    public void updateTariff(@PathVariable(name = "id") Long id, @RequestBody @Valid TariffCreateDTO tariffCreateDTO) {
        tariffService.updateTariff(id, tariffCreateDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteTariff(@PathVariable(name = "id")Long id) {
        tariffService.deleteTariff(id);
    }

    @GetMapping("")
    public List<TariffDTO> getTariffs() {
        return tariffService.getTariffs();
    }
}
