package com.example.hotelback.services.impl;


import com.example.hotelback.Entities.Cabin;
import com.example.hotelback.dto.CabinDto;
import com.example.hotelback.repositories.CabinRepository;
import com.example.hotelback.services.CabinService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CabinServiceImpl implements CabinService {

    @Autowired
    private CabinRepository cabinRepository;
    @Override
    public CabinDto addCabine(CabinDto cabinDto) {
        Cabin cabin = new Cabin();
        BeanUtils.copyProperties(cabinDto,cabin);

        Cabin newCabin = cabinRepository.save(cabin);

        CabinDto addedCabin = new CabinDto();
        BeanUtils.copyProperties(newCabin,addedCabin);
        return addedCabin;
    }

    @Override
    public List<CabinDto> getAllCabines() {
        List<CabinDto> cabinDtos = new ArrayList<>();
        List<Cabin> cabins = new ArrayList<>();
        cabinRepository.findAll().forEach(cabins::add);

        for(Cabin cabinEntity:cabins){
            CabinDto cabinDto = new CabinDto();
            BeanUtils.copyProperties(cabinEntity,cabinDto);
            cabinDtos.add(cabinDto);
        }

        return cabinDtos;
    }

    @Override
    public CabinDto getCabinByIdCabin(Integer idCabin) {
        CabinDto cabinDto = new CabinDto();
        Cabin cabinEntity = cabinRepository.findByIdcabin(idCabin);

        BeanUtils.copyProperties(cabinEntity,cabinDto);

        return cabinDto;
    }

    @Override
    public Cabin getCabinById(int idCabin) {
        return cabinRepository.findById(idCabin)
                .orElseThrow(() -> new ResourceNotFoundException("Cabin not found with id: " + idCabin));
    }
}