package com.example.cookers.domain.recipe.dto;

import lombok.*;

@Getter
@Setter
public class MakingStepForm {
    private int stepNumber;
    private String stepText;
    private String stepTipText;
    private String imageFilename;
    private String imageFilePath;
}