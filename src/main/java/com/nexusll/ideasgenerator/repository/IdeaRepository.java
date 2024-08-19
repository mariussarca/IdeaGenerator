package com.nexusll.ideasgenerator.repository;

import com.nexusll.ideasgenerator.model.ApiData;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface IdeaRepository extends JpaRepository<ApiData, Long> {

    @Query(value = "SELECT * FROM api_data ORDER BY RAND() LIMIT :noOfPicks", nativeQuery = true)
    public List<ApiData> getRandom(int noOfPicks);

}
