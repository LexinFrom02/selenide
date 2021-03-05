import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import org.junit.jupiter.api.BeforeEach;

import com.codeborne.selenide.Selenide.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static java.time.Duration.*;
import static java.util.Calendar.*;


public class DeliveryCardTest {
    String setDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 3);
        return dateFormat.format(calendar.getTime());
    }

    public void setUp() {
        open("http://localhost:9999");
    }

    @Test
    public void shouldAutorizeFormWithValidValues() {
        setUp();
        $("[placeholder='Город']").setValue("Краснодар");
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.DELETE);
        $("[placeholder='Дата встречи']").setValue(setDate());
        $("[name='name']").setValue("Макаров Антон");
        $("[name='phone']").setValue("+79123456790");
        $("[data-test-id='agreement']").click();
        $(withText("Забронировать")).click();
        $(withText("Успешно!")).waitUntil(visible, 13000);
    }
}
