package com.makcent.pojo;

public class QuestionRewardKey {
    private Long rewardId;

    private Long memberId;

    public QuestionRewardKey(Long rewardId, Long memberId) {
        this.rewardId = rewardId;
        this.memberId = memberId;
    }

    public QuestionRewardKey() {
        super();
    }

    public Long getRewardId() {
        return rewardId;
    }

    public void setRewardId(Long rewardId) {
        this.rewardId = rewardId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
}