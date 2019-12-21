package com.javachinna.linkpreview.controller;

import java.io.IOException;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.net.InternetDomainName;
import com.javachinna.linkpreview.model.Link;

/**
 * 
 * @author Chinna
 *
 */
@RestController
public class PagesController {

	private final Logger logger = LogManager.getLogger(getClass());

	@GetMapping({ "/", "/home" })
	public ModelAndView home(@RequestParam(value = "view", required = false) String view) {
		logger.info("Entering home page");
		ModelAndView model = new ModelAndView("previewLink");
		model.addObject("title", "Home");
		model.addObject("view", view);
		return model;
	}

	/**
	 * Fetches rich link preview info for the given URL based on the meta tags
	 * present in the web page
	 * 
	 * 
	 * @param url
	 * @return
	 */
	@GetMapping("/api/link/preview")
	public Link getLinkPreviewInfo(@RequestParam(value = "url", required = true) String url) {
		Link link = null;
		try {
			link = extractLinkPreviewInfo(url);
		} catch (IOException e) {
			logger.error("Unable to connect to : {}", url);
		}
		return link;
	}

	/**
	 * Generates rich link preview for the given URL based on the meta tags present
	 * in the web page
	 * 
	 * @param url
	 * @return
	 */
	@GetMapping("/link/preview")
	public ModelAndView linkPreview(@RequestParam(value = "url", required = true) String url) {
		ModelAndView model = new ModelAndView("link");
		try {
			model.addObject("link", extractLinkPreviewInfo(url));
		} catch (IOException e) {
			logger.error("Unable to connect to : {}", url);
			model.addObject("css", "danger");
			model.addObject("msg", "Unable to connect to '" + url + "': " + e.getMessage());
		}
		return model;
	}

	/**
	 * Parses the web page and extracts the info from meta tags required for preview
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	private Link extractLinkPreviewInfo(String url) throws IOException {
		if (!url.startsWith("http")) {
			url = "http://" + url;
		}
		Document document = Jsoup.connect(url).get();
		String title = getMetaTagContent(document, "meta[name=title]");
		String desc = getMetaTagContent(document, "meta[name=description]");
		String ogUrl = StringUtils.defaultIfBlank(getMetaTagContent(document, "meta[property=og:url]"), url);
		String ogTitle = getMetaTagContent(document, "meta[property=og:title]");
		String ogDesc = getMetaTagContent(document, "meta[property=og:description]");
		String ogImage = getMetaTagContent(document, "meta[property=og:image]");
		String ogImageAlt = getMetaTagContent(document, "meta[property=og:image:alt]");
		String domain = ogUrl;
		try {
			domain = InternetDomainName.from(new URL(ogUrl).getHost()).topPrivateDomain().toString();
		} catch (Exception e) {
			logger.warn("Unable to connect to extract domain name from : {}", url);
		}
		return new Link(domain, url, StringUtils.defaultIfBlank(ogTitle, title), StringUtils.defaultIfBlank(ogDesc, desc), ogImage, ogImageAlt);
	}

	/**
	 * Returns the given meta tag content
	 * 
	 * @param document
	 * @return
	 */
	private String getMetaTagContent(Document document, String cssQuery) {
		Element elm = document.select(cssQuery).first();
		if (elm != null) {
			return elm.attr("content");
		}
		return "";
	}
}
