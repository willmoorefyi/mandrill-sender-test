package com.webs.service.mandrill.model;

import com.google.common.base.Objects;

public class MandrillResponse {
	String email;
	String status;
	String _id;
	String reject_reason;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getReject_reason() {
		return reject_reason;
	}

	public void setReject_reason(String reject_reason) {
		this.reject_reason = reject_reason;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("_id", _id)
				.add("email", email)
				.add("status", status)
				.add("reject_reason", reject_reason)
				.toString();
	}
}
