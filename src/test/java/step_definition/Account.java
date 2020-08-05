package step_definition;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import helper.TestBase;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.support.ui.Select;
import java.util.List;
import java.util.Random;


public class Account extends TestBase {
    WebDriverWait wait = new WebDriverWait(driver, 30);

    private By bankingLink = By.xpath("//ul/li[6]/a/figure");
    private By managerLink = By.xpath("//button[@ng-click='manager()']");
    private By addCustomerLink = By.xpath("//button[@ng-click='addCust()']");
    private By firstName = By.xpath("//input[@placeholder='First Name']");
    private By lastName = By.xpath("//input[@placeholder='Last Name']");
    private By postCode = By.xpath("//input[@placeholder='Post Code']");
    private By addCustomerBtn = By.xpath("//button[@type='submit']");
    private By customersTab = By.xpath("//button[@ng-click='showCust()']");
    private By customerName = By.xpath("//td[contains(text(),'Nada')]");
    private By openAccountTab = By.xpath("//button[@ng-click='openAccount()']");
    private By customerDrpDown = By.xpath("//*[@id='userSelect']");
    private By currencyDrpDown = By.xpath("//*[@id='currency']");
    private By processBtn = By.xpath("//button[contains(text(),'Process')]");
    private By customerID = By.xpath("//table/tbody/tr[6]/td[4]/span");
    private By deleteBtn = By.xpath("(//button[@ng-click='deleteCust(cust)'])[6]");

    private String id;


    @Given("^Navigate the URL$")
    public void navigateTheURL() {
        driver.navigate().to("http://www.way2automation.com/protractor-angularjs-practice-website.html");
    }


    @And("^Click on banking link$")
    public void clickOnBankingLink() {
        String winHandleBefore = driver.getWindowHandle();
        driver.findElement(bankingLink).click();
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);

        }
    }

    @And("^Click on bank manager login$")
    public void clickOnBankManagerLogin() {
        driver.findElement(managerLink).click();
    }


    @And("^Click on add customer$")
    public void clickOnAddCustomer() {
        driver.findElement(addCustomerLink).click();

    }

    @And("^fill all the required fields Name \"([^\"]*)\" and \"([^\"]*)\" and Code \"([^\"]*)\" and get the id from the alert$")
    public String fillAllTheRequiredFieldsNameAndAndCodeAndGetTheIdFromTheAlert(String fName, String lName, String code){
        driver.findElement(firstName).sendKeys(fName);
        driver.findElement(lastName).sendKeys(lName);
        driver.findElement(postCode).sendKeys(code);
        driver.findElement(addCustomerBtn).click();
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String ID = alert.getText();
        alert.accept();
        return ID;

    }
    @And("^Click on customer tab$")
    public void clickOnCustomerTab() {
        driver.findElement(customersTab).click();
    }

    @Then("^Assert that the user details are same as entered in the form$")
    public void assertThatTheUserDetailsAreSameAsEnteredInTheForm() {
        String customer = driver.findElement(customerName).getText();
        Assert.assertEquals(customer, "Nada");
    }


    @Given("^Click on Open Account$")
    public void clickOnOpenAccount() {
        driver.findElement(openAccountTab).click();
    }

    @And("^Select the customer entered$")
    public void selectTheCustomerEnteredInScenario() {
        new Select(driver.findElement(customerDrpDown)).selectByVisibleText("Nada Hassan");

    }

    @And("^Select a random currency$")
    public void selectARandomCurrencyEachRunShouldHaveRandomSelection() {
        WebElement drpDwnList = driver.findElement(currencyDrpDown);
        Select objSel = new Select(drpDwnList);
        List<WebElement> weblist = objSel.getOptions();
        int iCnt = weblist.size();
        Random num = new Random();
        int iSelect = num.nextInt(iCnt);
        objSel.selectByIndex(iSelect);

    }

    @And("^Submit account and get the id from the alert$")
    public String submitAccount() {
        driver.findElement(processBtn).click();
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String id = alert.getText();
        alert.accept();
        this.setID(id);
        return id;

    }

    private void setID(String id){
        this.id=id;
    }

    private String getID(){
        return this.id;
    }

    @And("^Assert that the Account Number retrieved from the alert is displayed in its cell for the created customer$")
    public void assertThatTheAccountNumberRetrievedFromTheAlertIsDisplayedInItsCellForTheCreatedCustomer() {
        String customer = driver.findElement(customerID).getText();
        Assert.assertTrue(this.getID().contains(customer));

    }

    @And("^Delete Customer added$")
    public void deleteCustomerAdded() {
        driver.findElement(deleteBtn).click();
    }


    @Then("^Assert that customer is deleted is \"([^\"]*)\"$")
    public void assertThatCustomerIsDeletedIs(String name) {
        String customer = driver.findElement(By.xpath("//div[@class='marTop ng-scope']")).getText();
        Assert.assertFalse(customer.contains(name));

    }


}
