package org.alghom.deathstar.config;

import org.alghom.deathstar.exceptions.MillenniumFalcomException;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.IOException;

public class SQLiteDataSource {
    private static final String DRIVER_CLASS_NAME = "org.sqlite.JDBC";

    private final ConfigurationReader configurationReader;

    public SQLiteDataSource(ConfigurationReader configurationReader) {
        this.configurationReader = configurationReader;
    }

    public DataSource dataSource() throws IOException, MillenniumFalcomException {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DRIVER_CLASS_NAME);
        dataSource.setUrl(configurationReader.dbPath());
        return dataSource;
    }
}
