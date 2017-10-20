package br.com.datasources.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.datasources.repository.customer2.CustomerRepository2;

@Service
public class Customer2Service {

	@Autowired
	private CustomerRepository2 customerRepository2;
	
	@Autowired 
	private Customer1Service customer1Service;

	@Transactional("customer2TransactionManager")
	public void addAll() {
		customerRepository2.deleteAll();

		customerRepository2.save(new br.com.datasources.repository.customer2.Customer("Jack2", "Bauer"));
		customerRepository2.save(new br.com.datasources.repository.customer2.Customer("Chloe2", "O'Brian"));
		customerRepository2.save(new br.com.datasources.repository.customer2.Customer("Kim2", "Bauer"));
		customerRepository2.save(new br.com.datasources.repository.customer2.Customer("David2", "Palmer"));
		customerRepository2.save(new br.com.datasources.repository.customer2.Customer("Michelle2", "Dessler"));
		
		customer1Service.addAll();
	}

	public Collection<br.com.datasources.repository.customer2.Customer> listAll2() {
		return customerRepository2.findAll();
	}
}
