package terrenceong.link.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class DataReader {

    public static List<HashMap<Object,Object>> getJsonDataToMap(String filePath) throws IOException {
        // read json to string
        String json=  FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<Object,Object>> data = mapper.readValue(json, new TypeReference<List<HashMap<Object, Object>>>() {
        });
        return data;
    }
}
