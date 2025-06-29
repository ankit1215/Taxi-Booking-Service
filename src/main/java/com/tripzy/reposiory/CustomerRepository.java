package com.tripzy.reposiory;

import com.tripzy.Enum.Gender;
import com.tripzy.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

   List<Customer> findByGender(Gender gender);

  List<Customer> findByGenderAndAge(Gender gender, int Age);

  /*

  //This is a HQL query==> Hibernate Query Language
  @Query("select c from Customer c where c.gender = :gender and c.age > :age")
  List<Customer> getAllByGenderAndAgeGreaterThan(@Param("gender") Gender gender,
                                                 @Param("age") int age);


   */

    //this is Jpa Native Query Using Sql
    @Query(value = "select * from customer where gender = :gender and age > :age", nativeQuery = true)
    List<Customer> getAllByGenderAndAgeGreaterThan(@Param("gender") String gender,
                                                   @Param("age") int age);
}
