package ru.netology.web;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class AppDeliveryTest {


    @Test
    void shouldRegister() {
        open("http://localhost:9999");
        $("[autocomplete='off']").setValue("Санкт-Петербург");
        $("[placeholder='Дата встречи']").sendKeys(Keys.CONTROL + "a");
        LocalDate date = LocalDate.now().plusDays(3);
        String inputDate = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[placeholder='Дата встречи']").setValue(inputDate);
        $("[name='name']").setValue("Валентина Терешкова");
        $("[name='phone']").setValue("+79210000000");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id = 'notification'] .notification__content").shouldBe(visible, Duration.ofMillis(15000)).shouldHave(exactText("Встреча успешно забронирована на " + inputDate));
        $("[data-test-id='notification']").click();
        $(byText("Успешно")).shouldBe(disappear, Duration.ofSeconds(4));
    }






}
