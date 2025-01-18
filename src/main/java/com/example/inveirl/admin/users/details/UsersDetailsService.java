package com.example.inveirl.admin.users.details;

import com.example.inveirl.infrastructure.exceptions.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
class UsersDetailsService {

    private final UsersDetailsUsersRepository userRepository;

    public UsersDetailsResponse getUserDetails(final UUID userId) {
        final UsersDetailsUsersEntity user = getUser(userId);

        return user.toUsersDetailsResponse();
    }

    private UsersDetailsUsersEntity getUser(UUID userId) {
        return userRepository.findById(userId)
                             .orElseThrow(() -> UserNotFoundException.of(userId));
    }
}