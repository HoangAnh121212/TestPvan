package com.example.testlifetext.Repository;

import com.example.testlifetext.Entity.Relationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface RelationshipRepository extends JpaRepository<Relationship, Integer> {
}
