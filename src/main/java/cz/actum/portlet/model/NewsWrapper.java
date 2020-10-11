/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package cz.actum.portlet.model;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link News}.
 * </p>
 *
 * @author Andrey
 * @see News
 * @generated
 */
public class NewsWrapper implements ModelWrapper<News>, News {

	public NewsWrapper(News news) {
		_news = news;
	}

	@Override
	public Class<?> getModelClass() {
		return News.class;
	}

	@Override
	public String getModelClassName() {
		return News.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("id", getId());
		attributes.put("title", getTitle());
		attributes.put("body", getBody());
		attributes.put("date", getDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long id = (Long)attributes.get("id");

		if (id != null) {
			setId(id);
		}

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		String body = (String)attributes.get("body");

		if (body != null) {
			setBody(body);
		}

		Date date = (Date)attributes.get("date");

		if (date != null) {
			setDate(date);
		}
	}

	@Override
	public Object clone() {
		return new NewsWrapper((News)_news.clone());
	}

	@Override
	public int compareTo(cz.actum.portlet.model.News news) {
		return _news.compareTo(news);
	}

	/**
	 * Returns the body of this news.
	 *
	 * @return the body of this news
	 */
	@Override
	public String getBody() {
		return _news.getBody();
	}

	/**
	 * Returns the date of this news.
	 *
	 * @return the date of this news
	 */
	@Override
	public Date getDate() {
		return _news.getDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _news.getExpandoBridge();
	}

	/**
	 * Returns the ID of this news.
	 *
	 * @return the ID of this news
	 */
	@Override
	public long getId() {
		return _news.getId();
	}

	/**
	 * Returns the primary key of this news.
	 *
	 * @return the primary key of this news
	 */
	@Override
	public long getPrimaryKey() {
		return _news.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _news.getPrimaryKeyObj();
	}

	/**
	 * Returns the title of this news.
	 *
	 * @return the title of this news
	 */
	@Override
	public String getTitle() {
		return _news.getTitle();
	}

	@Override
	public int hashCode() {
		return _news.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _news.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _news.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _news.isNew();
	}

	@Override
	public void persist() {
		_news.persist();
	}

	/**
	 * Sets the body of this news.
	 *
	 * @param body the body of this news
	 */
	@Override
	public void setBody(String body) {
		_news.setBody(body);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_news.setCachedModel(cachedModel);
	}

	/**
	 * Sets the date of this news.
	 *
	 * @param date the date of this news
	 */
	@Override
	public void setDate(Date date) {
		_news.setDate(date);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_news.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_news.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_news.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the ID of this news.
	 *
	 * @param id the ID of this news
	 */
	@Override
	public void setId(long id) {
		_news.setId(id);
	}

	@Override
	public void setNew(boolean n) {
		_news.setNew(n);
	}

	/**
	 * Sets the primary key of this news.
	 *
	 * @param primaryKey the primary key of this news
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_news.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_news.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the title of this news.
	 *
	 * @param title the title of this news
	 */
	@Override
	public void setTitle(String title) {
		_news.setTitle(title);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel
		<cz.actum.portlet.model.News> toCacheModel() {

		return _news.toCacheModel();
	}

	@Override
	public cz.actum.portlet.model.News toEscapedModel() {
		return new NewsWrapper(_news.toEscapedModel());
	}

	@Override
	public String toString() {
		return _news.toString();
	}

	@Override
	public cz.actum.portlet.model.News toUnescapedModel() {
		return new NewsWrapper(_news.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _news.toXmlString();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof NewsWrapper)) {
			return false;
		}

		NewsWrapper newsWrapper = (NewsWrapper)object;

		if (Objects.equals(_news, newsWrapper._news)) {
			return true;
		}

		return false;
	}

	@Override
	public News getWrappedModel() {
		return _news;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _news.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _news.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_news.resetOriginalValues();
	}

	private final News _news;

}