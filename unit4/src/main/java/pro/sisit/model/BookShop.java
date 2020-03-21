package pro.sisit.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pro.sisit.CSVBehavior;

import java.util.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookShop implements CSVBehavior {

    private String address;
    private String name;
    private String workingHours;

    @Override
    public List<String> getFieldsForCSV() {
        return Arrays.asList(address, name, workingHours);
    }

    @Override
    public void fillObjectFromCSVFields(List<String> fields) {
        if (fields == null) {
            throw new RuntimeException(String.format("Не удалось создать объект %s из csv файла", this.getClass().getSimpleName()));
        }
        this.address = Optional.ofNullable(fields.get(0)).orElseThrow(() -> new RuntimeException("Поле \"Address\" не должно быть пустым"));
        this.name = Optional.ofNullable(fields.get(1)).orElseThrow(() -> new RuntimeException("Поле \"Name\" не должно быть пустым"));
        this.workingHours = Optional.ofNullable(fields.get(2)).orElseThrow(() -> new RuntimeException("Поле \"WorkingHours\" не должно быть пустым"));
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
