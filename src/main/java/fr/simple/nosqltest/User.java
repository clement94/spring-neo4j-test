package fr.simple.nosqltest;


import java.util.HashSet;
import java.util.Set;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedToVia;


@NodeEntity
public class User {
	
	@GraphId
	private Long id;
	
	@RelatedToVia(type="HAS_ROLE") //automaticaly fetch when user is find from neo4j database
	private Set<HasRole> hasRoles;
	
	private String username;

	public User() {
	}
	
	public User(String username){
		this.username = username;
	}
	
	public HasRole addHasRole(Role role, String locale){
		if(hasRoles == null){
			hasRoles = new HashSet<HasRole>();
		}
		HasRole hasRole = new HasRole(this, role, locale);
		hasRoles.add(hasRole);
		return hasRole;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@Override
	public int hashCode() {
        return (id == null) ? 0 : id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		User other = (User) obj;
		if (id == null) return other.id == null;
        return id.equals(other.id);
    }
	
	@Override
    public String toString() {
        return String.format("User{username='%s'}", username);
    }

	public Set<HasRole> getHasRoles() {
		return hasRoles;
	}

	public void setHasRoles(Set<HasRole> hasRoles) {
		this.hasRoles = hasRoles;
	}

}
