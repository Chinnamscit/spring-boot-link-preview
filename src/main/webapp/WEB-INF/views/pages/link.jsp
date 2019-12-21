<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
	<c:when test="${empty msg}">
		<div class="card shadow mb-2">
			<div class="card-body">
				<h6 class="text-muted pb-2 m-0">
					<i class="fas fa-link"></i> Link Preview
				</h6>
				<a href="${link.url}" class="custom-card" target="_blank" data-image="${link.image}" data-image-alt="${link.imageAlt}" data-title="${link.title}"
					data-desc="${link.desc}" data-domain="${link.domain}">
					<div class="card">
						<img src="${link.image}" class="card-img-top" alt="${link.imageAlt}" style="max-height: 300px; object-fit: fill;">
						<div class="card-footer">
							<h5 class="card-title">${link.title}</h5>
							<p class="card-text">${link.desc}</p>
							<p class="card-text">
								<small class="text-muted">${link.domain}</small>
							</p>
						</div>
					</div>
				</a>
			</div>
		</div>
	</c:when>
	<c:otherwise>
		<div class="alert alert-${css} alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
				<span aria-hidden="true">×</span>
			</button>
			<strong>${msg}</strong>
		</div>
	</c:otherwise>
</c:choose>