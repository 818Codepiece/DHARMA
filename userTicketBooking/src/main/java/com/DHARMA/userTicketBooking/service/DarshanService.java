package com.DHARMA.userTicketBooking.service;

import com.DHARMA.userTicketBooking.entity.Darshan;
import com.DHARMA.userTicketBooking.entity.Temple;
import com.DHARMA.userTicketBooking.repositiory.DarshanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DarshanService {

    @Autowired
    private DarshanRepository darshanRepository;


    public Darshan getDarshanByZone(String zone) {
            return null;
    }

    public Darshan getDarshanTime(String zone) {
        return null;
    }

    public Temple updateDarshanTime(Long id, DarshanDto darshanDto) {
            Darshan darshan = darshanRepository.findById(id)
                    .orElseThrow(()-> new RuntimeException("Not Found"));

            darshan.setDarshanTime(darshanDto.getDarshanTime());
            darshan.setId(darshanDto.getDarshanId());
            darshan.setPrice(darshanDto.getPrice());

            return darshanRepository.save(darshan).getTemple();
    }

    public void deleteDarshan(Long id) {
        darshanRepository.deleteById(id);
    }
}
