package com.makcent.pojo;

import java.util.Date;

public class QuestionAnswer {
    private Integer answerId;

    private Byte status;

    private Date createdAt;

    private Date updatedAt;

    private Long questionId;

    private Long memberId;

    private String answer;

    public QuestionAnswer(Integer answerId, Byte status, Date createdAt, Date updatedAt, Long questionId, Long memberId, String answer) {
        this.answerId = answerId;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.questionId = questionId;
        this.memberId = memberId;
        this.answer = answer;
    }

    public QuestionAnswer() {
        super();
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }
}