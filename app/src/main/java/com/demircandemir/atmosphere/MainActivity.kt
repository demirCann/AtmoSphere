package com.demircandemir.atmosphere

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material3.Scaffold
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.demircandemir.atmosphere.navigation.SetupNavGraph
import com.demircandemir.atmosphere.ui.theme.AtmoSphereTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

//    @Inject
//    lateinit var prepopulateDatabase: PrepopulateDatabase

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            AtmoSphereTheme {
                navController = rememberNavController()

                Scaffold {
//                    SetupNavGraph(navController = navController)
//                    HorizontalPager()
                    SetupNavGraph(navController = navController)
                }

            }
        }


//        lifecycleScope.launch {
//            prepopulateDatabase.prepopulateDatabase()
//
//        }



    }
}

