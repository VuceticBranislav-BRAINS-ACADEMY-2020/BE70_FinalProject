package com.iktakademija.FinalProject.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktakademija.FinalProject.dtos.NewPersonDTO;
import com.iktakademija.FinalProject.dtos.PersonDTO;
import com.iktakademija.FinalProject.entities.AddressEntity;
import com.iktakademija.FinalProject.entities.PersonEntity;
import com.iktakademija.FinalProject.entities.enums.EStatus;
import com.iktakademija.FinalProject.repositories.AddressRepository;
import com.iktakademija.FinalProject.repositories.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private AddressService addressService;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private PersonService personService;

	@Override
	public PersonEntity createPerson(NewPersonDTO source) {
		Optional<AddressEntity> op = addressRepository.findById(source.getAddress());
		if (op.isPresent() == false)
			return null;
		AddressEntity address = op.get();

		PersonEntity person = new PersonEntity();
		person.setAddress(address);
		person.setBirthdate(source.getBirthdate());
		person.setFirstname(source.getFirstname());
		person.setJmbg(source.getJmbg());
		person.setLastname(source.getLastname());
		person.setMphone(source.getMphone());
		person.setStatus(EStatus.ACTIVE);

		person = personRepository.save(person);
		return person;
	}

	@Override
	public PersonDTO createDTO(PersonEntity source) {
		PersonDTO retVal = new PersonDTO();
		if (source == null)
			return retVal;
		retVal.setId(source.getId());
		retVal.setFirstname(source.getFirstname());
		retVal.setLastname(source.getLastname());
		retVal.setJmbg(source.getJmbg());
		retVal.setMphone(source.getMphone());
		retVal.setBirthdate(source.getBirthdate());
		retVal.setAddress(addressService.createDTO(source.getAddress()));
		retVal.setVersion(source.getVersion());
		retVal.setStatus(source.getStatus());
		return retVal;
	}

	@Override
	public List<PersonDTO> getDTOList() {
		List<PersonDTO> list = new ArrayList<>();
		for (PersonEntity person : personRepository.findAllUndeleted())
			list.add(personService.createDTO(person));
		return list;
	}

	@Override
	public PersonDTO getPersonDTO(Integer personId) {
		Optional<PersonEntity> op = personRepository.findById(personId);
		if (op.isPresent() == false)
			return null;
		return this.createDTO(op.get());
	}

	@Override
	public PersonDTO removePerson(Integer personId) {
		Optional<PersonEntity> op = personRepository.findById(personId);
		if (op.isPresent() == false)
			return null;
		PersonEntity address = op.get();
		address.setStatus(EStatus.DELETED);
		address = personRepository.save(address);
		return this.createDTO(address);
	}

	@Override
	public PersonDTO setPerson(Integer personId, NewPersonDTO newPerson) {
		Optional<PersonEntity> opp = personRepository.findById(personId);
		if (opp.isPresent() == false)
			return null;
		PersonEntity person = opp.get();

		Optional<AddressEntity> opa = addressRepository.findById(newPerson.getAddress());
		if (opa.isPresent() == false)
			return null;
		AddressEntity address = opa.get();

		if (newPerson.getAddress() != null)
			person.setAddress(address); // TODO FIx this
		if (newPerson.getBirthdate() != null)
			person.setBirthdate(newPerson.getBirthdate());
		if (newPerson.getFirstname() != null)
			person.setFirstname(newPerson.getFirstname());
		if (newPerson.getJmbg() != null)
			person.setJmbg(newPerson.getJmbg());
		if (newPerson.getLastname() != null)
			person.setLastname(newPerson.getLastname());
		if (newPerson.getMphone() != null)
			person.setMphone(newPerson.getMphone());

		person = personRepository.save(person);
		return this.createDTO(person);
	}
}
