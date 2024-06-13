package day3;

import com.microsoft.playwright.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class InteractWithInputs {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.firefox().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );
        Page page = browser.newPage();
//        page.navigate("https://www.lambdatest.com/selenium-playground/simple-form-demo");
//        page.locator("input#user-message").fill("Hey Tester");
//        page.locator("id=showInput").click();
//        String message = page.locator("#message").textContent();
//        System.out.println(message);
//        assertThat(page.locator("#message")).hasText("Hey Tester");
//
//        // type vs fill
//
//        page.navigate("https://www.lambdatest.com/selenium-playground/generate-file-to-download-demo");
//        page.locator("#textbox").fill("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fri");

//        get input values
        page.navigate("https://letcode.in/edit");
        String inputValue = page.locator("#getMe").inputValue();
        System.out.println(inputValue);

        String placeholderValue = page.locator("#fullName").getAttribute("placeholder");
        System.out.println(placeholderValue);

        Locator fullNamelocator = page.locator("#fullName");
        assertThat(fullNamelocator).hasAttribute("placeholder", "Enter first & last name");

        page.locator("id=clearMe").clear();
//        page.navigate("https://www.lambdatest.com/selenium-playground/checkbox-demo");
//        Locator isAge = page.locator("#isAgeSelected");
//        assertThat(isAge).isChecked();
//        isAge.check();
//        assertThat(isAge).isChecked();


        playwright.close();
    }
}
