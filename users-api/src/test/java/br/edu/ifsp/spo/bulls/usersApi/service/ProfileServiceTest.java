package br.edu.ifsp.spo.bulls.usersApi.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import br.edu.ifsp.spo.bulls.usersApi.bean.UserBeanUtil;
import br.edu.ifsp.spo.bulls.usersApi.domain.Profile;
import br.edu.ifsp.spo.bulls.usersApi.domain.User;
import br.edu.ifsp.spo.bulls.usersApi.dto.UserTO;
import br.edu.ifsp.spo.bulls.usersApi.exception.ResourceBadRequestException;
import br.edu.ifsp.spo.bulls.usersApi.exception.ResourceNotFoundException;
import br.edu.ifsp.spo.bulls.usersApi.repository.UserRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ProfileServiceTest {

	@Autowired
	ProfileService service;
	
	@Autowired
	UserService userService;
	
	@Autowired
	private UserBeanUtil beanUtil;
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	void testSave() throws ResourceBadRequestException, Exception {
		
		User user = userRepository.save(new User("testeProfile3", "testeS@teste13", "senhateste"));
		
		Profile profile = new Profile("nome", "sobrenome", "pais", "sao paulo", "SP", "10/10/1998",user );
		
		Profile profile2 = service.save(profile);
		
		assertEquals(profile, profile2);
		
	}
	
	@Test
	void testGetById() throws Exception {
		
		User user = userRepository.save(new User("testeProfileGet", "testeS@gert", "senhateste"));
		
		Profile profileSet = service.save(new Profile("nome", "sobrenome", "pais", "sao paulo", "SP", "10/10/1998",user));
		
		Profile profileGet = service.getById(profileSet.getId());
		
		assertEquals(profileSet, profileGet);
	}
	
	@Test
	void testGetByIdProfileNotFound() throws Exception {
		
		Throwable e = assertThrows(ResourceNotFoundException.class, ()-> service.getById(123456789));
		assertEquals("Profile not found", e.getMessage());
	}
	
	@Test
	void testDelete() throws ResourceBadRequestException, Exception {
		
		User user = userRepository.save(new User("testeProfileGet", "testeS@gert", "senhateste"));
		
		Profile profile = service.save(new Profile("nome", "sobrenome", "pais", "sao paulo", "SP", "10/10/1998", user));
		
		service.delete(profile.getId());
		
		Throwable e = assertThrows(ResourceNotFoundException.class, ()-> {service.getById(profile.getId());});
		assertEquals("Profile not found", e.getMessage());
		
	}
	
	@Test
	void testDeleteProfileNotFound() throws ResourceBadRequestException, Exception {
			
		Throwable e = assertThrows(ResourceNotFoundException.class, ()-> service.delete(12345678));
		assertEquals("Profile not found", e.getMessage());
		
	}

	@Test
	void testUpdate() throws ResourceBadRequestException, Exception {
		
		UserTO user = userService.save(new UserTO("testeUpdateOk", "testeS@updateOk", "senhateste", "nome", "sobrenome"));
		
		Profile profile = service.getByUser(beanUtil.toUser(user));
		
		profile.setName("Mudando o nome");
		
		Profile profileUpdate = service.update(profile);
		
		assertEquals("Mudando o nome", profileUpdate.getName()); 
	}
	
	@Test
	void testUpdateProfileNotFound() throws ResourceBadRequestException, Exception {
		
		User user = new User("testeProfile3", "testeS@teste13", "senhateste");
		
		Profile profile = new Profile("nome", "sobrenome", "pais", "sao paulo", "SP", "10/10/1998", user);
		
		Throwable e = assertThrows(ResourceNotFoundException.class, ()-> service.update(profile));
		assertEquals("Profile not found", e.getMessage());
		
	}

	@Test
	void testGetAllProfiles() throws ResourceBadRequestException, Exception {
		
		userService.save(new UserTO("testeProfileGetAll", "testeS@getAll", "senhateste", "nome", "sobrenome"));
		HashSet<Profile> allProfiles = service.getAll();
		
		assertFalse(allProfiles.isEmpty());
	}
}
