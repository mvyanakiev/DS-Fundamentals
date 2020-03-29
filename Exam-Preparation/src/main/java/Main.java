import interfaces.Entity;
import model.BaseEntity;
import model.Invoice;

public class Main {
    public static void main(String[] args) {

        Entity entity = new Invoice(11,222);

        System.out.println(entity.getClass().getSimpleName());

    }
}