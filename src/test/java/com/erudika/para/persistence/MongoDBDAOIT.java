/*
 * Copyright 2013-2016 Erudika. http://erudika.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * For issues and patches go to: https://github.com/erudika
 */
package com.erudika.para.persistence;

import com.erudika.para.utils.Config;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Luca Venturella [lucaventurella@gmail.com]
 */
public class MongoDBDAOIT extends DAOTest {

	@BeforeClass
	public static void setUpClass() throws InterruptedException {
		dao = new MongoDBDAO();
		MongoDBUtils.createTable(Config.APP_NAME_NS);
		MongoDBUtils.createTable(appid1);
		MongoDBUtils.createTable(appid2);
	}

	@AfterClass
	public static void tearDownClass() {
		MongoDBUtils.deleteTable(Config.APP_NAME_NS);
		MongoDBUtils.deleteTable(appid1);
		MongoDBUtils.deleteTable(appid2);
		MongoDBUtils.shutdownClient();
	}

	@Test
	public void testCreateDeleteExistsTable() throws InterruptedException {
		String testappid1 = "test-index";
		String badAppid = "test index 123";

		MongoDBUtils.createTable("");
		assertFalse(MongoDBUtils.existsTable(""));

		MongoDBUtils.createTable(testappid1);
		assertTrue(MongoDBUtils.existsTable(testappid1));

		MongoDBUtils.deleteTable(testappid1);
		assertFalse(MongoDBUtils.existsTable(testappid1));

		assertFalse(MongoDBUtils.createTable(badAppid));
		assertFalse(MongoDBUtils.existsTable(badAppid));
		assertFalse(MongoDBUtils.deleteTable(badAppid));
	}

}