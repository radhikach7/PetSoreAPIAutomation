package api.orgutilities;

import java.io.IOException;
import org.testng.annotations.DataProvider;


public class ORGDataProviders {

	@DataProvider(name = "userData")
    public static Object[][] getUserData() throws IOException {
        String path = System.getProperty("user.dir") + "/testData/Userdataapi.xlsx";
        ORGExcelUtility excel = new ORGExcelUtility(path);
        return excel.readExcelData("Sheet1");
    }

    @DataProvider(name = "usernames")
    public static Object[][] getUsernames() throws IOException {
        String path = System.getProperty("user.dir") + "/testData/Userdataapi.xlsx";
        ORGExcelUtility excel = new ORGExcelUtility(path);
        Object[][] userData = excel.readExcelData("Sheet1");
        Object[][] usernames = new Object[userData.length][1]; // Assuming username is in the second column (index 1)
        for (int i = 0; i < userData.length; i++) {
            usernames[i][0] = userData[i][1]; // Assuming username is in the second column (index 1)
        }
        return usernames;
    }
}