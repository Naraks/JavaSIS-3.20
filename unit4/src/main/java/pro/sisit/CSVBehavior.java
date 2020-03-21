package pro.sisit;

import java.util.List;

public interface CSVBehavior {
    List<String> getFieldsForCSV();
    void fillObjectFromCSVFields(List<String> fields);
}
