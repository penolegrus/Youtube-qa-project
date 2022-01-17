package api.kuCoinApi;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class StreamApiExamples {

    public List<TickerData> getTickers(){
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get("https://api.kucoin.com/api/v1/market/allTickers")
                .then()
                .extract().jsonPath().getList("data.ticker",TickerData.class);
    }
    @Test
    public void checkCrypto(){
       List<TickerData> usdTickers = getTickers().stream().filter(x->x.getSymbol().endsWith("USDT")).collect(Collectors.toList());
       Assertions.assertTrue(usdTickers.stream().allMatch(x->x.getSymbol().endsWith("USDT")));
       int a = 0;
    }

    @Test
    public void sortHighToLow(){
        List<TickerData> highToLow = getTickers().stream().filter(x->x.getSymbol().endsWith("USDT")).sorted(new Comparator<TickerData>() {
            @Override
            public int compare(TickerData o1, TickerData o2) {
                return o2.getChangeRate().compareTo(o1.getChangeRate());
            }
        }).collect(Collectors.toList());
        List<TickerData> top10 = highToLow.stream().limit(10).collect(Collectors.toList());
        Assertions.assertEquals(top10.get(0).getSymbol(),"BTC-USDT");
        int a = 9;
    }

    @Test
    public void sortLowToHigh(){
        List<TickerData> lowToHight = getTickers().stream().filter(x->x.getSymbol().endsWith("USDT"))
                .sorted(new TickerComparatorLow()).limit(10).collect(Collectors.toList());
        int a =0;
    }

    @Test
    public void map(){
        Map<String, Float> usd = new HashMap<>();
        List<String> lowerCases = getTickers().stream().map(x->x.getSymbol().toLowerCase()).collect(Collectors.toList());
        getTickers().forEach(x->usd.put(x.getSymbol(),Float.parseFloat(x.getChangeRate())));
        List<TickerShort> shortList = new ArrayList<>();
        getTickers().forEach(x->shortList.add(new TickerShort(x.getSymbol(),Float.parseFloat(x.getChangeRate()))));
        int a = 0;
        }
}
