package com.codingnomads.impacttracker.logic;

public class Commitment {

    private Integer id;
    private Integer userId;
    private Integer reductionId;
    private String startDate;
    private String endDate;
    private Integer amountToReduceBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getReductionId() {
        return reductionId;
    }

    public void setReductionId(Integer reductionId) {
        this.reductionId = reductionId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getAmountToReduceBy() {
        return amountToReduceBy;
    }

    public void setAmountToReduceBy(Integer amountToReduceBy) {
        this.amountToReduceBy = amountToReduceBy;
    }

    @Override
    public String toString() {
        return "Commitment{" +
                "id=" + id +
                ", userId=" + userId +
                ", reductionId=" + reductionId +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", amountToReduceBy=" + amountToReduceBy +
                '}';
    }
}
