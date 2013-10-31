package fr.simple.nosqltest;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.conversion.EndResult;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:simple-sprint-context.xml")  
public class UserTest {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private Neo4jTemplate template;
	
	@Test
	public void addUser() throws Exception{
		User user = new User("clement");
		user.addHasRole(new Role("my role"), "en_US");
		try {
			save(user);
		} catch (Exception e) {
			System.out.println("Error "+e.getMessage());
		}
		EndResult<User> users = template.findAll(User.class);
		User next = users.iterator().next();
		assertEquals("clement", next.getUsername());
		Set<HasRole> hasRoles = next.getHasRoles();
		assertEquals(1, hasRoles.size());
		for (HasRole hasRole : hasRoles) {
			System.out.println(hasRole.getRole().getId());
			assertEquals("en_US",hasRole.getLocale());
			assertEquals("my role",hasRole.getRole().getRolename());
		}
	}
	
	public User save(User user) throws Exception {
		User save = userService.addUser(user);
		return save;
	}
	
}
