package com.example.eshopping;

public class GetSumOfTotalPriceModel {
    private Double PriceSum;

    public GetSumOfTotalPriceModel(Double priceSum) {
        PriceSum = priceSum;
    }

    public Double getPriceSum() {
        return PriceSum;
    }

    public void setPriceSum(Double priceSum) {
        PriceSum = priceSum;
    }
}
