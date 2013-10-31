package fr.simple.nosqltest;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

	@Autowired
	private Neo4jTemplate template;
	 @Autowired
	private UserRepository userRepository;
	
	@Transactional(rollbackFor=Exception.class)
	//@Neo4jTransactional
	public User addUser(User user) throws Exception{
		//We first save roles in database
		Set<HasRole> hasRoles = user.getHasRoles();
		for (HasRole hasRole : hasRoles) {
			template.save(hasRole.getRole());
		}
		//And then we save the user which save in same time
		//the relationship "has_role". Sad that Role object is
		//not saved in same time (like cascade save)...
		User save = userRepository.save(user);
		if(true){
			throw new Exception("just to see that transaction is not working when we lauch a test on real neo4j server");
		}
		return save;
	}
	
}
