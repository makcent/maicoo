package com.makcent.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class QuestionReward extends QuestionRewardKey {
    private BigDecimal money;

    private Date createdAt;

    private Date updatedAt;

    private Byte status;

    private Long questionId;

    public QuestionReward(Long rewardId, Long memberId, BigDecimal money, Date createdAt, Date updatedAt, Byte status, Long questionId) {
        super(rewardId, memberId);
        this.money = money;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
        this.questionId = questionId;
    }

    public QuestionReward() {
        super();
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }
}