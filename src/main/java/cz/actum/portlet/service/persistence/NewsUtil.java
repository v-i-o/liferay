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

package cz.actum.portlet.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import cz.actum.portlet.model.News;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the news service. This utility wraps <code>cz.actum.portlet.service.persistence.impl.NewsPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Andrey
 * @see NewsPersistence
 * @generated
 */
public class NewsUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(News news) {
		getPersistence().clearCache(news);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, News> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<News> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<News> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<News> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<News> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static News update(News news) {
		return getPersistence().update(news);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static News update(News news, ServiceContext serviceContext) {
		return getPersistence().update(news, serviceContext);
	}

	/**
	 * Caches the news in the entity cache if it is enabled.
	 *
	 * @param news the news
	 */
	public static void cacheResult(News news) {
		getPersistence().cacheResult(news);
	}

	/**
	 * Caches the newses in the entity cache if it is enabled.
	 *
	 * @param newses the newses
	 */
	public static void cacheResult(List<News> newses) {
		getPersistence().cacheResult(newses);
	}

	/**
	 * Creates a new news with the primary key. Does not add the news to the database.
	 *
	 * @param id the primary key for the new news
	 * @return the new news
	 */
	public static News create(long id) {
		return getPersistence().create(id);
	}

	/**
	 * Removes the news with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the news
	 * @return the news that was removed
	 * @throws NoSuchNewsException if a news with the primary key could not be found
	 */
	public static News remove(long id)
		throws cz.actum.portlet.exception.NoSuchNewsException {

		return getPersistence().remove(id);
	}

	public static News updateImpl(News news) {
		return getPersistence().updateImpl(news);
	}

	/**
	 * Returns the news with the primary key or throws a <code>NoSuchNewsException</code> if it could not be found.
	 *
	 * @param id the primary key of the news
	 * @return the news
	 * @throws NoSuchNewsException if a news with the primary key could not be found
	 */
	public static News findByPrimaryKey(long id)
		throws cz.actum.portlet.exception.NoSuchNewsException {

		return getPersistence().findByPrimaryKey(id);
	}

	/**
	 * Returns the news with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the news
	 * @return the news, or <code>null</code> if a news with the primary key could not be found
	 */
	public static News fetchByPrimaryKey(long id) {
		return getPersistence().fetchByPrimaryKey(id);
	}

	/**
	 * Returns all the newses.
	 *
	 * @return the newses
	 */
	public static List<News> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the newses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of newses
	 * @param end the upper bound of the range of newses (not inclusive)
	 * @return the range of newses
	 */
	public static List<News> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the newses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of newses
	 * @param end the upper bound of the range of newses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of newses
	 */
	public static List<News> findAll(
		int start, int end, OrderByComparator<News> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the newses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of newses
	 * @param end the upper bound of the range of newses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of newses
	 */
	public static List<News> findAll(
		int start, int end, OrderByComparator<News> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the newses from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of newses.
	 *
	 * @return the number of newses
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static NewsPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<NewsPersistence, NewsPersistence>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(NewsPersistence.class);

		ServiceTracker<NewsPersistence, NewsPersistence> serviceTracker =
			new ServiceTracker<NewsPersistence, NewsPersistence>(
				bundle.getBundleContext(), NewsPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}