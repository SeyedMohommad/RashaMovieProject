package com.example.rasha.service;
import com.example.rasha.repository.OscarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OscarService {

    private final OscarRepository oscarRepository;

    @Autowired
    public OscarService(OscarRepository oscarRepository) {
        this.oscarRepository = oscarRepository;
    }

    public boolean checkOscarAward(String title) {
        // Logic to check if the movie has won an Oscar
        // This can be a simple existence check or more complex logic based on your requirements
        return oscarRepository.existsByTitle(title);
    }
}
