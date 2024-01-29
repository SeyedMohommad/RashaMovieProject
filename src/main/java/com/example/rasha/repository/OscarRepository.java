package com.example.rasha.repository;
import com.example.rasha.model.Oscar;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OscarRepository extends JpaRepository<Oscar, Long> {
    boolean existsByTitle(String title);

    @Override
    boolean existsById(Long aLong);

    @Override
    <S extends Oscar> boolean exists(Example<S> example);
}
