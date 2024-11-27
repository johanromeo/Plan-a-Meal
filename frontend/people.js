const baseApiUrl = "http://localhost:8080/household-people";

let isEditMode = false; // Flag to determine if we're adding or editing
let editPersonId = null; // Store the ID of the person being edited

document.addEventListener("DOMContentLoaded", () => {
  loadPeople();

  // Show the form when "Add Person" button is clicked
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
    .getElementById("person-form")
    .addEventListener("submit", handleFormSubmit);
});

// Function to load people and populate the table
function loadPeople() {
  fetch(baseApiUrl)
    .then((response) => response.json())
    .then((data) => populateTable(data, "people"))
    .catch((error) =>
      console.error("Error fetching the household people:", error)
    );
}

// Function to open the modal for adding or editing
function openModal(action, person = {}) {
  const modal = document.getElementById("person-modal");
  const modalTitle = document.getElementById("modal-title");
  const submitButton = document.getElementById("submit-button");

  if (action === "add") {
    isEditMode = false;
    modalTitle.textContent = "Add Person";
    submitButton.textContent = "Add Person";
    document.getElementById("person-form").reset();
    document.getElementById("person-id").value = "";
  } else if (action === "edit") {
    isEditMode = true;
    editPersonId = person.id;
    modalTitle.textContent = "Edit Person";
    submitButton.textContent = "Update Person";
    document.getElementById("person-id").value = person.id;
    document.getElementById("person-name").value = person.name;
    document.getElementById("person-email").value = person.email;
  }

  modal.style.display = "block";
}

// Function to close the modal
function closeModal() {
  const modal = document.getElementById("person-modal");
  modal.style.display = "none";
  document.getElementById("person-form").reset();
  isEditMode = false;
  editPersonId = null;
}

// Handle form submission for both add and edit
function handleFormSubmit(event) {
  event.preventDefault(); // Prevent default form submission

  const id = document.getElementById("person-id").value;
  const name = document.getElementById("person-name").value.trim();
  const email = document.getElementById("person-email").value.trim();

  if (!name || !email) {
    alert("Please enter both name and email.");
    return;
  }

  const personData = {
    name: name,
    email: email,
  };

  let fetchOptions = {
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(personData),
  };

  let fetchUrl = baseApiUrl;
  let fetchMethod = "POST";

  if (isEditMode && editPersonId) {
    // Edit existing person
    fetchMethod = "PUT";
    fetchUrl = `${baseApiUrl}/${editPersonId}`;
  }

  fetchOptions.method = fetchMethod;

  // Send request to the backend API
  fetch(fetchUrl, fetchOptions)
    .then((response) => {
      if (response.ok) {
        // Close the form and reset it
        closeModal();

        // Refresh the table data
        loadPeople();
      } else {
        return response.text().then((text) => {
          throw new Error(text || "Failed to save person.");
        });
      }
    })
    .catch((error) => {
      console.error("Error saving person:", error);
      alert("Failed to save person: " + error.message);
    });
}

// Modify the populateTable function to add an event listener to the "Remove" buttons
function populateTable(data, type) {
  const tableBody = document.getElementById("table-body");
  tableBody.innerHTML = "";

  if (data.length === 0) {
    return;
  }

  data.forEach((item) => {
    const row = document.createElement("tr");
    row.innerHTML = `
      <td>${item.id}</td>
      <td>${item.name}</td>
      <td>${item.email}</td>
      <td>
        <button class="action-button edit-button">Edit</button>
        <button class="action-button remove-button">Remove</button>
      </td>
    `;

    // Add event listener to the "Edit" button
    row.querySelector(".edit-button").addEventListener("click", () => {
      openModal("edit", item);
    });

    // Add event listener to the "Remove" button
    row.querySelector(".remove-button").addEventListener("click", () => {
      confirmRemove(item.id);
    });

    tableBody.appendChild(row);
  });
}

// Optional: Close modal when clicking outside of it
window.onclick = function (event) {
  const modal = document.getElementById("person-modal");
  if (event.target == modal) {
    closeModal();
  }
};

// Function to confirm and remove a person
function confirmRemove(personId) {
  const confirmed = confirm("Are you sure you want to remove this person?");
  if (confirmed) {
    deletePerson(personId);
  }
}

// Function to delete a person
function deletePerson(personId) {
  fetch(`${baseApiUrl}/${personId}`, {
    method: "DELETE",
  })
    .then((response) => {
      if (response.ok) {
        // Refresh the table data
        loadPeople();
      } else {
        return response.text().then((text) => {
          throw new Error(text || "Failed to delete person.");
        });
      }
    })
    .catch((error) => {
      console.error("Error deleting person:", error);
      alert("Failed to delete person: " + error.message);
    });
}

let deletePersonId = null; // Store the ID of the person to delete

function confirmRemove(personId) {
  deletePersonId = personId;
  const modal = document.getElementById("confirmation-modal");
  modal.style.display = "block";
}

// Handle confirmation modal buttons
document
  .getElementById("confirm-delete-button")
  .addEventListener("click", () => {
    deletePerson(deletePersonId);
    closeConfirmationModal();
  });

document
  .getElementById("cancel-delete-button")
  .addEventListener("click", () => {
    closeConfirmationModal();
  });

function closeConfirmationModal() {
  const modal = document.getElementById("confirmation-modal");
  modal.style.display = "none";
  deletePersonId = null;
}

// Close modal when clicking outside of it
window.addEventListener("click", function (event) {
  const modal = document.getElementById("confirmation-modal");
  if (event.target == modal) {
    closeConfirmationModal();
  }
});
