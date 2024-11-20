const baseApiUrl = "http://localhost:8080/provisions";

let isEditMode = false; // Flag to determine if we're adding or editing
let editProvisionId = null; // Store the ID of the provision being edited

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

  // Handle form submission
  document
    .getElementById("provision-form")
    .addEventListener("submit", handleFormSubmit);
});

function loadProvisions() {
  fetch(baseApiUrl)
    .then((response) => response.json())
    .then((data) => renderProvisions(data))
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
    provisionItem.appendChild(actionButtons);

    container.appendChild(provisionItem);
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

// Function to confirm and remove a provision
function confirmRemove(provisionId) {
  const confirmed = confirm("Are you sure you want to remove this provision?");
  if (confirmed) {
    deleteProvision(provisionId);
  }
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

// Close modal when clicking outside of it
window.onclick = function (event) {
  const modal = document.getElementById("provision-modal");
  if (event.target == modal) {
    closeModal();
  }
};
