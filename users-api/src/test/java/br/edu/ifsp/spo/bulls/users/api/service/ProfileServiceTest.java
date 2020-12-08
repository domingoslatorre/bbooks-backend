package br.edu.ifsp.spo.bulls.users.api.service;

import br.edu.ifsp.spo.bulls.users.api.bean.ProfileBeanUtil;
import br.edu.ifsp.spo.bulls.users.api.repository.UserRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ProfileServiceTest {

	@Autowired
	ProfileService service;
	
	@Autowired
	UserService userService;
	
	@Autowired
	private ProfileBeanUtil profileBeanUtil;
	
	@Autowired
	private UserRepository userRepository;
	
//	@Test
//	void testSave() throws ResourceBadRequestException, Exception {
//
//		User user = userRepository.save(new User("testeProfile3", "testeS@teste13", "senhate"));
//
//		Profile profile = new Profile("nome", "sobrenome", "pais", "sao paulo", "SP", "10/10/1998",user );
//
//		Profile profile2 = service.save(profile);
//
//		assertEquals(profile, profile2);
//
//	}
//
//	@Test
//	void testGetById() throws Exception {
//
//		User user = userRepository.save(new User("testeProfileGet", "testeS@gert", "senhate"));
//
//		Profile profileSet = service.save(new Profile("nome", "sobrenome", "pais", "sao paulo", "SP", "10/10/1998",user));
//
//		Profile profileGet = profileBeanUtil.toProfile(service.getById(profileSet.getId()));
//
//		assertEquals(profileSet.getId(), profileGet.getId());
//	}
//
//	@Test
//	void testGetByIdProfileNotFound() throws Exception {
//
//		Throwable e = assertThrows(ResourceNotFoundException.class, ()-> service.getById(123456789));
//		assertEquals(CodeException.PF001.getText(), e.getMessage());
//	}
//
//	@Test
//	void testDelete() throws ResourceBadRequestException, Exception {
//
//		User user = userRepository.save(new User("testeProfileGet", "testeS@gert", "senhate"));
//
//		Profile profile = service.save(new Profile("nome", "sobrenome", "pais", "sao paulo", "SP", "10/10/1998", user));
//
//		service.delete(profile.getId());
//
//		Throwable e = assertThrows(ResourceNotFoundException.class, ()-> {service.getById(profile.getId());});
//		assertEquals(CodeException.PF001.getText(), e.getMessage());
//
//	}
//
//	@Test
//	void testDeleteProfileNotFound() throws ResourceBadRequestException, Exception {
//
//		Throwable e = assertThrows(ResourceNotFoundException.class, ()-> service.delete(12345678));
//		assertEquals(CodeException.PF001.getText(), e.getMessage());
//
//	}
//
//	@Test
//	void testUpdate() throws ResourceBadRequestException, Exception {
//
//		String userName = userService.save(new CadastroUserTO("testeUpdat", "testeS@updat", "senhate", "nome", "sobrenome")).getUserName();
//
//		ProfileTO profile = service.getByUser(userName);
//
//		profile.setName("Mudando o nome");
//
//		Profile profileUpdate = profileBeanUtil.toProfile(service.update(profile));
//
//		assertEquals("Mudando o nome", profileUpdate.getName());
//	}
//
//	@Test
//	void testUpdateProfileNotFound() throws ResourceBadRequestException, Exception {
//
//		User user = new User("testeProfile3", "testeS@teste13", "senhate");
//
//		Profile profile = new Profile("nome", "sobrenome", "pais", "sao paulo", "SP", "10/10/1998", user);
//
//		Throwable e = assertThrows(ResourceNotFoundException.class, ()-> service.update(profileBeanUtil.toProfileTO(profile)));
//		assertEquals(CodeException.PF001.getText(), e.getMessage());
//
//	}
//
//	@Test
//	void testGetAllProfiles() throws ResourceBadRequestException, Exception {
//
//		userService.save(new CadastroUserTO("testeProfileGetAll", "testeS@getAll", "senhate", "nome", "sobrenome"));
//		HashSet<ProfileTO> allProfiles = service.getAll();
//
//		assertFalse(allProfiles.isEmpty());
//	}
}