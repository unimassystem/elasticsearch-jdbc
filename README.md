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
    
    
