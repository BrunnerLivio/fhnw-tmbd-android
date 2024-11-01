package fhnw.emoba.blockbuster

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import fhnw.emoba.EmobaApp
import fhnw.emoba.blockbuster.data.TMDBService
import fhnw.emoba.blockbuster.model.BlockbusterModel
import fhnw.emoba.blockbuster.ui.AppUI


object BlockbusterApp : EmobaApp {
    private lateinit var model: BlockbusterModel

    override fun initialize(activity: ComponentActivity) {
        val tmbdService = TMDBService()
        model = BlockbusterModel(activity, tmbdService)
        model.init()
    }

    @Composable
    override fun CreateUI() {

        AppUI(model)
    }

}