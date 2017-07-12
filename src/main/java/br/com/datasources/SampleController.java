package br.com.datasources;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.datasources.repository.customer1.Customer;
import br.com.datasources.repository.customer1.CustomerRepository;
import br.com.datasources.repository.customer2.CustomerRepository2;

@Controller
public class SampleController {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CustomerRepository2 customerRepository2;

	@RequestMapping(value = "/addAll")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void addAll() {
		customerRepository.save(new Customer("Jack", "Bauer"));
		customerRepository.save(new Customer("Chloe", "O'Brian"));
		customerRepository.save(new Customer("Kim", "Bauer"));
		customerRepository.save(new Customer("David", "Palmer"));
		customerRepository.save(new Customer("Michelle", "Dessler"));
	}

	@RequestMapping(value = "/listAll", method = RequestMethod.POST, produces = { "application/json" })
	public @ResponseBody Collection<Customer> listAll() {
		return customerRepository.findAll();
	}

	@RequestMapping(value = "/addAll2")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void addAll2() {
		customerRepository2.save(new br.com.datasources.repository.customer2.Customer("Jack2", "Bauer"));
		customerRepository2.save(new br.com.datasources.repository.customer2.Customer("Chloe2", "O'Brian"));
		customerRepository2.save(new br.com.datasources.repository.customer2.Customer("Kim2", "Bauer"));
		customerRepository2.save(new br.com.datasources.repository.customer2.Customer("David2", "Palmer"));
		customerRepository2.save(new br.com.datasources.repository.customer2.Customer("Michelle2", "Dessler"));
	}

	@RequestMapping(value = "/listAll2", method = RequestMethod.POST, produces = { "application/json" })
	public @ResponseBody Collection<br.com.datasources.repository.customer2.Customer> listAll2() {
		return customerRepository2.findAll();
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SampleController.class, args);
	}
}