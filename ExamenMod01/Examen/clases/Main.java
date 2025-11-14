package POO.Examen.clases;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║   SISTEMA DE GESTIÓN DE RENTA DE PELÍCULAS   ║");
        System.out.println("║   Examen de Miguel Angel Mamani Zeballos     ║");
        System.out.println("╚══════════════════════════════════════════════╝");

        Movie movie1 = new Movie("Kimetsu no Yaiba", "M001", "Juan Perez", 127, "+14", "Anime / Acción / Violencia");
        Movie movie2 = new Movie("El Rey León", "M002", "Waldo Disney", 118, "APT", "Animación / Aventura");
        Movie movie3 = new Movie("Lilo & Stitch", "M003", "Addam Lopez", 85, "APT", "Animación / Comedia");
        Movie movie4 = new Movie("Spider-Man: Un nuevo universo", "M004", "Stan Lee", 117, "APT", "Animación / Superhéroes");
        Movie movie5 = new Movie("One Piece", "M005", "Oda Hechiro", 102, "APT", "Anime / Acción");
        Movie movie6 = new Movie("Que paso ayer", "M006", "Aldo Miyashiro", 86, "+14", "Suspenso / Comedia Adultos / Violencia");
        Movie movie7 = new Movie("Coco", "M006", "Bruce Lee", 105, "APT", "Animación / Musical");

        MovieRentalSystem cinePeru = new MovieRentalSystem();

        System.out.println("\nRegistrando películas:");
        cinePeru.addItem(movie1);
        cinePeru.addItem(movie2);
        cinePeru.addItem(movie3);
        cinePeru.addItem(movie4);
        cinePeru.addItem(movie5);
        cinePeru.addItem(movie6);
        cinePeru.addItem(movie7);

        Customer customer1 = new Customer("Yahaira Plasencia", "C001");
        Customer customer2 = new Customer("Andrés Wiese", "C002");
        Customer customer3 = new Customer("Luciana Fuster", "C003");
        Customer customer4 = new Customer("Jeferson Farfan", "C004");
        Customer customer5 = new Customer("Flavia Laos", "C001");

        System.out.println("\nRegistrando Clientes:");
        cinePeru.addItem(customer1);
        cinePeru.addItem(customer2);
        cinePeru.addItem(customer3);
        cinePeru.addItem(customer4);
        cinePeru.addItem(customer5);
        Scanner scanner = new Scanner(System.in);
        int opc;
        String customerID;
        String movieID;
        do{
            System.out.println("\nSISTEMA DE RENTA DE PELÍCULAS  ");
            System.out.println("=======================================");
            System.out.println("1 Rentar una película a un cliente");
            System.out.println("2 Procesar devolución de película");
            System.out.println("3 Mostrar detalles de todas las películas");
            System.out.println("4 Mostrar detalles de todos los clientes");
            System.out.println("5 Salir del sistema");
            System.out.println("---------------------------------------");
            System.out.print("Ingrese una opcion: ");
            opc = scanner.nextInt();
            scanner.nextLine();
            switch (opc) {
                case 1:
                    System.out.print("Ingrese el ID del cliente: ");
                    customerID = scanner.nextLine();
                    System.out.print("Ingrese el ID de la película: ");
                    movieID = scanner.nextLine();
                    cinePeru.rentMovieToCustomer(customerID, movieID);
                    break;
                case 2:
                    System.out.print("Ingrese el ID del cliente: ");
                    customerID = scanner.nextLine();
                    System.out.print("Ingrese el ID de la película: ");
                    movieID = scanner.nextLine();
                    cinePeru.returnMovie(customerID, movieID);
                    break;
                case 3:
                    System.out.println("\nMostrar detalles de todas las películas");
                    cinePeru.showAllItems();
                    break;
                case 4:
                    System.out.println("\nMostrar detalles de todos los clientes");
                    cinePeru.showAllCustomers();
                    break;
                case 5:
                    System.out.println("Saliendo del sistema");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente por favor.");
                    break;
            }
        }while(opc!=5);
        System.out.println("\nFIN DEL PROGRAMA 24/10/2025");
        scanner.close();
    }
}
