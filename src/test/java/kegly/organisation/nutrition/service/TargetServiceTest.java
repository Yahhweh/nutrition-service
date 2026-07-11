//package kegly.organisation.nutrition.service;
//
//import jakarta.persistence.EntityNotFoundException;
//import kegly.organisation.nutrition.dto.request.TargetRequestDto;
//import kegly.organisation.nutrition.entity.GoalType;
//import kegly.organisation.nutrition.entity.Target;
//import kegly.organisation.nutrition.repository.TargetRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class TargetServiceTest {
//
//    @Mock
//    private TargetRepository targetRepository;
//
//
//    @InjectMocks
//    private TargetService targetService;
//
//    @Test
//    void create_whenValidDto_thenTargetIsSaved() {
//        TargetRequestDto dto = buildDto();
//        Target target = buildTarget();
//        when(targetMapper.toEntity(dto)).thenReturn(target);
//        when(targetRepository.save(target)).thenReturn(target);
//
//        Target result = targetService.create(dto);
//
//        assertThat(result).isEqualTo(target);
//        verify(targetMapper).toEntity(dto);
//        verify(targetRepository).save(target);
//    }
//
//    @Test
//    void getById_whenTargetExists_thenReturnTarget() {
//        Target target = buildTarget();
//        when(targetRepository.findById(1L)).thenReturn(Optional.of(target));
//
//        Target result = targetService.getById(1L);
//
//        assertThat(result).isEqualTo(target);
//    }
//
//    @Test
//    void getById_whenTargetNotFound_thenThrowEntityNotFoundException() {
//        when(targetRepository.findById(99L)).thenReturn(Optional.empty());
//
//        assertThrows(EntityNotFoundException.class, () -> targetService.getById(99L));
//    }
//
//    @Test
//    void update_whenTargetExists_thenMapperIsCalledAndTargetIsSaved() {
//        TargetRequestDto dto = buildDto();
//        Target target = buildTarget();
//        when(targetRepository.findById(1L)).thenReturn(Optional.of(target));
//        when(targetRepository.save(target)).thenReturn(target);
//
//        targetService.update(1L, dto);
//
//        verify(targetMapper).updateEntityFromDto(dto, target);
//        verify(targetRepository).save(target);
//    }
//
//    @Test
//    void delete_whenCalled_thenRepositoryDeleteByIdIsCalled() {
//        targetService.delete(1L);
//
//        verify(targetRepository).deleteById(1L);
//    }
//
//    private TargetRequestDto buildDto() {
//        return new TargetRequestDto(1L, 60, GoalType.LEAN_BULK);
//    }
//
//    private Target buildTarget() {
//        Target target = new Target();
//        target.setId(1L);
//        target.setProtein(150.0);
//        target.setFat(60.0);
//        target.setCarbohydrates(200.0);
//        target.setCcal(1920);
//        target.setType(GoalType.LEAN_BULK);
//        return target;
//    }
//}