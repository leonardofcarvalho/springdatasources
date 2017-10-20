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
import br.com.datasources.service.Customer1Service;
import br.com.datasources.service.Customer2Service;

@Controller
@ResponseBody
public class SampleController {

	@Autowired
	private Customer1Service customer1Service;
	@Autowired
	private Customer2Service customer2Service;

	@RequestMapping(value = "/addAll", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void addAll() {
		customer1Service.addAll();
	}

	@RequestMapping(value = "/listAll", method = { RequestMethod.POST, RequestMethod.GET })
	public Collection<Customer> listAll() {
		return customer1Service.listAll();
	}

	@RequestMapping(value = "/addAll2")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void addAll2() {
		customer2Service.addAll();
	}

	@RequestMapping(value = "/listAll2", method = { RequestMethod.POST, RequestMethod.GET })
	public Collection<br.com.datasources.repository.customer2.Customer> listAll2() {
		return customer2Service.listAll2();
	}
}