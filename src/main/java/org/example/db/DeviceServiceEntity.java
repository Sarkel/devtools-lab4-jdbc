package org.example.db;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DeviceServiceEntity {
    private Integer id;
    private String ownerName;
    private Status status;

    private Decision decision;

    @Override
    public String toString() {
        return "id=" + id +
                ", ownerName='" + ownerName + '\'' +
                ", status=" + status.name() +
                ", decision=" + decision.getMessage();
    }

    public void setDecision(Decision decision) {
        this.status = Status.SENT;
        this.decision = decision;
    }
}
