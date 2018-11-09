package com.elasticsearch.jdbc;

import java.io.Closeable;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;


public class ElasticsearchDriver implements Driver, Closeable {

	private static final String DRIVER_URL_START = "jdbc:elasticsearch:";
	static {
		try {
			DriverManager.registerDriver(new ElasticsearchDriver());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub

	}
	
	
	private URI parseURL(String url, Properties info) throws SQLException {
		URI uri ;
		try {
			uri = new URI(url.substring(DRIVER_URL_START.length()));
		}catch (URISyntaxException e) {
            throw new SQLException("Invalid JDBC URL: " + url, e);
        }
		return uri;
	}
	
	

	@Override
	public boolean acceptsURL(String uri) throws SQLException {
		// TODO Auto-generated method stub
		return uri.startsWith(DRIVER_URL_START);
	}

	@Override
	public Connection connect(String url, Properties info) throws SQLException {
		// TODO Auto-generated method stub
		URI uri = parseURL(url,info);
		QueryExecutor executor = new QueryExecutor(uri,info);
		return new ElasticsearchConnection(executor);
	}

	@Override
	public int getMajorVersion() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMinorVersion() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public DriverPropertyInfo[] getPropertyInfo(String arg0, Properties arg1) throws SQLException {
		// TODO Auto-generated method stub
		return new DriverPropertyInfo[0];
	}

	@Override
	public boolean jdbcCompliant() {
		// TODO Auto-generated method stub
		return false;
	}
}
