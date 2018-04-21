package com.sunbufu.entity;

import com.sunbufu.entity.type.SystemRecordType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 系统记录
 *
 * @author sunbufu
 */
@Entity
@Table
@EntityListeners(AuditingEntityListener.class)
public class SystemRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private SystemRecordType type;

    /**主机ip*/
    private String hostIp;
    /**主机名称*/
    private String hostName;


    @Column(nullable = false, columnDefinition = "timestamp")
    @CreatedDate
    private Timestamp time;

    public SystemRecord() {
    }

    public SystemRecord(SystemRecordType type, String hostIp, String hostName) {
        this.type = type;
        this.hostIp = hostIp;
        this.hostName = hostName;
    }

//-----------GETTER/SETTER

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SystemRecordType getType() {
        return type;
    }

    public void setType(SystemRecordType type) {
        this.type = type;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }
}
