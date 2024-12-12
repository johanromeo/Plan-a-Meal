# Plan a Meal

Get AI-generated recipes and grocery shopping lists emailed to you - based on the availability of your household's provisions.

## Table of Contents

- [Features](#features)
- [Usage](#usage)
- [Installation](#installation)
- [License](#license)

## Features

### People in the Household

Manage the people in your household with features:

- Add a person by providing his/hers **name** and an **email**.
- See all of your household's people.
- Edit a person.
- Remove a person from your household.

### Provisions

Manage your household's provision inventory. <br>
Here, pre-generated DallE 2 images of each provision are displayed, along with the following features:

- Add a provision by providing its **name** and the **image's path**.
- See all provisions.
- Increment and decrement the units of each provision.
- Edit a provision.
- Remove a provision from your household's inventory.
- Search for a specific provision.
- Add a provision to the grocery shopping list.
- Generate a grocery shopping list.

### AI-generated recipes

Manage the recipes of your household with the following features: <br>

- Generate a Recipe based on the user's instructions:
  - **Food Culture:** from which culture should your recipe be inspired? Swedish, Italian or maybe Assyrian?
  - **Language:** in what language do you want the recipe? Swedish or English?
  - **Meal Type:** should it be a recipe for Breakfast, Brunch, Lunch or Dinner?
  - **Minutes to complete the recipe:** how many minutes should it take to make the recipe?
- See all the generated recipes.
- View a specific recipe.
- Remove a recipe.

---

## Usage

Here is a simple demonstration of the web application's functionality.

### Landing page

The clickable images gives us three options:

- People
- Provisions
- Recipes

Let's start with **People** by clicking on its image.

![1.png](usage-example%2F1.png)

### People

Here, we have an overview of the People of the household along with links that either takes us back to the landing page or to the next steps.   
![2.png](usage-example%2F2.png)

We start by clicking on "Add Person"
![3.png](usage-example%2F3.png)

And type in what's necessary - Your **name** and your **email address**.   
The name could pretty much be whatever you want, but it's important to type in your correct email address, since it will be used to later receive an email of the grocery shopping list, containing all the provisions that are missing and need to be bought.

![4.png](usage-example%2F4.png)

The result should look like the image above, and we are now ready for the next step, adding Provisions to our household's inventory!
Go ahead and click on "Provisions".

### Provisions
Here is the Provisions overview. Let's add a provision to the household's inventory by clicking on the "Add Provision" button.

![5.png](usage-example%2F5.png)

We type in the provision's **name** and **image URL**. DallE 2 is used to generate some default images of commonly used provisions. When we want to add more, simply download images of particular provisions and place them in "images/provisions" before adding them. 
![6.png](usage-example%2F6.png)

"Mjölk" and its corresponding image have been added. By default, each provision's unit is set to 0 as shown. We can decrement and increment the units by pressing "-" or "+".
When unit reaches "0", the "Add to Grocery List" checkbox appears. If we click that, "Mjölk" will be added to the grocery shopping list.
And of course, we can either edit the provision or remove it from our inventory.
![7.png](usage-example%2F7.png)

Let's add some more commonly used provisions, shall we?
![8.png](usage-example%2F8.png)

Now we increment the units of some of the provisions. Let us add some of them to the grocery list as well and see what happens!
First thing's first, we press the "Generate Grocery Shopping List" button.
![9.png](usage-example%2F9.png)

It says that we have received an email with a grocery shopping list. Let's check it out!
![10.png](usage-example%2F10.png)

If we go to the mail we added earlier, indeed, we see that we have received a grocery shopping list of the provisions we wanted to add earlier.

![11.png](usage-example%2F11.png)

Next, let's see how the AI-chef can generate a recipe for us with the available provisions.   
_Note: the chef will only use the provisions with a positive number of units._   
Now we press the "Generate Recipe" button.
![12.png](usage-example%2F12.png)

Here have some options to chose from in the drop-down menus.
![13.png](usage-example%2F13.png)

Let's try something Italian? Good, now press the "Generate Recipe" button.
![14.png](usage-example%2F14.png)

We now have generated a recipe with step-by-step instructions on how to do it.
Lastly, we can view the recipe and in case we don't like it, we can of course tell the chef we didn't like it very much and delete it!
![15.png](usage-example%2F15.png)


## Installation

There are a couple of things we need to do before we can use the web application:



```
# Clone the repository
git clone https://github.com/your_username/project_name.git

# Navigate to the project directory
cd project_name

# Install dependencies
npm install
```


