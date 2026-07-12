package kegly.organisation.nutrition.service;

import jakarta.persistence.EntityNotFoundException;
import kegly.organisation.nutrition.dto.request.UserRequestDto;
import kegly.organisation.nutrition.entity.Target;
import kegly.organisation.nutrition.entity.User;
import kegly.organisation.nutrition.mapper.UserMapper;
import kegly.organisation.nutrition.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    @Test
    void create_whenValidDto_thenUserIsSaved() {
        UserRequestDto dto = buildDto();
        User user = buildUser();
        when(userMapper.toEntity(dto)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);

        User result = userService.create(dto);

        assertThat(result).isEqualTo(user);
        verify(userMapper).toEntity(dto);
        verify(userRepository).save(user);
    }

    @Test
    void getById_whenUserExists_thenReturnUser() {
        User user = buildUser();
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User result = userService.getById(1L);

        assertThat(result).isEqualTo(user);
    }

    @Test
    void getById_whenUserNotFound_thenThrowEntityNotFoundException() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> userService.getById(99L));
    }

    @Test
    void findByTelegramId_whenUserExists_thenReturnOptionalWithUser() {
        User user = buildUser();
        when(userRepository.findByTelegramId(100L)).thenReturn(Optional.of(user));

        Optional<User> result = userService.findByTelegramId(100L);

        assertThat(result).isPresent().contains(user);
    }

    @Test
    void update_whenUserExists_thenMapperIsCalledAndUserIsSaved() {
        UserRequestDto dto = buildDto();
        User user = buildUser();
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);

        userService.update(1L, dto);

        verify(userMapper).updateEntityFromDto(dto, user);
        verify(userRepository).save(user);
    }

    @Test
    void delete_whenCalled_thenRepositoryDeleteByIdIsCalled() {
        userService.delete(1L);

        verify(userRepository).deleteById(1L);
    }

    private UserRequestDto buildDto() {
        return new UserRequestDto(100L, "testUser", 70.0, 175);
    }

    private User buildUser() {
        User user = new User();
        user.setId(1L);
        user.setTelegramId(100L);
        user.setUsername("testUser");
        user.setWeight(70.0);
        user.setHeight(175);
        return user;
    }
}