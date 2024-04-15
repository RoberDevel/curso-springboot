package com.example.springbootjpa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.example.springbootjpa.dto.PersonDto;
import com.example.springbootjpa.entity.Person;
import com.example.springbootjpa.repository.PersonRepository;

@SpringBootApplication
public class SpringbootJpaApplication implements CommandLineRunner {

	@Autowired
	private PersonRepository personRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		/*
		 * List<Person> personsCreate = new ArrayList<>();
		 * personsCreate.add(new Person(null, "John", "Doe", "Java"));
		 * personsCreate.add(new Person(null, "Jane", "Doe", "Python"));
		 * personsCreate.add(new Person(null, "Alice", "Doe", "C#"));
		 * personsCreate.add(new Person(null, "Bob", "Doe", "JavaScript"));
		 * 
		 * personRepository.saveAll(personsCreate);
		 */

		// list();
		// findOne();
		// create();
		// update();
		// delete();
		// personalizedQueries();
		// personalizedQueries2();
		// personalizedQueriesDistinct();
		// personalizedQueriesConcatUpperLowerCase();
		// queriesFunctionAggregation();
		// subQueries();
		whereIn();
	}

	// anotacion para cuando solo se va a leer la tabla
	@Transactional(readOnly = true)
	public void findOne() {
		// Person person = null;
		// Optional<Person> optionalPerson = personRepository.findById(1L);
		// if (optionalPerson.isPresent()) {
		// person = optionalPerson.get();
		// }
		// System.out.println(person);

		// personRepository.findById(1L).ifPresent(System.out::println);
		// personRepository.encontrarUno(1L).ifPresent(System.out::println);
		// personRepository.encontrarPorNombre("Maria").stream().findFirst().ifPresent(System.out::println);
		// personRepository.encontrarPorNombreLike("ri").stream().forEach(System.out::println);
		personRepository.findByNameContaining("ri").stream().forEach(System.out::println);

	}

	// anotacion para cuando tenemos algo que modifique la tabla
	@Transactional
	public void create() {

		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el nombre: ");
		String name = scanner.nextLine();
		System.out.println("Ingrese el apellido: ");
		String lastname = scanner.nextLine();
		System.out.println("Ingrese el lenguaje de programación: ");
		String programmingLanguage = scanner.nextLine();

		Person person = new Person(name, lastname, programmingLanguage);

		personRepository.save(person);
		System.out.println(person);
		scanner.close();

	}

	@Transactional
	public void update() {

		Scanner scanner = new Scanner(System.in);

		System.out.println("Ingrese el id de la persona a modificar: ");
		Long id = scanner.nextLong();
		scanner.nextLine();
		Optional<Person> optionalPerson = personRepository.findById(id);
		Person person = personRepository.encontrarUno(id).get();

		while (!optionalPerson.isPresent()) {
			System.out.println("Ingrese un id existente de la persona a modificar: ");
			id = scanner.nextLong();
			scanner.nextLine();
			System.out.println("obteniendo persona con id: " + id);
			optionalPerson = personRepository.findById(id);
		}
		person = optionalPerson.get();
		System.out.println(person);
		System.out.println("Ingrese el nombre: ");
		String name = scanner.nextLine();
		if (name.isBlank()) {
			scanner.close();
			return;
		}
		person.setName(name);
		personRepository.save(person);
		System.out.println("aqui: " + person);
		scanner.close();
	}

	// anotacion para cuando solo se va a leer la tabla
	@Transactional(readOnly = true)
	public void list() {
		List<Person> persons = personRepository.findByProgrammingLanguage("java");
		List<Person> persons2 = personRepository.buscarPorNombreYLenguaje("Maria",
				"Java");
		List<Person> persons3 = personRepository.findByNameAndProgrammingLanguage("Maria", "Go");
		List<Object[]> persons4 = personRepository.obtenerNombresYLenguaje();
		List<Object[]> persons5 = personRepository.obtenerNombresYLenguajeEspec("Maria", "Java");

		// persons.stream().forEach(System.out::println);
		// persons2.stream().forEach(System.out::println);
		// persons3.stream().forEach(System.out::println);
		// persons4.stream().forEach(p -> System.out.println(p[0] + " - " + p[1]));
		persons5.stream().forEach(p -> System.out.println(p[0] + " - " + p[1]));

	}

	@Transactional
	public void delete() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Ingrese el id de la persona a eliminar: ");
		Long id = scanner.nextLong();
		scanner.nextLine();
		Optional<Person> optionalPerson = personRepository.findById(id);

		/*
		 * optionalPerson.ifPresentOrElse(p -> {
		 * Person person = optionalPerson.get();
		 * System.out.println(person);
		 * personRepository.delete(person);
		 * scanner.close();
		 * }, () -> {
		 * System.out.println("No se encontro la persona con el id: " + id);
		 * });
		 */

		while (!optionalPerson.isPresent()) {
			System.out.println("Ingrese un id existente de la persona a eliminar: ");
			id = scanner.nextLong();
			scanner.nextLine();
			System.out.println("obteniendo persona con id: " + id);
			optionalPerson = personRepository.findById(id);
		}

		Person person = optionalPerson.get();
		System.out.println(person);
		personRepository.delete(person);
		scanner.close();

	}

	@Transactional(readOnly = true)
	public void personalizedQueries() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el id de la persona: ");
		Long id = scanner.nextLong();
		scanner.nextLine();

		// System.out.println(personRepository.getNameById(id));
		System.out.println(personRepository.getFullNameById(id));
		Object[] personReg = (Object[]) personRepository.obtenerPersonDataFullById(id);
		System.out.println(personReg.length);
		System.out.println("id: " + personReg[0] + " - name: " + personReg[1] + " - lastname: " + personReg[2]
				+ " - programmingLanguage: " + personReg[3]);

		scanner.close();

	}

	@Transactional(readOnly = true)
	public void personalizedQueries2() {
		List<Object[]> persons = personRepository.findAllMixPerson();
		// persons.stream().forEach(
		// p -> System.out.println("programming language: " + p[1] + "\n " + "person: "
		// + p[0]));

		List<Person> persons2 = personRepository.findAllPersonalizedObjectPerson();
		// persons2.stream().forEach(System.out::println);

		List<PersonDto> personsDto = personRepository.findAllObjectPersonDto();
		personsDto.stream().forEach(System.out::println);

	}

	@Transactional(readOnly = true)
	public void personalizedQueriesDistinct() {
		List<String> names = personRepository.findAllNames();
		names.stream().forEach(System.out::println);

		List<String> namesDistinct = personRepository.findAllNamesDistinct();
		namesDistinct.stream().forEach(System.out::println);

		Long totalLanguage = personRepository.findAllProgrammingLanguageDistinctCount();
		System.out.println("total Language: " + totalLanguage);
	}

	@Transactional(readOnly = true)
	public void personalizedQueriesConcatUpperLowerCase() {
		List<String> names = personRepository.findAllFullNameConcat();
		names.stream().forEach(System.out::println);

		names = personRepository.findAllFullNameConcatLower();
		names.stream().forEach(System.out::println);

		names = personRepository.findAllFullNameConcatUpper();
		names.stream().forEach(System.out::println);

		List<Object[]> persons = personRepository.findAllPersonDataListCase();
		persons.stream().forEach(p -> System.out.println("id: " + p[0] + " - name: " + p[1] + " - lastname: " + p[2]
				+ " - programmingLanguage: " + p[3]));

		List<Person> persons2 = personRepository.findAllBetweenId(2L, 4L);
		persons2.stream().forEach(System.out::println);

		persons2 = personRepository.findByNameBetween("J", "N");
		persons2.stream().forEach(System.out::println);

		persons2 = personRepository.findAllBetweenNameOrder("J", "N");
		persons2.stream().forEach(System.out::println);

		persons2 = personRepository.findAllBetweenIdOrder(2L, 5L);
		persons2.stream().forEach(System.out::println);

		persons2 = personRepository.findByNameBetweenOrderByNameDescLastnameAsc("J", "N");
		persons2.stream().forEach(System.out::println);

		persons2 = personRepository.findAllByOrderByNameDescLastnameDesc();
		persons2.stream().forEach(System.out::println);

	}

	@Transactional(readOnly = true)
	public void queriesFunctionAggregation() {
		Long cant = 0L;

		cant = personRepository.getTotalPerson();
		System.out.println("total de personas: " + cant);

		cant = personRepository.getMinId();
		System.out.println("minimo id: " + cant);

		cant = personRepository.getMaxId();
		System.out.println("maximo id: " + cant);

		List<Object[]> regs = personRepository.getPersonNameLength();
		regs.forEach(p -> System.out.println("name: " + p[0] + " - length: " + p[1]));

		cant = personRepository.getMinLengthName();
		System.out.println("minimo length name: " + cant);

		cant = personRepository.getMaxLengthName();
		System.out.println("maximo length name: " + cant);

		Object[] resumeReg = (Object[]) personRepository.getResumeAggregationFunction();
		System.out.println("min id: " + resumeReg[0] + " - max id: " + resumeReg[1] + " - suma ids: " + resumeReg[2]
				+ " - avg length names: " + resumeReg[3] + " - count id: " + resumeReg[4]);
	}

	@Transactional(readOnly = true)
	public void subQueries() {

		List<Object[]> registers = personRepository.getShorterName();
		registers.stream().forEach(p -> System.out.println("name: " + p[0] + " - length: " + p[1]));

		Optional<Person> person = personRepository.getLastPersonRegistration();
		person.ifPresent(System.out::println);

	}

	public void whereIn() {

		List<Person> persons = personRepository.getPersonByIds(Arrays.asList(1L, 2L, 6L));
		persons.stream().forEach(System.out::println);

		persons = personRepository.getPersonByIdsNot(Arrays.asList(1L, 2L, 6L));
		persons.stream().forEach(System.out::println);

	}

}