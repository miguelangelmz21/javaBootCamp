package POO.Examen.clases;

import POO.Examen.interfaces.MetodoComun;

public abstract class RentalItem implements MetodoComun {
    private String itemName;
    private String itemID;
    private boolean available;

    public RentalItem(String itemName, String itemID) {
        this.itemName = itemName;
        this.itemID = itemID;
        this.available = true;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void rent() {
        if (available) {
            available = false;
            System.out.println("✓ Ítem rentado: " + itemName);
        } else {
            System.out.println("✗ El ítem no está disponible para renta.");
        }
    }

    public void returnItem() {
        if (!available) {
            available = true;
            System.out.println("✓ Ítem devuelto: " + itemName);
        } else {
            System.out.println("✗ El ítem ya estaba disponible.");
        }
    }

    public abstract void showDetails();
    /*public void showDetails() {
        System.out.println("=== Detalles del Ítem ===");
        System.out.println("Nombre: " + itemName);
        System.out.println("ID: " + itemID);
        System.out.println("Disponible: " + (available ? "Sí" : "No"));
        System.out.println("=========================");
    }*/
}
