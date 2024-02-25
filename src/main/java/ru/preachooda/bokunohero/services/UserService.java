package ru.preachooda.bokunohero.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.preachooda.bokunohero.entity.User;
import ru.preachooda.bokunohero.repository.UserRepository;
import ru.preachooda.bokunoherocore.repository.BaseEntityRepository;
import ru.preachooda.bokunoherocore.services.BaseEntityService;
import ru.preachooda.bokunoherocore.validator.exceptions.IncorrectDataException;
import ru.preachooda.bokunoherocore.validator.exceptions.NotFoundException;


@Service
public class UserService extends BaseEntityService<User> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public BaseEntityRepository<User> getRepository() {
        return userRepository;
    }

    /**
     * Получение пользователя по имени пользователя
     * <p>
     * Нужен для Spring Security
     *
     * @return пользователь
     */
    public UserDetailsService userDetailsService() {
        return this::findByUsername;
    }

    /**
     * Получение текущего пользователя
     *
     * @return текущий пользователь
     */
    public User getCurrentUser() {
        // Получение имени пользователя из контекста Spring Security
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return findByUsername(username);
    }

    @Override
    public User create(User object) {
        User dbUser = this.findByUsername(object.getUsername());
        if (dbUser != null) {
            throw new IncorrectDataException("Уже существует пользователь с никнеймом '" + object.getUsername() + "'");
        }
        return super.create(object);
    }

    @Override
    public User update(User object) throws IncorrectDataException, NotFoundException {
        User dbUser = this.findByUsername(object.getUsername());
        if (dbUser != null) {
            throw new IncorrectDataException("Уже существует пользователь с никнеймом '" + object.getUsername() + "'");
        }
        return super.update(object);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
}
