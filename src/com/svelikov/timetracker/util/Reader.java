package com.svelikov.timetracker.util;

import java.io.InputStream;

public interface Reader {

	void read();

	void read(String path);

	void read(InputStream in);
}
