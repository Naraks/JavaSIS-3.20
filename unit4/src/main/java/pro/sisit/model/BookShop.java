package pro.sisit.model;

import lombok.Getter;
import pro.sisit.CSVBehavior;

import java.util.*;

@Getter
public class BookShop implements CSVBehavior {

    private String address;
    private String name;
    private String workingHours;

    public BookShop(){}

    public BookShop(String address, String name, String workingHours) {
        this.address = address;
        this.name = name;
        this.workingHours = workingHours;
    }

    @Override
    public List<String> getFieldsForCSV() {
        return Arrays.asList(address, name, workingHours);
    }

    @Override
    public void fillObjectFromCSVFields(List<String> fields) {
        this.address = fields.get(0);
        this.name = fields.get(1);
        this.workingHours = fields.get(2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BookShop shop = (BookShop) o;
        return getAddress().equals(shop.getAddress()) &&
                getName().equals(shop.getName()) &&
                getWorkingHours().equals(shop.getWorkingHours());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAddress(), getName(), getWorkingHours());
    }
}
