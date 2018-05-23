package com.makcent.pojo;

import java.util.Date;

public class Question {
    private Long questionId;

    private String title;

    private String question;

    private String tag;

    private Date createdAt;

    private Date updatedAt;

    private Byte status;

    private Long memberId;

    private Integer categoryId;

    private Integer columnId;

    public Question(Long questionId, String title, String question, String tag, Date createdAt, Date updatedAt, Byte status, Long memberId, Integer categoryId, Integer columnId) {
        this.questionId = questionId;
        this.title = title;
        this.question = question;
        this.tag = tag;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
        this.memberId = memberId;
        this.categoryId = categoryId;
        this.columnId = columnId;
    }

    public Question() {
        super();
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question == null ? null : question.trim();
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
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

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getColumnId() {
        return columnId;
    }

    public void setColumnId(Integer columnId) {
        this.columnId = columnId;
    }
}