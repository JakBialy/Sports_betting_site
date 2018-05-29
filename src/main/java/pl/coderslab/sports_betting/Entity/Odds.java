//package pl.coderslab.sports_betting.Entity;
//
//import lombok.Data;
//
//import javax.persistence.*;
//import java.math.BigDecimal;
//
//@Entity
//@Table(name = "odds")
//public @Data
//class Odds {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    private String bookmaker;
//
//    private BigDecimal oddOne;
//
//    private BigDecimal oddOneHalf;
//
//    private BigDecimal oddX;
//
//    private BigDecimal oddTwo;
//
//    private BigDecimal oddTwoHalf;
//
//    @OneToOne
//    @JoinColumn(name = "match_id")
//    private Match match;
//}
