package POO.Examen.clases;

import POO.Examen.interfaces.MetodoComun;

public class Movie extends RentalItem implements MetodoComun {
    private String director;
    private int duration;
    private String ageRating;
    private String genre;

    public Movie(String itemName, String itemID, String director, int duration, String ageRating, String genre) {
        super(itemName, itemID);
        this.director = director;
        this.duration = duration;
        this.ageRating = ageRating;
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getAgeRating() {
        return ageRating;
    }

    public void setAgeRating(String ageRating) {
        this.ageRating = ageRating;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public void showDetails() {
        System.out.println("Película: " + getItemName());
        System.out.println("ID: " + getItemID());
        System.out.println("Disponible: " + (isAvailable() ? "Sí" : "No"));
        System.out.println("Director: " + director);
        System.out.println("Duración: " + duration + " minutos");
        System.out.println("Clasificación: " + ageRating);
        System.out.println("Género: " + genre);
        System.out.println("-----------------------------------");
    }
}
