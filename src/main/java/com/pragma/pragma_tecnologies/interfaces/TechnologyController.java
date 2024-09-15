package com.pragma.pragma_tecnologies.interfaces;

import com.pragma.pragma_tecnologies.application.ports.in.CreateTechnologyUseCase;
import com.pragma.pragma_tecnologies.domain.model.TechnologiesDTO;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/technologies")
public class TechnologyController {

    private static final Logger logger = LoggerFactory.getLogger(TechnologyController.class);

    private final CreateTechnologyUseCase createTechnologyUseCase;

    public TechnologyController(CreateTechnologyUseCase createTechnologyUseCase) {
        this.createTechnologyUseCase = createTechnologyUseCase;
    }


    @PostMapping("/createTechnology")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<TechnologiesDTO> createTechnology(@Valid @RequestBody TechnologiesDTO technologiesDTO) {
        logger.info(">> Starting Controller createTechnology");
        return createTechnologyUseCase.createTechnology(technologiesDTO);
    }
}
