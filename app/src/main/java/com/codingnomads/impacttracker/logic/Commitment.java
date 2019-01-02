package com.codingnomads.impacttracker.logic;

import java.time.LocalDate;

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

// public Commitment() {
   // }
//
   // public Commitment(Integer id, LocalDate startDate, LocalDate endDate, Integer amountToReduceBy, Integer userId, Integer reductionId) {
   //     this.id = id;
   //     this.startDate = startDate;
   //     this.endDate = endDate;
   //     this.amountToReduceBy = amountToReduceBy;
   //     this.userId = userId;
   //     this.reductionId = reductionId;
   // }
//
//
   // public Commitment(Integer userId, Integer reductionId, LocalDate startDate, LocalDate endDate, Integer amountToReduceBy) {
   //     this.userId = userId;
   //     this.reductionId = reductionId;
   //     this.startDate = startDate;
   //     this.endDate = endDate;
   //     this.amountToReduceBy = amountToReduceBy;
   // }
//
  //  @Override
  //  public String toString() {
  //      return "Commitment{" +
  //              "id=" + id +
  //              ", startDate=" + startDate +
  //              ", endDate=" + endDate +
  //              ", amountToReduceBy=" + amountToReduceBy +
  //              ", userId=" + userId +
  //              ", reductionId=" + reductionId +
  //              '}';
  //  }


  //  @Override
  //  public boolean equals(Object o) {
  //      if (this == o) return true;
  //      if (o == null || getClass() != o.getClass()) return false;
  //      Commitment that = (Commitment) o;
  //      return Objects.equals(id, that.id) &&
  //              Objects.equals(userId, that.userId) &&
  //              Objects.equals(reductionId, that.reductionId) &&
  //              Objects.equals(startDate, that.startDate) &&
  //              Objects.equals(endDate, that.endDate) &&
  //              Objects.equals(amountToReduceBy, that.amountToReduceBy);
  //  }
//
  //  @Override
  //  public int hashCode() {
  //      return Objects.hash(id, userId, reductionId, startDate, endDate, amountToReduceBy);
  //  }

}
