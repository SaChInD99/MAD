package com.example.project;

public class CardPay {


    private float CardNum;
    private String SeqNum;
    private String ExDate;
    private String phnNum;
    private String crdName;

    public CardPay() {
    }

    public float getCardNum() {
        return CardNum;
    }

    public void setCardNum(float cardNum) {
        CardNum = cardNum;
    }

    public String getSeqNum() {
        return SeqNum;
    }

    public void setSeqNum(String seqNum) {
        SeqNum = seqNum;
    }

    public String getExDate() {
        return ExDate;
    }

    public void setExDate(String exDate) {
        ExDate = exDate;
    }

    public String getPhnNum() {
        return phnNum;
    }

    public void setPhnNum(String phnNum) {
        this.phnNum = phnNum;
    }

    public String getCrdName() {
        return crdName;
    }

    public void setCrdName(String crdName) {
        this.crdName = crdName;
    }
}
