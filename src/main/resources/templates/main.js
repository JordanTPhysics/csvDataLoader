document.addEventListener("DOMContentLoaded", function () {
    const getDataButton = document.getElementById("getDataButton");
    const uploadForm = document.getElementById("uploadForm");
    const resultDiv = document.getElementById("result");

    // Add click event handlers for the buttons
    getDataButton.addEventListener("click", function () {
        // Make a GET request to retrieve data from the backend
        fetch("/api/customers")
            .then(response => response.json())
            .then(data => {
                // Display the data on the page
                resultDiv.innerHTML = JSON.stringify(data, null, 2);
            })
            .catch(error => {
                console.error("Error fetching data:", error);
            });
    });

    uploadDataButton.addEventListener("click", function () {
        // Create a sample data object to upload (modify as needed)
        const formData = new FormData(uploadForm);
        const textEncoder = new TextEncoder();

        const byteArray = textEncoder.encode(formData.get("filepath"));

        // Make a POST request to upload data to the backend
        fetch("/api/upload-csv", {
            method: "POST",
            headers: {
                "Content-Type": "text/plain"
            },
            body: byteArray
        }
        .then(response => response.json())
        .then(data => {
            // Display the uploaded data or a success message
            resultDiv.innerHTML = "Data uploaded successfully!";
        })
        .catch(error => {
            console.error("Error uploading data:", error);
        });
    });
});
