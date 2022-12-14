package privatBank;

import java.math.BigDecimal;

public class RateResponseDto {
    private Currency currencyFrom;
    private Currency currencyTo;
    private BigDecimal currencyBuy;
    private BigDecimal currnecySale;

    @Override
    public String toString() {
        return "Курс валют у ПриватБанк " +
                currencyFrom + "/" + currencyTo + "\n" +
                "Продаж " + currnecySale + "\n" +
                "Купівля " + currencyBuy;
    }



    public Currency getCurrencyFrom() {
        return currencyFrom;
    }

    public void setCurrencyFrom(Currency currencyFrom) {
        this.currencyFrom = currencyFrom;
    }

    public Currency getCurrencyTo() {
        return currencyTo;
    }

    public void setCurrencyTo(Currency currencyTo) {
        this.currencyTo = currencyTo;
    }

    public BigDecimal getCurrencyBuy() {
        return currencyBuy;
    }

    public void setCurrencyBuy(BigDecimal currencyBuy) {
        this.currencyBuy = currencyBuy;
    }

    public BigDecimal getCurrnecySale() {
        return currnecySale;
    }

    public void setCurrnecySale(BigDecimal currnecySale) {
        this.currnecySale = currnecySale;
    }
}
