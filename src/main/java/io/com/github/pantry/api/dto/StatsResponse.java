package io.com.github.pantry.api.dto;

public class StatsResponse {
    private double total;
    private int visits;

    public StatsResponse(double total, int visits) {
        this.total = total;
        this.visits = visits;
    }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public int getVisits() { return visits; }
    public void setVisits(int visits) { this.visits = visits; }
}
