package com.kafka.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.entity.AddressBookEntity;
import com.kafka.repository.AddressBookRepo;

@RestController
@Transactional
@RequestMapping("/user")
public class AddressBookController {
	
	private static final List<AddressBookEntity> Op = null;
	@Autowired
	AddressBookRepo addressBookRepo;
	
	@Autowired
	private KafkaTemplate<String, List<AddressBookEntity>> kafkaTemplate;
	
	private static final String TOPIC="addressbook";
	
	@PostMapping("/add")
	public AddressBookEntity addEntity(@RequestBody AddressBookEntity addressBookEntity)
	{
		kafkaTemplate.send(TOPIC,(List<AddressBookEntity>) addressBookRepo.save(addressBookEntity));
		return addressBookRepo.save(addressBookEntity);
	}
	
	@GetMapping("/list")
	public List<AddressBookEntity> list()
	{
		kafkaTemplate.send(TOPIC,(List<AddressBookEntity>)addressBookRepo.findAll());
		return addressBookRepo.findAll();
	}
	
	@GetMapping("/findById/id")
	public List<AddressBookEntity> findId(long id)
	{
		kafkaTemplate.send(TOPIC,(List<AddressBookEntity>)addressBookRepo.findById(id));
		return addressBookRepo.findById(id);
		
	}
	
	@GetMapping("/findByFirstName/fname")
	public List<AddressBookEntity> findfName(String fname)
	{
		kafkaTemplate.send(TOPIC,(List<AddressBookEntity>)(List<AddressBookEntity>) addressBookRepo.findByFname(fname));
		return (List<AddressBookEntity>) addressBookRepo.findByFname(fname);
	
		
	}
	
	@DeleteMapping("/deleteById/id")
	public void deleteById(long id)
	{
		addressBookRepo.deleteById(id);
	}
	
	@DeleteMapping("/deleteByEmail/email")
	public void deleteByEmail(String email)
	{
		addressBookRepo.deleteByEmail(email);
	}

}
