import com.microsoft.playwright.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LaunchBrowser {
    public static void main(String[] args) {

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );
        Page page = browser.newPage();
        page.navigate("https://ecommerce-playground.lambdatest.io/");
        Locator myAccount = page.locator("//a[contains(.,'My account')][@role='button']");
        myAccount.hover();
//        page.click("//a[contains(., 'Login')]");
        Locator login = page.locator("//a[contains(., 'Login')]");
        login.click();
        assertThat(page).hasTitle("Account Login");
        page.getByPlaceholder("E-Mail Address").fill("testing300bucks@gmail.com");
        page.getByPlaceholder("Password").fill("qweqwe");
        page.locator("//input[@value='Login']").click();
        assertThat(page).hasTitle("My Accunt");
        page.close();
        browser.close();
        playwright.close();

    }
}
