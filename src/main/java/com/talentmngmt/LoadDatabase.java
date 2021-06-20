package com.talentmngmt;

import com.talentmngmt.model.*;
import com.talentmngmt.repository.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {


    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initPositionDatabase(PositionRepository repository) {

        return args -> {
            Position position = new Position();

            position.setName("Developer");
            log.info("Preloading " + repository.save(position));
            position = new Position();
            position.setName("QA");
            log.info("Preloading " + repository.save(position));
            position = new Position();
            position.setName("DevOps Engineer");
            log.info("Preloading " + repository.save(position));
        };
    }

    @Bean
    CommandLineRunner initCandidateDatabase(CandidateRepository repository) {

        return args -> {
            Candidate candidate = new Candidate();
            candidate.setName("Tito");
            candidate.setLastName("Maturana");
            candidate.setAddress("DG 40 A Bis");
            candidate.setCityName("Bogotá");
            candidate.setCellphone("3017777777");

            log.info("Preloading " + repository.save(candidate));

            candidate = new Candidate();
            candidate.setName("Santiago");
            candidate.setLastName("Nitola");
            candidate.setAddress("DG 40 A Bis");
            candidate.setCityName("Bogotá");
            candidate.setCellphone("3017777777");

            log.info("Preloading " + repository.save(candidate));
        };
    }
}
