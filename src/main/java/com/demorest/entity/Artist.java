package com.demorest.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Created by marcus on 09/11/2016.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Artist {
    private int id;
    private String firstName;
    private String lastName;
    private int rate;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime date;
}
