package day6;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import com.microsoft.playwright.Browser.NewContextOptions;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginLogoutCodegen {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false));
        BrowserContext context = browser.newContext(
                new NewContextOptions().setRecordVideoDir(Paths.get("videos/"))
                        .setRecordVideoSize(new RecordVideoSize(1280, 720))
        );
        Page page = context.newPage();
        page.navigate("https://ecommerce-playground.lambdatest.io/index.php?route=common/home");
        Locator myAccount = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("My account"));
        myAccount.click();
        page.getByPlaceholder("E-Mail Address").fill("testing300bucks@gmail.com");
        page.getByPlaceholder("Password").fill("qweqwe");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Edit your account")).click();
        page.getByPlaceholder("Last Name").fill("R");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continue")).click();
        assertThat(page.locator("#account-account")).containsText("Success: Your account has been successfully updated.");
        myAccount.hover();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Logout").setExact(true)).click();
        assertThat(page.locator("h1")).containsText("Account Logout");
        page.close();
        context.close();
        browser.close();
        playwright.close();
    }
}