package kegly.organisation.nutrition.service;

import jakarta.persistence.EntityNotFoundException;
import kegly.organisation.nutrition.dto.request.UserRequestDto;
import kegly.organisation.nutrition.entity.Target;
import kegly.organisation.nutrition.entity.User;
import kegly.organisation.nutrition.mapper.UserMapper;
import kegly.organisation.nutrition.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    public User create(UserRequestDto dto) {
        User user = userMapper.toEntity(dto);
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found: id=" + id));
    }

    @Transactional(readOnly = true)
    public Optional<User> findByTelegramId(Long telegramId) {
        return userRepository.findByTelegramId(telegramId);
    }

    @Transactional
    public User update(Long id, UserRequestDto dto) {
        User user = getById(id);
        userMapper.updateEntityFromDto(dto, user);
        return userRepository.save(user);
    }

    @Transactional
    public User assignTarget(Long id, Target target) {
        User user = getById(id);
        user.setTarget(target);
        return user;
    }

    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}