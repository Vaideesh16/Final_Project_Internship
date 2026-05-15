package pagedObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HotelSearchPage {

    private static final Logger log = LogManager.getLogger(HotelSearchPage.class);

    WebDriver driver;

    @FindBy(xpath = "//button[@aria-label='Dismiss sign-in info.']")
    private WebElement dismissPopup;

    @FindBy(xpath = "//div[@data-testid='destination-container']")
    private WebElement destinationContainer;

    @FindBy(id = ":R55amr5:")
    private WebElement destinationTextBox;

    @FindBy(xpath = "//div[text()='Nairobi']/following-sibling::div[text()='Kericho, Kericho, Kenya']")
    private WebElement nairobiOption;

    @FindBy(xpath = "//span[@data-testid='searchbox-form-button-icon']")
    private WebElement personsSelector;

    @FindBy(xpath = "//div[@class='c5aae0350e']/label[@for='group_adults']/parent::div/following-sibling::div/button[2]")
    private WebElement adultsPlusButton;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement searchButton;

    @FindBy(xpath = "//button[@data-testid='sorters-dropdown-trigger']")
    private WebElement sortDropdown;

    @FindBy(xpath = "//button[@aria-label='Top reviewed']")
    private WebElement sortTopReviewed;

    @FindBy(xpath = "(//span[@data-testid='filters-group-expand'])[1]")
    private WebElement expandFacilities;

    @FindBy(xpath = "//div[text()='Free Wifi']")
    private WebElement freeWifiOption;

    // Stores the names of the hotels
    @FindBy(xpath = "//div[@data-testid='title']")
    private List<WebElement> hotelNames;

    // Stores the distances in the list
    @FindBy(xpath = "//button[@data-testid='distance']")
    private List<WebElement> distances;

    // Stores the prices in the List
    @FindBy(xpath = "//span[@data-testid='price-and-discounted-price']")
    private List<WebElement> prices;

    // Constructor
    // driver comes from the step definitions file
    public HotelSearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        log.info("HotelSearchPage initialized");
    }

    public void closePopup() {
        dismissPopup.click();
        log.info("Sign-in popup dismissed");
    }

    public void enterDestination(String city) {
        destinationContainer.click();
        destinationTextBox.sendKeys(city);
        log.info("Entered destination: " + city);
    }

    public void selectNairobiOption() {
        nairobiOption.click();
        log.info("Nairobi option selected from dropdown");
    }

    public void selectCheckInDate(LocalDate date) {
        driver.findElement(By.xpath(String.format("//span[@data-date='%s']", date))).click();
        log.info("Check-in date selected: " + date);
    }

    public void selectCheckOutDate(LocalDate date) {
        driver.findElement(By.xpath(String.format("//span[@data-date='%s']", date))).click();
        log.info("Check-out date selected: " + date);
    }

    public void openPersonsSelector() {
        personsSelector.click();
        log.info("Persons selector opened");
    }

    public void setAdults(int count) {
        for (int i = 2; i < count; i++) adultsPlusButton.click();
        log.info("Adults set to: " + count);
    }

    public void clickSearch() {
        searchButton.click();
        log.info("Search button clicked");
    }

    public void sortByTopReviewed() {
        sortDropdown.click();
        sortTopReviewed.click();
        log.info("Sorted by Top Reviewed");
    }

    public void filterFreeWifi() {
        expandFacilities.click();
        freeWifiOption.click();
        log.info("Free Wifi filter applied");
    }

    // Logs the results to console
    public void printHotelResults() {
        log.info("Printing hotel results — total found: " + hotelNames.size());
        System.out.println("--------------------------------------------------");
        System.out.println("  Hotel Name \tDistance\tPrice");
        System.out.println("--------------------------------------------------");
        // storing the hotel names
        for (int i = 0; i < hotelNames.size(); i++) {
            String name = hotelNames.get(i).getText();
            String distance = distances.get(i).getText();
            String price = prices.get(i).getText();

            // If hotel name is too long, then print the smaller name in console
            String shortName = name.length() > 10 ? name.substring(0, 10) : name;
            System.out.println((i + 1) + ". " + shortName + "\t" + distance + "\t" + price);
            log.info("Hotel " + (i + 1) + ": " + name + " | " + distance + " | " + price);
        }
    }

    // Stores data in List for Excel
    public List<String[]> getHotelData() {
        List<String[]> data = new ArrayList<>();
        for (int i = 0; i < hotelNames.size(); i++) {
            String name = hotelNames.get(i).getText();
            String distance = distances.get(i).getText();
            String price = prices.get(i).getText();
            data.add(new String[]{name, distance, price});
            log.debug("Extracted hotel: " + name + " | " + distance + " | " + price);
        }
        log.info("Total hotels: " + data.size());
        return data;
    }
}
