package day4;

import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.SelectOption;

import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class InteractWithDropdowns {
    public static void main(String[] args) {
        String selectURL = "https://www.lambdatest.com/selenium-playground/select-dropdown-demo";
        String jqueryURL = "https://www.lambdatest.com/selenium-playground/jquery-dropdown-search-demo";

        Page page = Playwright.create().chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        ).newPage();
        page.navigate(selectURL);

        Locator dayLocator = page.locator("select#select-demo");

        //  select by value
        dayLocator.selectOption("Wednesday");
        Locator result = page.locator("p.selected-value");
        assertThat(result).containsText("Wednesday");

        //  select by label
        dayLocator.selectOption(new SelectOption().setValue("Friday"));
        System.out.println(result.textContent());
        assertThat(result).containsText("Friday");

        //  select by index
        dayLocator.selectOption(new SelectOption().setIndex(2));

        //  multiple select
        Locator stateLocator = page.locator("select[name='States']");
        stateLocator.selectOption(new String[] {"New Jersey", "Ohio"});
//        page.locator("button#printMe").click();
//        page.locator("button#printAll").click();

        Locator options = stateLocator.locator("option");
        System.out.println(options.count());
        List<String> allInnerTexts = options.allInnerTexts();
        allInnerTexts.forEach(option -> System.out.println(option));

        //  jquery
        page.navigate(jqueryURL);
        Locator country = page.locator("span.select2-container").first();
        country.click();
        Locator list = page.locator("span.select2-results ul li",
                new Page.LocatorOptions().setHasText("India"));

        list.click();
        Locator files = page.locator("select[name='files']");
        files.selectOption("Ruby");



    }
}
