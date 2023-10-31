package org.alghom.deathstar.exposure;

import org.alghom.deathstar.config.ConfigurationReader;
import org.alghom.deathstar.config.SQLiteDataSource;
import org.alghom.deathstar.exceptions.MillenniumFalcomException;
import org.alghom.deathstar.domain.models.GalaxyMap;
import org.alghom.deathstar.domain.repositories.UniverseRepository;
import org.alghom.deathstar.domain.repositories.UniverseRepositoryImpl;
import org.alghom.deathstar.domain.services.ComputerService;
import org.alghom.deathstar.domain.services.ComputerServiceImpl;
import org.alghom.deathstar.domain.services.FalconService;
import org.alghom.deathstar.domain.services.FalconServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.io.IOException;

@SpringBootApplication
public class GuiStartUnit {
    @Bean
    public DataSource dataSource(ConfigurationReader configurationReader) throws IOException, MillenniumFalcomException {
        SQLiteDataSource sqLiteDataSource = new SQLiteDataSource(configurationReader);
        return sqLiteDataSource.dataSource();
    }

    @Bean
    public ConfigurationReader configurationReader(@Value("${config.path}") String path) {
        return new ConfigurationReader(path);
    }

    @Bean
    public ComputerService computerService(ConfigurationReader configurationReader, GalaxyMap galaxyMap) throws MillenniumFalcomException {
        return new ComputerServiceImpl(configurationReader.millenniumFalcon(), galaxyMap);
    }

    @Bean
    public FalconService falconService(ComputerService computerService) {
        return new FalconServiceImpl(computerService);
    }

    @Bean
    public GalaxyMap galaxyMap(UniverseRepository universeRepository) {
        return new GalaxyMap(universeRepository.getRoutes());
    }

    @Bean
    public UniverseRepository universeRepository(JdbcTemplate jdbcTemplate) {
        return new UniverseRepositoryImpl(jdbcTemplate);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/give-me-the-odds").allowedOrigins("http://localhost:3000");
            }
        };
    }

}
