package org.eredlab.g4;

import org.eredlab.g4.rif.server.G4Server;

/**
 * 系统启动
 * @author XiongChun
 * @since 2009-01-13
 */
public class Run {
	public static void main(String[] args) {
		G4Server server = new G4Server(
				"F:\\eRedG4\\web\\03.WebRoot",
				"/eRedG4",
				8899
				);
		server.stop();
		try {
			server.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
