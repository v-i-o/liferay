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

package cz.actum.portlet.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.spring.extender.service.ServiceReference;

import cz.actum.portlet.exception.NoSuchNewsException;
import cz.actum.portlet.model.News;
import cz.actum.portlet.model.impl.NewsImpl;
import cz.actum.portlet.model.impl.NewsModelImpl;
import cz.actum.portlet.service.persistence.NewsPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the news service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Andrey
 * @generated
 */
public class NewsPersistenceImpl
	extends BasePersistenceImpl<News> implements NewsPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>NewsUtil</code> to access the news persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		NewsImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public NewsPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("id", "id_");
		dbColumnNames.put("date", "date_");

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
				"_dbColumnNames");

			field.setAccessible(true);

			field.set(this, dbColumnNames);
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception, exception);
			}
		}

		setModelClass(News.class);
	}

	/**
	 * Caches the news in the entity cache if it is enabled.
	 *
	 * @param news the news
	 */
	@Override
	public void cacheResult(News news) {
		entityCache.putResult(
			NewsModelImpl.ENTITY_CACHE_ENABLED, NewsImpl.class,
			news.getPrimaryKey(), news);

		news.resetOriginalValues();
	}

	/**
	 * Caches the newses in the entity cache if it is enabled.
	 *
	 * @param newses the newses
	 */
	@Override
	public void cacheResult(List<News> newses) {
		for (News news : newses) {
			if (entityCache.getResult(
					NewsModelImpl.ENTITY_CACHE_ENABLED, NewsImpl.class,
					news.getPrimaryKey()) == null) {

				cacheResult(news);
			}
			else {
				news.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all newses.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(NewsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the news.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(News news) {
		entityCache.removeResult(
			NewsModelImpl.ENTITY_CACHE_ENABLED, NewsImpl.class,
			news.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<News> newses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (News news : newses) {
			entityCache.removeResult(
				NewsModelImpl.ENTITY_CACHE_ENABLED, NewsImpl.class,
				news.getPrimaryKey());
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				NewsModelImpl.ENTITY_CACHE_ENABLED, NewsImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new news with the primary key. Does not add the news to the database.
	 *
	 * @param id the primary key for the new news
	 * @return the new news
	 */
	@Override
	public News create(long id) {
		News news = new NewsImpl();

		news.setNew(true);
		news.setPrimaryKey(id);

		return news;
	}

	/**
	 * Removes the news with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the news
	 * @return the news that was removed
	 * @throws NoSuchNewsException if a news with the primary key could not be found
	 */
	@Override
	public News remove(long id) throws NoSuchNewsException {
		return remove((Serializable)id);
	}

	/**
	 * Removes the news with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the news
	 * @return the news that was removed
	 * @throws NoSuchNewsException if a news with the primary key could not be found
	 */
	@Override
	public News remove(Serializable primaryKey) throws NoSuchNewsException {
		Session session = null;

		try {
			session = openSession();

			News news = (News)session.get(NewsImpl.class, primaryKey);

			if (news == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchNewsException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(news);
		}
		catch (NoSuchNewsException noSuchEntityException) {
			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected News removeImpl(News news) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(news)) {
				news = (News)session.get(
					NewsImpl.class, news.getPrimaryKeyObj());
			}

			if (news != null) {
				session.delete(news);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (news != null) {
			clearCache(news);
		}

		return news;
	}

	@Override
	public News updateImpl(News news) {
		boolean isNew = news.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(news);

				news.setNew(false);
			}
			else {
				news = (News)session.merge(news);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew) {
			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(
			NewsModelImpl.ENTITY_CACHE_ENABLED, NewsImpl.class,
			news.getPrimaryKey(), news, false);

		news.resetOriginalValues();

		return news;
	}

	/**
	 * Returns the news with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the news
	 * @return the news
	 * @throws NoSuchNewsException if a news with the primary key could not be found
	 */
	@Override
	public News findByPrimaryKey(Serializable primaryKey)
		throws NoSuchNewsException {

		News news = fetchByPrimaryKey(primaryKey);

		if (news == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchNewsException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return news;
	}

	/**
	 * Returns the news with the primary key or throws a <code>NoSuchNewsException</code> if it could not be found.
	 *
	 * @param id the primary key of the news
	 * @return the news
	 * @throws NoSuchNewsException if a news with the primary key could not be found
	 */
	@Override
	public News findByPrimaryKey(long id) throws NoSuchNewsException {
		return findByPrimaryKey((Serializable)id);
	}

	/**
	 * Returns the news with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the news
	 * @return the news, or <code>null</code> if a news with the primary key could not be found
	 */
	@Override
	public News fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			NewsModelImpl.ENTITY_CACHE_ENABLED, NewsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		News news = (News)serializable;

		if (news == null) {
			Session session = null;

			try {
				session = openSession();

				news = (News)session.get(NewsImpl.class, primaryKey);

				if (news != null) {
					cacheResult(news);
				}
				else {
					entityCache.putResult(
						NewsModelImpl.ENTITY_CACHE_ENABLED, NewsImpl.class,
						primaryKey, nullModel);
				}
			}
			catch (Exception exception) {
				entityCache.removeResult(
					NewsModelImpl.ENTITY_CACHE_ENABLED, NewsImpl.class,
					primaryKey);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return news;
	}

	/**
	 * Returns the news with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the news
	 * @return the news, or <code>null</code> if a news with the primary key could not be found
	 */
	@Override
	public News fetchByPrimaryKey(long id) {
		return fetchByPrimaryKey((Serializable)id);
	}

	@Override
	public Map<Serializable, News> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, News> map = new HashMap<Serializable, News>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			News news = fetchByPrimaryKey(primaryKey);

			if (news != null) {
				map.put(primaryKey, news);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				NewsModelImpl.ENTITY_CACHE_ENABLED, NewsImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (News)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler sb = new StringBundler(
			(uncachedPrimaryKeys.size() * 2) + 1);

		sb.append(_SQL_SELECT_NEWS_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			sb.append((long)primaryKey);

			sb.append(",");
		}

		sb.setIndex(sb.index() - 1);

		sb.append(")");

		String sql = sb.toString();

		Session session = null;

		try {
			session = openSession();

			Query query = session.createQuery(sql);

			for (News news : (List<News>)query.list()) {
				map.put(news.getPrimaryKeyObj(), news);

				cacheResult(news);

				uncachedPrimaryKeys.remove(news.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					NewsModelImpl.ENTITY_CACHE_ENABLED, NewsImpl.class,
					primaryKey, nullModel);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the newses.
	 *
	 * @return the newses
	 */
	@Override
	public List<News> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<News> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<News> findAll(
		int start, int end, OrderByComparator<News> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
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
	@Override
	public List<News> findAll(
		int start, int end, OrderByComparator<News> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<News> list = null;

		if (useFinderCache) {
			list = (List<News>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_NEWS);

				appendOrderByComparator(
					new com.liferay.petra.string.StringBundler(sb.toString()), _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_NEWS;

				sql = sql.concat(NewsModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<News>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the newses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (News news : findAll()) {
			remove(news);
		}
	}

	/**
	 * Returns the number of newses.
	 *
	 * @return the number of newses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_NEWS);

				count = (Long)query.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return NewsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the news persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			NewsModelImpl.ENTITY_CACHE_ENABLED,
			NewsModelImpl.FINDER_CACHE_ENABLED, NewsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			NewsModelImpl.ENTITY_CACHE_ENABLED,
			NewsModelImpl.FINDER_CACHE_ENABLED, NewsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			NewsModelImpl.ENTITY_CACHE_ENABLED,
			NewsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	}

	public void destroy() {
		entityCache.removeCache(NewsImpl.class.getName());

		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_NEWS = "SELECT news FROM News news";

	private static final String _SQL_SELECT_NEWS_WHERE_PKS_IN =
		"SELECT news FROM News news WHERE id_ IN (";

	private static final String _SQL_COUNT_NEWS =
		"SELECT COUNT(news) FROM News news";

	private static final String _ORDER_BY_ENTITY_ALIAS = "news.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No News exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		NewsPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"id", "date"});

}