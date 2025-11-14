package POO.Examen.clases;

import java.util.List;
import java.util.ArrayList;

public class MovieRentalSystem {
    private List<RentalItem> rentalItems;
    private List<Customer> customers;

    public MovieRentalSystem() {
        this.rentalItems = new ArrayList<>();
        this.customers = new ArrayList<>();
    }

    public void addItem(RentalItem item) {
        for (RentalItem r : rentalItems) {
            if (r.getItemID().equals(item.getItemID())) {
                System.out.println("✗ La pelicula con ID " + item.getItemID() + " ya está registrada.");
                return;
            }
        }
        rentalItems.add(item);
        System.out.println("✓ Película agregada: " + item.getItemName());
    }

    public void addItem(Customer customer) {
        for (Customer c : customers) {
            if (c.getCustomerID().equals(customer.getCustomerID())) {
                System.out.println("✗ El cliente con ID " + customer.getCustomerID() + " ya está registrado.");
                return;
            }
        }
        customers.add(customer);
        System.out.println("✓ Cliente agregado: " + customer.getCustomerName());
    }

    public void rentMovieToCustomer(String customerID, String movieID) {
        Customer customer = findCustomerByID(customerID);
        Movie movie = findMovieByID(movieID);

        if (customer == null) {
            System.out.println("✗ Cliente no encontrado.");
            return;
        }
        if (movie == null) {
            System.out.println("✗ Película no encontrada.");
            return;
        }
        if (!movie.isAvailable()) {
            System.out.println("✗ La película ya está rentada.");
            return;
        }
        movie.setAvailable(false);
        customer.getRentedMovies().add(movie);
        System.out.println("✓ Película '" + movie.getItemName() + "' rentada a " + customer.getCustomerName());
    }

    public void returnMovie(String customerID, String movieID) {
        Customer customer = findCustomerByID(customerID);
        Movie movie = findMovieByID(movieID);
        if (customer == null || movie == null) {
            System.out.println("✗ Cliente o película no encontrados.");
            return;
        }
        if (customer.getRentedMovies().remove(movie)) {
            movie.setAvailable(true);
            System.out.println("✓ Película '" + movie.getItemName() + "' devuelta por " + customer.getCustomerName());
        } else {
            System.out.println("✗ El cliente no tenía rentada esta película.");
        }
    }

    public void showAllItems() {
        System.out.println("===== PELÍCULAS DISPONIBLES =====");
        for (RentalItem item : rentalItems) {
            item.showDetails();
        }
    }

    public void showAllCustomers() {
        System.out.println("===== CLIENTES REGISTRADOS =====");
        for (Customer c : customers) {
            c.showCustomerDetails();
        }
    }

    private Customer findCustomerByID(String id) {
        for (Customer c : customers) {
            if (c.getCustomerID().equals(id)) {
                return c;
            }
        }
        return null;
    }

    private Movie findMovieByID(String id) {
        for (RentalItem item : rentalItems) {
            if (item instanceof Movie && item.getItemID().equals(id)) {
                return (Movie) item;
            }
        }
        return null;
    }
}
