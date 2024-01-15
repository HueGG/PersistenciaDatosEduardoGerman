package com.example.inventory.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    /**
     * El argumento onConflict le indica a Room qué hacer en caso de conflicto.
     * La estrategia OnConflictStrategy.IGNORE ignora un elemento nuevo.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Item)

    /**
     * La función de update recibe como parámetro un item
     * suspend permite que la operación o subproceso se ejecute por separado
     */
    @Update
    suspend fun update(item: Item)

    /**
     * La anotación @Delete borra un elemento o una lista de elementos.
     * Se le deben pasar las entidades que deseas borrar.
     */
    @Delete
    suspend fun delete(item: Item)

    /**
     * Consulta personalizada mediante ID
     */
    @Query("SELECT * from items WHERE id = :id")
    fun getItem(id: Int): Flow<Item>


    /**
     * Consulta que selecciona todos los elementos de la tabla
     */
    @Query("SELECT * from items ORDER BY name ASC")
    fun getAllItems(): Flow<List<Item>>
}
