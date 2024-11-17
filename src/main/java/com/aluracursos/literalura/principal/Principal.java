package com.aluracursos.literalura.principal;

import com.aluracursos.literalura.model.Autor;
import com.aluracursos.literalura.model.Datos;
import com.aluracursos.literalura.model.Libro;
import com.aluracursos.literalura.repository.AutorRepository;
import com.aluracursos.literalura.repository.LibroRepository;
import com.aluracursos.literalura.service.AutorService;
import com.aluracursos.literalura.service.ConsumoApi;
import com.aluracursos.literalura.service.ConvierteDatos;

import com.aluracursos.literalura.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

@Component

public class Principal {
    private final LibroService libroService;
    private final AutorService autorService;

    private LibroRepository repositorioLibro;
    private AutorRepository repositorioAutor;

    Scanner scanner = new Scanner(System.in);

    public Principal(LibroService libroService, AutorService autorService, LibroRepository repositorioLibro, AutorRepository repositorioAutor) {
        this.libroService = libroService;
        this.autorService = autorService;
        this.repositorioLibro = repositorioLibro;
        this.repositorioAutor = repositorioAutor;
    }

    @Autowired



    public void menuPrincipal() throws IOException, InterruptedException {
        System.out.println("Bienvenido");

        int opcion = -1;
        while (opcion != 0) {
            try {
                System.out.println("Selecciona la opcion que deseas ejecutar");
                System.out.println("""
                        -1. Buscar libro por titulo.
                        -2. Listar libros registrados.
                        -3. Listar autores registrados.
                        -4. Listar autores vivos en un determinado año.
                        -5. Listar libros por idioma.
                        -0. salir
                        """);

                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer del scanner


            } catch (Exception e) {
                System.out.println("Por favor ingresa un dato valido ");
                scanner.nextLine();

            }
            if (opcion == 0) {
                break;
            }
            switch (opcion) {
                case 1:
                    buscarTituloLibro();//funcion para obtener el primer libro de busqueda por titulo
                    break;
                case 2:
                    //funcion listar libros registrados
                    break;
                case 3:
                    // funcion listar autores registrados
                    break;
                case 4:
                    //funcion mostrar autores vivos por anho
                    break;
                case 5:
                    //funcion buscar libros por idioma

                    break;
                default:
                    System.out.println("Has ingresado una opción NO válida\n"); // opcion por defecto
            }

        }

    }

    private void buscarTituloLibro() throws IOException, InterruptedException {

        ConsumoApi consumoApi = new ConsumoApi();
        ConvierteDatos convierte = new ConvierteDatos();

        System.out.println("Ingrese el titulo del libro que desea buscar:");
        String titulo = scanner.nextLine();

        var tituloBuscado = consumoApi.consultaApi(titulo.toLowerCase().replace(" ", "+"));
        Datos datosLibro = convierte.obtenerDatos(tituloBuscado, Datos.class);

        // Obtener el primer libro de los resultados
        Optional<Libro> primerLibro = datosLibro.datosLibro().stream()
                .findFirst()
                .map(l -> {
                    Libro libro = new Libro();
                    libro.setIdApi(l.idApi());
                    libro.setTitulo(l.titulo());
                    libro.setIdiomas(l.idiomas());
                    libro.setDescargas(l.descargas());  // Suponemos que la API devuelve las descargas

                    // Verificar si el autor ya está en la base de datos
                    Autor autor = new Autor();
                    autor.setNombre(l.datosAutor().get(0).nombreAutor());
                    autor.setFechaDeNacimiento(l.datosAutor().get(0).fechaDeNacimiento());
                    autor.setFechaDeFallecimiento(l.datosAutor().get(0).fechaDeFallecimiento());
                    libro.setAutor(autor);
                    autorService.guardar(libro.getAutor());
                    System.out.println(libro.getAutor());


                    return libro;
                });

        if (primerLibro.isPresent()) {
            Libro libro = primerLibro.get();

            // Validar si el libro ya está guardado en la base de datos
            boolean libroExistente = libroService.existsByIdApi(libro.getIdApi());


                // Guardar el libro en la base de datos
                System.out.println(libro);
                libroService.guardar(libro);



        }


    }


    }



