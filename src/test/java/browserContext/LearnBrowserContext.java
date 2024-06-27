package browserContext;

import com.microsoft.playwright.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import com.microsoft.playwright.options.AriaRole;

public class LearnBrowserContext {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = browser.newContext();
        Page page = context.newPage();
        page.navigate("https://ecommerce-playground.lambdatest.io/index.php?route=account/login");
        page.getByLabel("E-Mail Address").type("testing300bucks@gmail.com");
        page.getByLabel("Password").type("qweqwe");
        page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Login")).click();
                Locator myAccount = page.getByText("Edit your account information");
        assertThat(myAccount).isVisible();

        Page newTab = page.context().newPage();
        newTab.navigate("https://ecommerce-playground.lambdatest.io/index.php?route=account/account");
        assertThat(myAccount).isVisible();
        newTab.close();
        context.close();

        BrowserContext context2 = browser.newContext();
        Page userPage = context2.newPage();
        userPage.navigate("https://ecommerce-playground.lambdatest.io/index.php?route=account/account");

        BrowserType firefox = playwright.firefox();
        firefox.launch(new BrowserType.LaunchOptions().setHeadless(false)).newPage();

        playwright.close();

    }
}
