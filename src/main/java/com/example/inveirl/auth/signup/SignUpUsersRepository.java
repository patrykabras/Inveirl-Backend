package com.example.inveirl.auth.signup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface SignUpUsersRepository extends JpaRepository<SignUpUsersEntity, UUID> {

}