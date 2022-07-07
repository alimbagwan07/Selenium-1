package com.cg;

import org.openqa.selenium.By;


import com.base.Base;

public class PostContent extends Base{
    public static void main(String[] args) throws Exception {

        logIn();

            //select the YammerAutomation community
            Thread.sleep(5000); 
            driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div[1]/div/div/div/div[1]/div[2]/div/div/div/div/nav/div[2]/div/div/div[1]/div[2]/div/div/div/div[1]/div/ul/li/div/div/div/a"))
            .click();

            //write a post
            Thread.sleep(5000);        
            driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div[2]/div/div/div/div/div/div[2]/div[1]/div/div/div[1]/div/div/div/div/div/div/div/div[2]/div/button/div/div"))
            .click();

            Thread.sleep(5000);
            driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div[2]/div/div/div/div/div/div[2]/div[1]/div/div/div/div/div/div[3]/div/div/span/div/div[2]/div/div/div/div"))
            .sendKeys("Posting to YammerAutomation for the 1 time");

            //post button
            driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div[2]/div/div/div/div/div/div[2]/div[1]/div/div/div/div/div/div[5]/div[2]/div/ul/li/div/div/button"))
            .click();

            System.out.println("\nposted successfully -> Posting to YammerAutomation for the 1 time");


        }


    }

