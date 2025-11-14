package POO.Examen.clases;

import POO.Examen.interfaces.MetodoComun;

import java.util.List;
import java.util.ArrayList;

public class Customer {
    private String customerName;
    private String customerID;
    private List<Movie> rentedMovies;

    public Customer(String customerName, String customerID) {
        this.customerName = customerName;
        this.customerID = customerID;
        this.rentedMovies = new ArrayList<>();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public List<Movie> getRentedMovies() {
        return rentedMovies;
    }

    public void setRentedMovies(List<Movie> rentedMovies) {
        this.rentedMovies = rentedMovies;
    }

    public void showCustomerDetails() {
        System.out.println("Cliente: " + customerName);
        System.out.println("ID: " + customerID);
        System.out.println("Películas rentadas:");
        if (rentedMovies.isEmpty()) {
            System.out.println(" - Ninguna película rentada");
        } else {
            for (Movie movie : rentedMovies) {
                System.out.println(" - " + movie.getItemName());
            }
        }
        System.out.println("-----------------------------------");
    }
}
