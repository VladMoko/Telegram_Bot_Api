package privatBank;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

public class PrivatCurrenceRateService implements CurrencyRateApiService {

    private String url = "https://api.privatbank.ua/p24api/pubinfo?exchange&json&coursid=11";
    private Gson gson = new Gson();

    //Connect with API Private Bank
    @Override
    public List<RateResponseDto> getCurrency() {
        String text = null;
        try {
            text = Jsoup.connect(url).ignoreContentType(true).get().body().text();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Convert from Private Bank to another bank
        List<PrivatBankCurrencyDto> responseDtos = convertResponse(text);
        return responseDtos.stream()
                .map(item -> {
                    RateResponseDto rateResponseDto = new RateResponseDto();
                    rateResponseDto.setCurrencyTo(item.getCcy());
                    rateResponseDto.setCurrencyFrom(item.getBase_ccy());
                    rateResponseDto.setCurrencyBuy(item.getBuy());
                    rateResponseDto.setCurrnecySale(item.getSale());
                    return rateResponseDto;
                })
                .collect(Collectors.toList());
    }

    //Convert from GSON to List
    private List<PrivatBankCurrencyDto> convertResponse(String response) {
        Type type = TypeToken
                .getParameterized(List.class, PrivatBankCurrencyDto.class)
                .getType();
        List<PrivatBankCurrencyDto> currencyDtos = gson.fromJson(response, type);
        return currencyDtos;
    }
}
