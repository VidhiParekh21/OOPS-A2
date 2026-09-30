
class OutOfStockException extends Exception {

    private int shortfall;

    public OutOfStockException(String message, int shortfall) {
        super(message);
        this.shortfall = shortfall;
    }

    public int getShortfall() {
        return shortfall;
    }
}

class InvalidQuantityException extends Exception {

    public InvalidQuantityException(String message) {
        super(message);
    }
}

public class Warehouse {

    private int stock = 10;

    public void issue(String item, int qty)
            throws OutOfStockException, InvalidQuantityException {

        if (qty <= 0) {
            throw new InvalidQuantityException(
                    "Quantity must be greater than 0.");
        }

        if (qty > stock) {
            int shortfall = qty - stock;

            throw new OutOfStockException(
                    "Not enough stock for " + item,
                    shortfall);
        }

        stock = stock - qty;

        System.out.println(
                qty + " " + item + " issued successfully.");

        System.out.println("Remaining stock: " + stock);
    }

    public static void main(String[] args) {

        Warehouse warehouse = new Warehouse();

        String[] items = {
                "Pen", "Notebook", "Bag", "Pencil"
        };

        int[] quantities = {
                3, 20, -2, 2
        };

        for (int i = 0; i < items.length; i++) {

            try {

                warehouse.issue(items[i], quantities[i]);

            } catch (OutOfStockException e) {

                System.out.println(
                        "Out of stock. Shortfall: "
                                + e.getShortfall());

            } catch (InvalidQuantityException e) {

                System.out.println(e.getMessage());
            }

            System.out.println();
        }
    }
}
