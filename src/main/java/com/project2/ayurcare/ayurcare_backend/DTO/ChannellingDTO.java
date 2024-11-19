package com.project2.ayurcare.ayurcare_backend.DTO;

public class ChannellingDTO {
    private Integer channellingId;
    private String fee;

    public ChannellingDTO() {}

    public ChannellingDTO(Integer channellingId, String fee) {
        this.channellingId = channellingId;
        this.fee = fee;
    }

    public Integer getChannellingId() {
        return channellingId;
    }

    public void setChannellingId(Integer channellingId) {
        this.channellingId = channellingId;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }
}
