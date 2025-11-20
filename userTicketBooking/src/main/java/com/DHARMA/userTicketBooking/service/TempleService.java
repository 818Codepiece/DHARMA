package com.DHARMA.userTicketBooking.service;

import com.DHARMA.userTicketBooking.dto.TempleDto;
import com.DHARMA.userTicketBooking.entity.Temple;
import com.DHARMA.userTicketBooking.repositiory.TempleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TempleService {

    @Autowired
    private TempleRepository templeRepository;


    public Temple addTemple(TempleDto templeDto) {
        Temple temple = new Temple();
        temple.setName(templeDto.getName());
        temple.setDescription(templeDto.getDescription());
        temple.setDuration(templeDto.getDuration());
        temple.setClosingDate(templeDto.getClosingDate());
        temple.setSpeciality(templeDto.getSpeciality());

        return templeRepository.save(temple);
    }

    public List<Temple> getAllTemples() {
         return templeRepository.findAll();
    }

    public List<Temple> getTempleBySpeciality(String speciality) {
        Optional<List<Temple>> listOfTemple = templeRepository.findBySpeciality(speciality);

        if(listOfTemple.isPresent()){
            return listOfTemple.get();
        }
        else throw new RuntimeException("NO TEMPLE FOUND FOR THIS SPECIALITY "+speciality);
    }

    public Temple getTempleByName(String title) {
        Optional<Temple> temple = templeRepository.findByName(title);
        if (temple.isPresent()){
            return temple.get();
        }
        else throw new RuntimeException("NO TEMPLE FOUND FOR TITLE "+title);
    }

    public Temple updateTemple(Long id, TempleDto templeDto) {
        Temple temple = templeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("NO TEMPLE FOUND FOR ID "+id));
        temple.setName(templeDto.getName());
        temple.setDescription(templeDto.getDescription());
        temple.setDuration(templeDto.getDuration());
        temple.setClosingDate(templeDto.getClosingDate());
        temple.setSpeciality(templeDto.getSpeciality());

        return templeRepository.save(temple);
    }

    public void deleteTemple(Long id) {
        templeRepository.deleteById(id);
    }

}
