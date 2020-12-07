package com.crud.bets.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "BETS")
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "BET_PROSPECT_ID")
    private BetProspect betProspect;

    @Column(name = "CREATED")
    private LocalDateTime created;

    @Column(name = "TIPPED_WINNER")
    private Winner tippedWinner;

    @Column(name = "STAKE")
    private BigDecimal stake;

    @Column(name = "FINALIZED")
    private Boolean finalized;

    @Column(name = "WINNER")
    Winner winner;

    @Column(name = "WON")
    Boolean won;

    @Column(name = "CASH_WIN")
    BigDecimal cashWin;

   }
