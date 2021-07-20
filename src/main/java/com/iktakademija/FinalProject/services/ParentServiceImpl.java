package com.iktakademija.FinalProject.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktakademija.FinalProject.entities.ParentEntity;
import com.iktakademija.FinalProject.entities.PersonEntity;
import com.iktakademija.FinalProject.entities.RoleEntity;
import com.iktakademija.FinalProject.entities.dtos.NewParentDTO;
import com.iktakademija.FinalProject.entities.dtos.ParentDTO;
import com.iktakademija.FinalProject.entities.dtos.StudentDTO;
import com.iktakademija.FinalProject.entities.enums.ERole;
import com.iktakademija.FinalProject.entities.enums.EStatus;
import com.iktakademija.FinalProject.repositories.JoinTableStudentParentRepository;
import com.iktakademija.FinalProject.repositories.ParentRepository;
import com.iktakademija.FinalProject.repositories.PersonRepository;
import com.iktakademija.FinalProject.repositories.RoleRepository;
import com.iktakademija.FinalProject.repositories.StudentRepository;
import com.iktakademija.FinalProject.utils.Encryption;

@Service
public class ParentServiceImpl implements ParentService {
	
	@Autowired
	private JoinTableStudentParentRepository joinTableStudentParentRepository;
	
	@Autowired
	private ParentRepository parentRepository;
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private StudentService studentService;
	
	@Override
	public ParentEntity createParent(NewParentDTO source) {
		
		Optional<RoleEntity> opr = roleRepository.findByRole(ERole.ROLE_PARENT);
		if (opr.isPresent() == false) return null;
		RoleEntity role = opr.get();
		
		Optional<PersonEntity> opp = personRepository.findById(source.getPersonId());
		if (opp.isPresent() == false) return null;
		PersonEntity person = opp.get();
		
		ParentEntity parent = new ParentEntity(source.getUsername(), source.getPassword(), person, role);	
		parent.setEmail(source.getEmail());
		return parent;
	}
	
	@Override
	public ParentDTO createDTO(ParentEntity source) {	
		ParentDTO retVal = new ParentDTO();
		if (source == null) return retVal;
		retVal.setId(source.getId());
		retVal.setUsername(source.getUsername());
		retVal.setPerson(personService.createDTO(source.getPerson()));
		retVal.setRole(roleService.createDTO(source.getRole()));
		retVal.setVersion(source.getVersion());
		retVal.setStatus(source.getStatus());
		retVal.setEmail(source.getEmail());
		retVal.setChilds(joinTableStudentParentRepository.findChildOfParent(source));
		return retVal;			
	}
	
	@Override
	public List<ParentDTO> createDTOList(List<ParentEntity> source) {		
		List<ParentDTO> list = new ArrayList<>();
		for (ParentEntity parent : source) 
			list.add(this.createDTO(parent));		
		return list;
	}
	
	@Override
	public List<ParentDTO> getDTOList() {	
		return this.createDTOList(parentRepository.findAllUndeleted());
	}
	
	public ParentDTO getParentDTO(Integer parentId){		
		
		Optional<ParentEntity> op = parentRepository.findById(parentId);
		if (op.isPresent() == false) return null;		
		
		return getDTOfromEntity(op.get());
	};	
	
	private ParentDTO getDTOfromEntity(ParentEntity source) {
		
		ParentDTO dto = new ParentDTO();
		
		if (source.getId() != null) {
			dto.setId(source.getId());	
		}
		
		if (source.getUsername() != null) {
			dto.setUsername(source.getUsername());	
		}
		
		if (source.getPerson() != null) {
			dto.setPerson(personService.createDTO(source.getPerson()));
		}

		if (source.getRole() != null) {
			dto.setRole(roleService.createDTO(source.getRole()));
		}
		
		if (source.getStatus() != null) {
			dto.setStatus(source.getStatus());	
		}
		
		if (source.getEmail() != null) {
			dto.setEmail(source.getEmail());	
		}
		
		if (source.getId() != null) {
			List<Integer> asd = joinTableStudentParentRepository.findChildOfParent(source);
			dto.setChilds(asd);	
		}	
		
		return dto;
	}	
	
	@Override
	public ParentDTO setParent(Integer parentId, NewParentDTO newParent) {
		Optional<ParentEntity> op1 = parentRepository.findById(parentId);
		if (op1.isPresent() == false) return null;
		ParentEntity parent = op1.get();		
		if (newParent.getPassword() != null) parent.setPassword(Encryption.passwordEncode(newParent.getPassword()));
		
		Optional<PersonEntity> opp = personRepository.findById(newParent.getPersonId());
		if (opp.isPresent() == false) return null;
		PersonEntity person = opp.get();	
		
		if (newParent.getPersonId() != null) parent.setPerson(person); // TODO Fix this
		if (newParent.getUsername() != null) parent.setUsername(newParent.getUsername());
		if (newParent.getEmail() != null) parent.setEmail(newParent.getEmail());
		
		parent = parentRepository.save(parent);
		return this.createDTO(parent);	
	}	
	
	@Override
	public ParentDTO removeParent(Integer parentId) {			
		Optional<ParentEntity> op = parentRepository.findById(parentId);
		if (op.isPresent() == false) return null;
		ParentEntity parent = op.get();		
		parent.setStatus(EStatus.DELETED);			
		parent = parentRepository.save(parent);
		return this.createDTO(parent);		
	}
	
	@Override
	public List<StudentDTO> getAllChildrens(ParentEntity childId) {	
		return studentService.createDTOList(studentRepository.findAllChildrens(childId));
	}
	
//	@PersistenceContext()
//	private EntityManager em;
//	
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<AddressEntity> findAddressesByUserName(String name) {
//		
//		// create SQL to invoke
//		// radi se sa objektima i pisu se nazivi polja iz klasa		
//		String sql = "SELECT a FROM AddressEntity AS a LEFT JOIN FETCH a.users AS u WHERE u.name = :name";
//		// mesto ON postoji FETCH koji je lista veza
//		// parametrizovani se koriste proti injectiona i 
//		
//		// SELECT * 
//		// FROM AddressEntity AS a INNER JOIN UserEntyty AS u ON a.id=u.addressID 
//		// WHERE u.name = :name
//		
//		// invoke SQL
//		Query query = em.createQuery(sql);		
//		query.setParameter("name", name);
//		
//		List<AddressEntity> retVal = new ArrayList<AddressEntity>();
//		retVal = query.getResultList();
//		
//		// handle the return value of the SQL statement
//		
//		
//		return retVal;
//	}
	
//	@Test
//	public void whenUsingMultipleQueries_thenRetrieveSuccess() {
//	    String jpql = "SELECT DISTINCT artist FROM Artist artist "
//	      + "LEFT JOIN FETCH artist.songs ";
//
//	    List<Artist> artists = entityManager.createQuery(jpql, Artist.class)
//	      .setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false)
//	      .getResultList();
//
//	    jpql = "SELECT DISTINCT artist FROM Artist artist "
//	      + "LEFT JOIN FETCH artist.offers "
//	      + "WHERE artist IN :artists ";
//
//	    artists = entityManager.createQuery(jpql, Artist.class)
//	      .setParameter("artists", artists)
//	      .setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false)
//	      .getResultList();
//
//	    assertEquals(2, artists.size());
//	}
	
}
