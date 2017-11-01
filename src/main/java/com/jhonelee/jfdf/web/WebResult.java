package com.jhonelee.jfdf.web;

public class WebResult<T> {

	private String returnCode;

	private String returnMessage;

	private T data;

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnMessage() {
		return returnMessage;
	}

	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public static <T> Builder<T> builder() {
		return new Builder<T>();
	}

	public static class Builder<T> {
		private String returnCode;
		private String returnMessage;
		private T data;

		public Builder<T> returnCode(String returnCode) {
			this.returnCode = returnCode;
			return this;
		}

		public Builder<T> returnMessage(String returnMessage) {
			this.returnMessage = returnMessage;
			return this;
		}

		public Builder<T> data(T data) {
			this.data = data;
			return this;
		}

		public WebResult<T> build() {
			WebResult<T> webResult = new WebResult<T>();
			webResult.setReturnCode(this.returnCode);
			webResult.setReturnMessage(this.returnMessage);
			webResult.setData(this.data);
			return webResult;
		}

	}

}
