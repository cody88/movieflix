function clearText(element) {
	document.getElementById(element).value = '';
}


function toggleOptions(element) {
	document.getElementById(element).classList.toggle('active');
}

function setDropdownText(element, value) {
	document.getElementById(element).value = value;
}