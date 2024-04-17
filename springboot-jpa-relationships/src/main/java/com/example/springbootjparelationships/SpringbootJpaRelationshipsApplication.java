package com.example.springbootjparelationships;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.example.springbootjparelationships.entity.Address;
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
		// manyToOne();
		// manyToOneFindByIdClient();
		// oneToMany();
		// oneToManyFindById();
		// removeAddress();
		// removeAddressFindById();
		// oneToManyBidireccional();
		// oneToManyInvoiceBidireccionalFindById();
		// removeInvoiceBidireccionalFindById();
		removeInvoiceBidireccional();
	}

	@Transactional
	public void removeInvoiceBidireccional() {

		Client cliente = new Client("Frank", "Abagnale");

		Invoice invoice1 = new Invoice("Laptop with intel", 1000L);
		Invoice invoice2 = new Invoice("Laptop with AMD", 800L);
		cliente.addInvoice(invoice1).addInvoice(invoice2);
		clientRepository.save(cliente);
		System.out.println(cliente);

		Optional<Client> optionalClientBd = clientRepository.findOne(5L);
		optionalClientBd.ifPresent(client -> {
			Optional<Invoice> optionalInvoice = invoiceRepository.findById(1L);
			optionalInvoice.ifPresent(invoice -> {
				client.removeInvoice(invoice);
				clientRepository.save(client);
				System.out.println(client);
			});

		});

	}

	@Transactional
	public void removeInvoiceBidireccionalFindById() {

		Optional<Client> optionalClient = clientRepository.findOne(1L);

		optionalClient.ifPresent(client -> {

			Invoice invoice1 = new Invoice("Laptop with intel", 1000L);
			Invoice invoice2 = new Invoice("Laptop with AMD", 800L);
			client.addInvoice(invoice1).addInvoice(invoice2);
			clientRepository.save(client);
			System.out.println(client);
		});
		Optional<Client> optionalClientBd = clientRepository.findOne(1L);
		optionalClientBd.ifPresent(client -> {
			Optional<Invoice> optionalInvoice = invoiceRepository.findById(1L);
			optionalInvoice.ifPresent(invoice -> {
				client.removeInvoice(invoice);
				clientRepository.save(client);
				System.out.println(client);
			});

		});

	}

	@Transactional
	public void oneToManyInvoiceBidireccionalFindById() {

		Optional<Client> optionalClient = clientRepository.findOne(1L);

		optionalClient.ifPresent(client -> {

			Invoice invoice1 = new Invoice("Laptop with intel", 1000L);
			Invoice invoice2 = new Invoice("Laptop with AMD", 800L);
			client.addInvoice(invoice1).addInvoice(invoice2);
			clientRepository.save(client);
			System.out.println(client);
		});

	}

	@Transactional
	public void oneToManyBidireccional() {
		Client client = new Client("Frank", "Abagnale");

		Invoice invoice1 = new Invoice("Laptop with intel", 1000L);
		Invoice invoice2 = new Invoice("Laptop with AMD", 800L);

		/*
		 * client.setInvoices(Arrays.asList(invoice1, invoice2));
		 * invoice1.setClient(client);
		 * invoice2.setClient(client);
		 */

		// Teniendo el método addInvoice en la clase Client, que devuelve la referencia
		// al mismo cliente, puedes encadenar las llamadas a los métodos.
		client.addInvoice(invoice1).addInvoice(invoice2);

		clientRepository.save(client);
		System.out.println(client);

	}

	@Transactional
	public void removeAddressFindById() {

		Optional<Client> optionalClient = clientRepository.findById(2L);

		optionalClient.ifPresent(client -> {
			Address address1 = new Address("Cort Street", 325);
			Address address2 = new Address("Five Street", 147);

			Set<Address> addresses = new HashSet<>();
			addresses.add(address1);
			addresses.add(address2);
			client.setAddresses(addresses);

			clientRepository.save(client);

			System.out.println(client);

			System.out.println(address1);
			System.out.println(address2);

			Optional<Client> optionalClient2 = clientRepository.findById(2L);
			optionalClient2.ifPresent(c -> {
				System.out.println(c.getAddresses());

				/*
				 * for (int i = 0; i < c.getAddresses().size(); i++) {
				 * 
				 * if (c.getAddresses().get(i).equals(address2)) {
				 * System.out.println("Address found: " + c.getAddresses().get(i));
				 * } else {
				 * System.out.println("Address not found: ");
				 * }
				 * 
				 * }
				 */

				c.getAddresses().remove(address1);

				clientRepository.save(c);
				System.out.println(c);
			});
		});

	}

	@Transactional
	public void removeAddress() {

		Client client = new Client();
		client.setName("Frank");
		client.setLastname("Abagnale");
		Address address1 = new Address("Main Street", 123);
		Address address2 = new Address("Second Street", 456);

		client.getAddresses().add(address1);
		client.getAddresses().add(address2);

		clientRepository.save(client);

		System.out.println(client);

		Optional<Client> optionalClient = clientRepository.findById(5L);
		optionalClient.ifPresent(c -> {
			c.getAddresses().remove(address1);
			clientRepository.save(c);
			System.out.println(c);
		});
	}

	@Transactional
	public void manyToOne() {

		Client client = new Client();
		client.setName("Jhon");
		client.setLastname("Alvarez");
		clientRepository.save(client);

		Invoice invoice = new Invoice("Laptop with intel", 1000L);

		invoice.setClient(client);
		Invoice invoiceDB = invoiceRepository.save(invoice);

		System.out.println(invoiceDB);
	}

	@Transactional
	public void manyToOneFindByIdClient() {

		Optional<Client> optionalClient = clientRepository.findById(1L);

		if (optionalClient.isPresent()) {
			Client client = optionalClient.orElseThrow();

			Invoice invoice = new Invoice("Laptop with intel", 1000L);

			invoice.setClient(client);
			Invoice invoiceDB = invoiceRepository.save(invoice);

			System.out.println(invoiceDB);

		}
	}

	@Transactional
	public void oneToMany() {

		Client client = new Client();
		client.setName("Frank");
		client.setLastname("Abagnale");
		Address address1 = new Address("Main Street", 123);
		Address address2 = new Address("Second Street", 456);
		// List<Address> addresses = new ArrayList<>();
		// addresses.add(address1);
		// addresses.add(address2);
		// client.setAddresses(addresses);

		client.getAddresses().add(address1);
		client.getAddresses().add(address2);

		clientRepository.save(client);

		System.out.println(client);

	}

	@Transactional
	public void oneToManyFindById() {

		Optional<Client> optionalClient = clientRepository.findById(2L);

		optionalClient.ifPresent(client -> {
			Address address1 = new Address("Cort Street", 325);
			Address address2 = new Address("Five Street", 147);

			Set<Address> addresses = new HashSet<>();
			addresses.add(address1);
			addresses.add(address2);
			client.setAddresses(addresses);

			clientRepository.save(client);

			System.out.println(client);
		});

	}

}
