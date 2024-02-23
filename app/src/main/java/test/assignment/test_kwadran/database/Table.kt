package test.assignment.test_kwadran.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_data")
data class TableData(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int? = null,
    @ColumnInfo(name = "max") val max: String,
    @ColumnInfo(name = "urutan") val urutan: String,
)
