const baseApiUrl = "http://localhost:8080/provisions";

let isEditMode = false; // Flag to determine if we're adding or editing
let editProvisionId = null; // Store the ID of the provision being edited
let deleteProvisionId = null; // Store the ID of the provision to delete
let allProvisions = []; // Store all provisions data

document.addEventListener("DOMContentLoaded", () => {
  loadProvisions();

  // Show the form when "Add Provision" button is clicked
  document.querySelector(".add-button").addEventListener("click", () => {
    openModal("add");
  });

  // Close the form when "Cancel" button is clicked
  document
    .getElementById("cancel-button")
    .addEventListener("click", closeModal);

  // Close the form when the close button is clicked
  document.getElementById("close-form").addEventListener("click", closeModal);

  // Add event listener for the search input
  document
    .getElementById("search-input")
    .addEventListener("input", handleSearch);

  // Handle form submission
  document
    .getElementById("provision-form")
    .addEventListener("submit", handleFormSubmit);

  // Add event listeners for the confirmation modal buttons
  document
    .getElementById("confirm-delete-button")
    .addEventListener("click", () => {
      deleteProvision(deleteProvisionId);
      closeConfirmationModal();
    });

  document
    .getElementById("cancel-delete-button")
    .addEventListener("click", closeConfirmationModal);

  document
    .getElementById("close-confirmation")
    .addEventListener("click", closeConfirmationModal);

  // Update the window.onclick function to handle the confirmation modal
  window.onclick = function (event) {
    const provisionModal = document.getElementById("provision-modal");
    const confirmationModal = document.getElementById("confirmation-modal");
    if (event.target == provisionModal) {
      closeModal();
    }
    if (event.target == confirmationModal) {
      closeConfirmationModal();
    }
  };
});

function loadProvisions() {
  fetch(baseApiUrl)
    .then((response) => response.json())
    .then((data) => {
      allProvisions = data; // Store all provisions
      renderProvisions(data);
    })
    .catch((error) => {
      console.error("Error fetching provisions:", error);
      alert("Failed to load provisions.");
    });
}

function renderProvisions(provisions) {
  const container = document.querySelector(".provisions-container");
  container.innerHTML = ""; // Clear existing content

  provisions.forEach((provision) => {
    // Create provision item element
    const provisionItem = document.createElement("div");
    provisionItem.classList.add("provision-item");
    provisionItem.dataset.id = provision.id;

    // Provision Image
    const img = document.createElement("img");
    img.classList.add("provision-image");
    img.alt = provision.name;
    img.src = provision.imgUrl || "images/default_provision.png"; // Use default image if imgUrl is not provided

    // Provision Title
    const title = document.createElement("h2");
    title.classList.add("provision-title");
    title.textContent = provision.name;

    // Units Controls
    const unitsControls = document.createElement("div");
    unitsControls.classList.add("quantity-controls");

    const decrementButton = document.createElement("button");
    decrementButton.classList.add("quantity-button", "decrement");
    decrementButton.textContent = "-";
    decrementButton.addEventListener("click", () =>
      adjustUnits(provision.id, -1)
    );

    const unitsDisplay = document.createElement("span");
    unitsDisplay.classList.add("quantity-display");
    unitsDisplay.textContent = provision.units;

    const incrementButton = document.createElement("button");
    incrementButton.classList.add("quantity-button", "increment");
    incrementButton.textContent = "+";
    incrementButton.addEventListener("click", () =>
      adjustUnits(provision.id, 1)
    );

    unitsControls.appendChild(decrementButton);
    unitsControls.appendChild(unitsDisplay);
    unitsControls.appendChild(incrementButton);

    // Add to Grocery Shopping List Checkbox (if units are zero)
    if (provision.units === 0) {
      const checkboxContainer = document.createElement("div");
      checkboxContainer.classList.add("checkbox-container");

      const shoppingListCheckbox = document.createElement("input");
      shoppingListCheckbox.type = "checkbox";
      shoppingListCheckbox.id = `checkbox-${provision.id}`;
      shoppingListCheckbox.checked = provision.addedToShoppingList;
      shoppingListCheckbox.addEventListener("change", () =>
        toggleShoppingList(provision.id, shoppingListCheckbox.checked)
      );

      const checkboxLabel = document.createElement("label");
      checkboxLabel.htmlFor = `checkbox-${provision.id}`;
      checkboxLabel.textContent = "Add to Grocery List";

      checkboxContainer.appendChild(shoppingListCheckbox);
      checkboxContainer.appendChild(checkboxLabel);

      provisionItem.appendChild(checkboxContainer);
    }

    // Action Buttons
    const actionButtons = document.createElement("div");
    actionButtons.classList.add("action-buttons");

    const editButton = document.createElement("button");
    editButton.classList.add("action-button", "edit-button");
    editButton.textContent = "Edit";
    editButton.addEventListener("click", () => openModal("edit", provision));

    const removeButton = document.createElement("button");
    removeButton.classList.add("action-button", "remove-button");
    removeButton.textContent = "Remove";
    removeButton.addEventListener("click", () => confirmRemove(provision.id));

    actionButtons.appendChild(editButton);
    actionButtons.appendChild(removeButton);

    // Assemble provision item
    provisionItem.appendChild(img);
    provisionItem.appendChild(title);
    provisionItem.appendChild(unitsControls);

    // Append the checkbox if it exists
    if (provision.units === 0) {
      provisionItem.appendChild(
        provisionItem.querySelector(".checkbox-container")
      );
    }

    provisionItem.appendChild(actionButtons);

    container.appendChild(provisionItem);
  });
}

function toggleShoppingList(provisionId, addedToShoppingList) {
  // Fetch the current provision data
  fetch(`${baseApiUrl}/${provisionId}`)
    .then((response) => response.json())
    .then((provisionData) => {
      // Update the addedToShoppingList field
      provisionData.addedToShoppingList = addedToShoppingList;

      // Send PUT request to backend to update the provision
      fetch(`${baseApiUrl}/${provisionId}`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(provisionData),
      })
        .then((response) => {
          if (!response.ok) {
            throw new Error("Failed to update shopping list status.");
          }
        })
        .catch((error) => {
          console.error("Error updating shopping list status:", error);
          alert("Failed to update shopping list status.");
        });
    })
    .catch((error) => {
      console.error("Error fetching provision data:", error);
      alert("Failed to update shopping list status.");
    });
}

// Function to adjust units
function adjustUnits(provisionId, change) {
  // Find the provision in the DOM to update the display optimistically
  const provisionItem = document.querySelector(
    `.provision-item[data-id='${provisionId}']`
  );
  const unitsDisplay = provisionItem.querySelector(".quantity-display");
  let currentUnits = parseInt(unitsDisplay.textContent);

  // Calculate new units
  const newUnits = currentUnits + change;
  if (newUnits < 0) {
    return; // Do not allow negative units
  }

  // Update the units display optimistically
  unitsDisplay.textContent = newUnits;

  // Get the current provision data
  fetch(`${baseApiUrl}/${provisionId}`)
    .then((response) => response.json())
    .then((provisionData) => {
      // Update the units in the provision data
      provisionData.units = newUnits;

      // If units increased from 0 to positive, reset addedToShoppingList to false
      if (currentUnits === 0 && newUnits > 0) {
        provisionData.addedToShoppingList = false;
      }

      // Send PUT request to backend to update the provision
      fetch(`${baseApiUrl}/${provisionId}`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(provisionData),
      })
        .then((response) => {
          if (!response.ok) {
            throw new Error("Failed to update units.");
          }
          // Reload provisions to update the UI
          loadProvisions();
        })
        .catch((error) => {
          console.error("Error updating units:", error);
          alert("Failed to update units.");
          // Revert units display if update fails
          unitsDisplay.textContent = currentUnits;
        });
    })
    .catch((error) => {
      console.error("Error fetching provision data:", error);
      alert("Failed to update units.");
      // Revert units display if fetching provision data fails
      unitsDisplay.textContent = currentUnits;
    });
}

// Function to open the modal for adding or editing
function openModal(action, provision = {}) {
  const modal = document.getElementById("provision-modal");
  const modalTitle = document.getElementById("modal-title");
  const submitButton = document.getElementById("submit-button");

  if (action === "add") {
    isEditMode = false;
    modalTitle.textContent = "Add Provision";
    submitButton.textContent = "Add Provision";
    document.getElementById("provision-form").reset();
    document.getElementById("provision-id").value = "";
  } else if (action === "edit") {
    isEditMode = true;
    editProvisionId = provision.id;
    modalTitle.textContent = "Edit Provision";
    submitButton.textContent = "Update Provision";
    document.getElementById("provision-id").value = provision.id;
    document.getElementById("provision-name").value = provision.name;
    document.getElementById("provision-image-url").value = provision.imgUrl;
  }

  modal.style.display = "block";
}

// Function to close the modal
function closeModal() {
  const modal = document.getElementById("provision-modal");
  modal.style.display = "none";
  document.getElementById("provision-form").reset();
  isEditMode = false;
  editProvisionId = null;
}

// Handle form submission for both add and edit
function handleFormSubmit(event) {
  event.preventDefault(); // Prevent default form submission

  const id = document.getElementById("provision-id").value;
  const name = document.getElementById("provision-name").value.trim();
  const imgUrl = document.getElementById("provision-image-url").value.trim();

  if (!name || !imgUrl) {
    alert("Please enter both name and image URL.");
    return;
  }

  const provisionData = {
    name: name,
    imgUrl: imgUrl,
    units: isEditMode ? undefined : 0, // Default units when adding, keep units unchanged when editing
    addedToShoppingList: false, // Default value
  };

  let fetchOptions = {
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(provisionData),
  };

  let fetchUrl = baseApiUrl;
  let fetchMethod = "POST";

  if (isEditMode && editProvisionId) {
    // Edit existing provision
    fetchMethod = "PUT";
    fetchUrl = `${baseApiUrl}/${editProvisionId}`;

    // For editing, include existing units and addedToShoppingList
    fetch(`${baseApiUrl}/${editProvisionId}`)
      .then((response) => response.json())
      .then((existingProvisionData) => {
        // Include units and addedToShoppingList from existing data
        provisionData.units = existingProvisionData.units;
        provisionData.addedToShoppingList =
          existingProvisionData.addedToShoppingList;

        // Update fetch options
        fetchOptions.method = fetchMethod;
        fetchOptions.body = JSON.stringify(provisionData);

        // Send PUT request to update the provision
        fetch(fetchUrl, fetchOptions)
          .then((response) => {
            if (response.ok) {
              // Close the form and reset it
              closeModal();

              // Refresh the provisions list
              loadProvisions();
            } else {
              return response.text().then((text) => {
                throw new Error(text || "Failed to save provision.");
              });
            }
          })
          .catch((error) => {
            console.error("Error saving provision:", error);
            alert("Failed to save provision: " + error.message);
          });
      })
      .catch((error) => {
        console.error("Error fetching existing provision data:", error);
        alert("Failed to save provision.");
      });

    return; // Exit the function since we're handling the fetch inside the fetch of existing data
  }

  fetchOptions.method = fetchMethod;

  // Send request to the backend API
  fetch(fetchUrl, fetchOptions)
    .then((response) => {
      if (response.ok) {
        // Close the form and reset it
        closeModal();

        // Refresh the provisions list
        loadProvisions();
      } else {
        return response.text().then((text) => {
          throw new Error(text || "Failed to save provision.");
        });
      }
    })
    .catch((error) => {
      console.error("Error saving provision:", error);
      alert("Failed to save provision: " + error.message);
    });
}

// Modify the confirmRemove function to open a styled modal
function confirmRemove(provisionId) {
  deleteProvisionId = provisionId;
  const modal = document.getElementById("confirmation-modal");
  modal.style.display = "block";
}

// Function to close the confirmation modal
function closeConfirmationModal() {
  const modal = document.getElementById("confirmation-modal");
  modal.style.display = "none";
  deleteProvisionId = null;
}

// Function to delete a provision
function deleteProvision(provisionId) {
  fetch(`${baseApiUrl}/${provisionId}`, {
    method: "DELETE",
  })
    .then((response) => {
      if (response.ok) {
        // Reload provisions
        loadProvisions();
      } else {
        throw new Error("Failed to delete provision.");
      }
    })
    .catch((error) => {
      console.error("Error deleting provision:", error);
      alert("Failed to delete provision.");
    });
}

// Search functionality
function handleSearch(event) {
  const searchTerm = event.target.value.toLowerCase();
  const filteredProvisions = allProvisions.filter((provision) =>
    provision.name.toLowerCase().includes(searchTerm)
  );
  renderProvisions(filteredProvisions);
}
