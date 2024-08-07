package phonebook_tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import phonebook.data.ContactData;
import phonebook.model.Contact;
import phonebook.utils.DataProviders;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AddContactTests extends TestBase {
  private static final String CONTACT_NAME = "TestName";

  @BeforeMethod
  public void ensurePrecondition() {
    if (!app.getUserHelper().isLoginLinkPresent()) {
      app.getUserHelper().clickOnSignOutButton();
    }
    app.getUserHelper().loginExistedUserPositive();
  }

  @AfterMethod(enabled = true)
  public void postCondition() {
    app.getContactHelper().deleteOneContactOnly();
  }

  @Test(invocationCount = 1)
  public void addContactPositiveTest() {
    app.getContactHelper().addNewContactPositiveData(CONTACT_NAME);
    //! Добавляем проверку, что контакт появился
    Assert.assertTrue(app.getContactHelper().isContactAdded(CONTACT_NAME));
  }

  @Test
  public void addContactWithOutDescription() {
    app.getContactHelper().clickADDLink();
    app.getContactHelper().fillAddContactForm(new Contact().setName("TestName").setLastName("TestLastName").setPhone("1234567890").setEmail("admin@gmail.com").setAddress("Germany, Berlin"));
    app.getContactHelper().clickOnSAVEContactButton();
    Assert.assertTrue(app.getContactHelper().isContactAdded(CONTACT_NAME));
  }

  @Test
  public void addContactFromContactData() {
    app.getContactHelper().clickADDLink();
    app.getContactHelper().fillAddContactForm(new Contact().setName(ContactData.NAME).setLastName(ContactData.LAST_NAME).setPhone(ContactData.PHONE).setEmail(ContactData.EMAIL).setAddress(ContactData.ADDRESS).setDescription(ContactData.DESC));
    app.getContactHelper().clickOnSAVEContactButton();
    Assert.assertTrue(app.getContactHelper().isContactAdded(ContactData.NAME));
  }

  @Test(dataProvider = "addContactString", dataProviderClass = DataProviders.class)
  public void addNewContactPositiveTestFromDataProvider1(String name, String lastName, String phone, String email, String address, String description) {
    app.getContactHelper().clickADDLink();
    app.getContactHelper().fillAddContactForm(new Contact().setName(name).setLastName(lastName).setPhone(phone).setEmail(email).setAddress(address).setDescription(description));
    app.getContactHelper().clickOnSAVEContactButton();
    Assert.assertTrue(app.getContactHelper().isContactAdded(name));
  }

  @Test(dataProvider = "addContactObject", dataProviderClass = DataProviders.class)
  public void addContactStringTest(Contact contact) {
    app.getContactHelper().clickADDLink();
    app.getContactHelper().fillAddContactForm(contact);
    app.getContactHelper().clickOnSAVEContactButton();
    Assert.assertTrue(app.getContactHelper().isContactAdded(contact.getName()));
  }

  @Test(dataProvider = "addContactFromCsv", dataProviderClass = DataProviders.class)
  public void addContactFromScvTest(Contact contact) {
    app.getContactHelper().clickADDLink();
    app.getContactHelper().fillAddContactForm(contact);
    app.getContactHelper().clickOnSAVEContactButton();
    Assert.assertTrue(app.getContactHelper().isContactAdded(contact.getName()));
  }
}
