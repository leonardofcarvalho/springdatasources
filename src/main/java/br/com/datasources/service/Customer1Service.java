package br.com.datasources.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.datasources.repository.customer1.Customer;
import br.com.datasources.repository.customer1.CustomerRepository;

@Service
public class Customer1Service {

	@Autowired
	private CustomerRepository customerRepository;

	@Transactional("customer1TransactionManager")
	public void addAll() {

		customerRepository.deleteAll();

		customerRepository.save(new Customer("Jack", "Bauer"));
		System.out.println(customerRepository.findAll().size());
		customerRepository.save(new Customer("Chloe", "O'Brian"));
		System.out.println(customerRepository.findAll().size());
		customerRepository.save(new Customer("Kim", "Bauer"));
		System.out.println(customerRepository.findAll().size());
		customerRepository.save(new Customer("David", "Palmer"));
		System.out.println(customerRepository.findAll().size());
		customerRepository.save(new Customer("Michelle", "Dessler"));
		System.out.println(customerRepository.findAll().size());
	}

	public Collection<Customer> listAll() {
		return customerRepository.findAll();
	}
}
