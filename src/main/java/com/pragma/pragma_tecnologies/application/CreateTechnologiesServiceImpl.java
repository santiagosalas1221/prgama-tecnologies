package com.pragma.pragma_tecnologies.application;

import com.pragma.pragma_tecnologies.application.exceptions.TechnologyAlreadyExistsException;
import com.pragma.pragma_tecnologies.application.ports.in.CreateTechnologyUseCase;
import com.pragma.pragma_tecnologies.application.ports.out.TechnologyRepository;
import com.pragma.pragma_tecnologies.domain.model.TechnologiesDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.util.UUID;

@Service
public class CreateTechnologiesServiceImpl implements CreateTechnologyUseCase {

    private static final Logger logger = LoggerFactory.getLogger(CreateTechnologiesServiceImpl.class);

    private final TechnologyRepository technologyRepository;

    public CreateTechnologiesServiceImpl(TechnologyRepository technologyRepository) {
        this.technologyRepository = technologyRepository;
    }

    @Override
    public Mono<TechnologiesDTO> createTechnology(TechnologiesDTO technologiesDTO) {
        logger.info("Start service createTechnology for technology: {}", technologiesDTO.getName());

        // Generar el UUID como id
        technologiesDTO.setId(UUID.randomUUID().toString());
        logger.info("technology id generated: {}", technologiesDTO.getId());

        return technologyRepository.existsByName(technologiesDTO.getName())
                .flatMap(exists -> {
                    if (exists) {
                        logger.error("Technology '{}' already exists", technologiesDTO.getName());
                        return Mono.error(new TechnologyAlreadyExistsException("Technology already exists: " + technologiesDTO.getName()));
                    }
                    logger.info("Saving technology: {}", technologiesDTO.getName());
                    return technologyRepository.save(technologiesDTO);
                });
    }
}