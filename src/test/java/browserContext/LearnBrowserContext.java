package browserContext;

import com.microsoft.playwright.*;
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


    }
}
