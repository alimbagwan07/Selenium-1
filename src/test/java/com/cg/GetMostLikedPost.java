package com.cg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.base.Base;

public class GetMostLikedPost extends Base {

	public static void main(String[] args) throws Exception {

		logIn();

		// going inside a community
		Thread.sleep(12000);
		driver.findElement(By.xpath("//span[text()=\"My communities\"]//following::li")).click();

		// list of post in the given community
		Thread.sleep(12000);

		// scroller
		System.out.println("Scrolling the page for finding the amount of post");
		long temp = 0;
		try {

			while (true) {
				((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
				Thread.sleep(3000);

				long start = (Long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");

				if (start == temp) {
					break;
				}
				temp = start;
			}

			System.out.println("completed the scroll");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// get all the post in a list of webElement
		List<WebElement> posts = driver
				.findElement(By.xpath("//div[@role=\"main\"]//h2[text()='Conversations']//following::ul"))
				.findElements(By.xpath("//button[@aria-label=\"Show like reactions\"]"));

		// post content
		List<WebElement> postContent = driver
				.findElement(By.xpath("//div[@role=\"main\"]//h2[text()='Conversations']//following::ul"))
				.findElements(By.xpath("//button[@aria-label=\"Show like reactions\"]/ancestor::div/h3"));

		TreeMap<Integer, String> reactions = new TreeMap<Integer, String>();
		ArrayList<String> peopleReacted = new ArrayList<String>();
		int i = 0;
		for (WebElement post : posts) {
			// name of person who posted the post
			peopleReacted.add(driver
					.findElement(By.xpath("//span[@class=\"undefined lpc-hoverTarget\"]//following::div/a")).getText());

			Thread.sleep(2000);
			Actions action = new Actions(driver);
			action.moveToElement(post).click().build().perform();

			// get the no. of likes and post content
			Thread.sleep(5000);
			List<WebElement> reactors = driver
					.findElements(By.xpath("//div[@id=\"y-modalDialog--title\"]//following::ul/li//a"));
			int numOfLikes = reactors.size();

			// get the people names who liked the post
			Thread.sleep(3000);
			for (WebElement name : reactors) {
				peopleReacted.add(name.getAttribute("aria-label"));
			}

			// close reaction tab
			driver.findElement(By.xpath("//button[@aria-label=\"Close\"]")).click();

			// store numOfLikes and post details in TreeMap reactions
			reactions.put(numOfLikes, postContent.get(i).getText());

			i++;
		}

		// for most liked post get the data from TreeMap -> reactions and display it
		// and Name as String
		Integer[] mostLiked = reactions.keySet().toArray(new Integer[reactions.size()]);
		System.out.println("\nThe most no. of likes is:" + mostLiked[mostLiked.length - 1]);
		System.out.println("The most liked post is: ");
		System.out.println(reactions.get(mostLiked[mostLiked.length - 1]));

		// for most active users getting the frequecy of duplicates in the
		// ArrayList -> peopleReacted and create a TreeMap with the frequency as keySet
		Map<Integer, Set<String>> mostActiveUsers = new TreeMap<Integer, Set<String>>();
		
		//Joining the Fname and Lname
		List<String> joinedFnameLname = new ArrayList<String>();
		for(String string : peopleReacted) {
			String[] nameArray = string.split(",");
			String fname = nameArray[0];
			String lname = nameArray[1];
			joinedFnameLname.add(fname + lname);
		}

		// finding the frequency of most occuring name in the list
		for (String activeUser : joinedFnameLname) {
			Set<String> tempSet = new HashSet<String>();
			tempSet.add(activeUser);
			for (String user : joinedFnameLname) {
				if (Collections.frequency(joinedFnameLname, activeUser) == Collections.frequency(joinedFnameLname,
						user)) {
					tempSet.add(user);
				}
			}
			mostActiveUsers.put(Collections.frequency(joinedFnameLname, activeUser), tempSet);
		}
		System.out.println("\nThe most active users are as follows : ");
		Integer[] reverseOrder = mostActiveUsers.keySet().toArray(new Integer[mostActiveUsers.size()]);
		for (int index = reverseOrder.length - 1; index >= 0; index--) {
			System.out.println(mostActiveUsers.get(reverseOrder[index]));
		}

		System.out.println("\nSuccessfully extracted the data of most like post and most users online.");

	}

}