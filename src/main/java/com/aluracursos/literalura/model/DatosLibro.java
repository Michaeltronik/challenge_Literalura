package com.aluracursos.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public record DatosLibro(
        @JsonAlias("id") Long idApi,
        @JsonAlias("title")String titulo,
        @JsonAlias("authors")List<DatosAutor> datosAutor,
        @JsonAlias("Languajes") List<String>idiomas,
        @JsonAlias("download_count")Double descargas

        ) {
}
