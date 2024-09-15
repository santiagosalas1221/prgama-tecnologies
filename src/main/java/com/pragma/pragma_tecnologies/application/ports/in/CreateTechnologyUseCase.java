package com.pragma.pragma_tecnologies.application.ports.in;

import com.pragma.pragma_tecnologies.domain.model.TechnologiesDTO;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public interface CreateTechnologyUseCase {
    Mono<TechnologiesDTO> createTechnology(TechnologiesDTO technologiesDTO);
}