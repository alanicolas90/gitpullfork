package config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@Getter
@Log4j2
public class Configuration {

    private Configuration(){}

    private static Configuration configuracion;

    public static synchronized Configuration getInstance(){
        if (configuracion == null)
        {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            mapper.findAndRegisterModules();

            try {
                configuracion = mapper.readValue(
                        Configuration.class.getClassLoader().getResourceAsStream("config.yaml"),
                        Configuration.class);


            } catch (IOException e) {
                log.error(e.getMessage(),e);
            }
        }
        return configuracion;
    }


//    METER LO QUE CONTIENE EL JODIDO YAML
    private String clientsSource;
    private String productsSource;

}
