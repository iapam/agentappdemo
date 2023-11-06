package com.example.transmissionproject.Repository;

import com.example.transmissionproject.Model.Agent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Repository
public interface agentRepository extends JpaRepository<Agent,Long> {

    Optional<Agent>findByUserId(String userid);
    @Modifying
    @Transactional
    @Query("update Agent set ndcvotes=:ndcvotes,nppvotes=:nppvotes,total_ndc_vote=:total_ndc_vote,total_npp_votes=:total_npp_votes,status=:status where userId=:userId")
    void updateAgent(String userId, int nppvotes, int ndcvotes, int total_ndc_vote, int total_npp_votes, String status);

    @Query("select total_ndc_vote,total_npp_votes from Agent where userId=:userId")
    Agent findAgentByUserId(String userId);

}
