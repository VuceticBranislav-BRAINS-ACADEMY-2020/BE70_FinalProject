package com.iktakademija.FinalProject.services;

import java.util.List;

import com.iktakademija.FinalProject.dtos.NewPersonDTO;
import com.iktakademija.FinalProject.dtos.PersonDTO;
import com.iktakademija.FinalProject.entities.PersonEntity;

public interface PersonService {

	List<PersonDTO> getDTOList();

	PersonDTO createDTO(PersonEntity source);

	PersonDTO getPersonDTO(Integer personId);

	PersonDTO removePerson(Integer personId);

	PersonEntity createPerson(NewPersonDTO source);

	PersonDTO setPerson(Integer personId, NewPersonDTO newPerson);

}
