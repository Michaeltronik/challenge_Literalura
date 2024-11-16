package com.aluracursos.literalura.model;

import java.util.List;

public class Libro {
    private Long idApi;
    private String titulo;
    private List<String> idiomas;
    private double descargas;
    Autor autor;

    @Override
    public String toString() {
        return "\n***************Datos Libro***************" +
                "\ntitulo = "+ titulo +
                "\nautor = " + autor.getNombre() +
                "\ndescargas = "+ descargas +
                "\nidiomas + " + idiomas +
                "__________________________________________";
    }
}
