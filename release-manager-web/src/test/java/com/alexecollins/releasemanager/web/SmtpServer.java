package com.alexecollins.releasemanager.web;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
public class SmtpServer implements Runnable, Closeable {

	private ServerSocket server;

	public static void main(String[] args) throws Exception {
		new SmtpServer().run();
	}

	public void start() {
		new Thread(this, "SmtpServer").start();
	}

	@Override
	public void close() throws IOException {
		server.close();
	}

	@Override
	public void run() {
		log("running");
		try {
			loop();
		} catch (IOException e) {
			System.err.println(e);
		}
		log("closed");
	}

	private void log(String text) {
		System.out.println(this.getClass().getSimpleName() + " " + text);
	}

	private void loop() throws IOException {
		server = new ServerSocket(10587);
		while (!server.isClosed()) {
			final Socket socket = server.accept();
			final BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			final PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			println(out, "220 " + InetAddress.getLocalHost().getHostAddress() + " ESMTP Postfix");
			String line;
			boolean data = false;
			while ((line = in.readLine()) != null) {
				log(line);
				switch (line) {
					case "DATA":
						println(out, "354 End data with <CR><LF>.<CR><LF>");
						break;
					case "QUIT":
						println(out, "221 Bye");
						break;
					case ".":
						data = false;
					default:
						if (!data)
							println(out, "250 Ok");
				}
			}

			socket.close();
		}
	}

	private void println(PrintWriter out, String text) {
		log(text);
		out.println(text);
		out.flush();
	}
}
