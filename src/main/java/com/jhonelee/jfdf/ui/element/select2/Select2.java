package com.jhonelee.jfdf.ui.element.select2;

public class Select2 {
	
	private String id;
	
	private String text;
	
	private Boolean selected = Boolean.FALSE;
	
	private Boolean disabled = Boolean.FALSE;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

}
