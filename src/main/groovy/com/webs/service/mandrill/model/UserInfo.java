package com.webs.service.mandrill.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

public class UserInfo {

	@JsonProperty("owner_full_name")
	private String ownerFullName;

	//@JsonProperty("username")
	private String username;

	//@JsonProperty("title")
	private String title;

	public String getOwnerFullName() {
		return ownerFullName;
	}

	public void setOwnerFullName(String ownerFullName) {
		this.ownerFullName = ownerFullName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof UserInfo)) return false;

		UserInfo userInfo = (UserInfo) o;

		if (ownerFullName != null ? !ownerFullName.equals(userInfo.ownerFullName) : userInfo.ownerFullName != null)
			return false;
		if (username != null ? !username.equals(userInfo.username) : userInfo.username != null) return false;
		return !(title != null ? !title.equals(userInfo.title) : userInfo.title != null);

	}

	@Override
	public int hashCode() {
		int result = ownerFullName != null ? ownerFullName.hashCode() : 0;
		result = 31 * result + (username != null ? username.hashCode() : 0);
		result = 31 * result + (title != null ? title.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("ownerFullName", ownerFullName)
				.add("username", username)
				.add("title", title)
				.toString();
	}
}
