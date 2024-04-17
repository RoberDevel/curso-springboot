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
import com.example.springbootjparelationships.entity.Course;
import com.example.springbootjparelationships.entity.DetailsClient;
import com.example.springbootjparelationships.entity.Invoice;
import com.example.springbootjparelationships.entity.Student;
import com.example.springbootjparelationships.repository.ClientRepository;
import com.example.springbootjparelationships.repository.CourseRepository;
import com.example.springbootjparelationships.repository.DetailsClientRepository;
import com.example.springbootjparelationships.repository.InvoiceRepository;
import com.example.springbootjparelationships.repository.StudentRepository;

@SpringBootApplication
public class SpringbootJpaRelationshipsApplication implements CommandLineRunner {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Autowired
	private DetailsClientRepository detailsClientRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private CourseRepository courseRepository;

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
		// removeInvoiceBidireccional();
		// oneToOne();
		// oneToOneFindById();
		// oneToOneBidireccional();
		// oneToOneBidireccionalFindById();
		// manyToMany();
		// manyToManyFind();
		// manyToManyRemoveFind();
		// manyToManyRemove();
		// manyToManyBidireccional();
		// manyToManyBidireccionalRemove();
		// manyToManyBidireccionalFind();
		manyToManyBidireccionalRemoveFind();
	}

	@Transactional
	public void manyToManyBidireccionalRemoveFind() {

		Optional<Student> optionalStudent1 = studentRepository.findOneWithCourses(1L);
		Optional<Student> optionalStudent2 = studentRepository.findOneWithCourses(2L);
		Course course1 = courseRepository.findOneWithStudents(1L).get();
		Course course2 = courseRepository.findOneWithStudents(2L).get();

		Student student1 = optionalStudent1.get();
		Student student2 = optionalStudent2.get();

		student1.addCourse(course1).addCourse(course2);
		student2.addCourse(course2);
		studentRepository.saveAll(Set.of(student1, student2));
		System.out.println(student1);
		System.out.println(student2);

		Optional<Student> optionalStudent = studentRepository.findOneWithCourses(1L);
		optionalStudent.ifPresent(student -> {

			Optional<Course> optionalCourse = courseRepository.findOneWithStudents(1L);

			optionalCourse.ifPresent(course -> {
				student.removeCourse(course);
				studentRepository.save(student);
				System.out.println(student);

			});
		});

	}

	@Transactional
	public void manyToManyBidireccionalFind() {

		Optional<Student> optionalStudent1 = studentRepository.findOneWithCourses(1L);
		Optional<Student> optionalStudent2 = studentRepository.findOneWithCourses(2L);
		Course course1 = courseRepository.findOneWithStudents(1L).get();
		Course course2 = courseRepository.findOneWithStudents(2L).get();

		Student student1 = optionalStudent1.get();
		Student student2 = optionalStudent2.get();

		student1.addCourse(course1).addCourse(course2);
		student2.addCourse(course2);
		studentRepository.saveAll(Set.of(student1, student2));
		System.out.println(student1);
		System.out.println(student2);

	}

	@Transactional
	public void manyToManyBidireccionalRemove() {

		Student student1 = new Student("Lucas", "Alvarez");
		Student student2 = new Student("Jhon", "Doe");
		Course course1 = new Course("curso de Java", "Andres Guzman");
		Course course2 = new Course("curso de Spring", "Sara Marin");

		// student1.setCourses(Set.of(course1, course2));
		// student2.setCourses(Set.of(course2));

		student1.addCourse(course1).addCourse(course2);
		student2.addCourse(course2);
		studentRepository.saveAll(Set.of(student1, student2));
		System.out.println(student1);
		System.out.println(student2);

		Optional<Student> optionalStudent = studentRepository.findOneWithCourses(4L);
		optionalStudent.ifPresent(student -> {

			Optional<Course> optionalCourse = courseRepository.findOneWithStudents(3L);

			optionalCourse.ifPresent(course -> {
				student.removeCourse(course);
				studentRepository.save(student);
				System.out.println(student);

			});
		});

	}

	@Transactional
	public void manyToManyBidireccional() {

		Student student1 = new Student("Lucas", "Alvarez");
		Student student2 = new Student("Jhon", "Doe");
		Course course1 = new Course("curso de Java", "Andres Guzman");
		Course course2 = new Course("curso de Spring", "Sara Marin");

		// student1.setCourses(Set.of(course1, course2));
		// student2.setCourses(Set.of(course2));

		student1.addCourse(course1).addCourse(course2);
		student2.addCourse(course2);
		studentRepository.saveAll(Set.of(student1, student2));
		System.out.println(student1);
		System.out.println(student2);

	}

	@Transactional
	public void manyToManyRemove() {

		Student student1 = new Student("Lucas", "Alvarez");
		Student student2 = new Student("Jhon", "Doe");
		Course course1 = new Course("curso de React", "Ismael Sanchez");
		Course course2 = new Course("curso de Spring", "Marcos Lopez");

		student1.setCourses(Set.of(course1, course2));
		student2.setCourses(Set.of(course2));
		studentRepository.saveAll(List.of(student1, student2));
		System.out.println(student1);
		System.out.println(student2);

		Optional<Student> optionalStudent = studentRepository.findOneWithCourses(4L);
		optionalStudent.ifPresent(student -> {

			Optional<Course> optionalCourse = courseRepository.findById(3L);

			optionalCourse.ifPresent(course -> {

				student.getCourses().remove(course);
				studentRepository.save(student);
				System.out.println(student);

			});
		});

	}

	@Transactional
	public void manyToManyRemoveFind() {

		Optional<Student> optionalStudent1 = studentRepository.findById(1L);
		Optional<Student> optionalStudent2 = studentRepository.findById(2L);
		Course course1 = courseRepository.findById(1L).get();
		Course course2 = courseRepository.findById(2L).get();

		Student student1 = optionalStudent1.get();
		Student student2 = optionalStudent2.get();

		student1.setCourses(Set.of(course1, course2));
		student2.setCourses(Set.of(course2));
		studentRepository.saveAll(Set.of(student1, student2));
		System.out.println(student1);
		System.out.println(student2);

		Optional<Student> optionalStudent = studentRepository.findOneWithCourses(1L);
		optionalStudent.ifPresent(student -> {

			Optional<Course> optionalCourse = courseRepository.findById(2L);

			optionalCourse.ifPresent(course -> {

				student.getCourses().remove(course);
				studentRepository.save(student);
				System.out.println(student);

			});
		});

	}

	@Transactional
	public void manyToManyFind() {

		Optional<Student> optionalStudent1 = studentRepository.findById(1L);
		Optional<Student> optionalStudent2 = studentRepository.findById(2L);
		Course course1 = courseRepository.findById(1L).get();
		Course course2 = courseRepository.findById(2L).get();

		Student student1 = optionalStudent1.get();
		Student student2 = optionalStudent2.get();

		student1.setCourses(Set.of(course1, course2));
		student2.setCourses(Set.of(course2));
		studentRepository.saveAll(Set.of(student1, student2));
		System.out.println(student1);
		System.out.println(student2);

	}

	@Transactional
	public void manyToMany() {

		Student student1 = new Student("Lucas", "Alvarez");
		Student student2 = new Student("Jhon", "Doe");
		Course course1 = new Course("curso de Java", "Andres Guzman");
		Course course2 = new Course("curso de Spring", "Sara Marin");

		student1.setCourses(Set.of(course1, course2));
		student2.setCourses(Set.of(course2));
		studentRepository.saveAll(Set.of(student1, student2));
		System.out.println(student1);
		System.out.println(student2);

	}

	@Transactional
	public void oneToOneBidireccionalFindById() {

		Optional<Client> optionalClient = clientRepository.findOne(1L);

		optionalClient.ifPresent(client -> {
			DetailsClient detailsClient = new DetailsClient(true, 1000);
			client.addClientDetail(detailsClient);
			clientRepository.save(client);

			System.out.println(client);
		});

	}

	@Transactional
	public void oneToOneBidireccional() {
		DetailsClient detailsClient = new DetailsClient(true, 1000);
		Client client = new Client("Lucas", "Alvarez");

		client.addClientDetail(detailsClient);
		clientRepository.save(client);

		System.out.println(client);
	}

	@Transactional
	public void oneToOneFindById() {

		DetailsClient detailsClient = new DetailsClient(true, 1000);
		detailsClientRepository.save(detailsClient);

		Optional<Client> client = clientRepository.findOne(1L);
		client.ifPresent(c -> {
			c.setDetailsClient(detailsClient);
			clientRepository.save(c);
			System.out.println(c);

		});

	}

	@Transactional
	public void oneToOne() {

		DetailsClient detailsClient = new DetailsClient(true, 1000);
		detailsClientRepository.save(detailsClient);

		Client client = new Client("Lucas", "Alvarez");
		client.setDetailsClient(detailsClient);
		clientRepository.save(client);

		System.out.println(client);
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
