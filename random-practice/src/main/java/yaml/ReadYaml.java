package yaml;

import lombok.extern.slf4j.Slf4j;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;

@Slf4j
public class ReadYaml {
    private static final String YAML_FILE_NAME = "yaml/yaml1.yaml";

    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(
                ReadYaml.class.getClassLoader().getResource(YAML_FILE_NAME).getPath());

        Yaml yaml = new Yaml();

        Map<String, Object> load = yaml.load(fileInputStream);
        ArrayList<Object> x = (ArrayList<Object>) load.get("x");
        x.forEach((e) -> {
            Map<String, Object> y = (Map<String, Object>) e;
            Map<String, Object> z = (Map<String, Object>) y.get("y");
            String z1 = (String) z.get("z1");
            String z2 = (String) z.get("z2");

            log.info("z1 = {}, z2 = {}", z1, z2);
        });
    }
}
