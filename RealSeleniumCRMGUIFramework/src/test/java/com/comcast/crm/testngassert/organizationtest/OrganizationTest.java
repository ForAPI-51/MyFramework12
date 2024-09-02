package com.comcast.crm.testngassert.organizationtest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.objectrepositry.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositry.HomePage;
import com.comcast.crm.objectrepositry.OrganizationInfoPage;
import com.comcast.crm.objectrepositry.OrganizationPage;

public class OrganizationTest extends BaseClass {
	@Test
	public void CreateOrganizationTest() throws EncryptedDocumentException, IOException {
		
		//click on Organization Module
		HomePage hp=new HomePage(driver);
		hp.getOrglink().click();
		
		//click on new Organization button(plus button)
		OrganizationPage op=new OrganizationPage(driver);
		op.getCreateorgbtn().click();
		
		//Enter all the details
		String orgname=elib.getDataFromExcelFile("org", 1, 2)+jlib.getRandomNumber();
		
		CreatingNewOrganizationPage cnop=new CreatingNewOrganizationPage(driver);
		cnop.createNewOrg(orgname);
		
		//verify 
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		String headerinfo=oip.getHeaderinfo().getText();
		
		if(headerinfo.contains(orgname)) {
			System.out.println(orgname+" is verified====PASS");
		}else {
			System.out.println(orgname+" is not verified====FAIL");
		}
		String actorgname=oip.getEditorgname().getText();
		if(actorgname.equals(orgname)) {
			System.out.println(orgname+" is verified====PASS");
			}else {
				System.out.println(orgname+" is not verified====FAIL");
			}
		
	}
	@Test
	public void CreateOrganizationWithIndustryDDTest() throws EncryptedDocumentException, IOException {
	
		//click on Organization Module
		
		HomePage hp=new HomePage(driver);
		hp.getOrglink().click();
		
		//click on new Organization button(plus button)
		OrganizationPage op=new OrganizationPage(driver);
		op.getCreateorgbtn().click();
		
		//Enter all the details
		String orgname=elib.getDataFromExcelFile("org", 4, 2)+jlib.getRandomNumber();
		String indusrty=elib.getDataFromExcelFile("org", 4, 3);
		String type=elib.getDataFromExcelFile("org", 4, 4);
		
		CreatingNewOrganizationPage cnop=new CreatingNewOrganizationPage(driver);
		cnop.createNewOrg(orgname, indusrty, type);
		
		//verify 
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		String headerinfo=oip.getHeaderinfo().getText();
		
		if(headerinfo.contains(orgname)) {
			System.out.println(orgname+" is verified====PASS");
		}else {
			System.out.println(orgname+" is not verified====FAIL");
		}
		String actindustry=oip.getIndustrydd().getText();
		String acttype=oip.getTypedd().getText();
		if((actindustry.equals(indusrty))&&(acttype.equals(type))) {
			System.out.println(indusrty+" is verified====PASS");
			System.out.println(type+" is verified====PASS");
			}else {
				System.out.println(indusrty+" is not verified====FAIL");
				System.out.println(type+" is not verified====FAIL");
			}
		
	}
	@Test
	public void CreateOrganizationWithPhonenoTest() throws EncryptedDocumentException, IOException {
		
		//click on Organization Module
		HomePage hp=new HomePage(driver);
		hp.getOrglink().click();
		
		//click on new Organization button(plus button)
		OrganizationPage op=new OrganizationPage(driver);
		op.getCreateorgbtn().click();
		
		//Enter all the details
		String orgname=elib.getDataFromExcelFile("org", 7, 2)+jlib.getRandomNumber();
		String phoneno=elib.getDataFromExcelFile("org", 7, 5);
		
		CreatingNewOrganizationPage cnop=new CreatingNewOrganizationPage(driver);
		cnop.createNewOrg(orgname, phoneno);
		
		//verify 
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		String headerinfo=oip.getHeaderinfo().getText();
		
		if(headerinfo.contains(orgname)) {
			System.out.println(orgname+" is verified====PASS");
		}else {
			System.out.println(orgname+" is not verified====FAIL");
		}
		String actphoneno=oip.getEditphoneno().getText();
		if(actphoneno.equals(phoneno)) {
			System.out.println(phoneno+" is verified====PASS");
			
			}else {
				System.out.println(phoneno+" is not verified====FAIL");
				
			}
		
	}
	@Test
	public void CreateOrganizationAndDeleteOrganizationTest() throws EncryptedDocumentException, IOException, InterruptedException {
		
		//click on Organization Module
		HomePage hp=new HomePage(driver);
		hp.getOrglink().click();
		
		//click on new Organization button(plus button)
		OrganizationPage op=new OrganizationPage(driver);
		op.getCreateorgbtn().click();
		
		//Enter all the details
		String orgname=elib.getDataFromExcelFile("org", 7, 2)+jlib.getRandomNumber();
		
		CreatingNewOrganizationPage cnop=new CreatingNewOrganizationPage(driver);
		cnop.createNewOrg(orgname);
		
		//go back to organization page by clicking on organization  link present in home page
		Thread.sleep(2000);
		hp.getOrglink().click();
		
		//search the orgname you want to delete
		op.deleteAlreadyCreatedOrg(orgname, "Organization Name");
		
		driver.findElement(By.xpath("//a[text()='"+orgname+"']/../..//td[8]/a[2]")).click();
		wlib.alterPopupAccept(driver);
		
		//verify 
		
		boolean actsearch=op.getNoorgtext().isDisplayed();
		if(actsearch) {
			System.out.println(orgname+" is verified====PASS");
		}
		else {
				System.out.println(orgname+" is not verified====FAIL");
			}
		
	}
}
