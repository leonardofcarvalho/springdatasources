package br.com.datasources.repository;

import java.util.Properties;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jndi.JndiTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@PropertySource({ "classpath:jndi.properties" })
@EnableJpaRepositories(basePackages = {
		"br.com.datasources.repository.customer2" }, entityManagerFactoryRef = "customer2EntityManagerFactory", transactionManagerRef = "customer2TransactionManager")
public class Customer2Config {
	@Autowired
	private Environment env;

	@Bean
	public @Primary LocalContainerEntityManagerFactoryBean customer2EntityManagerFactory() throws NamingException {
		final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(customer2DataSource());
		// em.setPackagesToScan(new String[] { "org.baeldung.persistence.model" });
		em.setPackagesToScan("br.com.datasources.repository.customer2");
		em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		em.setJpaProperties(additionalProperties());
		return em;
	}

	@Bean
	public @Primary DataSource customer2DataSource() throws NamingException {
		return (DataSource) new JndiTemplate().lookup(env.getProperty("spring.datasource.jndi-name2"));
	}

	@Bean
	public @Primary PlatformTransactionManager customer2TransactionManager(final EntityManagerFactory emf) {
		final JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		return transactionManager;
	}

	// @Bean
	// public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
	// return new PersistenceExceptionTranslationPostProcessor();
	// }

	final Properties additionalProperties() {
		final Properties hibernateProperties = new Properties();
		// hibernateProperties.setProperty("hibernate.hbm2ddl.auto",
		// env.getProperty("hibernate.hbm2ddl.auto"));
		hibernateProperties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		hibernateProperties.setProperty("hibernate.cache.use_second_level_cache", "false");
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");

		return hibernateProperties;
	}
}
