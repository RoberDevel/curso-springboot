package com.example.springbootjpa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.springbootjpa.dto.PersonDto;
import com.example.springbootjpa.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("SELECT p FROM Person p WHERE p.id NOT IN (?1)")
    List<Person> getPersonByIdsNot(List<Long> ids);

    @Query("SELECT p FROM Person p WHERE p.id IN (?1)")
    List<Person> getPersonByIds(List<Long> ids);

    @Query("SELECT p.name, LENGTH(p.name) FROM Person p WHERE LENGTH(p.name) = (SELECT MIN(LENGTH(p.name)) FROM Person p)")
    List<Object[]> getShorterName();

    @Query("SELECT p FROM Person p WHERE p.id=(SELECT MAX(p.id) FROM Person p)")
    Optional<Person> getLastPersonRegistration();

    @Query("SELECT MIN(p.id), MAX(p.id), SUM(p.id), AVG(LENGTH(p.name)), COUNT(p.id) FROM Person p")
    Object getResumeAggregationFunction();

    @Query("SELECT MIN(LENGTH(p.name)) FROM Person p")
    Long getMinLengthName();

    @Query("SELECT MAX(LENGTH(p.name)) FROM Person p")
    Long getMaxLengthName();

    @Query("SELECT p.name, LENGTH(p.name) FROM Person p ORDER BY LENGTH(p.name) ASC")
    List<Object[]> getPersonNameLength();

    @Query("SELECT MAX(p.id) FROM Person p")
    Long getMaxId();

    @Query("SELECT MIN(p.id) FROM Person p")
    Long getMinId();

    @Query("SELECT COUNT(p) FROM Person p")
    Long getTotalPerson();

    List<Person> findAllByOrderByNameDescLastnameDesc();

    List<Person> findByNameBetweenOrderByNameDescLastnameAsc(String name1, String name2);

    @Query("SELECT p FROM Person p WHERE p.name BETWEEN ?1 AND ?2 ORDER BY p.name ASC")
    List<Person> findAllBetweenNameOrder(String c1, String c2);

    @Query("SELECT p FROM Person p WHERE p.id BETWEEN ?1 AND ?2 ORDER BY p.name DESC, p.lastname ASC")
    List<Person> findAllBetweenIdOrder(Long id1, Long id2);

    List<Person> findByNameBetween(String name1, String name2);

    @Query("SELECT p FROM Person p WHERE p.name BETWEEN ?1 AND ?2")
    List<Person> findAllBetweenName(String c1, String c2);

    @Query("SELECT p FROM Person p WHERE p.id BETWEEN ?1 AND ?2")
    List<Person> findAllBetweenId(Long id1, Long id2);

    @Query("SELECT p.id, LOWER(p.name), UPPER(p.lastname), LOWER(p.programmingLanguage) FROM Person p")
    List<Object[]> findAllPersonDataListCase();

    @Query("SELECT UPPER(p.name|| ' ' || p.lastname) FROM Person p")
    List<String> findAllFullNameConcatUpper();

    @Query("SELECT LOWER(CONCAT(p.name|| ' ' || p.lastname)) FROM Person p")
    List<String> findAllFullNameConcatLower();

    // @Query("SELECT CONCAT ('Nombre completo: ',p.name, ' ', p.lastname) FROM
    // Person p")
    @Query("SELECT CONCAT(p.name|| ' ' || p.lastname) FROM Person p")
    List<String> findAllFullNameConcat();

    @Query("SELECT p.name FROM Person p")
    List<String> findAllNames();

    @Query("SELECT COUNT(DISTINCT(p.programmingLanguage))  FROM Person p")
    Long findAllProgrammingLanguageDistinctCount();

    @Query("SELECT DISTINCT(p.name)  FROM Person p")
    List<String> findAllNamesDistinct();

    @Query("SELECT new com.example.springbootjpa.dto.PersonDto(p.name, p.lastname) FROM Person p")
    List<PersonDto> findAllObjectPersonDto();

    @Query("SELECT new Person(p.name, p.lastname) FROM Person p")
    List<Person> findAllPersonalizedObjectPerson();

    @Query("SELECT p.name FROM Person p WHERE p.id=?1")
    String getNameById(Long id);

    @Query("SELECT concat ('Nombre completo: ',p.name, ' ', p.lastname) as fullname FROM Person p WHERE p.id=?1")
    String getFullNameById(Long id);

    @Query("SELECT p.id FROM Person p WHERE p.id=?1")
    Long getIdById(Long id);

    @Query("SELECT p FROM Person p WHERE p.id=?1")
    Optional<Person> encontrarUno(Long id);

    @Query("SELECT p FROM Person p WHERE p.name=?1")
    List<Optional<Person>> encontrarPorNombre(String name);

    List<Optional<Person>> findByNameContaining(String name);

    @Query("SELECT p FROM Person p WHERE p.name LIKE %?1%")
    List<Optional<Person>> encontrarPorNombreLike(String name);

    List<Person> findByProgrammingLanguage(String programmingLanguage);

    @Query("SELECT p FROM Person p WHERE p.name = ?1 AND p.programmingLanguage = ?2")
    List<Person> buscarPorNombreYLenguaje(String nombre, String lenguaje);

    List<Person> findByNameAndProgrammingLanguage(String name, String programmingLanguage);

    @Query("SELECT p.name, p.programmingLanguage FROM Person p")
    List<Object[]> obtenerNombresYLenguaje();

    @Query("SELECT p.name, p.programmingLanguage FROM Person p WHERE p.name = ?1 AND p.programmingLanguage = ?2")
    List<Object[]> obtenerNombresYLenguajeEspec(String name, String programmingLanguage);

    @Query("SELECT p.id, p.name, p.lastname, p.programmingLanguage FROM Person p where p.id = ?1")
    Object obtenerPersonDataFullById(Long id);

    @Query("SELECT p, p.programmingLanguage from Person p")
    List<Object[]> findAllMixPerson();

}
