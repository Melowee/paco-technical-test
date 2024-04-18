function togglePinned(flightJson) {
	let pinnedItems = JSON.parse(sessionStorage.getItem("pinnedItems")) || [];

	let index = pinnedItems.findIndex(flight => JSON.parse(flight)["id"] === flightJson["id"]);

	if (index === -1) {
		pinnedItems.push(JSON.stringify(flightJson));
	} else {
		pinnedItems.splice(index, 1)
	}

	sessionStorage.setItem("pinnedItems", JSON.stringify(pinnedItems));
	updatePinned()
}


function updatePinned() {
	let pinnedFlightsTable = document.getElementById("pinnedFlights");
	pinnedFlightsTable.innerHTML = ""
	let pinnedFlights = JSON.parse(sessionStorage.getItem("pinnedItems")) || [];
	pinnedFlights.forEach(flight => {
		let parsedFlight = JSON.parse(flight)

		const unpinButtonCell = document.createElement("th");
		const unpinButton = document.createElement("button");
		unpinButton.innerText = "Unpin"
		unpinButton.addEventListener("click", function() {
			togglePinned(parsedFlight)
		})
		unpinButtonCell.appendChild(unpinButton)

		const originCell = document.createElement("th");
		originCell.innerText = parsedFlight["origin"]["name"]

		const destinationCell = document.createElement("th");
		destinationCell.innerText = parsedFlight["destination"]["name"]

		const priceCell = document.createElement("th");
		priceCell.innerText = parsedFlight["price"]

		const departureCell = document.createElement("th");
		departureCell.innerText = parsedFlight["departure"]

		const arrivalCell = document.createElement("th");
		arrivalCell.innerText = parsedFlight["arrival"]

		const imageCell = document.createElement("th");
		const img = document.createElement("img");
		img.setAttribute("src", parsedFlight["image"]);
		imageCell.appendChild(img);

		const newRow = document.createElement("tr");
		newRow.appendChild(unpinButtonCell);
		newRow.appendChild(originCell);
		newRow.appendChild(destinationCell);
		newRow.appendChild(priceCell);
		newRow.appendChild(departureCell);
		newRow.appendChild(arrivalCell);
		newRow.appendChild(imageCell);
		pinnedFlightsTable.appendChild(newRow);
	});
}