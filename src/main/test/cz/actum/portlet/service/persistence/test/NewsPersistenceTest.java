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

package cz.actum.portlet.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import cz.actum.portlet.exception.NoSuchNewsException;
import cz.actum.portlet.model.News;
import cz.actum.portlet.service.NewsLocalServiceUtil;
import cz.actum.portlet.service.persistence.NewsPersistence;
import cz.actum.portlet.service.persistence.NewsUtil;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class NewsPersistenceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(
				Propagation.REQUIRED, "cz.actum.portlet.service"));

	@Before
	public void setUp() {
		_persistence = NewsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<News> iterator = _newses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		News news = _persistence.create(pk);

		Assert.assertNotNull(news);

		Assert.assertEquals(news.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		News newNews = addNews();

		_persistence.remove(newNews);

		News existingNews = _persistence.fetchByPrimaryKey(
			newNews.getPrimaryKey());

		Assert.assertNull(existingNews);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addNews();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		News newNews = _persistence.create(pk);

		newNews.setTitle(RandomTestUtil.randomString());

		newNews.setBody(RandomTestUtil.randomString());

		newNews.setDate(RandomTestUtil.nextDate());

		_newses.add(_persistence.update(newNews));

		News existingNews = _persistence.findByPrimaryKey(
			newNews.getPrimaryKey());

		Assert.assertEquals(existingNews.getId(), newNews.getId());
		Assert.assertEquals(existingNews.getTitle(), newNews.getTitle());
		Assert.assertEquals(existingNews.getBody(), newNews.getBody());
		Assert.assertEquals(
			Time.getShortTimestamp(existingNews.getDate()),
			Time.getShortTimestamp(newNews.getDate()));
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		News newNews = addNews();

		News existingNews = _persistence.findByPrimaryKey(
			newNews.getPrimaryKey());

		Assert.assertEquals(existingNews, newNews);
	}

	@Test(expected = NoSuchNewsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<News> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create(
			"news_News", "id", true, "title", true, "body", true, "date", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		News newNews = addNews();

		News existingNews = _persistence.fetchByPrimaryKey(
			newNews.getPrimaryKey());

		Assert.assertEquals(existingNews, newNews);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		News missingNews = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingNews);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		News newNews1 = addNews();
		News newNews2 = addNews();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newNews1.getPrimaryKey());
		primaryKeys.add(newNews2.getPrimaryKey());

		Map<Serializable, News> newses = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertEquals(2, newses.size());
		Assert.assertEquals(newNews1, newses.get(newNews1.getPrimaryKey()));
		Assert.assertEquals(newNews2, newses.get(newNews2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, News> newses = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertTrue(newses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		News newNews = addNews();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newNews.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, News> newses = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertEquals(1, newses.size());
		Assert.assertEquals(newNews, newses.get(newNews.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, News> newses = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertTrue(newses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		News newNews = addNews();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newNews.getPrimaryKey());

		Map<Serializable, News> newses = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertEquals(1, newses.size());
		Assert.assertEquals(newNews, newses.get(newNews.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			NewsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<News>() {

				@Override
				public void performAction(News news) {
					Assert.assertNotNull(news);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		News newNews = addNews();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			News.class, _dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id", newNews.getId()));

		List<News> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		News existingNews = result.get(0);

		Assert.assertEquals(existingNews, newNews);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			News.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq("id", RandomTestUtil.nextLong()));

		List<News> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		News newNews = addNews();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			News.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("id"));

		Object newId = newNews.getId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in("id", new Object[] {newId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingId = result.get(0);

		Assert.assertEquals(existingId, newId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			News.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("id"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"id", new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected News addNews() throws Exception {
		long pk = RandomTestUtil.nextLong();

		News news = _persistence.create(pk);

		news.setTitle(RandomTestUtil.randomString());

		news.setBody(RandomTestUtil.randomString());

		news.setDate(RandomTestUtil.nextDate());

		_newses.add(_persistence.update(news));

		return news;
	}

	private List<News> _newses = new ArrayList<News>();
	private NewsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}