package fr.simple.nosqltest;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

@RelationshipEntity(type="HAS_ROLE")
public class HasRole {
	
	@GraphId
	private Long id;
	
	@StartNode
	private User user;
	
	@EndNode
	@Fetch //Obligatoire si on veut qu'il soit récupéré en même temps que la relation à partir de User
	private Role role;
	
	private String locale;
	
	public HasRole() {
	}
	
	public HasRole(User user, Role role, String locale) {
		super();
		this.user = user;
		this.role = role;
		this.locale = locale;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
