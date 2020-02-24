package xyz.intellij.streamsys.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

/**
 * 在此标记不生成json对象的属性
 *
 * 因为jsonplugin用的是java的内审机制.hibernate会给被管理的pojo加入一个hibernateLazyInitializer属性,jsonplugin会把hibernateLazyInitializer也拿出来操作,并读取里面一个不能被反射操作的属性就产生了这个异常.
 *
 */

@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
@Entity
@Table(name = "rate", schema = "stream_share", catalog = "")
@IdClass(RateEntityPK.class)
public class RateEntity {
    private long streamId;
    private long userId;
    private Double cRate;
    private Double qRate;

    @Id
    @Column(name = "stream_id")
    public long getStreamId() {
        return streamId;
    }

    public void setStreamId(long streamId) {
        this.streamId = streamId;
    }

    @Id
    @Column(name = "user_id")
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "c_rate")
    public Double getcRate() {
        return cRate;
    }

    public void setcRate(Double cRate) {
        this.cRate = cRate;
    }

    @Basic
    @Column(name = "q_rate")
    public Double getqRate() {
        return qRate;
    }

    public void setqRate(Double qRate) {
        this.qRate = qRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RateEntity that = (RateEntity) o;
        return streamId == that.streamId &&
                userId == that.userId &&
                Objects.equals(cRate, that.cRate) &&
                Objects.equals(qRate, that.qRate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(streamId, userId, cRate, qRate);
    }
}
