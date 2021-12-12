package com.lh.spring.dto;

import lombok.Data;

@Data
public class Task {

    private Long id;

    private String taskName;

    private String taskContext;

    private boolean finish;
}
