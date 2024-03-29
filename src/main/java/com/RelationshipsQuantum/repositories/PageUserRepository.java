package com.RelationshipsQuantum.repositories;

		import com.RelationshipsQuantum.entities.PageUser;
		import org.springframework.data.jpa.repository.JpaRepository;
		import org.springframework.stereotype.Repository;

		import java.util.Optional;

@Repository
public interface PageUserRepository extends JpaRepository<PageUser, Long> {
	Optional<PageUser> findByNameIgnoreCase(String username);
}
