/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package biz.wittkemper.test;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import biz.wittkemper.jfire.data.dao.JPAEntityManager;
import biz.wittkemper.jfire.data.entity.Mitglied;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

/**
 *
 * @author joerg
 */
public class DBTest {

	public DBTest() {
	}

	@BeforeAll
	public static void setUpClass() {
	}

	@AfterAll
	public static void tearDownClass() {
	}

	@BeforeEach
	public void setUp() {
	}

	@AfterEach
	public void tearDown() {
	}

	@Test
	public void testDBCon() {
		EntityManager entityManager = JPAEntityManager.getInstance();

		Query q = entityManager.createQuery("from Mitglied", Mitglied.class);
		List<Mitglied> mitglieder = q.getResultList();

		mitglieder.forEach(m -> {
			System.out.println(m.getStatus().getBezeichnungKurz());
		});
	}
}
