package com.RelationshipsQuantum.entities;

import ch.qos.logback.core.net.server.Client;
import com.RelationshipsQuantum.constants.AuthorityName;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Authority {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(unique = true)
	private AuthorityName name;

	@ManyToMany(mappedBy = "authorities")
	private List<PageUser> pageUsers;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public AuthorityName getName() {
		return name;
	}

	public void setName(AuthorityName name) {
		this.name = name;
	}

	public List<PageUser> getUsers() {
		return pageUsers;
	}

	public void setUsers(List<PageUser> pageUsers) {
		this.pageUsers = pageUsers;
	}
}
