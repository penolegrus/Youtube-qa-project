package sms;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class NumberPage {
    private SelenideElement accountBalanceText = $x("//a[@id='balansUser']");
    private SelenideElement phoneIdText = $x("//table[@id='getNumberTable']//tr[2]/td[1]");
    private SelenideElement phoneNumberText = $x("//div[@class='input-group']//input");

    public String getAccountBalance(){
        return accountBalanceText.getText();
    }

    public String getPhoneId(){
        return phoneIdText.getText();
    }

    public String getPhoneNumber(){
        return phoneNumberText.getValue();
    }
}
