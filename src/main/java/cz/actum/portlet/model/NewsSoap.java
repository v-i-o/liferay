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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link cz.actum.portlet.service.http.NewsServiceSoap}.
 *
 * @author Andrey
 * @generated
 */
public class NewsSoap implements Serializable {

	public static NewsSoap toSoapModel(News model) {
		NewsSoap soapModel = new NewsSoap();

		soapModel.setId(model.getId());
		soapModel.setTitle(model.getTitle());
		soapModel.setBody(model.getBody());
		soapModel.setDate(model.getDate());

		return soapModel;
	}

	public static NewsSoap[] toSoapModels(News[] models) {
		NewsSoap[] soapModels = new NewsSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static NewsSoap[][] toSoapModels(News[][] models) {
		NewsSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new NewsSoap[models.length][models[0].length];
		}
		else {
			soapModels = new NewsSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static NewsSoap[] toSoapModels(List<News> models) {
		List<NewsSoap> soapModels = new ArrayList<NewsSoap>(models.size());

		for (News model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new NewsSoap[soapModels.size()]);
	}

	public NewsSoap() {
	}

	public long getPrimaryKey() {
		return _id;
	}

	public void setPrimaryKey(long pk) {
		setId(pk);
	}

	public long getId() {
		return _id;
	}

	public void setId(long id) {
		_id = id;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public String getBody() {
		return _body;
	}

	public void setBody(String body) {
		_body = body;
	}

	public Date getDate() {
		return _date;
	}

	public void setDate(Date date) {
		_date = date;
	}

	private long _id;
	private String _title;
	private String _body;
	private Date _date;

}