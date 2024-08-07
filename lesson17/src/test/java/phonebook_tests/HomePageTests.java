package phonebook_tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTests extends TestBase{

    @Test
    public void homePageTest(){
        Assert.assertTrue(app.getHomeHelper().isHomeComponentPresent());
    }



}