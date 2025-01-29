# Plan a Meal

Get AI-generated recipes and grocery shopping lists emailed to you - based on the availability of your household's provisions.

## Table of Contents

- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
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

## Installation

There are a couple of things we need to do before we can use the web application:

### Generate an App Password on your Gmail account
- In order to send the grocery shopping list by email, you need to generate an "App Password" from a Gmail account, like shown in [this video tutorial](https://youtu.be/FYq9yWgrO8o?si=oBtTRizcJnXnv7P-&t=242).
- Save the App Password, we'll need it later.

### Obtain an API-key from your OpenAI account.
- In order to generate the recipes, you need to have an OpenAI account with the pro plan in order to generate an API-key.
- Visit [this link](https://platform.openai.com/settings/organization/api-keys) and click on the green "+ Create new secret key"-button to generate your key.
- Save the API-key, we'll need it later.

### Install Docker Desktop
This web application consists of three Docker Containers:
- The backend
- A MySQL database
- And the frontend

In order to run these containers, install [Docker Desktop](https://docs.docker.com/desktop/setup/install/windows-install/) on your machine.

### Clone this repo
```bash
# Clone the repository to your machine
git clone https://github.com/johanromeo/Plan-a-Meal.git

# Go to the project's root folder
cd Plan-a-Meal
```

### Create an .env file
The last step is to create an ".env"-file containing all of our secrets that we pass to the Docker Containers as environment variables. <br>
- In the **project's root folder**, create a new "File" and name it "**.env**" - like this:

![env_file.png](usage-example%2Fenv_file.png)

- Finally, copy and paste these environment variables in your .env file and add your own secrets as shown below:

```text
# Database configuration
MYSQL_ROOT_PASSWORD=set_a_root_password
MYSQL_DATABASE=name_your_database
MYSQL_USER=set_a_username
MYSQL_PASSWORD=set_a_password
SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/${MYSQL_DATABASE}

# Gmail configuration
SPRING_MAIL_USERNAME=type_in_your_gmail_email_address_here
SPRING_MAIL_PASSWORD=type_in_your_generated_Gmail_App_Password_here

# OpenAI configuration
OPENAI_API_KEY=type_in_your_generated_OpenAI_APIkey_here
```
### Start the Docker Containers
- From the project's root folder, type in the following command:
```bash
docker-compose up --build -d
```
- After the build is done, type in the following command:
```bash
docker ps
```
You should now see three running Docker Containers like this:
![docker_ps.png](usage-example%2Fdocker_ps.png)

### Use the web application
- Open http://localhost:8000 in your browser and let's have a quick lookaround!

---

## Usage

Here is a simple demonstration of the web application's functionality.

### Landing page

These clickable images gives us three options:

- People
- Provisions
- Recipes

Let's start with **People** by clicking on its image.

![1.png](usage-example%2F1.png)

### People

Here, we have an overview of the People of the household along with the links in the navigation bar that either takes us back to the landing page or to the next steps.   
![2.png](usage-example%2F2.png)

We start by clicking on **"Add Person"**
![3.png](usage-example%2F3.png)

And type in what's necessary - Your **name** and **email address**.   
The name could pretty much be whatever you want, but it's important to type in your correct email address, since it will be used to later receive an email of the grocery shopping list, containing all the provisions that are missing and need to be bought.

![4.png](usage-example%2F4.png)

The result should look like the image above, and we are now ready for the next step, adding Provisions to our household's inventory!
Go ahead and **click on "Provisions"**.

### Provisions
Here is the Provisions overview. Let's add a provision to the household's inventory by clicking on the **"Add Provision" button**.

![5.png](usage-example%2F5.png)

We type in the provision's **name** and **image URL**. <br>
I have used DallE 2 to generate some default images of commonly used provisions. <br>
When we want to add more, simply download images of particular provisions and place them in "images/provisions" before adding them. 
![6.png](usage-example%2F6.png)

"Mjölk" and its corresponding image have been added.   
By default, each provision's unit is set to 0 as shown. We can decrement and increment the units by pressing "-" or "+".
When unit reaches "0", the "Add to Grocery List" checkbox appears. If we click that, "Mjölk" will be added to the grocery shopping list.   
And of course, we can either edit the provision or remove it from our inventory.
![7.png](usage-example%2F7.png)

Let's add some more commonly used provisions, shall we?
![8.png](usage-example%2F8.png)

Now we increment the units of some of the provisions and also add some of them to the grocery list by **clicking on the "Add to Grocery List button**
Lastly, we click on the "Generate Grocery Shopping List" button.
![9.png](usage-example%2F9.png)

It says that we have received an email with a grocery shopping list. Let's check it out!
![10.png](usage-example%2F10.png)

If we go to the mail we added earlier, indeed we see that we have received a grocery shopping list of the provisions we wanted to add earlier.

![11.png](usage-example%2F11.png)

Next, let's see how the AI-chef can generate a recipe for us with the available provisions.   
_Note: the chef will only use the provisions with a positive number of units._   
Now we press the "Generate Recipe" button.
![12.png](usage-example%2F12.png)

Here have some options to chose from in the drop-down menus.
![13.png](usage-example%2F13.png)

Let's try something Italian? Good, now press the "Generate Recipe" button.
![14.png](usage-example%2F14.png)

We have now generated a recipe with step-by-step instructions on how to do it.
Lastly, we can view the recipe and in case we don't like it, we can of course tell the chef we didn't like it very much and delete it and start over again!
![15.png](usage-example%2F15.png)

---

## License 

- [MIT](LICENSE)
