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

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the News service. Represents a row in the &quot;news_News&quot; database table, with each column mapped to a property of this class.
 *
 * @author Andrey
 * @see NewsModel
 * @generated
 */
@ImplementationClassName("cz.actum.portlet.model.impl.NewsImpl")
@ProviderType
public interface News extends NewsModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>cz.actum.portlet.model.impl.NewsImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<News, Long> ID_ACCESSOR =
		new Accessor<News, Long>() {

			@Override
			public Long get(News news) {
				return news.getId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<News> getTypeClass() {
				return News.class;
			}

		};

}