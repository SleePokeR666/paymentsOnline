package by.sinkevich.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {
		"by.sinkevich.dao.impl",
		"by.sinkevich.mapper",
		"by.sinkevich.service.impl"
})
public class SpringConfig {

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/payments?" +
				"useSSL=false&allowPublicKeyRetrieval=true&useUnicode=true&characterEncoding=utf8");
		dataSource.setUsername("root");
		dataSource.setPassword("pass");
		return dataSource;
	}

	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}

	@Bean
	public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
}
