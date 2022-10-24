package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private ElementsCollection topUpButtons = $$("button[data-test-id=action-deposit]");
    private static SelenideElement card1 = $("div[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']");
    private static SelenideElement card2 = $("div[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']");


    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public Transfer clickFirstCard() {

        card1.find("button[data-test-id=action-deposit]").click();
        return new Transfer();
    }
    public Transfer clickSecondCard() {

        card2.find("button[data-test-id=action-deposit]").click();
        return new Transfer();
    }

    public int getFirstCardBalance() {
        String[] text = card1.innerText().split(" ");
        return Integer.parseInt(text[5]);
    }
    public int getSecondCardBalance() {
        String[] text = card2.innerText().split(" ");
        return Integer.parseInt(text[5]);
    }
}
