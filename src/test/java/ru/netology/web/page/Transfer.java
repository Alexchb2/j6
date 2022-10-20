package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static java.lang.Thread.sleep;

public class Transfer {
    private SelenideElement amountField = $("[data-test-id=amount] input");
    private SelenideElement fromField = $("[data-test-id=from] input");
    private SelenideElement amountButton = $("[data-test-id=action-transfer]");


    public DashboardPage validTransfer(String sum, String cardNum) {
        amountField.setValue(sum);
        fromField.sendKeys(cardNum);
        amountButton.click();
        return new DashboardPage();
    }
}
