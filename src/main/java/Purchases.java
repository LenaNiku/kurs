import java.util.ArrayList;
import java.util.List;

public class Purchases {

    private final List<Purchase> purchases;

    public Purchases() {
        purchases = new ArrayList<>();
    }

    public List<Purchase> addPurchase(Purchase purchase) {
        purchases.add(purchase);
        return purchases;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }
}
