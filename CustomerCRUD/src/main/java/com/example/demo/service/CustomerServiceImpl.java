package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.InvalidId;
import com.example.demo.exception.InvalidMobNumber;
import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository cr;

	@Override
	public void add(Customer customer) {
		List<Customer> list = cr.findAll();
		String mob = customer.getMob();
		if (mob != null) {
			mob = mob.trim();
			if (mob.startsWith("+91")) {
				mob = mob.substring(3);
			} else if (mob.startsWith("91")) {
				mob = mob.substring(2);
			}
		}
		if (mob.length() == 10) {
			if (mob.charAt(0) == '0' || mob.charAt(0) == '1' || mob.charAt(0) == '2' || mob.charAt(0) == '3'
					|| mob.charAt(0) == '4' || mob.charAt(0) == '5')
				throw new InvalidMobNumber("Invalid mobile number");
			for (int i = 0; i < mob.length(); i++) {
				if (!Character.isDigit(mob.charAt(i)))
					throw new InvalidMobNumber("Invalid Mobile Number");
			}
			
			for (Customer c : list) {
				if (c.getMob().equals(mob))
					throw new InvalidMobNumber("Mobile Number is already exists");
			}
			
		} else
			throw new InvalidMobNumber("Invalid Mobile Number");

		customer.setMob(mob);
		
		Integer id = customer.getId();
		
		if(id<=0 || id == null) {
			throw new InvalidId("Invalid Id");
		}
		

		for(Customer cust :list) {
			if(cust.getId().equals(id))
				throw new InvalidId("Id is already exists");
		}
		
		cr.save(customer); // Insert
	}
	
	@Override
	public List<Customer> display() {
		// TODO Auto-generated method stub
		return cr.findAll(); // Select * from Customer
	}

	@Override
	public Customer delete(Integer id) {
		// TODO Auto-generated method stub

		// Search
		if (cr.findById(id).isPresent()) {
			Customer temp = cr.findById(id).get();
			cr.deleteById(id);
			return temp;
		}
		return null;
	}

	@Override
	public void update(Customer customer, Integer id) {
		// TODO Auto-generated method stub
		customer.setId(id);
		cr.save(customer);
	}

	@Override
	public Customer search(Integer id) {
		// TODO Auto-generated method stub
		// Search
		if (cr.findById(id).isPresent()) {
			Customer temp = cr.findById(id).get();
			return temp;
		}
		return null;
	}

	@Override
	public void addAll(List<Customer> list) {
		// TODO Auto-generated method stub
		cr.saveAll(list);
	}

	@Override
	public Customer findByMob(String mob) {
		return cr.findByMob(mob);
	}

	@Override
	public List<Customer> findByName(String name) {
		// TODO Auto-generated method stub
		return cr.findByName(name);
	}

	@Override
	public List findByAddress(String address) {
		// TODO Auto-generated method stub
		return cr.findByAddress(address);
	}

}
