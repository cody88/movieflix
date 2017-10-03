function clearText(element) {
	document.getElementById(element).value = '';
}


function toggleOptions(element) {
	console.log(element);
	document.getElementById(element).classList.toggle('active');
}