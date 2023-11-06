package com.example.transmissionproject.Controllers;

import com.example.transmissionproject.Model.Agent;
import com.example.transmissionproject.Model.agentRole;
import com.example.transmissionproject.Repository.agentRepository;
import com.example.transmissionproject.Request.VoteRequest;
import com.example.transmissionproject.Services.AgentService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class RegistrationController {
    private final agentRepository agentRepository;
    private final AgentService agentService;


    @GetMapping("/user")
    public String firstApp(Model model){
        Agent agent=(Agent)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Agent agent1=new Agent(agent.getAgentName(),
                agent.getAgentNumber()
                ,agent.getAgentTown(),
                agent.getAgentConsituency(),
                agent.getUserId()
                ,agent.getRole());
        model.addAttribute("user",agent1);
        return "/record_page";
    }
    @PostMapping("/record")
    public String recordVotes(@RequestParam("userid") String userid,@RequestParam("ndc_votes") String ndc_votes,
                              @RequestParam("npp_votes") String npp_votes){
        //agentdetails.cnycdphn7qhf.eu-north-1.rds.amazonaws.com
        //13.48.104.154
        Agent agent=agentRepository.findByUserId(userid).orElseThrow();
        if(agent.getStatus().equals("record")){
            return "already record";
        }
        List<Agent> total_agent= agentRepository.findAll();
      String ndcvotes=ndc_votes;
      String nppvotes=npp_votes;
      int total_ndcVotes=0;
      int total_nppVotes=0;
      for(Agent addndc: total_agent){

         total_ndcVotes=total_ndcVotes+addndc.getNdcvotes();
          total_nppVotes=total_nppVotes+addndc.getNppvotes();
      }
      int finalndcVotes=total_ndcVotes+Integer.parseInt(ndcvotes);
        int finalnppVotes=total_nppVotes+Integer.parseInt(nppvotes);
        Agent agent1=new Agent(userid,Integer.parseInt(nppvotes)
        ,Integer.parseInt(ndcvotes)
        ,finalndcVotes,finalnppVotes,agentRole.USER);
      String message=agentService.updateagent(agent1);
        return "/finishpage";
    }
@GetMapping("/user/returnhome")
public String returnhome(){
        return "redirect:/user";
}

}
