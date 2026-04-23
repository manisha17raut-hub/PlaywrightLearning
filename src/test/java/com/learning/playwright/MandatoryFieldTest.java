package com.learning.playwright;

import com.microsoft.playwright.Page;

import com.microsoft.playwright.junit.UsePlaywright;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@UsePlaywright(BrowserLaunch.class)
public class MandatoryFieldTest {

    @BeforeEach
    void openPage(Page page){
        page.navigate("https://practicesoftwaretesting.com/contact");
    }

    @DisplayName("Mandatory Fields")
    @Test
    void mandatoryFields(Page page){
        var fieldFirstName = page.getByLabel("First name");
        var fieldLastName = page.getByLabel("Last name");
        var email = page.getByLabel("Email address");
        var subject =page.getByLabel("Subject");
        var message =page.getByLabel("Message");
        var sendButton=page.getByText("Send");

        sendButton.click();
        var errMsg = page.getByRole(AriaRole.ALERT).getByText("First name is required");
       assertThat(errMsg).isVisible();
    }

    @DisplayName("Checking Mandatory fields using Parameterized")
    @ParameterizedTest
    @ValueSource(strings ={"First name","Last name","email","Message"})

    void mandatoryFieldsCheck(String fieldName, Page page){
        var fieldFirstName = page.getByLabel("First name");
        var fieldLastName = page.getByLabel("Last name");
        var email = page.getByLabel("Email address");
        var subject =page.getByLabel("Subject");
        var message =page.getByLabel("Message");
        var sendButton = page.getByText("Send");

       //filling the values
        fieldFirstName.fill("Manisha");
        fieldLastName.fill("raut");
        email.fill("raut.manisha@example.com");

        message.fill("hello hello");
        subject.selectOption("Warranty");

        //clear one of the fields
        page.getByLabel(fieldName).clear();
      

        sendButton.click();

        //check the error messages present in the fields
        var errMessage = page.getByRole(AriaRole.ALERT).getByText(fieldName + " is required");

        assertThat(errMessage).isVisible();

    }

}
