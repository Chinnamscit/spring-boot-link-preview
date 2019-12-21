package com.javachinna.linkpreview.model;

/**
 * Created by Chinna on 12/19/16.
 */
public class Link {

	private static final long serialVersionUID = -706243242873257798L;

	/**
	 * 
	 */
	public Link() {
	}

	/**
	 * @param url
	 * @param title
	 * @param desc
	 * @param image
	 * @param imageAlt
	 */
	public Link(String domain, String url, String title, String desc, String image, String imageAlt) {
		this.domain = domain;
		this.url = url;
		this.title = title;
		this.desc = desc;
		this.image = image;
		this.imageAlt = imageAlt;
	}

	private String domain;

	private String url;

	private String title;

	private String desc;

	private String image;

	private String imageAlt;

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc
	 *            the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * @return the imageAlt
	 */
	public String getImageAlt() {
		return imageAlt;
	}

	/**
	 * @param imageAlt
	 *            the imageAlt to set
	 */
	public void setImageAlt(String imageAlt) {
		this.imageAlt = imageAlt;
	}

	/**
	 * @return the domain
	 */
	public String getDomain() {
		return domain;
	}

	/**
	 * @param domain
	 *            the domain to set
	 */
	public void setDomain(String domain) {
		this.domain = domain;
	}
}
