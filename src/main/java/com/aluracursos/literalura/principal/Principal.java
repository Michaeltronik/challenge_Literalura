package com.aluracursos.literalura.principal;

import com.aluracursos.literalura.model.Datos;
import com.aluracursos.literalura.service.ConsumoApi;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Scanner;

@Component

public class Principal {
    Scanner scanner = new Scanner(System.in);

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
                    obtenerDatosApi();
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
public void obtenerDatosApi() throws IOException, InterruptedException {
    ConsumoApi consumoApi = new ConsumoApi();
    System.out.println("Ingrese el titulo del libro que desea buscar:");
    String titulo = scanner.nextLine();

    var tituloBuscado = consumoApi.consultaApi(titulo.toLowerCase().replace(" ", "+"));
    System.out.println(tituloBuscado);
}



}
