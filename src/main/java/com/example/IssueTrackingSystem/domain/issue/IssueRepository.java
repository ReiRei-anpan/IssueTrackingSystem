package com.example.IssueTrackingSystem.domain.issue;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends CrudRepository<IssueEntity, Long> {



    //全件取得のSQLを発行したい
    @Query("SELECT * FROM issues")
    List<IssueEntity> findAll();

    @Modifying
    @Query("INSERT INTO issues (summary, description) VALUES (:summary, :description)")
    void insert(@Param("summary") String summary, @Param("description") String description);

    @Query("SELECT * FROM issues WHERE id = :issueId")
    IssueEntity findById(@Param("issueId") long issueId);

}
