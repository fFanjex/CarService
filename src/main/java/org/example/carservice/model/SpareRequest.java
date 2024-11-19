package org.example.carservice.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "spare_request")
public class SpareRequest {

    @EmbeddedId
    private SpareRequestId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @MapsId("requestId")
    @JoinColumn(name = "request_id", nullable = false)
    private Request request;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @MapsId("partId")
    @JoinColumn(name = "part_id", nullable = false)
    private SparePart part;

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public SparePart getPart() {
        return part;
    }

    public void setPart(SparePart part) {
        this.part = part;
    }

    public SpareRequestId getId() {
        return id;
    }

    public void setId(SpareRequestId id) {
        this.id = id;
    }

    @Embeddable
    public static class SpareRequestId implements Serializable {
        private Long requestId;
        private Long partId;

        public SpareRequestId() {}

        public SpareRequestId(Long requestId, Long partId) {
            this.requestId = requestId;
            this.partId = partId;
        }

        public Long getRequestId() {
            return requestId;
        }

        public void setRequestId(Long requestId) {
            this.requestId = requestId;
        }

        public Long getPartId() {
            return partId;
        }

        public void setPartId(Long partId) {
            this.partId = partId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SpareRequestId that = (SpareRequestId) o;
            return requestId.equals(that.requestId) && partId.equals(that.partId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(requestId, partId);
        }
    }
}
