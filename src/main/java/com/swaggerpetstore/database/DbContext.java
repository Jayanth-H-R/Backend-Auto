package com.swaggerpetstore.database;

import java.sql.SQLException;

public class DbContext {
    /** Fetch user by ID from the user database
        * this is how we can fetch data from the database using dbutils
        * Note: The commented-out method is an example of how to fetch a FundsTransferRequest by externalRequestId.
        * new BeanHandler<>(FundsTransferRequest.class) this is used to map the result set from db to the FundsTransferRequest class
        * @param externalRequestId
        * @return FundsTransferRequest
        */


    /**
     *
      *
     * public static FundsTransferRequest getByExternalRequestId(String externalRequestId) {
     *         String query = "SELECT * FROM cbp_db.funds_transfer_requests WHERE external_request_id = ?";
     *         try {
     *             QueryRunner runner = new QueryRunner();
     *             return runner.query(
     *                     ConnectionFactory.getConnection(DbConfigEnum.CBP.getDbName()),
     *                     query,
     *                     new BeanHandler<>(FundsTransferRequest.class),
     *                     externalRequestId
     *             );
     *         } catch (SQLException e) {
     *             e.printStackTrace();
     *             throw new RuntimeException("Failed to fetch external request Id", e);
     *         }
     *     }
     */

}
