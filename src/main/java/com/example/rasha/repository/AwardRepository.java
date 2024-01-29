package com.example.rasha.repository;
import com.example.rasha.model.Award;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AwardRepository extends JpaRepository<Award, Long> {
    @Override
    void delete(Award entity);

    @Override
    void deleteAll();


    @Override
    void deleteAllById(Iterable<? extends Long> longs);

    @Override
    <S extends Award> List<S> saveAll(Iterable<S> entities);

    @Override
    <S extends Award> S save(S entity);

    @Override
    List<Award> findAll(Sort sort);

    @Override
    List<Award> findAllById(Iterable<Long> longs);
    // Custom database queries can be defined here if needed
}
