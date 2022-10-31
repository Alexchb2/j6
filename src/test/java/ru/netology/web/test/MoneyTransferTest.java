package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.Transfer;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {
    int sum;
    int balance1;
    int balance2;

    int endBalance1;
    int endBalance2;

    DashboardPage dashboardPage;

    @BeforeEach
    void SetUp() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        dashboardPage = verificationPage.validVerify(verificationCode);
        balance1 = dashboardPage.getFirstCardBalance();
        balance2 = dashboardPage.getSecondCardBalance();
    }

    @Test
    void shouldTransferMoneyFromFirstToSecondCard() {
        sum = 500;
        val transfer = dashboardPage.clickSecondCard();
        val dashboardPage2 = transfer.validTransfer(Integer.toString(sum), String.valueOf(DataHelper.getCardFirst()));
        endBalance1 = dashboardPage2.getFirstCardBalance();
        endBalance2 = dashboardPage2.getSecondCardBalance();
        assertEquals(balance1 - sum, endBalance1);
        assertEquals(balance2 + sum, endBalance2);
    }

    @Test
    void shouldTransferMoneyFromSecondToFirstCard() {
        sum = 500;
        val transfer = dashboardPage.clickFirstCard();
        val dashboardPage2 = transfer.validTransfer(Integer.toString(sum), String.valueOf(DataHelper.getCardSecond()));
        endBalance1 = dashboardPage2.getFirstCardBalance();
        endBalance2 = dashboardPage2.getSecondCardBalance();
        assertEquals(balance1 + sum, endBalance1);
        assertEquals(balance2 - sum, endBalance2);
    }

    @Test
    void shouldTransferMoneyLargeLimitAmounts() {
        sum = balance2 + 1;
        val transfer = dashboardPage.clickFirstCard();
        val dashboardPage2 = transfer.validTransfer(Integer.toString(sum), String.valueOf(DataHelper.getCardSecond()));
        transfer.findErrorMessage("Сумма перевода превышает сумму остатка денег на счёте.");
        endBalance1 = dashboardPage2.getFirstCardBalance();
        endBalance2 = dashboardPage2.getSecondCardBalance();
        assertEquals(balance1, endBalance1);
        assertEquals(balance2, endBalance2);
    }
}
