package com.fasttrackit.JavaEncyclopediaProject.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {

}