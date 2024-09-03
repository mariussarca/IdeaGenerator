package com.nexusll.ideasgenerator.repository;

import com.nexusll.ideasgenerator.model.ApiData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IdeaRepository extends JpaRepository<ApiData, Long> {

    @Query(value = "SELECT * FROM api_data ORDER BY NEWID() OFFSET 0 ROWS FETCH NEXT :noOfPicks ROWS ONLY", nativeQuery = true)
    List<ApiData> getRandom(@Param("noOfPicks") int noOfPicks);

    boolean existsByUrl(String url);

}
