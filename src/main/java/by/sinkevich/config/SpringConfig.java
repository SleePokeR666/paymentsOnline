package by.sinkevich.config;

import by.sinkevich.dao.CustomerDao;
import by.sinkevich.dao.CustomerDaoImpl;
import by.sinkevich.service.CustomerService;
import by.sinkevich.service.CustomerServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/payments?useSSL=false");
		dataSource.setUsername("root");
		dataSource.setPassword("pass");
		return dataSource;
	}
	@Bean
	public JdbcTemplate getJdbcTemplate() {
		return new JdbcTemplate(getDataSource());
	}

	@Bean
	public CustomerDao getCustomerDao() {
		return new CustomerDaoImpl(getJdbcTemplate());
	}

	@Bean
	public CustomerService getCustomerService() {
		return new CustomerServiceImpl(getCustomerDao());
	}
}
