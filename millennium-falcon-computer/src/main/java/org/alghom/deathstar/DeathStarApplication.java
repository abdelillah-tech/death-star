package org.alghom.deathstar;

import org.alghom.deathstar.exposure.CliApplication;
import org.alghom.deathstar.exposure.GuiStartUnit;
import org.springframework.boot.SpringApplication;

public class DeathStarApplication {
	public static void main(String[] args) {
		if(args == null || args.length == 0) {
			System.out.println("Starting the ship in GUI Mode");
			SpringApplication.run(GuiStartUnit.class);
		} else {
			System.out.println("Starting the ship in CLI Mode");
			CliApplication.run(args);
		}
	}
}
