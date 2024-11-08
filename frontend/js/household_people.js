// URL of your backend API endpoint for Household People
const API_URL = 'http://localhost:8080/household-people';

// DOM Elements
const peopleList = document.getElementById('peopleList');
const addPersonBtn = document.getElementById('addPersonBtn');
const addPersonModal = document.getElementById('addPersonModal');
const closeAddModal = document.getElementById('closeAddModal');
const addPersonForm = document.getElementById('addPersonForm');

const updatePersonModal = document.getElementById('updatePersonModal');
const closeUpdateModal = document.getElementById('closeUpdateModal');
const updatePersonForm = document.getElementById('updatePersonForm');

// Fetch and display all Household People
async function fetchHouseholdPeople() {
    try {
        const response = await fetch(API_URL);
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        const people = await response.json();
        displayHouseholdPeople(people);
    } catch (error) {
        console.error('Error fetching household people:', error);
        peopleList.innerHTML = '<p>Error loading data.</p>';
    }
}

// Display Household People in the DOM
function displayHouseholdPeople(people) {
    peopleList.innerHTML = ''; // Clear existing content

    if (people.length === 0) {
        peopleList.innerHTML = '<p>No household people found.</p>';
        return;
    }

    people.forEach(person => {
        const personCard = document.createElement('div');
        personCard.classList.add('person-card');

        const personInfo = document.createElement('div');
        personInfo.classList.add('person-info');
        personInfo.innerHTML = `<strong>Name:</strong> ${person.name} <br> <strong>Email:</strong> ${person.email}`;

        const personActions = document.createElement('div');
        personActions.classList.add('person-actions');

        // Update Button
        const updateBtn = document.createElement('button');
        updateBtn.classList.add('btn', 'btn--primary');
        updateBtn.textContent = 'Update';
        updateBtn.addEventListener('click', () => openUpdateModal(person));

        // Remove Button
        const removeBtn = document.createElement('button');
        removeBtn.classList.add('btn', 'btn--danger');
        removeBtn.textContent = 'Remove';
        removeBtn.addEventListener('click', () => removeHouseholdPerson(person.id));

        personActions.appendChild(updateBtn);
        personActions.appendChild(removeBtn);

        personCard.appendChild(personInfo);
        personCard.appendChild(personActions);

        peopleList.appendChild(personCard);
    });
}

// Open Add Person Modal
addPersonBtn.addEventListener('click', () => {
    addPersonModal.style.display = 'block';
});

// Close Add Person Modal
closeAddModal.addEventListener('click', () => {
    addPersonModal.style.display = 'none';
    addPersonForm.reset();
});

// Handle Add Person Form Submission
addPersonForm.addEventListener('submit', async (e) => {
    e.preventDefault();
    const name = addPersonForm.name.value.trim();
    const email = addPersonForm.email.value.trim();

    if (name === '' || email === '') {
        alert('Please fill in all fields.');
        return;
    }

    try {
        const response = await fetch(API_URL, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ name, email })
        });

        if (!response.ok) {
            throw new Error('Failed to add household person.');
        }

        // Close modal and refresh list
        addPersonModal.style.display = 'none';
        addPersonForm.reset();
        fetchHouseholdPeople();
    } catch (error) {
        console.error('Error adding household person:', error);
        alert('Error adding household person.');
    }
});

// Remove Household Person
async function removeHouseholdPerson(id) {
    const confirmDelete = confirm('Are you sure you want to remove this household person?');
    if (!confirmDelete) return;

    try {
        const response = await fetch(`${API_URL}/${id}`, {
            method: 'DELETE'
        });

        if (!response.ok) {
            throw new Error('Failed to remove household person.');
        }

        fetchHouseholdPeople();
    } catch (error) {
        console.error('Error removing household person:', error);
        alert('Error removing household person.');
    }
}

// Open Update Person Modal
function openUpdateModal(person) {
    updatePersonModal.style.display = 'block';
    updatePersonForm.updateId.value = person.id;
    updatePersonForm.updateName.value = person.name;
    updatePersonForm.updateEmail.value = person.email;
}

// Close Update Person Modal
closeUpdateModal.addEventListener('click', () => {
    updatePersonModal.style.display = 'none';
    updatePersonForm.reset();
});

// Handle Update Person Form Submission
updatePersonForm.addEventListener('submit', async (e) => {
    e.preventDefault();
    const id = updatePersonForm.updateId.value;
    const name = updatePersonForm.updateName.value.trim();
    const email = updatePersonForm.updateEmail.value.trim();

    if (name === '' || email === '') {
        alert('Please fill in all fields.');
        return;
    }

    try {
        const response = await fetch(`${API_URL}/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ name, email })
        });

        if (!response.ok) {
            throw new Error('Failed to update household person.');
        }

        // Close modal and refresh list
        updatePersonModal.style.display = 'none';
        updatePersonForm.reset();
        fetchHouseholdPeople();
    } catch (error) {
        console.error('Error updating household person:', error);
        alert('Error updating household person.');
    }
});

// Close Modals When Clicking Outside of Them
window.addEventListener('click', (event) => {
    if (event.target === addPersonModal) {
        addPersonModal.style.display = 'none';
        addPersonForm.reset();
    }
    if (event.target === updatePersonModal) {
        updatePersonModal.style.display = 'none';
        updatePersonForm.reset();
    }
});

// Initial Fetch of Household People
document.addEventListener('DOMContentLoaded', fetchHouseholdPeople);
