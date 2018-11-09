# elasticsearch-jdbc

  Use dbcp2

		BasicDataSource basicDataSource = new BasicDataSource();
		// 创建连接池 一次性创建多个连接池

		// 连接池 创建连接 ---需要四个参数
		basicDataSource.setDriverClassName("com.elasticsearch.jdbc.ElasticsearchDriver");
		basicDataSource.setUrl("jdbc:elasticsearch://127.0.0.1:5000");

		// 从连接池中获取连接
		Connection conn = basicDataSource.getConnection();
		String sql = "select SRC_IP,SRC_PORT from \"my_test-*\"";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			System.out.println(rs.getString("SRC_IP"));
		}
		basicDataSource.close();
    
 
  Use DriverManager

		String sql = "select SRC_IP,SRC_PORT from my_test* where SRC_PORT between 10 and 100 limit 1000";
		String url = "jdbc:elasticsearch://127.0.0.1:5000";
		Connection connection = DriverManager.getConnection(url, "test", null);
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(sql);
		ResultSetMetaData meta = rs.getMetaData();
		String columns = "|";
		for (int i = 0; i < meta.getColumnCount(); i++) {
			columns += meta.getColumnLabel(i) + " | ";
		}
		System.out.println(columns);
		while (rs.next()) {
			String row = "|";
			for (int i = 0; i < meta.getColumnCount(); i++) {
				row += rs.getString(i) + " | ";
			}
			System.out.println(row);
		}   
