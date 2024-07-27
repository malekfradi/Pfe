package com.promosport.app.repository;

import com.promosport.app.model.Sport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface SportRepository extends JpaRepository<Sport, Long> {
}
