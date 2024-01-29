package com.example.rasha.controller;
import com.example.rasha.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/awards")
public class AwardController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        movieService.importCSV(file);
        return "File uploaded successfully!";
    }
}