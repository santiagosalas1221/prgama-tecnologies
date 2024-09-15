package com.pragma.pragma_tecnologies.application.ports.out;

import com.pragma.pragma_tecnologies.domain.model.TechnologiesDTO;
import reactor.core.publisher.Mono;

public interface TechnologyRepository {
    Mono<TechnologiesDTO> save(TechnologiesDTO technologiesDTO);
    Mono<Boolean>existsByName(String name);
}
