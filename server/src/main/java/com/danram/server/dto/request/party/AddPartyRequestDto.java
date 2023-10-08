package com.danram.server.dto.request.party;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Getter
@Setter
public class AddPartyRequestDto {
    private MultipartFile img;
    private String title;
    private String description;
    private String password;
    private String partyType;
    private Long max;
    private String location;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate statedAt;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endAt;
}
