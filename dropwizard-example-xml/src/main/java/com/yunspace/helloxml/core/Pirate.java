package com.yunspace.helloxml.core;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "pirates")
@NamedQueries({
    @NamedQuery(
        name = "com.yunspace.helloxml.core.Pirate.findAll",
        query = "SELECT p FROM Pirate p JOIN FETCH p.ships s WHERE p.pirateId = s.shipCaptain"
    ),
    @NamedQuery(
        name = "com.yunspace.helloxml.core.Pirate.findById",
        query = "SELECT p FROM Pirate p JOIN FETCH p.ships s WHERE p.pirateId = s.shipCaptain AND p.pirateId = :id"
    )
})
@JacksonXmlRootElement(localName="Pirate")
public class Pirate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pirate_id", nullable = false)
    @JacksonXmlProperty(isAttribute = true)
    private long pirateId;

    @Column(name = "nick_name", nullable = false)
    @JacksonXmlProperty(localName = "NickName")
    private String nickName;

    @Column(name = "real_name", nullable = false)
    @JacksonXmlProperty(localName = "RealName")
    private String realName;

    @OneToMany(mappedBy = "shipCaptain", fetch = FetchType.EAGER)
    private List<Ship> ships;

    public long getPirateId() {
        return pirateId;
    }

    public void setPirateId(long pirate_id) {
        this.pirateId = pirate_id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public List<Ship> getShips() {
        return ships;
    }
}