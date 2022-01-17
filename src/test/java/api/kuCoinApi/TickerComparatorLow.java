package api.kuCoinApi;

import java.util.Comparator;

public class TickerComparatorLow implements Comparator<TickerData> {
    @Override
    public int compare(TickerData o1, TickerData o2) {
        float result = Float.compare(Float.parseFloat(o1.getChangeRate()), Float.parseFloat(o2.getChangeRate()));
        return (int) result;
    }
}
