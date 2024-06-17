package com.cookandroid.android_project1;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiResponse {
    @SerializedName("header")
    private Header header;

    @SerializedName("body")
    private Body body;

    public Header getHeader() {
        return header;
    }

    public Body getBody() {
        return body;
    }

    public static class Header {
        @SerializedName("resultCode")
        private String resultCode;

        @SerializedName("resultMsg")
        private String resultMsg;

        public String getResultCode() {
            return resultCode;
        }

        public String getResultMsg() {
            return resultMsg;
        }
    }

    public static class Body {
        @SerializedName("items")
        private List<DrugItem> items;

        @SerializedName("pageNo")
        private int pageNo;

        @SerializedName("totalCount")
        private int totalCount;

        @SerializedName("numOfRows")
        private int numOfRows;

        public List<DrugItem> getItems() {
            return items;
        }

        public int getPageNo() {
            return pageNo;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public int getNumOfRows() {
            return numOfRows;
        }
    }

    @Entity(tableName = "drug_items")
    public static class DrugItem {
        @PrimaryKey(autoGenerate = true)
        private int id;
        private boolean isFavorite;

        public boolean isFavorite() {
            return isFavorite;
        }

        public void setFavorite(boolean favorite) {
            isFavorite = favorite;
        }

        @SerializedName("entpName")
        private String entpName;

        @SerializedName("itemName")
        private String itemName;

        @SerializedName("itemSeq")
        private String itemSeq;

        @SerializedName("efcyQesitm")
        private String efcyQesitm;

        @SerializedName("useMethodQesitm")
        private String useMethodQesitm;

        @SerializedName("atpnWarnQesitm")
        private String atpnWarnQesitm;

        @SerializedName("atpnQesitm")
        private String atpnQesitm;

        @SerializedName("intrcQesitm")
        private String intrcQesitm;

        @SerializedName("seQesitm")
        private String seQesitm;

        @SerializedName("depositMethodQesitm")
        private String depositMethodQesitm;

        @SerializedName("openDe")
        private String openDe;

        @SerializedName("updateDe")
        private String updateDe;

        @SerializedName("itemImage")
        private String itemImage;

        @SerializedName("bizrno")
        private String bizrno;

        public String getEntpName() {
            return entpName;
        }

        public String getItemName() {
            return itemName;
        }

        public String getItemSeq() {
            return itemSeq;
        }

        public String getEfcyQesitm() {
            return efcyQesitm;
        }

        public String getUseMethodQesitm() {
            return useMethodQesitm;
        }

        public String getAtpnWarnQesitm() {
            return atpnWarnQesitm;
        }

        public String getAtpnQesitm() {
            return atpnQesitm;
        }

        public String getIntrcQesitm() {
            return intrcQesitm;
        }

        public String getSeQesitm() {
            return seQesitm;
        }

        public String getDepositMethodQesitm() {
            return depositMethodQesitm;
        }

        public String getOpenDe() {
            return openDe;
        }

        public String getUpdateDe() {
            return updateDe;
        }

        public String getItemImage() {
            return itemImage;
        }

        public String getBizrno() {
            return bizrno;
        }


    }

}
