package currencyapp.csvmapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class CsvProcessorWrite extends ObjectMapper {

    private String fileName;
    private CsvMapper csvMapper;
    private CsvSchema schema;
    private ObjectWriter writer;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public CsvMapper getCsvMapper() {
        return csvMapper;
    }

    public void setCsvMapper(CsvMapper csvMapper) {
        this.csvMapper = csvMapper;
    }

    public CsvSchema getSchema() {
        return schema;
    }

    public void setSchema(CsvSchema schema) {
        this.schema = schema;
    }

    public ObjectWriter getWriter() {
        return writer;
    }

    public void setWriter(ObjectWriter writer) {
        this.writer = writer;
    }
}
