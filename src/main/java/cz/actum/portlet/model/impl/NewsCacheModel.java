/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 * <p>
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * <p>
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package cz.actum.portlet.model.impl;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.StringBundler;
import cz.actum.portlet.model.News;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Date;

/**
 * The cache model class for representing News in entity cache.
 *
 * @author Andrey
 * @generated
 */
public class NewsCacheModel implements CacheModel<News>, Externalizable {

    public long id;
    public String title;
    public String body;
    public long date;

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof NewsCacheModel)) {
            return false;
        }

        NewsCacheModel newsCacheModel = (NewsCacheModel) object;

		return id == newsCacheModel.id;
	}

    @Override
    public int hashCode() {
        return (int) id;
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(9);

        sb.append("{id=");
        sb.append(id);
        sb.append(", title=");
        sb.append(title);
        sb.append(", body=");
        sb.append(body);
        sb.append(", date=");
        sb.append(date);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public News toEntityModel() {
        NewsImpl newsImpl = new NewsImpl();

        newsImpl.setId(id);

        if (title == null) {
            newsImpl.setTitle("");
        } else {
            newsImpl.setTitle(title);
        }

        if (body == null) {
            newsImpl.setBody("");
        } else {
            newsImpl.setBody(body);
        }

        if (date == Long.MIN_VALUE) {
            newsImpl.setDate(null);
        } else {
            newsImpl.setDate(new Date(date));
        }

        newsImpl.resetOriginalValues();

        return newsImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        id = objectInput.readLong();
        title = objectInput.readUTF();
        body = objectInput.readUTF();
        date = objectInput.readLong();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeLong(id);

        if (title == null) {
            objectOutput.writeUTF("");
        } else {
            objectOutput.writeUTF(title);
        }

        if (body == null) {
            objectOutput.writeUTF("");
        } else {
            objectOutput.writeUTF(body);
        }

        objectOutput.writeLong(date);
    }

}