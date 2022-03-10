package com.example.dog.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dog.domain.Dog;

@Repository
public interface DogRepo extends JpaRepository<Dog, Integer> {

}
