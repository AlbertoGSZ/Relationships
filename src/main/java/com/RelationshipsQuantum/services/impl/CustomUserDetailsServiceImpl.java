package com.RelationshipsQuantum.services.impl;
import com.RelationshipsQuantum.repositories.PageUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

	private final PageUserRepository pageUserRepository;

	public CustomUserDetailsServiceImpl(PageUserRepository pageUserRepository) {
		this.pageUserRepository = pageUserRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return pageUserRepository.findByNameIgnoreCase(username).orElseThrow(() -> new UsernameNotFoundException(
				String.format("No user found with username '%s'.", username)));
	}
}
