package com.example.rasha.controller;
import com.example.rasha.service.AwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/awards")
public class AwardController {

    @Autowired
    private AwardService awardService;

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        awardService.importCSV(file);
        return "File uploaded successfully!";
    }
}