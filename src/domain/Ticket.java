package domain;

import java.time.LocalDateTime;

public class Ticket extends Entity<String> {
    private Long flightId;
    private LocalDateTime purchaseTime;

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + flightId +
                ", purchaseTime=" + purchaseTime +
                '}';
    }



    public void setPurchaseTime(LocalDateTime purchaseTime) {
        this.purchaseTime = purchaseTime;
    }



    public LocalDateTime getPurchaseTime() {
        return purchaseTime;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public Long getFlightId() {
        return flightId;
    }

    public Ticket(Long flightId, LocalDateTime purchaseTime) {
        this.flightId = flightId;
        this.purchaseTime = purchaseTime;
    }
}
