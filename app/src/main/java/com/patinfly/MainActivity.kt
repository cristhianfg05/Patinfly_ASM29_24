package com.patinfly

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.patinfly.data.datasource.RentDataSource
import com.patinfly.data.datasource.ScooterDataSource
import com.patinfly.data.datasource.UserDataSource
import com.patinfly.data.model.RentModel
import com.patinfly.data.model.UserModel
import com.patinfly.data.model.ScooterModel
import com.patinfly.data.repository.RentRepository
import com.patinfly.data.repository.ScooterRepository
import com.patinfly.data.repository.UserRepository
import com.patinfly.domain.model.Scooter
import java.time.LocalDate
import java.util.Date
import java.util.UUID

class MainActivity : AppCompatActivity() {

    private val userRepository = UserRepository(UserDataSource.getInstance(applicationContext))
    private val rentRepository = RentRepository(RentDataSource.getInstance(applicationContext))
    private val scooterRepository = ScooterRepository(ScooterDataSource.getInstance(applicationContext))

    override fun onStart() {
        super.onStart()
        initializeAndCheckRepositories()
    }

    private fun initializeAndCheckRepositories() {
        // Crear instancias
        val user = UserModel(UUID.randomUUID(), "user123","email", false,null,Date(), 0, "testPassword" )
        val scooter = ScooterModel(UUID.fromString("ScooterX"), "ScooterX", "1111", 2.4, 3.1, true, 100.0,"test",null)
        val rent = RentModel(UUID.randomUUID(), user.uuid, scooter.uuid, Date(),Date())

        // Añadir instancias a los repositorios
        userRepository.registerUser(user)
        scooterRepository.addNewScooter(scooter)
        rentRepository.createRent(rent)

        // Verificar que las instancias se han añadido correctamente
        val userAdded = userRepository.getUserByUsername(user.username) != null
        val scooterAdded = scooterRepository.getScooterByUUID(scooter.uuid) != null
        val rentAdded = rentRepository.getRentByUserUUID(user.uuid) != null

        // Puedes usar log para mostrar los resultados o hacer otros tipos de verificaciones
        Log.d("InitRepo", "User added: $userAdded, Scooter added: $scooterAdded, Rent added: $rentAdded")
    }
}