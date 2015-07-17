package com.webs.service.mandrill.model

import groovy.json.JsonOutput

class Message {
	String key
	Map message = [
	        "text" : "",
			"auto_html" : true,
			"subject" : "test email!",
			"from_email" : "william@webs.com",
			"from_name" : "William-Webs",
			"to" : [
			     new Expando([ "email" : "", "name": "", type: "to" ])
			]
	]

	public setMessageDetails(String text, String emailTo, String emailName)  {
		this.message.text = text
		this.message.to[0].email = emailTo
		this.message.to[0].name = emailName
	}

	def toJson() {
		return JsonOutput.toJson(this)
	}
}
