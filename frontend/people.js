const baseApi = "http://localhost:8080/household-people";

document.addEventListener("DOMContentLoaded", () => {
  fetch(baseApi)
    .then((response) => response.json())
    .then((data) => populateTable(data, "people"))
    .catch((error) =>
      console.error(
        "Error fetching the poor household people with reason: ",
        error
      )
    );
});

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
      <td contenteditable="true">${item.name}</td>
      <td contenteditable="true">${item.email}</td>
      <td>
        <button class="action-button edit-button" onclick="editRow(${item.id}, '${type}')">Edit Household Person</button>
        <button class="action-button remove-button" onclick="confirmRemove(${item.id}, '${type}')">Remove Person from Household</button>
      </td>
    `;

    tableBody.appendChild(row);
  });
}
