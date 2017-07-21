package cn.janescott;

import cn.janescott.common.constant.ConfigConstants;
import com.alibaba.druid.pool.DruidDataSource;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;
import javax.sql.DataSource;

@MapperScan("cn.janescott.repository.mapper")
@SpringBootApplication(exclude = {RedisAutoConfiguration.class})
public class ZoneRefactoringApplication {
	@Resource
	private Environment env;

	public static void main(String[] args) {
		SpringApplication.run(ZoneRefactoringApplication.class, args);
	}

	// 加密/解密对象
	@Bean(name = "encryptor")
	public StringEncryptor stringEncryptor(){
		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		SimpleStringPBEConfig config = new SimpleStringPBEConfig();
		config.setPassword(ConfigConstants.JASYPT_KEY);
		config.setAlgorithm(ConfigConstants.JASYPT_ALGORITHM);
		config.setPoolSize(ConfigConstants.ENCRYPT_POOL);
		encryptor.setConfig(config);
		return encryptor;
	}

	@Bean(name = "dataSource")
	public DataSource dataSource(StringEncryptor encryptor){
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName(env.getProperty(ConfigConstants.DATASOURCE_DRIVER_CLASS_NAME));
		dataSource.setUrl(encryptor.decrypt(env.getProperty(ConfigConstants.DATASOURCE_URL)));
		dataSource.setUsername(encryptor.decrypt(env.getProperty(ConfigConstants.DATASOURCE_USERNAME)));
		dataSource.setPassword(encryptor.decrypt(env.getProperty(ConfigConstants.DATASOURCE_PASSWORD)));
		return dataSource;
	}

}
