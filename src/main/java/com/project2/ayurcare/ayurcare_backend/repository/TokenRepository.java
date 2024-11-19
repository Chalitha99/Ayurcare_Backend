package com.project2.ayurcare.ayurcare_backend.repository;

import com.project2.ayurcare.ayurcare_backend.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token,Integer> {

    @Query("""
        select t from Token t inner join Medicaluser u 
        on t.medicaluser.medicaluserId = u.medicaluserId
        where t.medicaluser.medicaluserId = :userId and t.loggedOut = false 
        """)
    List<Token> findAllTokenByUser(String userId);

    Optional<Token> findByToken(String token);
}
