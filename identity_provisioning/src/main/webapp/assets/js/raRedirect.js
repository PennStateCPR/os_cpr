function ouaSubmit()
{
	// Set post value for principalId and password

	var form = document.createElement("form");
	form.setAttribute("method", "post");
	form.setAttribute("action", "preConnect.jsp");

	var addField = function( key, value ){
	        var hiddenField = document.createElement("input");
       		hiddenField.setAttribute("type", "hidden");
       		hiddenField.setAttribute("name", key);
       		hiddenField.setAttribute("value", value );

        	form.appendChild(hiddenField);
    	}; 

	addField("ra", "uao");

	document.body.appendChild(form);
	form.submit(); 
}

function gsoSubmit()
{
	// Set post value for principalId and password

	var form = document.createElement("form");
	form.setAttribute("method", "post");
	form.setAttribute("action", "preConnect.jsp");

	var addField = function( key, value ){
	        var hiddenField = document.createElement("input");
       		hiddenField.setAttribute("type", "hidden");
       		hiddenField.setAttribute("name", key);
       		hiddenField.setAttribute("value", value );

        	form.appendChild(hiddenField);
    	}; 

	addField("ra", "gso");

	document.body.appendChild(form);
	form.submit(); 
}