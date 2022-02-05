package com.kafka.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kafka.entity.AddressBookEntity;

public interface AddressBookRepo extends JpaRepository<AddressBookEntity, Long> {
	
	public AddressBookEntity findByFname(String fname);
	
	public List<AddressBookEntity> findById(long id);

	public void deleteByEmail(String email);
}
