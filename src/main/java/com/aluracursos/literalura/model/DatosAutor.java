package com.aluracursos.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DatosAutor(
        @JsonAlias ("name")String nombreAutor,
        @JsonAlias ("birth_year") int fechaDeNacimiento,
        @JsonAlias ("death_year")int fechaDeFallecimiento
) {
}
