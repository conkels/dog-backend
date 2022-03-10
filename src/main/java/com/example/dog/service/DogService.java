package com.example.dog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.dog.domain.Dog;
import com.example.dog.repo.DogRepo;

@Service
public class DogService {

	private DogRepo repo;
	
	@Autowired
	public DogService(DogRepo repo) {
		this.repo=repo;
	}
	
	public Dog create(Dog d) {
		Dog created = this.repo.save(d);
		return created;
	}
	
	public List<Dog> getAll() {
		return this.repo.findAll();
	}
	
	public Dog getOne(Integer id) {
		Optional<Dog> found = this.repo.findById(id);
		return found.get();
	}
	
	public Dog replace(Integer id, Dog newDog) {
		Dog existing = this.repo.findById(id).get();
		existing.setBreed(newDog.getBreed());
		existing.setName(newDog.getName());
		existing.setAge(newDog.getAge());
		existing.setIsMale(newDog.getIsMale());
		Dog updated = this.repo.save(existing);
		return updated;
	}
	
	public void remove(@PathVariable Integer id) {
		this.repo.deleteById(id);
	}
	
}
