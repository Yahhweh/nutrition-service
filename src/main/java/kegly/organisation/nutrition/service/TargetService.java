package kegly.organisation.nutrition.service;

import jakarta.persistence.EntityNotFoundException;
import kegly.organisation.nutrition.dto.request.TargetRequestDto;
import kegly.organisation.nutrition.entity.Target;
import kegly.organisation.nutrition.mapper.TargetMapper;
import kegly.organisation.nutrition.repository.TargetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TargetService {

    private final TargetRepository targetRepository;
    private final TargetMapper targetMapper;

    @Transactional
    public Target create(TargetRequestDto dto) {
        Target target = targetMapper.toEntity(dto);
        return targetRepository.save(target);
    }

    @Transactional(readOnly = true)
    public Target getById(Long id) {
        return targetRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Target not found: id=" + id));
    }

    @Transactional
    public Target update(Long id, TargetRequestDto dto) {
        Target target = getById(id);
        targetMapper.updateEntityFromDto(dto, target);
        return targetRepository.save(target);
    }

    @Transactional
    public void delete(Long id) {
        targetRepository.deleteById(id);
    }
}