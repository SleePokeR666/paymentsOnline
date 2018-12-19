package by.sinkevich.config;

import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {
		"by.sinkevich.dao.impl",
		"by.sinkevich.mapper",
		"by.sinkevich.service.impl"
})
@Import(DatasourceConfiguration.class)
public class SpringConfig {

	@Bean
	DatasourceConfiguration datasourceConfiguration() {
		return new DatasourceConfiguration();
	}

	@Bean
	public DataSource dataSource(DatasourceConfiguration datasourceConfiguration) {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(datasourceConfiguration.getDriverClassName());
		dataSource.setUrl(datasourceConfiguration.getUrl());
		dataSource.setUsername(datasourceConfiguration.getUsername());
		dataSource.setPassword(datasourceConfiguration.getPassword());

		Properties connectionProperties = new Properties();
		connectionProperties.put("useSSL", datasourceConfiguration.getUseSSL());
		connectionProperties.put("allowPublicKeyRetrieval",
				datasourceConfiguration.getAllowPublicKeyRetrieval());
		connectionProperties.put("useUnicode", datasourceConfiguration.getUseUnicode());
		connectionProperties.put("characterEncoding",
				datasourceConfiguration.getCharacterEncoding());
		dataSource.setConnectionProperties(connectionProperties);
		return dataSource;
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean
	public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
