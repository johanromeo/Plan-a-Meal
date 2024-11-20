const recipesApiUrl = "http://localhost:8080/recipes";

document.addEventListener("DOMContentLoaded", () => {
  loadRecipes();
  populateDropdowns();

  // Event listeners for opening and closing the recipe modal
  document
    .querySelector(".generate-recipe-button")
    .addEventListener("click", openRecipeModal);
  document
    .getElementById("cancel-recipe-button")
    .addEventListener("click", closeRecipeModal);
  document
    .getElementById("close-recipe-form")
    .addEventListener("click", closeRecipeModal);

  // Event listener for recipe form submission
  document
    .getElementById("recipe-form")
    .addEventListener("submit", handleRecipeFormSubmit);

  // Event listener for closing the view recipe modal
  document
    .getElementById("close-view-recipe")
    .addEventListener("click", closeViewRecipeModal);
  document
    .getElementById("close-recipe-button")
    .addEventListener("click", closeViewRecipeModal);

  // Close modals when clicking outside of them
  window.onclick = function (event) {
    const generateRecipeModal = document.getElementById(
      "generate-recipe-modal"
    );
    const viewRecipeModal = document.getElementById("view-recipe-modal");
    if (event.target == generateRecipeModal) {
      closeRecipeModal();
    }
    if (event.target == viewRecipeModal) {
      closeViewRecipeModal();
    }
  };
});

function loadRecipes() {
  fetch(recipesApiUrl)
    .then((response) => {
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
      return response.json();
    })
    .then((recipes) => {
      renderRecipes(recipes);
    })
    .catch((error) => {
      console.error("Error fetching recipes:", error);
      alert("Failed to load recipes.");
    });
}

function renderRecipes(recipes) {
  const container = document.querySelector(".recipes-container");
  container.innerHTML = ""; // Clear existing content

  recipes.forEach((recipe) => {
    const recipeItem = document.createElement("div");
    recipeItem.classList.add("recipe-item");

    const title = document.createElement("h3");
    title.classList.add("recipe-title");
    title.textContent = recipe.title;

    const instructions = document.createElement("p");
    instructions.classList.add("recipe-instructions");

    // Handle instructions as an array or string
    const instructionsText = Array.isArray(recipe.instructions)
      ? recipe.instructions.join(" ")
      : recipe.instructions;

    instructions.textContent = instructionsText.substring(0, 100) + "..."; // Show a snippet

    // Action Buttons
    const actionButtons = document.createElement("div");
    actionButtons.classList.add("action-buttons");

    const viewButton = document.createElement("button");
    viewButton.classList.add("action-button");
    viewButton.textContent = "View";
    viewButton.addEventListener("click", () => viewRecipe(recipe.id));

    const deleteButton = document.createElement("button");
    deleteButton.classList.add("action-button", "remove-button");
    deleteButton.textContent = "Delete";
    deleteButton.addEventListener("click", () => deleteRecipe(recipe.id));

    actionButtons.appendChild(viewButton);
    actionButtons.appendChild(deleteButton);

    recipeItem.appendChild(title);
    recipeItem.appendChild(instructions);
    recipeItem.appendChild(actionButtons);

    container.appendChild(recipeItem);
  });
}

function viewRecipe(recipeId) {
  fetch(`${recipesApiUrl}/${recipeId}`)
    .then((response) => {
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
      return response.json();
    })
    .then((recipe) => {
      const modal = document.getElementById("view-recipe-modal");
      document.getElementById("recipe-title").textContent = recipe.title;
      const instructionsContainer = document.getElementById(
        "recipe-instructions"
      );
      instructionsContainer.innerHTML = "";

      // Handle instructions as an array or string
      const instructionsArray = Array.isArray(recipe.instructions)
        ? recipe.instructions
        : recipe.instructions.split("\n");

      instructionsArray.forEach((step) => {
        const p = document.createElement("p");
        p.textContent = step;
        instructionsContainer.appendChild(p);
      });

      modal.style.display = "block";
    })
    .catch((error) => {
      console.error("Error fetching recipe:", error);
      alert("Failed to load recipe.");
    });
}

function openRecipeModal() {
  const modal = document.getElementById("generate-recipe-modal");
  modal.style.display = "block";
}

function closeRecipeModal() {
  const modal = document.getElementById("generate-recipe-modal");
  modal.style.display = "none";
  document.getElementById("recipe-form").reset();
}

function handleRecipeFormSubmit(event) {
  event.preventDefault();

  const foodCulture = document.getElementById("food-culture").value;
  const language = document.getElementById("language").value;
  const mealType = document.getElementById("meal-type").value;
  const maxTime = document.getElementById("max-time").value;

  if (!foodCulture || !language || !mealType || !maxTime) {
    alert("Please fill out all fields.");
    return;
  }

  const recipeInstructionDto = {
    foodCultureOfChoice: foodCulture,
    chatBotTextLanguage: language,
    mealType: mealType,
    maxMinutesToCompleteRecipe: parseInt(maxTime),
  };

  // Send request to backend to generate recipe
  fetch(`${recipesApiUrl}/generate-recipe`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(recipeInstructionDto),
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error(
          `Failed to generate recipe. Status: ${response.status}`
        );
      }
      return response.json();
    })
    .then((recipe) => {
      // Close the modal
      closeRecipeModal();

      // Reload recipes to include the new one
      loadRecipes();
    })
    .catch((error) => {
      console.error("Error generating recipe:", error);
      alert("Failed to generate recipe.");
    });
}

function closeViewRecipeModal() {
  const modal = document.getElementById("view-recipe-modal");
  modal.style.display = "none";
}

function deleteRecipe(recipeId) {
  const confirmed = confirm("Are you sure you want to delete this recipe?");
  if (confirmed) {
    fetch(`${recipesApiUrl}/${recipeId}`, {
      method: "DELETE",
    })
      .then((response) => {
        if (response.ok) {
          loadRecipes();
        } else {
          throw new Error(
            `Failed to delete recipe. Status: ${response.status}`
          );
        }
      })
      .catch((error) => {
        console.error("Error deleting recipe:", error);
        alert("Failed to delete recipe.");
      });
  }
}

function populateDropdowns() {
  const foodCultureSelect = document.getElementById("food-culture");
  const languageSelect = document.getElementById("language");
  const mealTypeSelect = document.getElementById("meal-type");

  const foodCultures = ["Swedish", "Italian", "Chinese", "Mexican"];
  const languages = ["English", "Swedish"];
  const mealTypes = ["Breakfast", "Lunch", "Dinner"];

  foodCultures.forEach((culture) => {
    const option = document.createElement("option");
    option.value = culture;
    option.textContent = culture;
    foodCultureSelect.appendChild(option);
  });

  languages.forEach((language) => {
    const option = document.createElement("option");
    option.value = language;
    option.textContent = language;
    languageSelect.appendChild(option);
  });

  mealTypes.forEach((mealType) => {
    const option = document.createElement("option");
    option.value = mealType;
    option.textContent = mealType;
    mealTypeSelect.appendChild(option);
  });
}
