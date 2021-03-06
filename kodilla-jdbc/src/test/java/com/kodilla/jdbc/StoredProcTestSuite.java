package com.kodilla.jdbc;

import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StoredProcTestSuite {
    @Test
    public void testUpdateVipLevels() throws SQLException {
        //Given
        DbManager dbManager = DbManager.getInstance();
        String sqlUpdate = "UPDATE READERS SET VIP_LEVEL=\"Not set\"";
        Statement statement = dbManager.getConnection().createStatement();
        statement.executeUpdate(sqlUpdate);

        //When
        String sqlProcedure = "CALL UpdateVipLevels()";
        statement.execute(sqlProcedure);

        //Then
        String sqlCheckTable = "select count(*) as how_many from READERS where VIP_LEVEL=\"Not set\"";
        ResultSet resultSet = statement.executeQuery(sqlCheckTable);
        int howMany = -1;
        if (resultSet.next()){
            howMany = resultSet.getInt("how_many");
        }

        Assert.assertEquals(0, howMany);
    }

    @Test
    public void testUpdateBestsellers() throws SQLException{
        //Given
        DbManager dbManager = DbManager.getInstance();
        String sqlUpdate = "update BOOKS set BESTSELLER=false";
        Statement statement = dbManager.getConnection().createStatement();
        statement.executeUpdate(sqlUpdate);

        //When
        String sqlProcedure = "call UpdateBestsellers()";
        statement.execute(sqlProcedure);

        //Then
        String sqlCheck = "select count(*) as best_true from BOOKS where BESTSELLER=true";
        ResultSet resultSet = statement.executeQuery(sqlCheck);

        int count = 0;

        while (resultSet.next()){
            count++;
        }

        Assert.assertEquals(1, count);
    }
}
