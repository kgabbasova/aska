package com.aska.repositories;


//public   class UserRepositoryDB implements UserRepository {
//
//    @Autowired
//   DataSource dataSource = new DBConfig().postgresqlDataSource();
//
//
//
//    //language = SQL
//    private final String IS_EXIST_SQL = "SELECT * FROM survey_user WHERE username = ?";
//
//    @Override
//    public boolean isExist(String username) throws SQLException {
//
//        Connection connection = dataSource.getConnection();
//        PreparedStatement preparedStatement = connection.prepareStatement(IS_EXIST_SQL);
//        preparedStatement.setString(1, username);
//        ResultSet resultSet = preparedStatement.executeQuery();
//        if (resultSet.next()) {
//            return true;
//        }
//
//        return false;
//    }
//}
