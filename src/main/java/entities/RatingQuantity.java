package entities;

public class RatingQuantity {

    private int rating;
    private int quantity;

    public RatingQuantity(int rating, int quantity) {
        this.rating = rating;
        this.quantity = quantity;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
