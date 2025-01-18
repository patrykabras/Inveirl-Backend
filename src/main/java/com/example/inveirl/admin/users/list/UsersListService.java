package com.example.inveirl.admin.users.list;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(access = AccessLevel.PACKAGE)
class UsersListService {

    private final UsersListUsersRepository repository;

    public List<UsersListResponse> allUsers(final UserListFilterRequest filterRequest) {
        final UserListUsersSpecifications specifications = new UserListUsersSpecifications(filterRequest);
        final List<UsersListUsersEntity> users = repository.findAll(specifications);

        return users.stream()
                    .map(UsersListUsersEntity::toUsersListResponse)
                    .toList();
    }
}