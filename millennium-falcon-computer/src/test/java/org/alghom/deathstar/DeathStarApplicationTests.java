package org.alghom.deathstar;

import org.alghom.deathstar.domain.models.*;
import org.alghom.deathstar.domain.services.ComputerService;
import org.alghom.deathstar.domain.services.ComputerServiceImpl;
import org.alghom.deathstar.domain.services.FalconService;
import org.alghom.deathstar.domain.services.FalconServiceImpl;
import org.alghom.deathstar.exposure.controllers.FalconCliController;
import org.alghom.deathstar.exposure.controllers.FalconCliControllerImpl;
import org.alghom.deathstar.utils.Initiator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.alghom.deathstar.utils.TestPlanets.*;

class DeathStarApplicationTests {
	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	private static FalconCliController falconController;

	@BeforeAll
	public static void setup() {
		MillenniumFalcon millenniumFalcon = new MillenniumFalcon(6, TATOOINE, ENDOR);
		GalaxyMap galaxyMap = Initiator.initGalaxy(getRoutes());
		ComputerService computerService = new ComputerServiceImpl(millenniumFalcon, galaxyMap);
		FalconService falconService = new FalconServiceImpl(computerService);
		falconController = new FalconCliControllerImpl(falconService);
	}

	@BeforeEach
	public void init() {
		System.setOut(new PrintStream(outputStreamCaptor));
	}

	@AfterEach
	public void tearDown() {
		System.setOut(standardOut);
	}

	@Test
	public void death_star_is_safe() {
		Empire empire = new Empire(10, getBountyHunters());
		falconController.getTheOdd(empire);
		assertEquals("100.0%", outputStreamCaptor.toString().trim());
	}

	@Test
	public void one_bounty_temptation() {
		Empire empire = new Empire(9, getBountyHunters());
		falconController.getTheOdd(empire);
		assertEquals("90.0%", outputStreamCaptor.toString().trim());
	}

	@Test
	public void two_bounty_temptations() {
		Empire empire = new Empire(8, getBountyHunters());
		falconController.getTheOdd(empire);
		assertEquals("81.0%", outputStreamCaptor.toString().trim());
	}

	@Test
	public void no_chance() {
		Empire empire = new Empire(7, getBountyHunters());
		falconController.getTheOdd(empire);
		assertEquals("0.0%", outputStreamCaptor.toString().trim());
	}

	private static List<Route> getRoutes() {
		List<Route> routes = new ArrayList<>();

		routes.add(new Route(TATOOINE, DAGOBAH, 6));
		routes.add(new Route(DAGOBAH, ENDOR, 4));
		routes.add(new Route(DAGOBAH, HOTH, 1));
		routes.add(new Route(HOTH, ENDOR, 1));
		routes.add(new Route(TATOOINE, HOTH, 6));

		return routes;
	}

	private static List<BountyHunter> getBountyHunters() {
		List<BountyHunter> bountyHunters = new ArrayList<>();

		bountyHunters.add(new BountyHunter(HOTH, 6));
		bountyHunters.add(new BountyHunter(HOTH, 7));
		bountyHunters.add(new BountyHunter(HOTH, 8));

		return bountyHunters;
	}
}
