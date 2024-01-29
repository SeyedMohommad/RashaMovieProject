package com.example.rasha.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {
    private String year;
    private String category;
    private String nominee;
    private String additionalInfo;
    private String won;
}
