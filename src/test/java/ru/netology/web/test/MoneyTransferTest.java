package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {
    int sum;
    int Balance1;
    int Balance2;

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
        Balance1 = dashboardPage.getCardBalance(dashboardPage.card1);
        Balance2 = dashboardPage.getCardBalance(dashboardPage.card2);
    }

    @Test
    void shouldTransferMoneyFromFirstToSecondCard() {
        sum = 500;
        val transfer = dashboardPage.clickCard(dashboardPage.card2);
        val dashboardPage2 = transfer.validTransfer(Integer.toString(sum), String.valueOf(DataHelper.getCardFirst()));
        endBalance1 = dashboardPage2.getCardBalance(dashboardPage2.card1);
        endBalance2 = dashboardPage2.getCardBalance(dashboardPage2.card2);
        assertEquals(Balance1 - sum, endBalance1);
        assertEquals(Balance2 + sum, endBalance2);
    }

    @Test
    void shouldTransferMoneyFromSecondToFirstCard() {
        sum = 500;
        val transfer = dashboardPage.clickCard(dashboardPage.card1);
        val dashboardPage2 = transfer.validTransfer(Integer.toString(sum), String.valueOf(DataHelper.getCardSecond()));
        endBalance1 = dashboardPage2.getCardBalance(dashboardPage2.card1);
        endBalance2 = dashboardPage2.getCardBalance(dashboardPage2.card2);
        assertEquals(Balance1 + sum, endBalance1);
        assertEquals(Balance2 - sum, endBalance2);
    }
}
