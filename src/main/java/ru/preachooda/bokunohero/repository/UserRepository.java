package ru.preachooda.bokunohero.repository;

import org.springframework.stereotype.Repository;
import ru.preachooda.bokunohero.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseEntityRepository<User> {

    Optional<User> findByUsername(String username);



}
