package com.crud.bets.repositories;

import com.crud.bets.domain.BetProspect;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface BetProspectRepository extends CrudRepository<BetProspect, Long> {

    @Override
    List<BetProspect> findAll();

    @Override
    BetProspect save(BetProspect betProspect);

    @Override
    Optional<BetProspect> findById(Long id);
}
