package xyz.intellij.streamsys.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;
public class RateEntityPK implements Serializable {
    private long streamId;
    private long userId;

    @Column(name = "stream_id")
    @Id
    public long getStreamId() {
        return streamId;
    }

    public void setStreamId(long streamId) {
        this.streamId = streamId;
    }

    @Column(name = "user_id")
    @Id
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RateEntityPK that = (RateEntityPK) o;
        return streamId == that.streamId &&
                userId == that.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(streamId, userId);
    }
}
