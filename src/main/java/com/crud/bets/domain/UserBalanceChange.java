package com.crud.bets.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@NamedNativeQuery(
        name = "UserBalanceChange.getAllBalanceChangesOfUser",
        query = "SELECT * FROM USER_BALANCE_CHANGES " +
                "WHERE USER_ID = :USER_ID " +
                "ORDER BY CHANGE_TIME DESC",
        resultClass = UserBalanceChange.class
)

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "USER_BALANCE_CHANGES")
public class UserBalanceChange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "OLD_VALUE")
    private BigDecimal oldValue;

    @Column(name = "NEW_VALUE")
    private BigDecimal newValue;

    @Column(name = "CHANGE_TIME")
    private LocalDateTime changeTime;

    public UserBalanceChange(User user, BigDecimal oldValue, BigDecimal newValue) {
        this.user = user;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.changeTime = LocalDateTime.now();
    }
}
