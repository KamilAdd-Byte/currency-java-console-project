package restconnection.jacksonCsv;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class PersonCsvWriterProcessor {
    public static void main(String[] args) throws IOException {

        File file = new File("person.csv");

        Person person1 = new Person("Zuza","Zajkowska","10",List.of(11,12,13));
        Person person2 = new Person("Kasia","Sulejewska","30",List.of(4,5,6));
        Person person3 = new Person("Kamil","Sulejewska","37",List.of(78,66,33));

        List<Person> personList = Arrays.asList(person1, person2, person3);

        CsvMapper csvMapper = new CsvMapper();
        csvMapper.configure(JsonGenerator.Feature.IGNORE_UNKNOWN,true);
        CsvSchema schema = CsvSchema.builder().setUseHeader(true)
                .addColumn("name")
                .addColumn("surname")
                .addColumn("age")
                .addColumn("numbers")
                .build();

        ObjectWriter writer = csvMapper.writerFor(Person.class).with(schema);
        writer.writeValues(file).writeAll(personList);







//        writer.writeValue(file,person4);


    }
}
