package com.crud.bets.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@NamedNativeQuery(
        name = "BetProspectsRequest.getBetProspectsRequestsOfUser",
        query = "SELECT * FROM BET_PROSPECTS_REQUESTS " +
                "WHERE USER_ID = :USER_ID " +
                "ORDER BY CREATED DESC",
        resultClass = BetProspectsRequest.class
)


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "BET_PROSPECTS_REQUESTS")
public class BetProspectsRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @OrderColumn
    private List<String> leagues;

    @Column(name = "CREATED")
    private LocalDateTime created;

    public BetProspectsRequest(User user, List<String> leagues, LocalDateTime created) {
        this.user = user;
        this.leagues = leagues;
        this.created = created;
    }
}
