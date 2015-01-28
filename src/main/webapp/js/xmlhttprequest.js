function getXmlHttp(){

    if (typeof XMLHttpRequest != "undefined") { return new XMLHttpRequest();}

    try { return new ActiveXObject("Msxml2.XMLHTTP.6.0"); }
    catch (e) {}
    try { return new ActiveXObject("Msxml2.XMLHTTP.3.0"); }
    catch (e) {}
    try { return new ActiveXObject("Microsoft.XMLHTTP"); }
    catch (e) {}
    throw new Error("This browser does not support XMLHttpRequest.");

}

function studentUpdate(form) {
	var req = getXmlHttp();
	var params = 'studentId=' + encodeURIComponent(form.studentId.value) + '&lastName='
		+ encodeURIComponent(form.lastName.value) + '&firstName='
		+ encodeURIComponent(form.firstName.value);
	req.open('POST', '/students/update', true);
	req.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
	req.onreadystatechange = function() {
		if (req.readyState == 4) {
			if(req.status == 200) {
				alert(req.responseText)
			}
		}
	};
	req.send(params);
	document.forms.formStudentUpdate.reset();
}