package ru.netology.web;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class AppDeliveryTest {


    @Test
    void shouldRegister() {
        open("http://localhost:9999");
        $("[autocomplete='off']").setValue("Санкт-Петербург");
        $("[placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.SHIFT,Keys.HOME),Keys.BACK_SPACE);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 3);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String inputDate = dateFormat.format(calendar.getTime());
        $("[placeholder='Дата встречи']").setValue(inputDate);
        $("[name='name']").setValue("Валентина Терешкова");
        $("[name='phone']").setValue("+79210000000");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id = 'notification'] .notification__content").shouldBe(visible, Duration.ofMillis(15000)).shouldHave(exactText("Встреча успешно забронирована на " + inputDate));
        $("div:nth-child(2) button").click();
        $(byText("Успешно")).shouldBe(disappear, Duration.ofSeconds(4));
    }

    @Test
    void shouldRegister2() {
        open("http://localhost:9999");
        $("[autocomplete='off']").setValue("Ка");
        $$("[class='menu-item__control']").find(exactText("Казань")).click();
        $("[placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.SHIFT,Keys.HOME),Keys.BACK_SPACE);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String inputDate = dateFormat.format(calendar.getTime());
        $("[placeholder='Дата встречи']").setValue(inputDate);
        $("[name='name']").setValue("Валентина Терешкова");
        $("[name='phone']").setValue("+79210000000");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id = 'notification'] .notification__content").shouldBe(visible, Duration.ofMillis(15000)).shouldHave(exactText("Встреча успешно забронирована на " + inputDate));
        $("div:nth-child(2) button").click();
        $(byText("Успешно")).shouldBe(disappear, Duration.ofSeconds(4));
    }




}
