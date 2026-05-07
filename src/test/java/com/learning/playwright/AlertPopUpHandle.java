package com.learning.playwright;

import com.microsoft.playwright.*;
import com.microsoft.playwright.junit.UsePlaywright;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.Test;


public class AlertPopUpHandle {

    @Test
    public void jsAlertPopupHandle(){
        Playwright playwright = Playwright.create();
       // Browser browser = playwright.chromium().launch();
       Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page =  browser.newPage();

        //handle prompt
        //add listener to the page which will be running in background
        page.onDialog(dialog -> {
            String text =dialog.message();
            System.out.println(text);
            //if we wanted to enter text and accept
            dialog.accept("This is manisha");
        });


        page.navigate("https://the-internet.herokuapp.com/javascript_alerts");

        page.click("//button[text()='Click for JS prompt']");

        //playwright will automatically handle the alert
        String text = page.locator("#result").textContent();
        System.out.println(text);

        page.close();
        browser.close();
        playwright.close();
        }
    }
