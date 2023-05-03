
var script = document.createElement('script');
script.src = '//stgauth.alertsec.com/js/modals.js';
document.head.appendChild(script);

var Ensure = {
	run: (guid, uid) => {
		return new Promise((resolve, reject) => {
			if (typeof window.as_ensure_loader === 'undefined') {
				reject(new Error('Ensure not loaded'))
			} else {
				console.log("starting ensure")
				window.as_ensure_loader('https://stgauth.alertsec.com', guid, uid, resolve);
			}
		})
	}
}
