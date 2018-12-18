package by.sinkevich.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:payments.online.properties")
public class DatasourceConfiguration {

	@Value("${datasource.driver}")
	private String driverClassName;

	@Value("${datasource.url}")
	private String url;

	@Value("${datasource.username}")
	private String username;

	@Value("${datasource.password}")
	private String password;

	@Value("${datasource.connection.useSSL}")
	private String useSSL;

	@Value("${datasource.connection.allowPublicKeyRetrieval}")
	private String allowPublicKeyRetrieval;

	@Value("${datasource.connection.useUnicode}")
	private String useUnicode;

	@Value("${datasource.connection.characterEncoding}")
	private String characterEncoding;

	public String getDriverClassName() {
		return driverClassName;
	}

	public String getUrl() {
		return url;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getUseSSL() {
		return useSSL;
	}

	public String getAllowPublicKeyRetrieval() {
		return allowPublicKeyRetrieval;
	}

	public String getUseUnicode() {
		return useUnicode;
	}

	public String getCharacterEncoding() {
		return characterEncoding;
	}
}
