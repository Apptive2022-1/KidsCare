package team3.OneSubscribe.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;


    @Column(unique = true)
    private String loginId; // 형식 확인 필요

    private String loginPassword; // 형식 확인 필요

    @Column(unique = true)
    private String name;

    @Column(unique = true)
    private String nickName;

    private String eMail;

    private String team; // group으로 하면 데이터베이스 예약어라서 안됨.

    @Column(unique = true)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Expert expert;

    @OneToMany(mappedBy = "member")
    private List<Writing> writings;

//    @OneToOne(mappedBy = "member", fetch = FetchType.LAZY)
//    private Answer answers;

    // 좋아요(피드백) 받은 개수
    private Long totalLikeNumber = 0L;

}
