package model;


import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Phone {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "phoneId")
    @SequenceGenerator(name = "phoneId", sequenceName = "phoneId", schema = "Login_Nisum")
    @Column(name = "phoneId")
    private Long phoneId;

    private Long number;

    private String cityCode;

    private String countryCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;
}
