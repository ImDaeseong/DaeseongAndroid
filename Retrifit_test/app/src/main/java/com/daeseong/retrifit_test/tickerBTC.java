package com.daeseong.retrifit_test;

import com.google.gson.annotations.SerializedName;

public class tickerBTC {

    @SerializedName("status")
    private String status;

    @SerializedName("data")
    private Data data;

    // Getter 메서드들
    public String getStatus() {
        return status;
    }

    public Data getData() {
        return data;
    }

    public static class Data {
        @SerializedName("opening_price")
        private String openingPrice;

        @SerializedName("closing_price")
        private String closingPrice;

        @SerializedName("min_price")
        private String minPrice;

        @SerializedName("max_price")
        private String maxPrice;

        @SerializedName("units_traded")
        private String unitsTraded;

        @SerializedName("acc_trade_value")
        private String accTradeValue;

        @SerializedName("prev_closing_price")
        private String prevClosingPrice;

        @SerializedName("units_traded_24H")
        private String unitsTraded24H;

        @SerializedName("acc_trade_value_24H")
        private String accTradeValue24H;

        @SerializedName("fluctate_24H")
        private String fluctate24H;

        @SerializedName("fluctate_rate_24H")
        private String fluctateRate24H;

        @SerializedName("date")
        private String date;

        // Getter 메서드들
        public String getOpeningPrice() {
            return openingPrice;
        }

        public String getClosingPrice() {
            return closingPrice;
        }

        public String getMinPrice() {
            return minPrice;
        }

        public String getMaxPrice() {
            return maxPrice;
        }

        public String getUnitsTraded() {
            return unitsTraded;
        }

        public String getAccTradeValue() {
            return accTradeValue;
        }

        public String getPrevClosingPrice() {
            return prevClosingPrice;
        }

        public String getUnitsTraded24H() {
            return unitsTraded24H;
        }

        public String getAccTradeValue24H() {
            return accTradeValue24H;
        }

        public String getFluctate24H() {
            return fluctate24H;
        }

        public String getFluctateRate24H() {
            return fluctateRate24H;
        }

        public String getDate() {
            return date;
        }
    }
}

