package com.example.inveirl.features.auth.signup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
interface UserSignUpUsersRepository extends JpaRepository<UserSignUpUsersEntity, UUID> {

    Optional<UserSignUpUsersEntity> findByEmail(String email);
}