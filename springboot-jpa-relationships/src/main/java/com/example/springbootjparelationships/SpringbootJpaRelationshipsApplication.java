package com.example.springbootjparelationships;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.springbootjparelationships.entity.Client;
import com.example.springbootjparelationships.entity.Invoice;
import com.example.springbootjparelationships.repository.ClientRepository;
import com.example.springbootjparelationships.repository.InvoiceRepository;

@SpringBootApplication
public class SpringbootJpaRelationshipsApplication implements CommandLineRunner {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private InvoiceRepository invoiceRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaRelationshipsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		manyToOne();
	}

	public void manyToOne() {

		Client client = new Client("Jhon", "Alvarez");
		clientRepository.save(client);

		Invoice invoice = new Invoice("Laptop with intel", 1000L);

		invoice.setClient(client);
		Invoice invoiceDB = invoiceRepository.save(invoice);

		System.out.println(invoiceDB);
	}

}
