window.centeredHalfPop = function (url) {
    var w = screen.width / 3;
    var h = screen.height / 4;
    var l = (screen.width / 2) - (w / 2);
    var t = (screen.height / 2) - (h / 2);
    window.open(url, 'filePopup', `width=${w}, height=${h}, left=${l}, top=${t}`);
};

window.centeredPop = function (url) {
	var w = screen.width / 2;
	var h = screen.height;
	var l = (screen.width / 2) - (w / 2);
	var t = (screen.height / 2) - (h / 2);
	window.open(url, 'filePopup', `width=${w}, height=${h}, left=${l}, top=${t}`);
};