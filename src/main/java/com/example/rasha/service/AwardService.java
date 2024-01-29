package com.example.rasha.service;
import com.example.rasha.model.Award;
import com.example.rasha.repository.AwardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class AwardService {

    @Autowired
    private AwardRepository awardRepository;

    public void importCSV(MultipartFile file) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            List<Award> awards = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Award award = new Award();
                // Set award properties from data
                awards.add(award);
            }
            awardRepository.saveAll(awards);
        } catch (Exception e) {
            // Handle exceptions
        }
    }
}

