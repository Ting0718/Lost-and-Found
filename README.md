# Zot-and-Found

## Table of Contents
1. [Inspiration](#Inspiration)
1. [Overview](#Overview)
2. [Product Spec](#Product-Spec)
3. [Wireframes](#Wireframes)

## Inspiration
At UCI, lost items would be turned into UCIPID's Lost and Found Service. However, UCIPD only accept certain items. Items such as clothing, water bottles, textbooks...etc are not accepted. Therefore, if students cannot find their items at UCIPID's Lost and Found. The only way is to see if anyone post on Facebook, but here comes another problem. There are more than three pages of Lost and Found on Facebook for UCI students. This kind of inconvenience inspired us to create an app that centralizes the service of Lost and Found for UCI students

## Overview
### Description
This is an app that helps UCI students look for the items they lost or got stolen around the campus. 

### App Evaluation
- **Category:**  Lifestlye, Lifehack
- **Mobile:** This app is designed for mobile app because the portability is important, and users can find what items are at a specific location. 
- **Story:** People can make a post after they find a lost item and make a post and post a question for validation. The users can see all the posts on the feed, and the users who are looking for their items should look for their food
- **Audience:** the app is specifically targeted to UCI students.

## Product Spec
### 1. User Stories (Required and Optional)

**Required Must-have Stories**

* User can view other people's post in the home page (feed)
* Verification Questions: user can set up questions for verification
* add tags to post abut the items #hashtag: easier for search
* Users can exchange contact information to for future communication

**Optional Nice-to-have Stories**

* Tipping Users can probably tip someone who found their items
* Notification: User can "tell" the app they lost something, the app notifies the user if someone found an item that matches the description
* A search box where users can search for a specific item (might need reg-ex)
* Items are categorized 
* Chat Room: in app chat so two parties can discuss authenticity/meet up 

### 2. Screen Archetypes

* Login: Users have to log in by entering the email and password, 
* Register - User signs up or logs into their account
  * If they do not have an account, they can create one, and that adds their account information (encrypted) to Authentication   Firebase.
* Home tab: 
  * Users can see all the posts on home feed 
  * Users can view the post details and have an option to answer the verification answers
* Focused tab: 
  * Chat Activity - Chat for users to communicate after the verificaiton questions get approved, User can talk to each other    or exchange contact information
