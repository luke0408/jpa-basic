package hellojpa;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "MEMBER") // @Table(name, catalog, schema, uniqueConstraints)
public class Member {

    @Id
    private Long id;

    @Column(name = "name")
    private String username;

    private Integer age;

    @Enumerated(EnumType.STRING) // EnumType.ORDINAL: enum 순서를 DB에 저장(사용 X), EnumType.STRING: enum 이름을 DB에 저장
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP) // TemporalType.DATE: 날짜, TemporalType.TIME: 시간, TemporalType.TIMESTAMP: 날짜와 시간
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob // CLOB, BLOB 타입을 사용할 때
    private String description;

}
