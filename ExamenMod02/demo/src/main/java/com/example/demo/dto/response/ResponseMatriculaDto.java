package com.example.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMatriculaDto {
    private int id;
    private String estudiante;
    private String curso;
    private Date fechaInscripcion;
    private BigDecimal nota;
}
