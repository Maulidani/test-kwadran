package test.assignment.test_kwadran.database

import androidx.room.*

@Dao
interface DataDao {
    @Insert
    suspend fun insertData(data: TableData): Long

    @Query("SELECT * FROM table_data")
    suspend fun getAllData(): List<TableData>

}