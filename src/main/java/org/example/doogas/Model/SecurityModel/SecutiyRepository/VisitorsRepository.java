package org.example.doogas.Model.SecurityModel.SecutiyRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VisitorsRepository extends JpaRepository<Visitors, Long> {
    Optional<Visitors> findVisitorsByUsername(String username);
}
