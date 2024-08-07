package phonebook_tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import phonebook.data.ContactData;
import phonebook.model.Contact;

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

  @DataProvider
  public Iterator<Object[]> addContactString() {
    List<Object[]> list = new ArrayList<>();
    list.add(new Object[]{"Name1", "LastName1", "1234567891", "admin1@gmail.com", "Germany, Berlin1", "Description 1"});
    list.add(new Object[]{"Name2", "LastName2", "1234567892", "admin2@gmail.com", "Germany, Berlin2", "Description 2"});
    list.add(new Object[]{"Name3", "LastName3", "1234567893", "admin3@gmail.com", "Germany, Berlin3", "Description 3"});
    list.add(new Object[]{"Name4", "LastName4", "1234567894", "admin4@gmail.com", "Germany, Berlin4", "Description 4"});
    return list.iterator();
  }

  @Test(dataProvider = "addContactString")
  public void addContactStringTest(String name, String lastName, String phone, String email, String address, String description) {
    app.getContactHelper().clickADDLink();
    app.getContactHelper().fillAddContactForm(new Contact().setName(name).setLastName(lastName).setPhone(phone).setEmail(email).setAddress(address).setDescription(description));
    app.getContactHelper().clickOnSAVEContactButton();
    Assert.assertTrue(app.getContactHelper().isContactAdded(name));
  }

  @DataProvider
  public Iterator<Object[]> addContactObject() {
    List<Object[]> list = new ArrayList<>();
    list.add(new Object[]{new Contact().setName("Name1").setLastName("LastName1").setPhone("1234567891").setEmail("admin1@gmail.com").setAddress("Germany, Berlin1").setDescription("Description 1")});
    list.add(new Object[]{new Contact().setName("Name2").setLastName("LastName2").setPhone("1234567892").setEmail("admin2@gmail.com").setAddress("Germany, Berlin2").setDescription("Description 2")});
    list.add(new Object[]{new Contact().setName("Name3").setLastName("LastName3").setPhone("1234567893").setEmail("admin3@gmail.com").setAddress("Germany, Berlin3").setDescription("Description 3")});
    list.add(new Object[]{new Contact().setName("Name4").setLastName("LastName4").setPhone("1234567894").setEmail("admin4@gmail.com").setAddress("Germany, Berlin4").setDescription("Description 4")});
    return list.iterator();
  }

  @Test(dataProvider = "addContactObject")
  public void addContactStringTest(Contact contact) {
    app.getContactHelper().clickADDLink();
    app.getContactHelper().fillAddContactForm(contact);
    app.getContactHelper().clickOnSAVEContactButton();
    Assert.assertTrue(app.getContactHelper().isContactAdded(contact.getName()));
  }

}
