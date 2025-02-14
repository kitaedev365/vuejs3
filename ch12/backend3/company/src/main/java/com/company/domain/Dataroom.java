package com.company.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dataroom {
    private int dno;
    private String title;
    private String content;
    private String author;
    private String datafile;
    private String resdate;
    private int hits;
}
