package com.cg;

import java.util.List;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.base.Base;

public class MostActiveDay extends Base {
	public static void main(String[] args) throws Exception {
		logIn();

		// going inside a community
		Thread.sleep(12000);
		driver.findElement(By.xpath(
				"/html/body/div[1]/div/div[2]/div/div[1]/div/div/div/div[1]/div[2]/div/div/div/div/nav/div[2]/div/div/div[1]/div[2]/div/div/div/div[2]/ul/li[1]/div/div/div/div/a"))
				.click();

		// getting the comunity name
		Thread.sleep(12000);
		String communityName = driver.findElement(By.xpath(
				"/html/body/div[1]/div/div[2]/div/div[2]/div/div/div/div/div/div[1]/div/div/div/div[3]/div/div[1]/div/div/div/span"))
				.getText();

		// About Button
		Thread.sleep(10000);
		driver.findElement(By.xpath(
				"/html/body/div[1]/div/div[2]/div/div[2]/div/div/div/div/div/div[1]/div/div/div/div[4]/div/div/button[2]/span/span/span/span"))
				.click();

		// see more
		Thread.sleep(5000);
		driver.findElement(By.xpath(
				"/html/body/div[1]/div/div[2]/div/div[2]/div/div/div/div/div/div[2]/div[1]/div/div/div/div/ul/li[3]/div/a"))
				.click();

		// svg element handling
		Thread.sleep(5000);
		List<WebElement> graph = driver.findElements(By.xpath(
				"//div[@class='highcharts-container ']//*[name()='svg']//*[local-name()='path' and @class='highcharts-point']"));

		// get the attribute value of each element of svg
		TreeMap<Integer, String> svgData = new TreeMap<Integer, String>();
		for (WebElement ele : graph) {
			// getting the data and spliting it accordingly
			System.out.println("All data ->");
			System.out.println(ele.getAttribute("aria-label"));
			String[] tempArray = ele.getAttribute("aria-label").split("\\W");
			svgData.put(Integer.parseInt(
					tempArray[5]),
					tempArray[2] + " " + tempArray[3]
				);
		}
		//sorting the data and printing the output 
		Integer[] highest = svgData.keySet().toArray(new Integer[svgData.size()]);
		System.out.println("\nName of community : " + communityName);
		System.out.println("The Day when user are most active in " + communityName + "is : "
				+ svgData.get(highest[highest.length - 1]));
		System.out.println("And the number of user is: " + highest[highest.length - 1]);

	}

}