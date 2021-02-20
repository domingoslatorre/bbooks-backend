package br.edu.ifsp.spo.bulls.common.api.dto;

import br.edu.ifsp.spo.bulls.common.api.enums.Role;
import br.edu.ifsp.spo.bulls.common.api.enums.Status;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

public class CompetitionMemberTO {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID memberId;

    private String title;

    private String story;

    private ProfileTO profile;

    private Role role;

    private Status status;

    private CompetitionTO competitionTO;

    private float meanVote;
}