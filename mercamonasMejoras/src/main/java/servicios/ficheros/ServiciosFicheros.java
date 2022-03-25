package servicios.ficheros;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import config.Configuration;
import ui.Main;

import java.io.IOException;

public class ServiciosFicheros {

    public static Configuration config;

    public static Configuration getConfig()
    {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();


        try {
            config = mapper.readValue(
                    Main.class.getClassLoader().getResourceAsStream("config.yaml"), Configuration.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return config;
    }
}
