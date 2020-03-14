# Zot-and-Found

## Table of Contents
1
2. [Overview](#Overview)
3. [Product Spec](#Product-Spec)
4. [Wireframes](#Wireframes)
5. [Schema](#Schema)

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

* Login 
* Register - User signs up or logs into their account
   * Upon Download/Reopening of the application, the user is prompted to log in to gain access to their profile information to be properly matched with another person. 
   * ...
* Messaging Screen - Chat for users to communicate (direct 1-on-1)
   * Upon selecting music choice users matched and message screen opens
* Profile Screen 
   * Allows user to upload a photo and fill in information that is interesting to them and others
* Song Selection Screen.
   * Allows user to be able to choose their desired song, artist, genre of preference and begin listening and interacting with others.
* Settings Screen
   * Lets people change language, and app notification settings.

### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Music selection
* Profile
* Settings

Optional:
* Music/Encounter Queue
* Discover (Top Choices)

**Flow Navigation** (Screen to Screen)
* Forced Log-in -> Account creation if no log in is available
* Music Selection (Or Queue if Optional) -> Jumps to Chat
* Profile -> Text field to be modified. 
* Settings -> Toggle settings

## Wireframes
<img src="https://i.imgur.com/9CrjH1K.jpg" width=800><br>

### [BONUS] Digital Wireframes & Mockups
<img src="https://i.imgur.com/lYHn37F.jpg" height=200>

### [BONUS] Interactive Prototype
<img src="https://i.imgur.com/AiKfE5g.gif" width=200>

## Schema 
### Models
#### Post

   | Property      | Type     | Description |
   | ------------- | -------- | ------------|
   | objectId      | String   | unique id for the user post (default field) |
   | author        | Pointer to User| image author |
   | image         | File     | image that user posts |
   | caption       | String   | image caption by author |
   | commentsCount | Number   | number of comments that has been posted to an image |
   | likesCount    | Number   | number of likes for the post |
   | createdAt     | DateTime | date when post is created (default field) |
   | updatedAt     | DateTime | date when post is last updated (default field) |
